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
	
	public void addDestination(Destination dest) {
		this.destinations.add(dest); 
	}
	
	public void deleteDestination(Destination dest) {
		this.destinations.remove(dest);
	}
	
	public ArrayList<Destination> getDestinations() {
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
