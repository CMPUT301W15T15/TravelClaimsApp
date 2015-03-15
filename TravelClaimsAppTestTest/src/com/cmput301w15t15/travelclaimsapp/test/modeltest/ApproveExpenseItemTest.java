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
		expense1 = new Expense("Expense1", null, null, null, null, 0);
		
		claim1.setStatus("Submitted");
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);
		
		testClaimList.addClaim(claim1);
		testClaimList.addClaim(claim2);
		
		Expense item1 = new Expense("food", null, null, null, null, 0);
		Expense item2 = new Expense("meat", null, null, null, null, 0);
		Expense item3 = new Expense("drink", null, null, null, null, 0);
		expenseList.addExpense(item1);
		expenseList.addExpense(item2);
		expenseList.addExpense(item3);
		
		
		
	}
	
	
}
