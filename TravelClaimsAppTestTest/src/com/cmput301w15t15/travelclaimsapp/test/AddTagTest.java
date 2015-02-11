package com.cmput301w15t15.travelclaimsapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;

public class AddTagTest extends TestCase {
	private Claim claim1;
	private ClaimList claimList;
	private ArrayList<String> tags = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		tags = claimList.getTagList();
		claim = new Claim("Claim1");
		claimList.addClaim(claim);
	}
	//test #
	public void testAddTagTest(){
		claim.addTag("Test");
		assertTrue("Tag 'Test' was not added to tags", claimList.getTagList().size() == 1);
		
		
	}

}
