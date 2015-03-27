package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.util.Calendar;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.GeoLocationController;

import android.location.Location;
import android.test.AndroidTestCase;

/**
 * Test GeoLocationController class 
 * @author searn
 *
 */
public class GeoLocationControllerTest extends AndroidTestCase {

	private Location currentLocation;
	private Location homeLocation;
	
	public GeoLocationControllerTest() {
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		GeoLocationController.initializeLocationManager(getContext());
		currentLocation = GeoLocationController.getLocation();
		homeLocation = GeoLocationController.getHomeLocation();
	}
	
	public void testGetCurrentgeolocation(){
		assertNotNull(currentLocation);
	}	
	
	public void testsetLatLng(){
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime();
		
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		Location temp = GeoLocationController.getHomeLocation();
		assertTrue("Location time stamps do not equal", temp.getTime() == homeLocation.getTime());
		assertTrue("Location latitudes do not equal", temp.getLatitude() == homeLocation.getLatitude());
		assertTrue("Location longitudes do not equal", temp.getLongitude() == homeLocation.getLongitude());	
	}
	public void testDistanceFromHome(){
		
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		Location temp = new Location("test");
		temp.setLatitude(51.048615);
		temp.setLatitude(-114.070846);
		
		assertEquals("Distance calculated not corrected", 280,Math.round(GeoLocationController.getDistanceFromHome(temp)));
		
	}
	
	
}
