package com.cmput301w15t15.travelclaimsapp.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;

import junit.framework.TestCase;

public class ApproveClaimListTest extends TestCase {
	private Claim Claim1;
	private Claim Claim2;
	private Claim Claim3;
	private Claim Claim4;
	
	
	public ApproveClaimListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	//TestNumber:8.1
	public void testViewSubmittedClaims() {
		ClaimList ApproveClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		Claim2 = new Claim("Claim2");
		Claim3 = new Claim("Claim3");
		Claim4 = new Claim("Claim4");
		ApproveClaimList.addClaim(Claim1);
		ApproveClaimList.addClaim(Claim2);
		ApproveClaimList.addClaim(Claim3);
		ApproveClaimList.addClaim(Claim4);
		Claim1.setStatus("Submitted");
		Claim2.setStatus("Returned");
		Claim3.setStatus("Process");
		Claim4.setStatus("Approved");
		ArrayList<Claim> testList = new ArrayList<Claim>();
		testList.add(Claim1);
		testList.add(Claim2);
		assertTrue("Submittedlist", ApproveClaimList.getClaimList().equals(testList));
		
	}
	
	//TestNumber:8.2
	public void testViewSubmittedExpense() {
		ClaimList ApproveClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		ApproveClaimList.addClaim(Claim1);
		Claim1.setStatus("Submitted");
		assertTrue("name is equal", ApproveClaimList.getClaimList()
				.get(0).getName().toString().equals("Claim1"));
		
		Date date = new Date();
		Claim1.setStartDate(date);
		assertTrue("starting date is equal", ApproveClaimList
				.getClaimList().get(0).getStartDate().equals(date));
		
		Date endDate = new Date();
		Claim1.setEndDate(endDate);
		assertTrue("ending date is equal", ApproveClaimList.getClaimList().get(0).getEndDate().equals(endDate));
		
		Claim1.setDescription("Description");
		assertTrue("description is equal", ApproveClaimList.getClaimList().get(0).getDescription().equals("Description"));
		
		Claim1.setStatus("Submitted");
		assertTrue("status is equal", ApproveClaimList.getClaimList().get(0).getStatus().equals("Submitted"));
		
		// destination data structure need to be discussed
		String Country="Canada";
		Map<String,String> testDestinationList = null ;
		testDestinationList.put(Country, "Edmonton");
		Claim1.addDestination(testDestinationList);
		assertTrue("destionation is true",ApproveClaimList.getClaimList().get(0).getDestinationList()
				.equals(testDestinationList));
	}
	
	
}
