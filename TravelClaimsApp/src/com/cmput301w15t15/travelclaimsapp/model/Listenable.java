package com.cmput301w15t15.travelclaimsapp.model;


/**
 * Listenable, defines the functions required for implement classes 
 * that can have listeners. Intended to use for managing {@link Listener} objects
 */
public interface Listenable {
	
	/**
	 * Use when object attributes have been changed, to notify listeners
	 */
	public void notifyListeners();
	
	/**
	 * Use for adding {@link Listener} objects to class
	 * 
	 * @param listener
	 */
	public void addListener(Listener listener);
	
	/**
	 * Use for setting listener array in objects that implement 
	 * this interface
	 */
	public void setListeners();
	
	/**
	 * Use to delete listeners attached to object implementing 
	 * interface 
	 * 
	 * @param listener
	 */
	public void deleteListener(Listener listener);
}
