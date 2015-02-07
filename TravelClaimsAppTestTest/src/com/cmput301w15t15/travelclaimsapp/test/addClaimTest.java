package com.cmput301w15t15.travelclaimsapp.test;
import junit.framework.TestCase;


public class addClaimTest extends TestCase {
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
	public addClaimTest(){
		assertTrue("The length of the claimList is not two",  this.claimList.size()==2);
		assertTrue("claim1 was not added", this.claimList.getClaim("Claim1") == this.claim1);
	}
	public void editClaimTest(){
		claim1.setDescription("testing");
		claim1.setStartDate(2015, 02, 05);
	}
	public void deleteClaimTest(){
		
	}
}
