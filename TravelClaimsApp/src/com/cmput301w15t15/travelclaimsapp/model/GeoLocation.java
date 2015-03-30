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
package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

/**
 * Contains a latitude and longitude for representing a location
 * 
 */
public class GeoLocation implements Listenable{

	private double latitude;
	private double longitude;
	private transient ArrayList<Listener> listeners;
	
	public GeoLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.listeners = new ArrayList<Listener>();
	}

	public GeoLocation() {
		this.listeners = new ArrayList<Listener>();
	}

	/**
	 * Returns the {@link GeoLocation} latitude
	 * 
	 * @return {@link GeoLocation} latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Returns the {@link GeoLocation} longitude
	 * 
	 * @return {@link GeoLocation} longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets {@link GeoLocation} longitude
	 * 
	 * @param lng pass the {@link Double} to set longitude to 
	 */
	public void setLongitude(double lng) {
		this.longitude = lng;
		notifyListeners();
	}
	
	/**
	 * Sets {@link GeoLocation} latitude 
	 * 
	 * @param lat pass the {@link Double} to set latitude to 
	 */
	public void setLatitude(double lat) {
		this.latitude = lat;
		notifyListeners();
	}
	
	/**
	 * Sets {@link GeoLocation} latitude and longitude
	 * 
	 * @param lat lat pass the {@link Double} to set latitude to
	 * @param lng lng pass the {@link Double} to set longitude to 
 	 */
	public void setLatLng(double lat,double lng){
		this.latitude = lat;
		this.longitude = lng;
		notifyListeners();
	}

	@Override
	public void notifyListeners() {
		for(Listener listener : listeners){
			listener.update();
		}
	}

	@Override
	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
	}

	@Override
	public void deleteListener(Listener listener) {
		listeners.remove(listener);
	}

	/**
	 * Returns a latitude and longitude as comma separated string
	 * 
	 * @return latitude and longitude string
	 */
	public String getString() {
		return new String(this.latitude+","+this.longitude);
	}

	/**
	 * Returns a {@link GeoLocation} from parsing the passed {@link String}
	 * 
	 * String needs to be formatted with a comma separating the latitude and longitude
	 * 
	 * @param geolocation String in the format "latitude,longitude"
	 * @return {@link GeoLocation} made from splitting passed string
	 */
	public static GeoLocation getFromString(String geolocation) {
		String[] parts = geolocation.split(",");
		GeoLocation gl = new GeoLocation(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
		return gl;
	}

}
