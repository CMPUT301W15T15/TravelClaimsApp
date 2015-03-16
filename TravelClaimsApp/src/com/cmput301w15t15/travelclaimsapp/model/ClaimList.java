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
 * Used to store claims and listeners for a user. Expenses are also stored within.
 *
 */
public class ClaimList implements Listenable{
	protected ArrayList<Claim> claimList;
	protected transient ArrayList<Listener> listeners;

	
	/**
	 * Initializes new claim and listener arrays.
	 */
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	
	}
	
	/**
	 * Adds a claim to the list.
	 * @param claim1
	 * 
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
	 * Gets a claim from a given name.
	 * @param name
	 * @return
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
	 * @param claim1
	 */
	public void removeClaim(Claim claim1) {
		claimList.remove(claim1);
		notifyListeners();
	}

	/**
	 * Returns number of claims in the list.
	 * @return
	 */
	public int size() {
		return claimList.size();
	}

	/**
	 * Returns an arraylist of claims.
	 * @return
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
