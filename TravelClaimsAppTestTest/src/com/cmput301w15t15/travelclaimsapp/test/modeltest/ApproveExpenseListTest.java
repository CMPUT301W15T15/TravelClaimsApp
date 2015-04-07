/*
 *TravelClaimsApp
 *Copyright (C) 2015 Jon Machinski, Bo Zhou, Henry Ha, Chris Wang, Sean Scheideman
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.IOException;
import java.util.Date;


import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;


import junit.framework.TestCase;

/**
 * test for approver to view list of expense
 * @author Chris Wang
 *
 */

public class ApproveExpenseListTest extends TestCase {
	private Claim claim1;
	private Claim claim2;
	private Expense expense1;
	private Expense expense2;
	
	public ApproveExpenseListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		
		
	}
	
	/**
	 * /TestNumber:ApproveViewTest #4
	 * test if the claim submitted check the expenseList in a claim does match with approver
	 */
	public void testViewSubmittedExpense() throws IOException {
		ClaimList approveClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		claim1.setStatus("Process");
		expense1 = new Expense("Expense1");
		
		approveClaimList.addClaim(claim1);
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);
		Date date = new Date();
		expense1.setDate(date);
		expense1.setDes("Description");
		expense1.setGeoLocation(null);
		expense1.setCat("Air Fare");
		expense1.setCost(100);
		expense1.setCurr("CAN");
		expense1.setFlag(1);
		claim1.setStatus("Submitted");
		
		
		assertTrue("name is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getName().toString().equals("Expense1"));
	
		assertTrue("date is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getDate().equals(date));
		
		assertTrue("Description is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getDes().equals("Description"));
		assertTrue("Geolocation is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getGeoLocation() == null);
		assertTrue("category is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getCat().equals("Air Fare"));
		assertTrue("cost is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getCost().equals(100));
		assertTrue("Currency is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getCurr().equals("CAN"));
		assertTrue("flag is existing", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getFlag()==1);
		
	}
	
	/**
	 *TestNumber:ReceiptViewTest #1
	 * check the approver get correct photo.
	 */
	public void testViewExpenseReceipt() throws IOException
	{

		ClaimList testClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		claim2 = new Claim("Claim2");
		expense1 = new Expense("Expense1");
		expense2 = new Expense("Expense2");
		
		claim1.setStatus("Process");
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);

		testClaimList.addClaim(claim1);
		testClaimList.addClaim(claim2);
		
		expense1.takePicture(null);
		claim1.setStatus("Submitted");
		assertTrue("Image yes?", expense1.getPicture() == null);
		assertTrue("Image no", expense2.getPicture() == null);

	}
}
	