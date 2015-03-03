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

	private Activity activity;
	private ClaimList claimList;
	private ClaimList claimList2;
	private Claim claim;
	private Expense expense;
	private FileManager fm;
	
	public ClaimListControllerTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		FileManager.initializeSaver(getContext());
		claimList = ClaimListController.getClaimList();
		claim = new Claim("c1");
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
		assertEquals("test", claim.getName());
		assertEquals("Claim loaded from file not the same as current", claim, claim2);
	}
	//ClaimListControllerTest #3
	public void testExpenseListeners(){
		ClaimListController.addClaimToClaimList(claim);
		ClaimListController.addExpense(expense, claim);
		claimList2 = ClaimListController.getClaimList();
		Claim claim2 = claimList2.toArrayList().get(0);
	
		assertEquals("Claim loaded from file not the same as current", claim, claim2);
		
		expense.setDescription("Something");
		assertEquals("Something", expense.getDescription());
		Expense expense2 = ClaimListController.getClaimList().toArrayList().get(0).getExpenseList().toArrayList().get(0);
		
		assertEquals("Expense loaded from file not the same as current", expense, expense2);
	}
	
	
}
