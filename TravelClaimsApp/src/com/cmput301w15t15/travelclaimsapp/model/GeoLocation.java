package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class GeoLocation implements Listenable{

	private double latitude;
	private double longitude;
	private ArrayList<Listener> listeners;
	
	public GeoLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.listeners = new ArrayList<Listener>();
	}

	public double getLatitude() {
		return latitude;
	}

	public double getlongitude() {
		return longitude;
	}

	public void setLongitude(double lng) {
		this.longitude = lng;
	}

	public void setLatitude(double lat) {
		this.latitude = lat;
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

}
