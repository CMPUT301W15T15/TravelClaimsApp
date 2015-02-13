package com.cmput301w15t15.travelclaimsapp.test;

import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.Expense;

import junit.framework.TestCase;

public class ApproveExpenseItemTest extends TestCase {

	public ApproveExpenseItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testViewSubmittedExpense() {
		ClaimList ApproveClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		Expense1 = new Expense("Expense1");
		
		ApproveClaimList.addClaim(Claim1);
		Claim1.setStatus("Submitted");
		Claim1.addExpense(Expense1);

		assertTrue("name is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getName().toString().equals("Expense1"));
		
		Date date = new Date();
		Expense1.setDate(date);
		assertTrue("date is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getDate().equals(date));
		
		Expense1.setDescription("Description");
		assertTrue("Description is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getDescription().equals("Description"));
	}
}
