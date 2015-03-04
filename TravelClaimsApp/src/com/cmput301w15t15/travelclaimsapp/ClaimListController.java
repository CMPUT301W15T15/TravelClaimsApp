package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Listener;

public class ClaimListController {
	
	private static ClaimList claimList = null;
	
	//returns the application claimlist and adds listener
	public static ClaimList getClaimList() {
		if(claimList == null){
			claimList = FileManager.getSaver().loadClaimLFromFile();
			claimList.addListener(new Listener(){
				@Override
				public void update() {
					save();
				
				}
			});
		}
		return claimList;
	}
	
	//adds claim to claimlist, and adds a listener to claim
	public static void addClaimToClaimList(Claim claim){
		claim.addListener(new Listener() {
			@Override
			public void update() {
				save();
				
			}
		});
		getClaimList().addClaim(claim);
		ExpenseList el;
		claim.getExpenseList().addListener(new Listener() {
			
			@Override
			public void update() {
				save();
				
			}
		});
	}
	//adds expense to claim and adds a listener to the expense
	public static void addExpense(Expense expense, Claim claim){
		claim.addExpense(expense);
		expense.addListener(new Listener() {
			
			@Override
			public void update() {
				save();
				
			}
		});
	}
	
	public static void save(){
		FileManager.getSaver().saveClaimLInFile(getClaimList());
	}


}
