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
 * List of {@link Destination} objects
 *
 */
public class DestinationList implements Listenable{

	private ArrayList<Destination> destinations;
	
	private transient ArrayList<Listener> listeners;
	
	
	/**
	 * Creates new {@link DestinationList} object
	 */
	public DestinationList(){
		this.destinations = new ArrayList<Destination>();
		this.listeners = new ArrayList<Listener>();
	}
	
	/**
	 * Adds a {@link Destination} to the DestinationList
	 * 
	 * @param dest Destination to add
	 */
	public void addDestination(Destination dest) {
		this.destinations.add(dest); 
		notifyListeners();
	}
	
	/**
	 * Delete a {@link Destination} from the DestinationList
	 * 
	 * @param dest Destination to add
	 */
	public void deleteDestination(Destination dest) {
		this.destinations.remove(dest);
		notifyListeners();
	}
	
	/**
	 * Returns the DestinationList as a {@link ArrayList} of Destinations 
	 * 
	 * @return the ArrayList<Destination>
	 */
	public ArrayList<Destination> toArrayList() {
		return destinations;
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
