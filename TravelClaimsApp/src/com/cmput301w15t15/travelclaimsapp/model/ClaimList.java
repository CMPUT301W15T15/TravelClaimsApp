package com.cmput301w15t15.travelclaimsapp.model;

import java.io.IOException;
import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;

public class ClaimList {
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
		if (claimList.size() ==0){
			claimList.add(claim1);
		}
		for (int i=0;i<claimList.size();i++){
			if (claimList.get(i)!=claim1){
				claimList.add(claim1);
				}
		}
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
		
		return null;
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
