/*
 *TravelClaimsApp
 *Copyright (C) 2015 Jon Machinski, Bo Zhou, Henry Ha, Chris Wang, Sean Scheideman
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cmput301w15t15.travelclaimsapp.activitys;


import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;



/**
 * Activity for displaying openstreetmap using osmdroid api 
 * 
 * Activity works for new users(without home GeoLocation), users picking a GeoLocation
 * from the map, and users viewing a already picked GeoLocation.
 * 
 * Used the osmdroid sample project OpenStreetMapViewer retrieved from 
 * https://github.com/osmdroid/osmdroid/tree/master/OpenStreetMapViewer March 25th 2015
 * 
 * Used https://github.com/osmdroid/osmdroid/blob/master/OpenStreetMapViewer/src/org/osmdroid/samples/SampleExtensive.java
 * written by Nicolas Gramlich retrieved on March 25th 2015 as base for this activity 
 * 
 * Used osmbonuspack from https://code.google.com/p/osmbonuspack/source/browse/#svn%2FBonusPackDownloads on April 1st 2015
 * 
 */
public class MapActivity extends Activity implements MapEventsReceiver{

	private static final int GOTO_HOME = Menu.FIRST;
	private static final int GOTO_CURRENT = GOTO_HOME + 1;
	private static final int GOTO_PICK = GOTO_HOME +2;
	private MapView mapView;
	private IMapController mapController;
	private ResourceProxy mResourceProxy;
	private ScaleBarOverlay mScaleBarOverlay;
	private GeoLocation currentLocation;
	private GeoLocation pickedLocation;
	private GeoLocation homeLocation;
	private Marker current;
	private Marker pick = null;
	private Marker home;
	private static String focusOn; 
	private static boolean canEdit;
	private GeoPoint currentGeoPoint; 
	private GeoPoint homeGeoPoint;
	private GeoPoint pickGeoPoint;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//check if there is a item to set the focus to 
		if(getIntent().getExtras().getString("LatLng").length() > 0){
			focusOn = getIntent().getExtras().getString("LatLng");
		}
		
		canEdit = getIntent().getExtras().getBoolean("MAP_EDIT");
		
		mResourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
		
		final RelativeLayout rl = new RelativeLayout(this);
		
