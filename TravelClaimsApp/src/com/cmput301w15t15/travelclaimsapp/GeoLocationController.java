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

import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import com.cmput301w15t15.travelclaimsapp.model.Listener;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GeoLocationController {

	private static Location currentLocation = null;
	private static LocationManager lm;
	
	
	//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
	public static void initializeLocationManager(Context context){
		if(lm == null){
			lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		}
	}
	/**
	 * Gets the current location using GPS and returns a geolocation object
	 * 
	 * @return returns the current GeoLocation 
	 */
	//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
	public static GeoLocation getLocation() {
		if(lm == null){
			throw new RuntimeException("Location manager was not initialized");
		}
		
		if(currentLocation == null){
			currentLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
				
				@Override
				public void onLocationChanged(Location location) {
					if (location != null) {
						double lat = location.getLatitude();
						double lng = location.getLongitude();
						Date date = new Date(location.getTime());
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
		return new GeoLocation(currentLocation.getLatitude(), currentLocation.getLongitude());
	}

	public static GeoLocation getHomeLocation() {
		return UserController.getUser().getHomeLocation();
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
		GeoLocation gl = UserController.getUser().getHomeLocation();
		if(gl == null){
			UserController.getUser().initHomeLocation();
			UserController.getUser().getHomeLocation().addListener(new Listener() {
				@Override
				public void update() {
					save();
				}
			});
		}
		UserController.getUser().getHomeLocation().setLatLng(latitude, longitude);
	}

	/**
	 * Takes a {@link GeoLocation} and returns the distance between that geolocation and the 
	 * Users home GeoLocation
	 * 
	 * @param gl GeoLocation to compare with home
	 * @return Distance between GeoLocations in Meters 
	 */
	public static double getDistanceFromHome(GeoLocation gl) {
		float[] results = new float[3];
		GeoLocation home = UserController.getUser().getHomeLocation();
		Location.distanceBetween(home.getLatitude(), home.getLongitude(), gl.getLatitude(), gl.getLongitude(), results);
		return results[0]/1000;
	}
	
	public static void save(){
		FileManager.getSaver().saveUserInFile(UserController.getUser());
	}

}
