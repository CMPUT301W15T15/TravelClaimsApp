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
 * 
 *
 */
public class ClaimList implements Listenable{
	protected ArrayList<Claim> claimList;
	protected transient ArrayList<Listener> listeners;

	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	
	}
	
	/**
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

	public Claim getClaim(String name) {
		if (claimList==null){
			return null;
		}
		else{
			for (int i=0; i<claimList.size();i++){
				if (claimList.get(i).toString().equals(name)){
					return claimList.get(i);
				}
			}
		}
		return null;
	}



	public void removeClaim(Claim claim1) {
		claimList.remove(claim1);
		notifyListeners();
	}

	public int size() {
		return claimList.size();
	}

	public ArrayList<Claim> toArrayList() {
		
		return this.claimList;
	}

	public ClaimList filterTag(Tag tag1)
	{

		// TODO Auto-generated method stub
		return null;
	}
	
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
