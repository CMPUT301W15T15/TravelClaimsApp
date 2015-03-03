package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import junit.framework.TestCase;

public class ApproveClaimListTest extends TestCase {
	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
	private Claim claim4;
	private ClaimList claimList;
	
	
	public ApproveClaimListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	//TestNumber: ApproveViewTest #1
	public void testViewSubmittedClaims() {
		claimList = new ClaimList();
		claim1 = new Claim("Claim1");
		claim2 = new Claim("Claim2");
		claim3 = new Claim("Claim3");
		claim4 = new Claim("Claim4");
		claimList.addClaim(claim1);
		claimList.addClaim(claim2);
		claimList.addClaim(claim3);
		claimList.addClaim(claim4);
		claim1.setStatus("Submitted");
		claim2.setStatus("Returned");
		claim3.setStatus("Process");
		claim4.setStatus("Approved");
		ArrayList<Claim> testList = new ArrayList<Claim>();
		testList.add(claim1);
		testList.add(claim2);
		assertTrue("Submittedlist", claimList.toArrayList().equals(testList));
		
	}
	
	//TestNumber: ApproveViewTest #2
	public void testViewSubmittedExpense() {
		ClaimList approveClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		approveClaimList.addClaim(claim1);
		claim1.setStatus("Submitted");
		assertTrue("name is equal", approveClaimList.toArrayList()
				.get(0).getName().toString().equals("Claim1"));
		
		Date date = new Date();
		claim1.setStartDate(date);
		assertTrue("starting date is equal", approveClaimList
				.toArrayList().get(0).getStartDate().equals(date));
		
		Date endDate = new Date();
		claim1.setEndDate(endDate);
		assertTrue("ending date is equal", approveClaimList.toArrayList().get(0).getEndDate().equals(endDate));
		
		//claim1.setDescription("Description");
		//assertTrue("description is equal", approveClaimList.getClaimList().get(0).getDescription().equals("Description"));
		
		claim1.setStatus("Submitted");
		assertTrue("status is equal", approveClaimList.toArrayList().get(0).getStatus().equals("Submitted"));
		
		// destination data structure need to be discussed
		String Country="Canada";
		Map<String,String> testDestinationList = null ;
		testDestinationList.put(Country, "Edmonton");
		//.addDestination(Country, "Edmonton");
		assertTrue("destionation is true",approveClaimList.toArrayList().get(0).getDestinationList()
				.equals(testDestinationList));
	}
	
	
}
