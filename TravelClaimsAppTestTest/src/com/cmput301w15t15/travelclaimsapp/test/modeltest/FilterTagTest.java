package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import android.nfc.Tag;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;



import junit.framework.TestCase;


public class FilterTagTest extends TestCase
{
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
//	private Tag tag1=new Tag("Business");
	
	protected void setUp() throws Exception
	{
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
		claim3 = new Claim("Claim3");
		claimList.addClaim(claim3);
		//claim1.addTag(tag1);
		//claim2.addTag(tag1);
	}
	//TestCase: FilterTagTest#1
	public void filterTagTest(){
//		assertTrue("the size is not correct!",this.claimList.filterTag(tag1).size()==2);
//		assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim1")==this.claim1);
//		assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim2")==this.claim2);
		//assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim1").getTag()==this.tag1);
		//assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim2").getTag()==this.tag1);
//		assertFalse("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim3")==this.claim3);
	}

}
