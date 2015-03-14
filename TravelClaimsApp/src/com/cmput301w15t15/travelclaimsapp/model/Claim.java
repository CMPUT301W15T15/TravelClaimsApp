package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import android.R;

public class Claim implements Listenable{
	private Claim claim;
	private String claimName;
	private Date startDate;
	private Date endDate;
	private DestinationList destinationList;
	private Tag tag;
	private String status;
	private String comment;
	private int totalValue=0;
	private int approveId;
	private int claimantId;
	private ExpenseList expenseList;
	private TagList tagList;
	protected transient ArrayList<Listener> listeners;
	private final String INPROGRESS = "In Progress";
	private final String SUBMITTED = "Submitted";
	private final String RETURNED = "Returned";
	private final String APPROVED = "Approved";

	public Claim(String string) {
		// TODO Auto-generated constructor stub
		this.claimName=string;
		this.listeners = new ArrayList<Listener>();
		this.expenseList = new ExpenseList();
		this.status = "In Progress";
		this.destinationList = new DestinationList();
		this.tagList = new TagList();
		
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
		
		this.startDate = date;
		notifyListeners();	
		
	}
	

	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		this.endDate=date;
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
		notifyListeners();
	}

	public void removeTag(String tagName){
		this.tag=new Tag(tagName);
		if (tagList.contains(tagName)){
			tagList.removeTag(this.tag);
		}
		notifyListeners();
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
		notifyListeners();
	}

	public boolean isEditable() {
		if(claim.getClaimStatus().equals(INPROGRESS) || claim.getStatus().equals(RETURNED)){
			return true;
		}
		return false;
		
	}

	
	public void setComment(String string) {
		// TODO Auto-generated method stub
		this.comment=string;
		notifyListeners();
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
		notifyListeners();
		
	}

	public void removeExpense(Expense expense)
	{

		// TODO Auto-generated method stub
		this.expenseList.removeExpense(expense);
		notifyListeners();
	}

	public Expense getExpense(int position)
	{

		// TODO Auto-generated method stub
		return this.expenseList.getExpenseByIndex(position);
			
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


	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}

	


}
