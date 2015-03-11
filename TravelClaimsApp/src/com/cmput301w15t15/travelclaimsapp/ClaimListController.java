package com.cmput301w15t15.travelclaimsapp;

import java.io.IOException;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.DestinationList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Listener;


/**
 * Singleton class used for getting static application ClaimList
 * and adding claims to ClaimList
 */
public class ClaimListController {
	
	private static ClaimList claimList = null;
	

	/**
	 * Returns the global application claimList
	 * 
	 * If claimList is null it will load the claimList from the android file system
	 * and returns claimList
	 * 
	 * @return the application claimList
	 */
	static public ClaimList getClaimList() {
		if(claimList == null){
			claimList = FileManager.getSaver().loadClaimLFromFile();
			claimList.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
					
				}
			});
			//add a listener to each claim in loaded claimlist
			for(Claim claim : claimList.toArrayList()){
				claim.setListeners(); 
				addClaimListeners(claim);
			}
			return claimList;
		}
		return claimList;
	}
	
	
	/**
	 * Uses ClaimList.addClaim to add claim with listener to claimList. 
	 * Also adds a listener to claims expenselist
	 * 
	 * @param claim claim to be added to claimlist 
	 * @throws IOException 
	 */
	public static void addClaimToClaimList(Claim claim) throws IOException{
		addClaimListeners(claim);
		getClaimList().addClaim(claim);
	}
	
	/**
	 * Uses Claim.addExpense() to add a expense with listener to claim
	 * 
	 * @param expense	the Expense to be added to claim
	 * @param claim		the Claim to add expense to
	 */
	public static void addExpense(Expense expense, Claim claim){
		claim.addExpense(expense);
		expense.addListener(new Listener() { 
			@Override
			public void update() {
				save();
				
			}
		});
	}
	
	public static void addDestination(Destination dest, Claim claim){
		claim.getDestinationList().addDestination(dest);
		dest.addListener(new Listener() {
			
			@Override
			public void update() {
				save();
				
			}
		});
	}
	public static void deleteDestination(Destination dest, Claim claim){
		claim.getDestinationList().deleteDestination(dest);
		
	}
	
	
	/**
	 * Saves claimlist to file using FileManager class 
	 */
	public static void save(){
		FileManager.getSaver().saveClaimLInFile(getClaimList());
	}
	
	/**
	 * Adds listeners to the claim as well as the claims expenselist and expenses 
	 * 
	 * @param claim 	the Claim to add listeners to
	 */
	private static void addClaimListeners(Claim claim){
		//first add a listener to the claim
		claim.addListener(new Listener() {
			@Override
			public void update() {
				save();
				
			}
		});
		//next add listener to expenselist
		ExpenseList eList = claim.getExpenseList();
		eList.setListeners();
		eList.addListener(new Listener() {
			
			@Override
			public void update() {
				save();
				
			}
		});
		//next add a listener to each expense in the claim 
		for(Expense expense : eList.toArrayList()){
			expense.setListeners();
			expense.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
					
				}
			});
		}
		
		DestinationList dList = claim.getDestinationList();
		dList.setListeners();
		dList.addListener(new Listener() {
			
			@Override
			public void update() {
				save();
				
			}
		});
		for(Destination dest : dList.toArrayList()){
			dest.setListeners();
			dest.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
					
				}
			});
			
		}
		
	}


}
