package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;


/**
 * Destination object refers to a location and reason for travel
 *
 */
public class Destination implements Listenable{
	
	private transient ArrayList<Listener> listeners;
	private String location;
	private String reason;
	
	/**
	 * Creates a Destination object with a location and reason
	 * 
	 * @param location location (city, country, place) of the destination
	 * @param reason reason for traveling to destination 
	 */
	public Destination(String location, String reason) {
		this.location = location;
		this.reason = reason;
		this.listeners = new ArrayList<Listener>();
	}

	/**
	 * Returns Destinations location
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set destination location attribute
	 * 
	 * @param location String to set location to 
	 */
	public void setLocation(String location) {
		this.location = location;
		notifyListeners();
	}
	
	/**
	 * Set the Destinations location attribute
	 * 
	 * @param string String to set Reason to 
	 */
	public void setReason(String string) {
		this.reason = string;
		notifyListeners();
	}
	
	public String getReason() {
		// TODO Auto-generated method stub
		return this.reason;
	}
	
	@Override
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
		
	}

	@Override
	public void addListener(Listener listener) {
		listeners.add(listener);
		
	}

	@Override
	public void deleteListener(Listener listener) {
		listeners.remove(listener);
		
	}

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}
	
}
