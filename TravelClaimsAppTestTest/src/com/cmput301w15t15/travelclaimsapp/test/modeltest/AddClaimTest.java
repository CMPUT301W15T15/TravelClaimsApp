package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.io.IOException;

import android.test.AndroidTestCase;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;


import junit.framework.TestCase;



public class AddClaimTest extends AndroidTestCase{
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		claimList = new ClaimList();
		
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
	}
	//test: AddClaimTest#1
	public void testAddClaim() throws IOException{
		claimList.addClaim(claim1);
		assertEquals("The length of the claimList is not two", this.claimList.size(),2);
		//assertTrue("claim1 was not added", claimList.getClaim("Claim1") == claim1);
	}
	//test: AddClaimTest#2
	public void testDeleteClaim(){
		claimList.removeClaim(claim1);
		//assertTrue("Claim was not removed from claimList", claimList.getClaim("Claim1")==null);
		assertEquals("Claim was not removed from claimList", claimList.size(),1); 
	}
	//test: AddClaimTest#3
	public void testAddClaimDuplicate() throws IOException{
		
		try{
			claimList.addClaim(new Claim("Claim4"));
			claimList.addClaim(new Claim("Claim4"));
			fail("Should have thrown a exception");
		}catch(IllegalArgumentException e){
			assertTrue("Two claims were added with the same name and not caught", e.getMessage().equals("Claim.setName was passed a illegal argument"));
		}
		
	}
	
}
