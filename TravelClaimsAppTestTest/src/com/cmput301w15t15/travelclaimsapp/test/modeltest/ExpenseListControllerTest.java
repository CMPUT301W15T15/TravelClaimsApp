package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import android.content.Context;
import android.test.AndroidTestCase;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

/**
 * 
 * Tests for the expense list controller 
 *
 */
public class ExpenseListControllerTest extends AndroidTestCase {

	private Claim claim;
	private ExpenseListController elc;
	private Context mContext;
	private Expense expense;
	
	
	
	public ExpenseListControllerTest() {
		super();
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		mContext = getContext();
		FileManager.initializeSaver(mContext);
		ClaimListController.removeAllClaims();
		claim = new Claim("Claim");
		ClaimListController.addClaim(claim);
		expense = new Expense("e1");
		elc = new ExpenseListController("Claim");
	}

	public void testAddExpense(){
		elc.addExpense(new Expense("e1"));
		assertEquals("Expense was not added", 1, claim.getExpenseList().size());
	}
	
	public void testDeleteExpense(){
		elc.removeExpense(expense);
		assertEquals("Expense was not added", 0, claim.getExpenseList().size());
	}
	
	public void testcontroller(){
		Claim claim1 = new Claim("Claim2");
		ClaimListController.addClaim(claim1);
		ExpenseListController elc2 = new ExpenseListController(claim1.getName());
		assertTrue(elc2.getExpenseList().size() == 0);
	}
	
}
