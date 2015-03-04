package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

public class ClaimListControllerTest extends AndroidTestCase {


	private ClaimList claimList;
	private ClaimList claimList2;
	private Claim claim;
	private Claim claim3;
	private Expense expense;

	
	public ClaimListControllerTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		FileManager.initializeSaver(getContext());
		claimList = ClaimListController.getClaimList();
		claim = new Claim("c1");
		claim3 = new Claim("c3");
		expense = new Expense("e1");
		
		
		
	}
	
	//ClaimListControllerTest #1
	public void testClaimListListeners(){

		ClaimListController.addClaimToClaimList(claim);
		claimList2 = ClaimListController.getClaimList();
		
		assertEquals("ClaimList loaded from file not the same as current", claimList, claimList2);
	}
	
	//ClaimListControllerTest #2
	public void testClaimListeners(){
		ClaimListController.addClaimToClaimList(claim);
		claim.setName("test");
		claimList2 = ClaimListController.getClaimList();
		Claim claim2 = claimList2.toArrayList().get(0);
		if(!claim.getName().equals("test")){
			fail("claim name was not set");
		}else{
			assertEquals("not the same size", 2, claimList2.size());
			assertEquals("Claim loaded from file not the same as current", claim.getName(), claim2.getName());
		}
		
	}
	//ClaimListControllerTest #3
	public void testExpenseListeners(){
		ClaimListController.addClaimToClaimList(claim3);
		ClaimListController.addExpense(expense, claim);
		claimList2 = ClaimListController.getClaimList();
		Claim claim2 = claimList2.toArrayList().get(0);
	
		assertEquals("Claim loaded from file not the same as current", claim3, claim2);
		
		expense.setDescription("Something");
	
		Expense expense2 = ClaimListController.getClaimList().toArrayList().get(0).getExpenseList().toArrayList().get(0);
		if(!expense.getDescription().equals("Something")){
			fail("description was not set");
		}else{
			assertEquals("Expense loaded from file not the same as current", expense, expense2);
		}
	}
		
	
	
}
