package com.cmput301w15t15.travelclaimsapp.activitys;




import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapEvent;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.drawable;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import com.cmput301w15t15travelclaimsapp.osmdroid.MapEventsOverlay;
import com.cmput301w15t15travelclaimsapp.osmdroid.MapEventsReceiver;
import com.cmput301w15t15travelclaimsapp.osmdroid.OpenStreetMapConstants;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
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
 * MODIFICATIONS:
 * 	-Added scroll limits and min zoom level
 *  -Uses default zoom control and no minimap
 *  -changed menu
 *  -added overlay items showing current, pick and home locations
 * 
 */
public class MapActivity extends Activity implements MapEventsReceiver{

	// ===========================================================
	// Constants
	// ===========================================================


	private static final int GOTO_HOME = Menu.FIRST;
	private static final int GOTO_CURRENT = GOTO_HOME + 1;
	private static final int GOTO_PICK = GOTO_HOME +2;

	// ===========================================================
	// Fields
	// ===========================================================

	private MapView mapView;
	private IMapController mapController;
	private SimpleLocationOverlay mMyLocationOverlay;
	private ResourceProxy mResourceProxy;
	private ScaleBarOverlay mScaleBarOverlay;
	private MinimapOverlay mMiniMapOverlay;
	private int lastVaildX;
	private int lastValidY;
	private GeoLocation currentLocation;
	private GeoLocation pickedLocation;
	private GeoLocation homeLocation;
	private List<OverlayItem> points;
	private ItemizedIconOverlay<OverlayItem> pointsOverlay;
	private OverlayItem current;
	private OverlayItem pick = null;
	private OverlayItem home;
	private static String focusOn; 
	private static Integer adaptorIndex;
	private static boolean canEdit;
	private GeoPoint currentGeoPoint; 
	private GeoPoint homeGeoPoint;
	private GeoPoint pickGeoPoint;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		//check if there is a item to set the focus to 
		if(getIntent().getExtras().getString("LatLng") != null){
			focusOn = getIntent().getExtras().getString("LatLng");
		}
		
		canEdit = getIntent().getExtras().getBoolean("MAP_EDIT");
		
		//mResourceProxy = new ResourceProxyImpl(getApplicationContext());
		mResourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
		points = new ArrayList<OverlayItem>();
		
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

		
		pointsOverlay = new ItemizedIconOverlay<OverlayItem>(points, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

			@Override
			public boolean onItemLongPress(int arg0, OverlayItem arg1) {
				return false;
			}
			@Override
			public boolean onItemSingleTapUp(int arg0, OverlayItem arg1) {
				return false;
			}
		},mResourceProxy);
		
		
		if(!getIntent().getExtras().getBoolean("newUser")){
			homeLocation = GeoLocationController.getHomeLocation();
			homeGeoPoint = new GeoPoint(homeLocation.getLatitude(),homeLocation.getLongitude());
			home = new OverlayItem("Home Location", "Home", homeGeoPoint);
			home.setMarker(getResources().getDrawable(drawable.map_home));
			pointsOverlay.addItem(home);
		}
		
		currentLocation = GeoLocationController.getLocation();
		currentGeoPoint = new GeoPoint(currentLocation.getLatitude(),currentLocation.getLongitude());
		current = new OverlayItem("Current Location","Current", currentGeoPoint);
		current.setMarker(getResources().getDrawable((R.drawable.person)));
		pointsOverlay.addItem(current);
	
		if(focusOn != null){
			pickGeoPoint = GeoPoint.fromDoubleString(focusOn, ',');
			pick = new OverlayItem("Picked Location","Pick", pickGeoPoint);
			pointsOverlay.addItem(pick);
			pointsOverlay.setFocus(pick);
			mapController.setCenter(pickGeoPoint);
		}else{
			this.mapController.setCenter(currentGeoPoint);
		}
		
		this.mapView.getOverlays().add(pointsOverlay);
		this.mapView.getOverlays().add(new MapEventsOverlay(this, this));
		//this.mapView.getOverlayManager().add(1, new MapEventsOverlay(this, this));
		
		this.setContentView(rl);
	}


	// ===========================================================
	// Methods from SuperClass/Interfaces
	// ===========================================================

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
				pick = new OverlayItem("Picked Location","Pick", p);
	            pick.setMarker(getResources().getDrawable((R.drawable.marker_default)));
	            pointsOverlay.addItem(pick);
	             
			}else{
				pointsOverlay.removeItem(pick);
	         	pick = new OverlayItem("Picked Location","Pick", p);
	         	pick.setMarker(getResources().getDrawable((R.drawable.marker_default)));
	         	pointsOverlay.addItem(pick);
	        }
			mapView.invalidate();
			pickedLocation = new GeoLocation(p.getLatitude(), p.getLongitude());
		}
		   
	     return false;
	}


	@Override
	public boolean longPressHelper(GeoPoint p) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}

