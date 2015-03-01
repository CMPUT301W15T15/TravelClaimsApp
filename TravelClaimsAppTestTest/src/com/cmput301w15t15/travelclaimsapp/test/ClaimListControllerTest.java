package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.ApproverClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimantClaimList;

import junit.framework.TestCase;

public class ClaimListControllerTest extends TestCase {

	private ApproverClaimList apClaimList;
	private ClaimantClaimList clClaimList;
	private Claim claim1;
	
 	
	
	public ClaimListControllerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		apClaimList = ClaimListController.getApproverClaimList();
		clClaimList = ClaimListController.getClaimantClaimList();
		claim1 = new Claim("Hello");
	}
	
	void testClaimListController(){
		
		apClaimList.addClaim(claim1);
		
	}
	
	void testSaveClaimList(){
		
	}
	
	

}
