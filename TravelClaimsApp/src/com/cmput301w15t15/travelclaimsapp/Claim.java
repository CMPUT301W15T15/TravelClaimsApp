package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;
import java.util.Map;

public class Claim {

	protected String Comment;
	protected String Approver;
	protected boolean Editable=true;

	

	public Claim(String string) {
		// TODO Auto-generated constructor stub
	}

	public void setDescription(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setStartDate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public void addExpense(Expense expense1) {
		// TODO Auto-generated method stub
		
	}

	public void removeExpense(Expense expense1) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Expense> getExpenseList(){
		return null;
		
	}
	public ArrayList<String> getTagList(){
		return null;
		
	}
	public void addTag(String tag){
		
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public Expense getExpense(String string) {
		// TODO Auto-generated method stub
		return null;
	}




	public void removeTag() {
		// TODO Auto-generated method stub
		
	}

	public void renameTag(String string) {
		// TODO Auto-generated method stub
		
	}

	public void addDestination(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, String> getDestinationList() {
		
		return null;
	}
	public String getClaimStatus() {
		return null;
	}
	public String getTag()
	{

		// TODO Auto-generated method stub
		return null;
	}


	public void deleteDestination(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setClaimStatus(String string) {
		// TODO Auto-generated method stub

	}
<<<<<<< HEAD
	
	public void setComment(String string) {
		// TODO Auto-generated method stub
		}

	public String getComment() {
		// TODO Auto-generated method stub
		return Comment;
	}

	public void setApprover(String approverName) {
		// TODO Auto-generated method stub
		this.Approver = approverName;
	}

	public String getApprover(){
		return Approver;	
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getEditable() {
		// TODO Auto-generated method stub
		return false;
	}
=======

	public String getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}
 
>>>>>>> 33169a18112c3c3187b048b2b5082bd7042b041b

}
