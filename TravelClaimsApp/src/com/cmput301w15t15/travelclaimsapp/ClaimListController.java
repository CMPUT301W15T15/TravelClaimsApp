package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.model.ApproverClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ClaimantClaimList;





public class ClaimListController {
	
	private static ClaimantClaimList claimaintClaimList = null;
	private static ApproverClaimList approverClaimList = null;



	public static ApproverClaimList getApproverClaimList() {
		if(approverClaimList == null){
			approverClaimList = new ApproverClaimList();
			
		}
		return approverClaimList;
	}

	public static ClaimantClaimList getClaimantClaimList() {
		if(claimaintClaimList == null){
			claimaintClaimList = new ClaimantClaimList();
		}
		return claimaintClaimList;
	}

}
