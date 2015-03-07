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
	private TagList tagList;
	protected boolean Editable=true;
	protected transient ArrayList<Listener> listeners;
	

	public Claim(String string) {
		// TODO Auto-generated constructor stub
		this.claimName=string;
		this.listeners = new ArrayList<Listener>();
		this.expenseList = new ExpenseList();
		
	}

	public ExpenseList getExpenseList(){
		return expenseList;
	}
	


	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
		this.startDate = startDate;
		
		
		
	}
	
	public TagList getTagList(){
		
		return tagList;
		
	}


	public void setName(String name) {
		// TODO Auto-generated method stub
		this.claimName=name;
		
		notifyListeners();
		
	}

	public void setEndDate(Date endDate) {
		// TODO Auto-generated method stub
		this.endDate=endDate;
		
		notifyListeners();
		
	}


	public void renameTag(String newName) {
		// TODO Auto-generated method stub
		this.tagList.getTag(newName).rename(newName);
		
		notifyListeners();
		
	}


	public Map<String, String> getDestinationList() {
		
		return null;
	}
	
	public String getClaimStatus() {
		return null;
	}
	
	
	
	public Tag getTag(String tagName)
	{
		
		// TODO Auto-generated method stub
		
		return this.tagList.getTag(tagName);
	}


	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEditable() {
		// TODO Auto-generated method stub
		
		return this.Editable;
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
		return this.claimName;
	}



	
	public Date getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	

	public void addExpense(Expense expense)
	{

		// TODO Auto-generated method stub
		this.expenseList.addExpense(expense);
		
		
	}

	public void removeExpense(Expense expense)
	{

		// TODO Auto-generated method stub
		this.expenseList.removeExpense(expense);
		
	}

	public Expense getExpense(String string)
	{

		// TODO Auto-generated method stub
		return this.expenseList.getExpense(string);
			
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

	
	public int getClaimantId()
	{
	
		return claimantId;
	}

	
	public void setClaimantId(int claimantId)
	{
	
		this.claimantId = claimantId;
	}

	public void addTag(String tag1)
	{

		// TODO Auto-generated method stub
		
	}



}
