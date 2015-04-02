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
package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.MapActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimListSaveListener;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import com.cmput301w15t15.travelclaimsapp.model.Listener;
import com.cmput301w15t15.travelclaimsapp.model.User;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Singleton class to track current location of device 
 * 
 * Also used for attaching {@link GeoLocation} to {@link Destination} and {@link Expense} 
 * and calculating distance from one {@link GeoLocation} to the users home {@link GeoLocation}
 *
 */
public class GeoLocationController {

	private static Location currentLocation = null;
	private static LocationManager lm = null;
	public static final int GET_GEOLOCATION_CODE = 10;
	
	/**
	 * Initializes location manager
	 * 
	 * Should be passed the Application context
	 * 
	 * @param context takes the Application context 
	 */
	public static void initializeLocationManager(Context context){
		if(lm == null){
			//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
			lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
				
				@Override
				public void onLocationChanged(Location location) {
					if (location != null) {
						if(currentLocation == null){
							currentLocation = new Location(location);
						}else{
							currentLocation.set(location);
						}
					}
				}
				
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	/**
	 * Used to check if the devices GPS is enabled
	 * 
	 * @return true if GPS enabled
	 */
	public static boolean checkGPSEnabled(){
		if(lm == null){
			throw new RuntimeException("Location manager was not initialized");
		}
		return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	
	/**
	 * Gets the current location using GPS and returns a geolocation object
	 * 
	 * @return returns the current GeoLocation 
	 */
	public static GeoLocation getLocation() {
		if(lm == null){
			throw new RuntimeException("Location manager was not initialized");
		}
		if(currentLocation == null){
			//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
			currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(currentLocation == null){
				return new GeoLocation(0,0);	
			}
		}
		return new GeoLocation(currentLocation.getLatitude(), currentLocation.getLongitude());
	}

	public static GeoLocation getHomeLocation() {
		return UserController.getUser().getGeoLocation();
	}

	/**
	 * 
	 * 
	 * TODO; decide if user should have to pick geolocation when constructed 
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public static void setHomeLocation(double latitude, double longitude) {
		GeoLocation gl = UserController.getUser().getGeoLocation();
		if(gl == null){
			UserController.getUser().initHomeLocation(new GeoLocation(latitude, longitude));
			UserController.getUser().getGeoLocation().addListener(new Listener() {
				@Override
				public void update() {
					save();
				}
			});
		}
		UserController.getUser().getGeoLocation().setLatLng(latitude, longitude);
	}

	
	
	/**
	 * Takes a {@link GeoLocation} and returns the distance IN KILOMETERS between that geolocation and the 
	 * Users home GeoLocation
	 * 
	 * @param gl GeoLocation to compare with home
	 * @return Distance between GeoLocations in kilometers
	 */
	public static double getDistanceFromHome(GeoLocation gl) {
		float[] results = new float[3];
		GeoLocation home = UserController.getUser().getGeoLocation();
		Location.distanceBetween(home.getLatitude(), home.getLongitude(), gl.getLatitude(), gl.getLongitude(), results);
		return results[0]/1000;
	}
	
	public static void save(){
		FileManager.getSaver().saveUserInFile(UserController.getUser());
	}
	
	/**
	 * Sets a {@link Destination} GeoLocation
	 * 
	 * If dest's GeoLocation is null then a new GeoLocation is made and added to dest 
	 * otherwise the dest's current GeoLocation Lat and Lng are updated
	 * 
	 * @param dest Destination to update
	 * @param lat  Latitude to set
	 * @param lng  Longitude to set 
	 */
	public static void setDestinationGeoLocation(Destination dest, double lat, double lng) {
		if(dest.getGeoLocation() == null){
			GeoLocation gl = new GeoLocation(lat,lng);
			gl.addListener(new ClaimListSaveListener());
			dest.setGeoLocation(gl);
		}else{
			dest.getGeoLocation().setLatLng(lat, lng);
		}	
	}
	/**
	 * Set a {@link Expense} GeoLocation
	 * 
	 * If expense's GeoLocation is null then a new GeoLocation is made and add to expense
	 * otherwise the dest's current {@link GeoLocation} Lat and Lng are updated 
	 * 
	 * @param expense {@link Expense} to update
	 * @param lat	   Latitude to set
	 * @param lng	   Longitude to set
	 */
	public static void setExpenseGeoLocation(Expense expense, double lat, double lng) {
		if(expense.getGeoLocation() == null){
			GeoLocation gl = new GeoLocation(lat, lng);
			gl.addListener(new ClaimListSaveListener());
			expense.setGeoLocation(gl);
		}else{
			expense.getGeoLocation().setLatLng(lat, lng);
		}
		
	}
	/**
	 * Set a {@link User} GeoLocation
	 * 
	 * If user's GeoLocation is null then a new GeoLocation is made and added to user
	 * otherwise user's GeoLocation lat and lng are updated
	 * 
	 * @param user	{@link User} to update
	 * @param lat	Latitude to set
	 * @param lng	Longitude to set
	 */
	public static void setUserGeoLocation(User user, double lat, double lng){
		if(user.getGeoLocation() == null){
			GeoLocation gl = new GeoLocation(lat, lng);
			gl.addListener(new ClaimListSaveListener());
			user.setGeoLocation(gl);
		}else{
			user.getGeoLocation().setLatLng(lat, lng);
		}
	}
	
	/**
	 * Sets the {@link GeoLocation} of the passed {@link Destination} 
	 * 
	 * Uses latitude and longitude of current location
	 * 
	 * @param dest 
	 */
	public static void setDestinationGeoLocationGPS(Destination dest) {
		GeoLocation gl = getLocation();
		setDestinationGeoLocation(dest, gl.getLatitude(), gl.getLongitude());
	}
	/**
	 * Sets the {@link GeoLocation} of the passed {@link Expense}
	 * 
	 * Uses latitude and longitude of current location
	 * 
	 * @param dest
	 */
	public static void setExpenseGeoLocationGPS(Expense expense) {
		GeoLocation gl = getLocation();
		setExpenseGeoLocation(expense, gl.getLatitude(), gl.getLongitude());
	}
	
	/**
	 * Returns a {@link Intent} used for opening the {@link MapActivity}, when wanting to pick a location from the map
	 * 
	 * @param context   The context of the activity you call the function from 
	 * @return			A {@link Intent} that will open {@link MapActivity} in the select location mode
	 */
	public static Intent pickLocationIntent(Context context){
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra("LatLng", "");
    	intent.putExtra("MAP_EDIT", true);
    	intent.putExtra("newUser", false);
    	return intent;
	}
	
	/**
	 * Returns a {@link Intent} used for opening the {@link MapActivity} focused on a specific {@link GeoLocation}
	 * 
	 * @param context 	The context of the activity you call the function from
	 * @param gl		The geolocation you wish to view when you start the {@link MapActivity}
	 * @return			A {@link Intent} that will open the {@link MapActivity} for viewing the supplied {@link GeoLocation}
	 */
	public static Intent viewLocationIntent(Context context, GeoLocation gl){
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra("LatLng", gl.getString());
    	intent.putExtra("MAP_EDIT", false);
    	intent.putExtra("newUser", false);
    	return intent;
	}
	
	/**
	 * Returns a {@link Intent} used for opening the {@link MapActivity} when creating a new user
	 * 
	 * @param context The context of the activity you call the function from
	 * @return 	A intent that will open MapActivity under new user conditions 
	 */
	public static Intent newUserLocationIntent(Context context){
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra("LatLng", "");
    	intent.putExtra("MAP_EDIT", true);
    	intent.putExtra("newUser", true);
		return intent;
	}
	
	/**
	 * Returns the color code and distance associated with the first destination with a GeoLocation 
	 * 
	 * Takes a {@link Claim} and finds its first destination with a {@link GeoLocation} attached and 
	 * calculates the distance from the users home location. Then returns the color code associated with 
	 * distance from home and distance calculated in int[]
	 * 
	 * @param claim 	{@link Claim} to find destination color code for  
	 * @return			A int[] containing the color code and distance from home for the Claim
	 */
	public static int[] getFirstDestinationColorCode(Claim claim){
		GeoLocation gl;
		int colorCode = Color.rgb(35, 54, 132);
		int distance = 0;
		ArrayList<Destination> dests = claim.getDestinationList().toArrayList();
		for(Destination dest : dests){
			gl = dest.getGeoLocation();
	    	if(gl != null){
	    		distance = (int) Math.round(GeoLocationController.getDistanceFromHome(gl));
	    		if(distance<400){
	    			colorCode = Color.rgb(13, 116, 11);		//green : close
	    		}else if(distance<2000){
	    			colorCode = Color.rgb(193, 191, 35);	//yellow : medium
	    		}else if(distance>2000){
	    			colorCode = Color.rgb(135, 27, 27);		//red : far
	    		}
	    		break;
	    	}else{
	    		colorCode = Color.rgb(35, 54, 132);			//blue : no GeoLocation
			   	distance = 0;
	    	}
	    }
		int[] colorAndDistance = {colorCode, distance};
		
		return colorAndDistance;
		
	}
	

	public static void resetGeoLocationController(){
		currentLocation = null;
		lm = null;
	}
	

}
