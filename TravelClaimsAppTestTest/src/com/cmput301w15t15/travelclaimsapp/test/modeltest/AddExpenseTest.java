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

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import junit.framework.TestCase;

/**
 * Tests adding and removing expenses from expenselist
 *
 */
public class AddExpenseTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Expense expense1;
	private Expense expense2;
	private ExpenseList expenseList;

	
	protected void setUp() throws Exception {
		super.setUp();
		claimList = new ClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		expense1 = new Expense("E1");
		expense2 = new Expense("E2");
		expenseList = claim1.getExpenseList();
	}
	/**
	 * test AddExpenseTest#1
	 */
	public void testAddExpense(){
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);
		
		assertEquals("Expenses not added", claim1.getExpenseList().size(),2);
	}
	/**
	 * test AddExpenseTest#2
	 */
	public void testDeleteExpense(){
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);
		expenseList.removeExpense(expense1);
		assertEquals("Expenses not removed", claim1.getExpenseList().size() ,1);
	}
	
	

}
