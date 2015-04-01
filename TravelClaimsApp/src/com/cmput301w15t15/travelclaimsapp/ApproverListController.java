package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Listener;


public class ApproverListController
{
	private static ClaimList approverClaimList = null;
	private static ExpenseList approverExpenseList = null;
	
	static public void initApproverListController() {
		if(approverClaimList == null){
			approverClaimList = FileManager.getSaver().loadClaimLFromFile();
			approverClaimList.sort();

	}
	}
}
