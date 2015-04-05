package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import android.graphics.Color;
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
	 * GeoLocationControllerTest#1
	 * Tests using GPS to get current location 
	 * 
	 * NOTE: If using emulator need to set latitude and longitude using DDMS for 
	 * this test to work 
	 */
	public void testGetCurrentgeolocation(){
		assertNotNull(currentLocation);
	}	
	
	/**
	 * GeoLocationControllerTest#2
	 * 
	 * Tests setting the home location 
	 */
	public void testsetLatLng(){	
		GeoLocationController.setHomeLocation(53.544389, -113.490927);
		GeoLocation temp = GeoLocationController.getHomeLocation();
		assertTrue("Location latitudes do not equal", temp.getLatitude() == homeLocation.getLatitude());
		assertTrue("Location longitudes do not equal", temp.getLongitude() == homeLocation.getLongitude());	
	}
	
	/**
	 * GeoLocationControllerTest#3
	 * 
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
	 * GeoLocationControllerTest#4
	 * 
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
	/**
	 * GeoLocationControllerTest#5
	 */
	public void testGPSenabled(){
		assertTrue(GeoLocationController.checkGPSEnabled());
	}
	/**
	 * GeoLocationControllerTest#6
	 * 
	 * Test getting the first destination with a geolocation color code 
	 */
	public void testGetFirstDestWithGeoLocaion(){
		GeoLocationController.setHomeLocation(53.581667, -116.434444);		//Edson
		GeoLocation gl = new GeoLocation(53.544389,-113.490927);			//Edmonton
		GeoLocation gl1 = new GeoLocation(52.133214,-106.670046);			//Saskatoon
		GeoLocation gl2 = new GeoLocation(51.507351,-0.127758);				//London, UK
		Destination d = new Destination("Edmonton", "Test");
		Destination d1 = new Destination("Saskatoon", "Test");
		Destination d2 = new Destination("London, UK", "Test");
		Destination d3 = new Destination("No GeoLocation", "Test");
		d.setGeoLocation(gl);
		d1.setGeoLocation(gl1);
		d2.setGeoLocation(gl2);
		
		Claim claim1 = new Claim("Test1");		//add first destination with green color code distance
		claim1.addDestination(d);
		claim1.addDestination(d1);
		claim1.addDestination(d2);
			
		Claim claim2 = new Claim("Test2");		//add first destination with yellow color code distance
		claim2.addDestination(d3);
		claim2.addDestination(d3);
		claim2.addDestination(d1);
		
		Claim claim3 = new Claim("Test3");		//add first destination with red color code distance
		claim3.addDestination(d3);
		claim3.addDestination(d2);
		claim3.addDestination(d3);
		
		Claim claim4 = new Claim("Test4");		//no destination color code should be blue 
		
		
		assertEquals("Color code should be green", Color.rgb(13, 116, 11), GeoLocationController.getFirstDestinationColorCode(claim1)[0]);
		assertEquals("Color code should be yellow", Color.rgb(193, 191, 35), GeoLocationController.getFirstDestinationColorCode(claim2)[0]);
		assertEquals("Color code should be red", Color.rgb(135, 27, 27), GeoLocationController.getFirstDestinationColorCode(claim3)[0]);
		assertEquals("Color code should be blue", Color.rgb(35, 54, 132), GeoLocationController.getFirstDestinationColorCode(claim4)[0]);
	}
	/**
	 * GeoLocationControllerTest#7
	 * 
	 * Test getting the first destination with a geolocation color code 
	 */
	public void testgetClaimDestinationMarkers(){
		GeoLocationController.setHomeLocation(53.581667, -116.434444);		//Edson
		GeoLocation gl = new GeoLocation(53.544389,-113.490927);			//Edmonton
		GeoLocation gl1 = new GeoLocation(52.133214,-106.670046);			//Saskatoon
		GeoLocation gl2 = new GeoLocation(51.507351,-0.127758);				//London, UK
		Destination d = new Destination("Edmonton", "Test");
		Destination d1 = new Destination("Saskatoon", "Test");
		Destination d2 = new Destination("London, UK", "Test");
		Destination d3 = new Destination("No GeoLocation", "Test");
		d.setGeoLocation(gl);
		d1.setGeoLocation(gl1);
		d2.setGeoLocation(gl2);
		
		Claim claim1 = new Claim("Test1");		//add first destination with green color code distance
		claim1.addDestination(d);
		claim1.addDestination(d1);
		claim1.addDestination(d2);
			
		Claim claim2 = new Claim("Test2");		//add first destination with yellow color code distance
		claim2.addDestination(d3);
		claim2.addDestination(d3);
		claim2.addDestination(d1);
		
		Claim claim3 = new Claim("Test3");		//add first destination with red color code distance
		claim3.addDestination(d3);
		claim3.addDestination(d2);
		claim3.addDestination(d3);
		
		Claim claim4 = new Claim("Test4");		//no destination color code should be blue 
		
		
		assertEquals("Color code should be green", Color.rgb(13, 116, 11), GeoLocationController.getFirstDestinationColorCode(claim1)[0]);
		assertEquals("Color code should be yellow", Color.rgb(193, 191, 35), GeoLocationController.getFirstDestinationColorCode(claim2)[0]);
		assertEquals("Color code should be red", Color.rgb(135, 27, 27), GeoLocationController.getFirstDestinationColorCode(claim3)[0]);
		assertEquals("Color code should be blue", Color.rgb(35, 54, 132), GeoLocationController.getFirstDestinationColorCode(claim4)[0]);
	}
	

}
