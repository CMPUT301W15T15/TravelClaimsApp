package com.cmput301w15t15.travelclaimsapp.test;

import java.util.Map;
import java.util.Set;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;

import junit.framework.TestCase;

public class EditClaimTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;

	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
		claim1.setStartDate(2015, 2,5);
	}
	
	//test case: EditClaimTest#1
	public void testEditClaimName(){
		claim1.setDescription("testing");

		try{
			claim1.setName("");
		}catch(IllegalArgumentException e){
			assertTrue("Claim name was set to equal empty string and not caught", e.getMessage().equals("Claim.setName was passed a illegal arguement"));
		}
		try{
			claim1.setName("Claim2");
		}catch(IllegalArgumentException e){
			assertTrue("Claim name was set to claim2's name and not caught", e.getMessage().equals("Claim.setName was passed a illegal argument"));
		}
	}
	
	//test case: EditClaimTest#2
	public void testEditClaimDate(){
		
		try{
			claim1.setEndDate(2015, 1, 5);
		}catch(IllegalArgumentException e){
			assertTrue("Claim end date set before claim start date", e.getMessage().equals("Claim.setEndDate was passed a illegal argument"));
		}
		
		try{
			claim1.setEndDate(2015, 3, 5);
		}catch(IllegalArgumentException e){
			
		}
		
		try{
			claim1.setStartDate(2015, 4, 5);
		}catch(IllegalArgumentException e){
			assertTrue("Claim start date set after claim end date", e.getMessage().equals("Claim.setEndDate was passed a illegal argument"));
		}
		
	}
	//test case: EditClaimTest#3
	public void testAddDestination(){
		claim1.addDestination("Rome", "to work");
		Set<String> dest = claim1.getDestinationList().keySet();
		assertTrue("Destination not added", dest.size() == 1);
		assertTrue("Destination description not correct", claim1.getDestinationList().get("Rome") == "to work");
	}
	
	//test case: EditClaimTest#4
	public void testDeleteDestination(){
		claim1.addDestination("Rome", "to work");
		claim1.deleteDestination("Rome");
		
		Set<String> dest = claim1.getDestinationList().keySet();
		assertTrue("Destination not added", dest.size() == 0);
	}
	//test case: EditClaimTest#5
	public void testSubmittedStatus(){
		claim1.setStatus("Submitted");
		assertTrue(claim1.isEditable());
		claim1.setStatus("Returned");
		assertFalse(claim1.isEditable());
		claim1.setStatus("Approved");
		assertTrue(claim1.isEditable());
	}
	
	
	
	
	

}
