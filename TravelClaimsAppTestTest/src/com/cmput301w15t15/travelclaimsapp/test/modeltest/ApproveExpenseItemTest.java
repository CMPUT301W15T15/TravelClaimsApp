package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import junit.framework.TestCase;

public class ApproveExpenseItemTest extends TestCase {

	private Claim claim1;
	private Claim claim2;
	private Expense expense1;
	private Expense expense2;
	
	public ApproveExpenseItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	@SuppressWarnings("null")
	//TestNumber:ApproveViewTest #3
	public void testViewExpenseItem() throws IOException{
		ClaimList testClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		expense1 = new Expense("Expense1");
		
		claim1.setStatus("Submitted");
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);
		
		testClaimList.addClaim(claim1);
		testClaimList.addClaim(claim2);
		
		Expense item1 = new Expense("food");
		Expense item2 = new Expense("meat");
		Expense item3 = new Expense("drink");
		expenseList.addExpense(item1);
		expenseList.addExpense(item2);
		expenseList.addExpense(item3);
		
		
		
	}
	
	
}
