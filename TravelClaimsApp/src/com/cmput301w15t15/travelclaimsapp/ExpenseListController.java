package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.Listener;

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
	
	
	public static void addExpense(Expense expense){
		//to do in ClaimListController
		
	}
	
	
	
	
	
	private static void addExpenseListener(Expense expense) {
		//same
	}


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
	

	private static void addExpenseListeners(Expense expense2) {
		// TODO Auto-generated method stub
		
	}
	
	public static void save() {
		FileManager.getSaver().saveClaimLInFile(ClaimListController.getClaimList());
		FileManager.getSaver().saveClaimLInFile(ClaimListController.getClaimList());
	}
	
	
	
	
}