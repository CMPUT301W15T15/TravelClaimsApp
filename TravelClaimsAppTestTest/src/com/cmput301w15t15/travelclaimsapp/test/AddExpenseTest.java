package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.Expense;

import junit.framework.TestCase;

public class AddExpenseTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Expense expense1;
	private Expense expense2;

	
	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		expense1 = new Expense("E1");
		expense2 = new Expense("E2");
	}
	//test AddExpenseTest#1
	public void testAddExpense(){
		claim1.addExpense(expense1);
		claim1.addExpense(expense2);
		
		assertTrue("Expenses not added", claim1.getExpenseList().size() == 2);
	}
	//test AddExpenseTest#2
	public void testDeleteExpense(){
		claim1.removeExpense(expense1);
		assertTrue("Expenses not added", claim1.getExpenseList().size() == 1);
	}
	
	

}