		this.mapView = new MapView(this, 256);
		this.mapController = this.mapView.getController();
		rl.addView(this.mapView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		
		mapView.setBuiltInZoomControls(true);
		mapView.setMinZoomLevel(3);
		mapController.setZoom(5);
		/* Scale Bar Overlay */
		{
			this.mScaleBarOverlay = new ScaleBarOverlay(this, mResourceProxy);
			this.mapView.getOverlays().add(mScaleBarOverlay);
			// Scale bar tries to draw as 1-inch, so to put it in the top center, set x offset to
			// half screen width, minus half an inch.
			this.mScaleBarOverlay.setScaleBarOffset(
					(int) (getResources().getDisplayMetrics().widthPixels / 2 - getResources()
							.getDisplayMetrics().xdpi / 2), 10);
		}

		
		//if it is not a newUser then display home location
		if(!getIntent().getExtras().getBoolean("newUser")){
			homeLocation = GeoLocationController.getHomeLocation();
			homeGeoPoint = new GeoPoint(homeLocation.getLatitude(),homeLocation.getLongitude());
			home = new Marker(mapView);
			home.setIcon(getResources().getDrawable(R.drawable.map_home));
			setMarkerInfoMessage(home, "Home", "Your home location is "+homeLocation.getString());
			setMarker(home, homeGeoPoint);
		}
		
		currentLocation = GeoLocationController.getLocation();
		currentGeoPoint = new GeoPoint(currentLocation.getLatitude(),currentLocation.getLongitude());
		current = new Marker(mapView);
		current.setIcon(getResources().getDrawable((R.drawable.person)));
		setMarker(current, currentGeoPoint);
		setMarkerInfoMessageWithDistance(current, "Current", "Your current GPS location is "+currentLocation.getString(), currentLocation);
		
		//if the user clicked on a already selected geolocation to get here then focus on point
		if(focusOn != null){
			pickGeoPoint = GeoPoint.fromDoubleString(focusOn, ',');
			pick = new Marker(mapView);
			setMarker(pick, pickGeoPoint);
			setMarkerInfoMessageWithDistance(pick, "Selected Location", "The selected location is at "+currentLocation.getString(), new GeoLocation(pickGeoPoint.getLatitude(), pickGeoPoint.getLongitude()));
		}else{
			this.mapController.setCenter(currentGeoPoint);
		}
		
		this.mapView.getOverlays().add(new MapEventsOverlay(this, this));	
		this.setContentView(rl);
	}
	@Override
	public boolean onCreateOptionsMenu(final Menu pMenu) {
		pMenu.add(0, GOTO_HOME, Menu.NONE, "Home Location");
		pMenu.add(0, GOTO_CURRENT, Menu.NONE, "Current Location");
		pMenu.add(0, GOTO_PICK, Menu.NONE, "Picked Location");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
		//focuses on the map at the pick location (Current, home or picked)
		switch (item.getItemId()) {
		case GOTO_HOME:
			if(homeLocation != null){
				mapController.setCenter(homeGeoPoint);
			}
			return true;
		case GOTO_CURRENT:
			mapController.setCenter(currentGeoPoint);
			return true;
		case GOTO_PICK:
			if(pickGeoPoint != null){
				mapController.setCenter(pickGeoPoint);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		if(pickedLocation != null){
			intent = new Intent();
			intent.putExtra("geoLocation", pickedLocation.getString());
			setResult(RESULT_OK,intent);
		}else{
			setResult(RESULT_CANCELED,intent);
		
		}
		finish();
	}


	@Override
	public boolean singleTapConfirmedHelper(GeoPoint p) {
		if(canEdit == true){
			if(pick == null){
				pick = new Marker(mapView);
			
			}else{
				mapView.getOverlays().remove(pick);
	        }
			setMarker(pick, p);
			
			mapView.invalidate();
			pickedLocation = new GeoLocation(p.getLatitude(), p.getLongitude());
		}
		   
	     return false;
	}


	@Override
	public boolean longPressHelper(GeoPoint p) {
		return false;
	}
	
	/**
	 * Adds a marker to the mapview at the provided GeoPoint
	 * 
	 * @param mark 		the marker that to add to mapview
	 * @param gp		the geopoint to set the marker at
	 */
	private void setMarker(Marker mark, GeoPoint gp){
		mark.setPosition(gp);
		mark.setDraggable(true);
		mark.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		mapController.setCenter(gp);
		mapView.getOverlays().add(mark);
	}

	/**
	 * Sets the markers info message
	 * 
	 * Message that will be displayed when the marker is clicked on the map
	 * 
	 * @param mark			the marker to set the message for 
	 * @param title			the message title
	 * @param description	the message body
	 */
	private void setMarkerInfoMessage(Marker mark, String title, String description){
		mark.setTitle(title);
		mark.setSnippet(description);
	}
	/**
	 * Sets the markers info message which includes the markers distance from home
	 * 
	 * Message that will be displayed when the marker is clicked on the map
	 * 
	 * @param mark			the marker to set the message for
	 * @param title			the title of the message
	 * @param description	the body of the message 
	 * @param gl			the geolocation of the marker for calculating distance from home
	 */
	private void setMarkerInfoMessageWithDistance(Marker mark, String title, String description, GeoLocation gl){
		mark.setTitle(title);
		mark.setSnippet(description);
		if(!getIntent().getExtras().getBoolean("newUser")){
			mark.setSubDescription("Distance from home: " + Double.toString(Math.round(GeoLocationController.getDistanceFromHome(gl)))+" KM");
		}
	
	}
}

