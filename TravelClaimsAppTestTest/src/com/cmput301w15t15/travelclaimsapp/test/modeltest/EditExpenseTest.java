package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import junit.framework.TestCase;

public class EditExpenseTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Expense expense1;
	private Expense expense2;
	

	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		expense1 = new Expense("E1", null, null, null, null, 0);
		expense2 = new Expense("E2", null, null, null, null, 0);
		claim1.addExpense(expense1);
		claim1.addExpense(expense2);
	}
	//Test case: EditExpenseTest#1
	public void testEditExpenseName(){
		expense1.setName("");
		assertEquals("",expense1.getName());
	}
}
