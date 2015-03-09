package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;





public class Claim implements Listenable{
	private Claim claim;
	private String claimName;
	private Date startDate;
	private Date endDate;
	private DestinationList destinationList;
	private Destination destination;
	private Tag tag;
	private String status;
	private String comment;
	private int totalValue=0;
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

	
	public String getName() {
		// TODO Auto-generated method stub
		return this.claimName;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.claimName=name;
		
		notifyListeners();
		
	}

	
	public ExpenseList getExpenseList(){
		return expenseList;
	}
	


	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
		this.startDate = startDate;
			
		
	}
	

	public void setEndDate(Date endDate) {
		// TODO Auto-generated method stub
		this.endDate=endDate;
		
		notifyListeners();
		
	}
	
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate;
	}





	
	public Date getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	
	
	/**
	 * @param tagName
	 */
	public void setTag(String tagName)
	{
		this.tag = new Tag(tagName);
		if (this.tagList.size()==0){
			this.tagList.addTag(tag);
		}
		else {
			if (!(this.tagList.contains(tagName))){
				this.tagList.addTag(tag);
			}
		}
		
	}

	public void removeTag(String tagName){
		this.tag=new Tag(tagName);
		if (tagList.contains(tagName)){
			tagList.removeTag(this.tag);
		}
	}
	
	
	public TagList getTagList(){
		
		return tagList;
		
	}
	
	
	public int getTotalValue(){
		
		return this.expenseList.getTotalValue();
		
	}



	public DestinationList getDestinationList() {
		
		return this.destinationList;
	}
	
	public String getClaimStatus() {
		return this.status;
	}
	
	
//commented this out as it had errors	
//	public Tag getTag(String tagName)
//	{
//		
//		// TODO Auto-generated method stub
//		
//		return this.tagList.getTag(tagName);
//	}


	public void setStatus(String status) {
		this.status=status;
		// TODO Auto-generated method stub
		
	}

	public boolean isEditable() {
		// TODO Auto-generated method stub
		
		return this.Editable;
	}

	
	public void setComment(String string) {
		// TODO Auto-generated method stub
		this.comment=string;
		
	}

	public String getComment() {
		// TODO Auto-generated method stub
		
		return this.comment;
	}

	public void setApprover(int approverId) {
		// TODO Auto-generated method stub
		this.approveId=approverId;
	
	}

	public int getApprover(){
		return this.approveId;	
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status;
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



	
	public String toString(){
		return this.claimName;
	}




}
