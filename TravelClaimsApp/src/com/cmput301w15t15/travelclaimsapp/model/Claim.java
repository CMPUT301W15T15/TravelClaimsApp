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

	/**
	 * Claim constructor
	 * @param string
	 */
	public Claim(String string) {
		// TODO Auto-generated constructor stub
		this.claimName=string;
		this.listeners = new ArrayList<Listener>();
		this.expenseList = new ExpenseList();
		this.status = "In Progress";
		this.destinationList = new DestinationList();
		this.tagList = new TagList();
		
	}

	
	/**
	 * return Claim Name
	 * @return
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return this.claimName;
	}
	
	
	/**
	 * set Claim Name
	 * @param name
	 */
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.claimName=name;
		notifyListeners();
	}

	
	/**
	 * return ExpenseList of one Claim
	 * @return ExpenseList
	 */
	public ExpenseList getExpenseList(){
		return expenseList;
	}
	


	/**
	 * set Claim Start Date
	 * @param StartDate
	 */
	public void setStartDate(Date date) {
		// TODO Auto-generated method stub
		
		this.startDate = date;
		notifyListeners();	
		
	}
	

	/**
	 * set Claim end Date
	 * @param EndDate
	 */
	public void setEndDate(Date date) {
		// TODO Auto-generated method stub
		this.endDate=date;
		notifyListeners();
		
	}
	
	
	/**
	 * get Claim Start Date
	 * @return startDate
	 */
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate;
	}





	
	/**
	 * get Claim End Date
	 * @return endDate
	 */
	public Date getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	
	
	/**
	 * set Claim Tag, no duplicate
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

	/**
	 * remove one Tag for a Claim
	 * @param tagName
	 */
	public void removeTag(String tagName){
		this.tag=new Tag(tagName);
		if (tagList.contains(tagName)){
			tagList.removeTag(this.tag);
		}
		notifyListeners();
	}
	
	
	/**
	 * get a list of all Tags for a Claim
	 * @return
	 */
	public TagList getTagList(){
		
		return tagList;
		
	}
	
	
	/**
	 * get total cost for one claim
	 * @return 
	 */
	public int getTotalValue(){
		
		return this.expenseList.getTotalValue();
		
	}



	/**
	 * get a list of destination for one claim
	 * @return
	 */
	public DestinationList getDestinationList() {
		
		return this.destinationList;
	}
	
	/**
	 * get one claim status
	 * @return claimStatus
	 */
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


	/**
	 * set claim status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status=status;
		notifyListeners();
	}

	/**
	 * check claim status and return true if status is INPROGRESS or RETURNED
	 * @return
	 */
	public boolean isEditable() {
		if(claim.getClaimStatus().equals(INPROGRESS) || claim.getStatus().equals(RETURNED)){
			return true;
		}
		return false;
		
	}

	
	/**
	 * set claim comment
	 * @param string
	 */
	public void setComment(String string) {
		// TODO Auto-generated method stub
		this.comment=string;
		notifyListeners();
	}

	/**
	 * get claim comment
	 * @return
	 */
	public String getComment() {
		// TODO Auto-generated method stub
		
		return this.comment;
	}

	/**
	 * set claim approver
	 * @param approverId
	 */
	public void setApprover(int approverId) {
		// TODO Auto-generated method stub
		this.approveId=approverId;
	
	}

	/**
	 * get claim approver ID
	 * @return approverId
	 */
	public int getApprover(){
		return this.approveId;	
	}

	/**
	 * get claim status
	 * @return claimStatus
	 */
	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}


	
	

	/**
	 * add one expense for one claim into its expenseList
	 * @param expense
	 */
	public void addExpense(Expense expense)
	{

		// TODO Auto-generated method stub
		this.expenseList.addExpense(expense);
		notifyListeners();
		
	}

	/**
	 * remove one expense for one claim from its expenseList
	 * @param expense
	 */
	public void removeExpense(Expense expense)
	{

		// TODO Auto-generated method stub
		this.expenseList.removeExpense(expense);
		notifyListeners();
	}

	/**
	 * return one expense of a claim 
	 * @param expenseName (string)
	 * @return expense (Expense)
	 */
	public Expense getExpense(String string)
	{

		// TODO Auto-generated method stub
		
		return this.expenseList.getExpense(string);
			
	}
	
	
	/* (non-Javadoc)
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#notifyListeners()
	 */
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#addListener(com.cmput301w15t15.travelclaimsapp.model.Listener)
	 */
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	/* (non-Javadoc)
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#deleteListener(com.cmput301w15t15.travelclaimsapp.model.Listener)
	 */
	public void deleteListener(Listener listener){
		listeners.remove(listener);
	}

	
	/**
	 * get claimant Id
	 * @return claimantID (int)
	 */
	public int getClaimantId()
	{
	
		return claimantId;
	}

	
	/**
	 * set Claimant ID
	 * @param claimantId
	 */
	public void setClaimantId(int claimantId)
	{
	
		this.claimantId = claimantId;
	}

//	/* 
//	 * 
//	 * @see java.lang.Object#toString()
//	 */
	public String toString(){
		return this.claimName;
	}


	/* (non-Javadoc)
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#setListeners()
	 */
	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}

	


}
