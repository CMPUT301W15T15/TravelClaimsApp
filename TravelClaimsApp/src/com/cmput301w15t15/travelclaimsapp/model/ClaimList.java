package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class ClaimList {
	protected ArrayList<Claim> claimList;
	protected transient ArrayList<Listener> listeners;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	public void addClaim(Claim claim1){
		claimList.add(claim1);
	}

	public Claim getClaim(String name) {
		int i= claimList.indexOf(name);
		return claimList.get(i);
	}

	public void removeClaim(Claim claim1) {
		claimList.remove(claim1);
		
	}

	public int size() {
		return claimList.size();
	}

	public ArrayList<Claim> toArrayList() {
		
		return this.claimList;
	}

	public ClaimList filterTag(String tag1)
	{

		// TODO Auto-generated method stub
		return null;
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
	

}
