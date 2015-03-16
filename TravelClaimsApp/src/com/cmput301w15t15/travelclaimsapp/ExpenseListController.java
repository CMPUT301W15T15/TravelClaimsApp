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
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.Listener;

/**
 * get expenseList from file directly
 *
 * @author bzhou2
 *
 */
public class ExpenseListController {
	
	private static ExpenseList expenseList = null;
	private static ClaimList claimList = null;
	private static String claimName;
	private static Claim claim;
	private static Expense expense;
	
	public ExpenseListController(String Name) {
		// TODO Auto-generated constructor stub	
		claimName =Name; 
	}
	
	
	/** Method that retrieves the expenseList 
	 * @return expenseList
	 */
	public static ExpenseList getExpenseList(){
		
		
		if (expenseList == null){
			claimList = ClaimListController.getClaimList();
			claim= claimList.getClaim(claimName);
			expenseList=claim.getExpenseList();
			
			expenseList.addListener(new Listener(){
				
				@Override
				public void update(){
					save();
					expenseList.sort();		
				}		
			});
			
			// this can be done in ClaimListController
//			for (Expense expense:expenseList.toArrayList()){
//				expense.setListeners();
//				addExpenseListeners(expense);
//			}
			
			return expenseList;
		}
		return expenseList;
		
		
	}
	
	
	/**
	 * 
	 * add expense into expense list
	 * (for now, deal it in claim controller)
	 * @param expense
	 */
	public static void addExpense(Expense expense){
		//to do in ClaimListController
		
	}
	
	
	
	
	

	/**
	 * 
	 * add expenses listener 
	 * (for now, deal with it in claimController)
	 * @param expense
	 */
	private static void addExpenseListener(Expense expense) {
		//same
	}


	/**
	 * get expenselist from internet
	 * 
	 * @return expenseList
	 */
	static public ExpenseList getExpenseListWithInternet(){
		if (expenseList == null){
			claimList = ClaimListController.getClaimList();
			claim= claimList.getClaim(claimName);
			expenseList=claim.getExpenseList();
			
			expenseList.addListener(new Listener(){
				
				@Override
				public void update(){
					save();
					expenseList.sort();		
				}		
			});
			
			for (Expense expense:expenseList.toArrayList()){
				expense.setListeners();
				addExpenseListeners(expense);
			}
			return expenseList;
		}
		return expenseList;
	}
	

	/**
	 * add expense listener
	 * @param expense2
	 */
	private static void addExpenseListeners(Expense expense2) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * save to file
	 * (for now, deal with it in claim controller)
	 */
	public static void save() {
//		FileManager.getSaver().saveClaimLInFile(ClaimListController.getClaimList());
//		FileManager.getSaver().saveClaimLInFile(ClaimListController.getClaimList());
	}
	
	
	
	
}