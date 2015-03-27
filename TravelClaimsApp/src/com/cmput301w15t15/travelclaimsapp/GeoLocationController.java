package com.cmput301w15t15.travelclaimsapp;

import java.util.Date;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GeoLocationController {

	private static Location homeLocation = null;
	private static Location currentLocation = null;
	private static LocationManager lm;
	
	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged (Location location) {
		
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}	
	};
	
	//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
	public static void initializeLocationManager(Context context){
		if(lm == null){
			lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		}
	}
	//referenced https://github.com/joshua2ua/MockLocationTester on March 26th 2015
	public static Location getLocation() {
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
		return currentLocation;
	}

	public static Location getHomeLocation() {
		if(homeLocation == null){
			homeLocation = UserController.getUser().getHomeLocation();
		}
		return homeLocation;
	}

	public static void setHomeLocation(double latitude, double longitude) {
		if(homeLocation == null){
			homeLocation = UserController.getUser().getHomeLocation();
		}
		homeLocation.setLatitude(latitude);
		homeLocation.setLongitude(longitude);
		
	}
	
	public static void setHomeLocation(Location location){
		homeLocation.set(location);
	}

	public static double getDistanceFromHome(Location temp) {
		// TODO Auto-generated method stub
		return 0;
	}

}
