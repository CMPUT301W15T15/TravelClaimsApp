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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.location.Location;

/**
 * @author machinsk
 *
 * User Class: intended to store usernames, hashed passwords, and approver boolean for a given user. 
 *
 */
public class User  implements Listenable{

	
	private String username;
	
	//private ClaimList claimList;
	private byte[] pHash;
	private boolean approver;
	private GeoLocation homeLocation;
	protected transient ArrayList<Listener> listeners;
	
	
	/**
	 * To construct a user, username and a password hash needed, approver defaults to false.
	 *
	 * @param username
	 * @param password
	 * @param approver
	 */
	public User(String username, String password, boolean approver){
		
		this.username = username;
		this.pHash = hashToSHA256(password);
		this.approver = approver;
		this.listeners = new ArrayList<Listener>();
	}
	
	
	/**
	 * To construct a user, username and a password hash needed, approver defaults to false.
	 *
	 * @param username
	 * @param password
	 */
	public User(String username, String password){
		
		this.username = username;
		this.pHash = hashToSHA256(password);
		this.approver = false;
		this.listeners = new ArrayList<Listener>();
	}

	
	/**
	 * Returns String of username. This attribute is read only from the constructor.
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	
	/**
	 * Returns byte array of the password hash. This hash is read only from the constructor.
	 * 
	 * @return
	 */
	public byte[] getpHash() {
		return pHash;
	}


	/**
	 * Returns if boolean whether user is an Approver. This attribute is read only from the constructor.
	 * 
	 * @return
	 */
	public boolean isApprover() {
		return approver;
	}

	/**
	 * Returns the users home location 
	 * 
	 * @return returns a {@link Location}
	 */
	public GeoLocation getGeoLocation() {
		return homeLocation;
	}

	/**
	 * Sets the homeGeoLocation field for user to the passed {@link GeoLocation}
	 * 
	 * @param gl  {@link GeoLocation} to set as {@link User} home location
	 */
	public void setGeoLocation(GeoLocation gl) {
		this.homeLocation = gl;
		notifyListeners();
	}

	/**
	 * Take the string entered for a password and hashes it into SHA-256 bit byte array
	 * 
	 * @param pass  {@link String} to SHA-256 bit hash {@link byte[]} 
	 */
	public static byte[] hashToSHA256(String pass) {
		MessageDigest md = null;
		byte[] hash = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		try {
			hash = md.digest(pass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		return hash;
	}
	
	
	/**
	 * Consider getting rid of this function
	 */
	public void initHomeLocation(GeoLocation gl){
		this.homeLocation = gl;
	}
	
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void deleteListener(Listener listener){
		listeners.remove(listener);
	}

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}




	
}
