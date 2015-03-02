package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;





public class Claim {
	private String claimName;
	private Date startDate;
	private Date endDate;
	private Destination destinationList;
	private String tag;
	private String status;
	private String comment;
	private int totalValue;
	private int approveId;
	private int claimantId;
	private ExpenseList expenseList;
	protected boolean Editable=true;
	protected transient ArrayList<Listener> listeners;
	

	public Claim(String string) {
		// TODO Auto-generated constructor stub
		this.claimName=string;
		this.listeners = new ArrayList<Listener>();
		
	}

	public ExpenseList getExpenseList(){
		return expenseList;
	}
	

	public void setStartDate(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getTagList(){
		return null;
		
	}
	public void addTag(String tag){
		
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		notifyListeners();
		
	}

	public void setEndDate(int i, int j, int k) {
		// TODO Auto-generated method stub
		notifyListeners();
		
	}

	public void removeTag() {
		// TODO Auto-generated method stub
		notifyListeners();
		
	}

	public void renameTag(String string) {
		// TODO Auto-generated method stub
		notifyListeners();
		
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
	
	public void setComment(String string) {
		// TODO Auto-generated method stub
		}

	public String getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setApprover(String approverName) {
		// TODO Auto-generated method stub
	
	}

	public String getApprover(){
		return null;	
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setEndDate(Date endDate) {
		// TODO Auto-generated method stub
		
	}

	public Date getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public void addExpense(Expense e)
	{

		// TODO Auto-generated method stub
		
	}

	public void removeExpense(Expense e)
	{

		// TODO Auto-generated method stub
		
	}

	public Claim getExpense(String string)
	{

		// TODO Auto-generated method stub
		return null;
	}
	
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void deleteListener(Listener listener){
		listeners.remove(listener);
	}


}
