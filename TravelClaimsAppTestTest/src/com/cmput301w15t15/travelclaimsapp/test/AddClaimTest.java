package com.cmput301w15t15.travelclaimsapp.test;
import java.io.IOException;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


// <<<<<<< HEAD:TravelClaimsAppTestTest/src/com/cmput301w15t15/travelclaimsapp/test/addClaimTest.java
public class addClaimTest extends TestCase {
//=======
public class claimTest extends TestCase{
// >>>>>>> b754c82f7b1d5e226a1058c09d770cc99edb7d0b:TravelClaimsAppTestTest/src/com/cmput301w15t15/travelclaimsapp/test/AddClaimTest.java
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
// <<<<<<< HEAD:TravelClaimsAppTestTest/src/com/cmput301w15t15/travelclaimsapp/test/addClaimTest.java
	public addClaimTest(){
		assertTrue("The length of the claimList is not two",  this.claimList.size()==2);
=======
	public void addClaimTest(){
		claimList.addClaim(new Claim("Claim3"));
		assertTrue("The length of the claimList is not two", this.claimList.size() == 3);
// >>>>>>> b754c82f7b1d5e226a1058c09d770cc99edb7d0b:TravelClaimsAppTestTest/src/com/cmput301w15t15/travelclaimsapp/test/AddClaimTest.java
		assertTrue("claim1 was not added", this.claimList.getClaim("Claim1") == this.claim1);
	}
	
	public void deleteClaimTest(){
		claimList.removeClaim(claim1);
		assertTrue("Claim was not removed from claimList", claimList.getClaim("Claim1")==null);
		assertTrue("Claim was not removed from claimList", claimList.size() == 2); 
	}
	
}
