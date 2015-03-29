//https://github.com/osmdroid/osmdroid/blob/master/OpenStreetMapViewer/src/org/osmdroid/samples/SampleExtensive.java March 25

package com.cmput301w15t15.travelclaimsapp.activitys;


import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.SimpleLocationOverlay;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15travelclaimsapp.osmdroid.OpenStreetMapConstants;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;



//public class MapActivity extends Activity{
//
//	
//	
//
//	private MapView mMapView;
//	private IMapController mapController;
//
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_map);
//		//http://androidcookbook.com/Recipe.seam?recipeId=2521
//		mMapView = (MapView) this.findViewById(R.id.mapview);
//		mMapView.setBuiltInZoomControls(true);
//		mMapView.setMultiTouchControls(true);
//		mapController = (MapController) this.mMapView.getController();
//		mapController.setZoom(2);
//
//	}
//
//	
//	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		return true;
//	}
//
//}

/**
*
* @author Nicolas Gramlich
*
*/
public class MapActivity extends Activity implements OpenStreetMapConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int MENU_ZOOMIN_ID = Menu.FIRST;
	private static final int MENU_ZOOMOUT_ID = MENU_ZOOMIN_ID + 1;
	private static final int MENU_TILE_SOURCE_ID = MENU_ZOOMOUT_ID + 1;
	private static final int MENU_ANIMATION_ID = MENU_TILE_SOURCE_ID + 1;
	private static final int MENU_MINIMAP_ID = MENU_ANIMATION_ID + 1;

	// ===========================================================
	// Fields
	// ===========================================================

	private MapView mOsmv;
	private IMapController mOsmvController;
	private SimpleLocationOverlay mMyLocationOverlay;
	private ResourceProxy mResourceProxy;
	private ScaleBarOverlay mScaleBarOverlay;
	private MinimapOverlay mMiniMapOverlay;

	// ===========================================================
	// Constructors
	// ===========================================================

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mResourceProxy = new ResourceProxyImpl(getApplicationContext());

		final RelativeLayout rl = new RelativeLayout(this);

		this.mOsmv = new MapView(this, 256);
		this.mOsmvController = this.mOsmv.getController();
		rl.addView(this.mOsmv, new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		/* Scale Bar Overlay */
		{
			this.mScaleBarOverlay = new ScaleBarOverlay(this, mResourceProxy);
			this.mOsmv.getOverlays().add(mScaleBarOverlay);
			// Scale bar tries to draw as 1-inch, so to put it in the top center, set x offset to
			// half screen width, minus half an inch.
			this.mScaleBarOverlay.setScaleBarOffset(
					(int) (getResources().getDisplayMetrics().widthPixels / 2 - getResources()
							.getDisplayMetrics().xdpi / 2), 10);
		}

		/* SingleLocation-Overlay */
		{
			/*
			 * Create a static Overlay showing a single location. (Gets updated in
			 * onLocationChanged(Location loc)!
			 */
			this.mMyLocationOverlay = new SimpleLocationOverlay(this, mResourceProxy);
			this.mOsmv.getOverlays().add(mMyLocationOverlay);
		}

		
		

		/* MiniMap */
		{
			mMiniMapOverlay = new MinimapOverlay(this, mOsmv.getTileRequestCompleteHandler());
			this.mOsmv.getOverlays().add(mMiniMapOverlay);
		}

		// PathOverlay pathOverlay = new PathOverlay(Color.RED, this);
		// pathOverlay.addPoint(new GeoPoint(40.714623, -74.006605));
		// pathOverlay.addPoint(new GeoPoint(38.8951118, -77.0363658));
		// pathOverlay.addPoint(new GeoPoint(34.052186, -118.243932));
		// pathOverlay.getPaint().setStrokeWidth(50.0f);
		// pathOverlay.setAlpha(100);
		// this.mOsmv.getOverlays().add(pathOverlay);

		this.setContentView(rl);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean onCreateOptionsMenu(final Menu pMenu) {
		pMenu.add(0, MENU_ZOOMIN_ID, Menu.NONE, "ZoomIn");
		pMenu.add(0, MENU_ZOOMOUT_ID, Menu.NONE, "ZoomOut");

		final SubMenu subMenu = pMenu.addSubMenu(0, MENU_TILE_SOURCE_ID, Menu.NONE,
				"Choose Tile Source");
		{
			for (final ITileSource tileSource : TileSourceFactory.getTileSources()) {
				subMenu.add(0, 1000 + tileSource.ordinal(), Menu.NONE,
						tileSource.localizedName(mResourceProxy));
			}
		}

		pMenu.add(0, MENU_ANIMATION_ID, Menu.NONE, "Run Animation");
		pMenu.add(0, MENU_MINIMAP_ID, Menu.NONE, "Toggle Minimap");

		return true;
	}

	@Override
	public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ZOOMIN_ID:
			this.mOsmvController.zoomIn();
			return true;

		case MENU_ZOOMOUT_ID:
			this.mOsmvController.zoomOut();
			return true;

		case MENU_TILE_SOURCE_ID:
			this.mOsmv.invalidate();
			return true;

		case MENU_MINIMAP_ID:
			mMiniMapOverlay.setEnabled(!mMiniMapOverlay.isEnabled());
			this.mOsmv.invalidate();
			return true;

		case MENU_ANIMATION_ID:
			// this.mOsmv.getController().animateTo(52370816, 9735936,
			// MapControllerOld.AnimationType.MIDDLEPEAKSPEED,
			// MapControllerOld.ANIMATION_SMOOTHNESS_HIGH,
			// MapControllerOld.ANIMATION_DURATION_DEFAULT); // Hannover
			// Stop the Animation after 500ms (just to show that it works)
			// new Handler().postDelayed(new Runnable(){
			// @Override
			// public void run() {
			// SampleExtensive.this.mOsmv.getController().stopAnimation(false);
			// }
			// }, 500);
			return true;

		default:
			ITileSource tileSource = TileSourceFactory.getTileSource(item.getItemId() - 1000);
			mOsmv.setTileSource(tileSource);
			mMiniMapOverlay.setTileSource(tileSource);
		}
		return false;
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
