package com.cmput301w15t15.travelclaimsapp;


import com.cmput301w15t15.travelclaimsapp.model.ClaimList;






public class ClaimListController {
	
	private static ClaimList claimList = null;
	
	public static ClaimList getClaimList() {
		if(claimList == null){
			claimList = new ClaimList();
			
		}
		return claimList;
	}



}
