
package com.cmput301w15t15.travelclaimsapp.activitys;



import org.osmdroid.api.IMapController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import com.cmput301w15t15.travelclaimsapp.R;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;




public class MapActivity extends Activity{

	
	

	private MapView mMapView;
	private IMapController mapController;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		//http://androidcookbook.com/Recipe.seam?recipeId=2521
		mMapView = (MapView) this.findViewById(R.id.mapview);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setMultiTouchControls(true);
		mapController = (MapController) this.mMapView.getController();
		mapController.setZoom(2);

	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}


