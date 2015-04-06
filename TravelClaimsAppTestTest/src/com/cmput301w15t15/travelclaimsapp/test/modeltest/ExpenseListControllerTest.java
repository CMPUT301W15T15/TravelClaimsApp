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

import java.util.Map;

import android.content.Context;
import android.test.AndroidTestCase;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

/**
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
		elc = new ExpenseListController("Claim",false);
	}
	/**
	 * ExpenseListControllerTest 1
	 * 
	 * Tests adding a expense with controller
	 */
	public void testAddExpense(){
		elc.addExpense(new Expense("e1"));
		assertEquals("Expense was not added", 1, claim.getExpenseList().size());
	}
	/**
	 * ExpenseListControllerTest 2
	 * 
	 * Test deleting expense with controller
	 */
	public void testDeleteExpense(){
		elc.removeExpense(expense);
		assertEquals("Expense was not added", 0, claim.getExpenseList().size());
	}
	/**
	 * ExpenseListControllerTest 3
	 * 
	 * Test creating expense list controller
	 */
	public void testcontroller(){
		Claim claim1 = new Claim("Claim2");
		ClaimListController.addClaim(claim1);
		ExpenseListController elc2 = new ExpenseListController(claim1.getName(),false);
		assertTrue(elc2.getExpenseList().size() == 0);
	}
	/**
	 * ExpenseListControllerTest 4
	 * 
	 * Test getAmountsTotal() function
	 */
	public void testgetAmountTotals(){
		Expense e2 = new Expense("e2");
		e2.setCost(50);
		e2.setCurr("CAD");
		
		Expense e3 = new Expense("e3");
		e3.setCost(1);
		e3.setCurr("JPY");
		
		elc.addExpense(e2);
		elc.addExpense(e3);
		
		Map<String,Integer> map = elc.getAmountTotals();
		
		assertEquals(2, map.size());	//test that there are only two currencies
		
		Expense e4 = new Expense("e4");
		e4.setCost(0);
		e4.setCurr("EUR");
	
		map = elc.getAmountTotals();
		
		assertEquals(2, map.size());	//should only be two currencies still as e4 amount == 0
		
		//test mapped values are correct 
		assertEquals("Mapped currency not correct", (Integer)50, (Integer)map.get("CAD"));
		assertEquals("Mapped currency not correct", null, (Integer)map.get("EUR"));
	}
	
}
