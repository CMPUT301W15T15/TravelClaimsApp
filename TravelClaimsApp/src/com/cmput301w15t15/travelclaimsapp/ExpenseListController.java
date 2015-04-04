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
package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimListSaveListener;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

/**
 * Used to get and manipulate a {@link ExpenseList} for a given {@link Claim}
 *
 */
public class ExpenseListController {
	
	private ExpenseList expenseList;
	private Claim claim;
	
	public ExpenseListController(String name) {
		claim = ClaimListController.getClaimList().getClaim(name);
		expenseList = claim.getExpenseList();
	}
	
	
	/** Method that retrieves the expenseList 
	 * @return expenseList
	 */
	public ExpenseList getExpenseList(){
		return expenseList;
	}
	
	/**
	 * Adds a expense with listener to the ExpenseListController {@link ExpenseList}
	 * 
	 * @param expense	the Expense to be added to the 
	 */
	public void addExpense(Expense expense){
		claim.addExpense(expense);
		expense.addListener(new ClaimListSaveListener());
	}
	
	/**
	 * Removes expense from the ExpenseListController {@link ExpenseList}
	 * 
	 * @param expense the expense to remove
	 */
	public void removeExpense(Expense expense){
		claim.removeExpense(expense);
	}
	
	
}