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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;



/**
 * Activity for displaying openstreetmap using osmdroid api 
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
	
	private void setMarker(Marker mark, GeoPoint gp){
		mark.setPosition(gp);
		mark.setDraggable(true);
		mark.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		mapController.setCenter(gp);
		mapView.getOverlays().add(mark);
	}

	private void setMarkerInfoMessage(Marker mark, String title, String description){
		mark.setTitle(title);
		mark.setSnippet(description);
	}
	private void setMarkerInfoMessageWithDistance(Marker mark, String title, String description, GeoLocation gl){
		mark.setTitle(title);
		mark.setSnippet(description);
		mark.setSubDescription("Distance from home: " + Double.toString(Math.round(GeoLocationController.getDistanceFromHome(gl)))+" KM");
	}
}

