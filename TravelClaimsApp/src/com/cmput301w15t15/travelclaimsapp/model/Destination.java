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
	
	/**
	 * Returns the reason for destination
	 * 
	 * @return reason string
	 */
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
