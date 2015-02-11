package com.cmput301w15t15.travelclaimsapp.test;
import java.io.IOException;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;



public class AddClaimTest extends TestCase{
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
	}
	//test #
	public void testAddClaim(){
		claimList.addClaim(new Claim("Claim3"));
		assertTrue("The length of the claimList is not two", this.claimList.size() == 3);
		assertTrue("claim1 was not added", this.claimList.getClaim("Claim1") == this.claim1);
	}
	//test #
	public void testDeleteClaim(){
		claimList.removeClaim(claim1);
		assertTrue("Claim was not removed from claimList", claimList.getClaim("Claim1")==null);
		assertTrue("Claim was not removed from claimList", claimList.size() == 2); 
	}
	//test #
	public void testAddClaimDuplicate(){
		
		
		try{
			claimList.addClaim(new Claim("Claim4"));
			claimList.addClaim(new Claim("Claim4"));
		}catch(IllegalArgumentException e){
			assertTrue("Two claims were added with the same name and not caught", e.getMessage().equals("Claim.setName was passed a illegal argument"));
		}
		
	}
	
}
