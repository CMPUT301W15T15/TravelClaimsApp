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
import java.util.Collections;

import com.cmput301w15t15.travelclaimsapp.ClaimListComparator;

/**
 * Used to store list of Claim objects.
 *
 */
public class ClaimList implements Listenable{
	protected ArrayList<Claim> claimList;
	protected transient ArrayList<Listener> listeners;
	protected String username;

	/**
	 * Initializes new claim and listener arrays.
	 */
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	
	}
	
	/**
	 * Adds a claim to the list.
	 * @param claim1	the claim to add to the ClaimList
	 */
	public void addClaim(Claim claim1){
		for (Claim claim : claimList){
			//if name already in claimlist return without adding claim
			if (claim.getName().equals(claim1.getName())){
				return;
			}
		}
		claimList.add(claim1);
		notifyListeners();
	}

	/**
	 * Gets a claim with a given name.
	 * @param name	 the name of the Claim to retrieve from ClaimList
	 * @return		the Claim object with the passed name
	 */
	public Claim getClaim(String name) {
		if (claimList==null){
			return null;
		}
		else{
			for (int i=0; i<claimList.size();i++){
				if (claimList.get(i).getName().equals(name)){
					return claimList.get(i);
				}
			}
		}
		return null;
	}



	/**
	 * Removes given claim.
	 * @param claim1	the Claim to remove from ClaimList
	 */
	public void removeClaim(Claim claim1) {
		claimList.remove(claim1);
		notifyListeners();
	}

	/**
	 * Returns number of claims in the list.
	 * @return		the integer number of claim in ClaimList
	 */
	public int size() {
		return claimList.size();
	}
	
	/**
	 * Gets the username string for the claimList so ownership can be checked
	 * 
	 * @return	 the username string attached to ClaimList
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username string for the claimList so ownership can be checked later
	 * 
	 * @param username		the username string to set as ClaimList username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns an arraylist of claims from ClaimList.
	 * @return  a ArrayList of claims
	 */
	public ArrayList<Claim> toArrayList() {
		return this.claimList;
	}

	/**
	 * Sorts claims in claimlist by starting date.
	 */
	public void sort() {
		if (claimList.size()!=0){
			Collections.sort(claimList, new ClaimListComparator());
		}
	}
	
	/* 
	 * call listener to update
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#notifyListeners()
	 */
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	/* add listener
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#addListener(com.cmput301w15t15.travelclaimsapp.model.Listener)
	 */
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	/* delete listener
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#deleteListener(com.cmput301w15t15.travelclaimsapp.model.Listener)
	 */
	public void deleteListener(Listener listener){
		listeners.remove(listener);
	}

	/* set listener
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#setListeners()
	 */
	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}


}
