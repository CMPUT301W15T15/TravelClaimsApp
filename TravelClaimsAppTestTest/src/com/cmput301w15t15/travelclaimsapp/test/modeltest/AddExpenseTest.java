package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import junit.framework.TestCase;

public class AddExpenseTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Expense expense1;
	private Expense expense2;
	private ExpenseList expenseList;

	
	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimantClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		expense1 = new Expense("E1");
		expense2 = new Expense("E2");
		expenseList = claim1.getExpenseList();
	}
	//test AddExpenseTest#1
	public void testAddExpense(){
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);
		
		assertTrue("Expenses not added", claim1.getExpenseList().size() == 2);
	}
	//test AddExpenseTest#2
	public void testDeleteExpense(){
		expenseList.removeExpense(expense1);
		assertTrue("Expenses not added", claim1.getExpenseList().size() == 1);
	}
	
	

}
