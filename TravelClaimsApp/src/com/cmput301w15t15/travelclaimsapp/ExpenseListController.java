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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	/**
	 * @param String name
	 * @param Boolean submitted
	 */
	public ExpenseListController(String name, boolean submitted) {
		if(submitted){
			claim = SubmittedClaimListController.getClaimList().getClaim(name);
		}else{
			claim = ClaimListController.getClaimList().getClaim(name);
		}
		
		expenseList = claim.getExpenseList();
	}
	
	/**
	 * constructor of ExpenseListController by claim
	 * @param claim
	 * @param submitted
	 */
	public ExpenseListController(Claim claim, boolean submitted) {
		if(submitted){
			claim = SubmittedClaimListController.getClaimList().getClaim(claim.getName());
		}else{
			claim = ClaimListController.getClaimList().getClaim(claim.getName());
		}
		
		expenseList = claim.getExpenseList();
	}
	
	
	/** 
	 * Method that retrieves the expenseList 
	 * @return ExpenseList expenseList
	 */
	public ExpenseList getExpenseList(){
		return expenseList;
	}
	
	/**
	 * Adds a expense with listener to the ExpenseListController {@link ExpenseList} 
	 * 
	 * @param Expense expense	the Expense to be added to the ExpenseList
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
	
	
	/**
	 * Takes the current expenses and returns a map containing the total 
	 * currency amounts for all expenses. <br>
	 * 
	 * Only returns currencies with amounts greater than zero
	 * 
	 * @return returns a map with key = currencies (String) and values = totals (Integer)
	 */
	public Map<String, Integer> getAmountTotals(){
		
		ArrayList<Expense> expenses = this.expenseList.toArrayList();
		Map<String, Integer> totalAmounts = new HashMap<String, Integer>();
		
		for(Expense expense : expenses){
			String cur = expense.getCurr();
			Integer amount = expense.getCost();
			
			if(amount == null){
				continue;
			}
			
			if(totalAmounts.containsKey(cur)){
				totalAmounts.put(cur, amount + totalAmounts.get(cur));
			}else if(amount != 0){
				totalAmounts.put(cur,amount);
			}
		}
		return totalAmounts;
	}
	
}