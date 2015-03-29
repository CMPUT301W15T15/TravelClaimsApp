package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.util.Calendar;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import android.location.Location;
import android.test.AndroidTestCase;

/**
 * Test GeoLocationController class 
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
	
	/**
	 * Tests setting the home location 
	 */
	public void testsetLatLng(){	
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		GeoLocation temp = GeoLocationController.getHomeLocation();
		assertTrue("Location latitudes do not equal", temp.getLatitude() == homeLocation.getLatitude());
		assertTrue("Location longitudes do not equal", temp.getLongitude() == homeLocation.getLongitude());	
	}
	
	/**
	 * Tests the getDistanceFromHome method
	 */
	public void testDistanceFromHome(){
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		GeoLocation gl = new GeoLocation(51.048615,-114.070846);
		
		//distance greater than 0
		assertEquals("Distance calculated not correct", 280,Math.round(Math.floor(GeoLocationController.getDistanceFromHome(gl))));	
		
		
		//distance equal to zero 
		GeoLocationController.setHomeLocation(51.048615, -114.070846);
		
		assertEquals("Distance calculated not correct", 0,Math.round(Math.floor(GeoLocationController.getDistanceFromHome(gl))));
		
	}
	
	/**
	 * Tests setting the geolocation for destinations and expenses 
	 */
	public void testSetGeoLocation(){
		Destination dest = new Destination("Somewhere", "Somehow");
		GeoLocationController.setDestinationGeoLocation(dest, 53.544389, -113.490927);
		
		assertEquals("Controller did not set geolocation",53.544389,dest.getGeoLocation().getLatitude());
		assertEquals("Controller did not set geolocation",-113.490927,dest.getGeoLocation().getLongitude());
		
		Expense expense = new Expense("Dur");
		
		GeoLocationController.setExpenseGeoLocation(expense, 53.544389,-113.490927);
		
		assertEquals("Controller did not set geolocation",53.544389,expense.getGeoLocation().getLatitude());
		assertEquals("Controller did not set geolocation",-113.490927,expense.getGeoLocation().getLongitude());
		
	}
	
	public void testGPSenabled(){
		assertTrue(GeoLocationController.checkGPSEnabled());
	}
	
}
