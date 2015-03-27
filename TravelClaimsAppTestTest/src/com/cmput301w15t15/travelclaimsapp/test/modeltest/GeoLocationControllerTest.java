package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.util.Calendar;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import android.location.Location;
import android.test.AndroidTestCase;

/**
 * Test GeoLocationController class 
 * @author searn
 *
 */
public class GeoLocationControllerTest extends AndroidTestCase {

	private GeoLocation currentLocation;
	private GeoLocation homeLocation;
	
	public GeoLocationControllerTest() {
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		GeoLocationController.initializeLocationManager(getContext());
		currentLocation = GeoLocationController.getLocation();
		homeLocation = GeoLocationController.getHomeLocation();
	}
	
	/**
	 * Tests using GPS to get current location 
	 * 
	 * NOTE: If using emulator need to set latitude and longitude using DDMS for 
	 * this test to work 
	 */
	public void testGetCurrentgeolocation(){
		assertNotNull(currentLocation);
	}	
	
	public void testsetLatLng(){	
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		GeoLocation temp = GeoLocationController.getHomeLocation();
		assertTrue("Location latitudes do not equal", temp.getLatitude() == homeLocation.getLatitude());
		assertTrue("Location longitudes do not equal", temp.getLongitude() == homeLocation.getLongitude());	
	}
	
	public void testDistanceFromHome(){
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		GeoLocation gl = new GeoLocation(51.048615,-114.070846);
		
		assertEquals("Distance calculated not correct", 280,Math.round(Math.floor(GeoLocationController.getDistanceFromHome(gl))));
		
	}
	
	
}
