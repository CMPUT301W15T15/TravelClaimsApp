package com.cmput301w15t15.travelclaimsapp.model;

/**
 * Listener interface, for creating Listener objects to 
 * attach to objects you want to listen to 
 *
 */
public interface Listener {
	/**
	 * This function should be used to perform a 
	 * action that a listener is waiting for
	 * 
	 * For example save a object to file when one
	 * of its attributes have been changed 
	 */
	public void update();
}

