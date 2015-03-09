package com.cmput301w15t15.travelclaimsapp.model;

import java.io.IOException;
import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;

public class ClaimList implements Listenable{
	protected ArrayList<Claim> claimList;
	protected transient ArrayList<Listener> listeners;
	
	public ClaimList(){
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();
	}
	
	/**
	 * @param claim1
	 * @throws IOException
	 */
	public void addClaim(Claim claim1) throws IOException{
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
