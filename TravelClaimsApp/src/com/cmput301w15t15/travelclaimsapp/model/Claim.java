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

/**
 * Object for tracking travel expenses
 * 
 * A Claim is a aggregate of  Expenses, Destinations and Tags
 *
 */
public class Claim implements Listenable{
	
	private Claim claim;
	private String claimName;
	private Date startDate;
	private Date endDate;
	private DestinationList destinationList;
	private Tag tag;
	private String status;
	private String comment;
	private String approverName;
	private String claimantName;
	private ExpenseList expenseList;
	private TagList tagList;
	private ArrayList<String> comments;
	protected transient ArrayList<Listener> listeners;
	public final static String INPROGRESS = "In Progress";
	public final static String SUBMITTED = "Submitted";
	public final static String RETURNED = "Returned";
	public final static String APPROVED = "Approved";

	/**
	 * Claim constructor 
	 * @param string  the name for the claim 
	 */
	public Claim(String string) {
		this.claimName=string;
		this.listeners = new ArrayList<Listener>();
		this.expenseList = new ExpenseList();
		this.status = "In Progress";
		this.destinationList = new DestinationList();
		this.tagList = new TagList();
		this.comments = new ArrayList<String>();
	}

	/**
	 * Returns Claim Name
	 * @return the claim name 
	 */
	public String getName() {
		return this.claimName;
	}
	
	/**
	 * Set Claim Name
	 * @param name the name to set claim name to 
	 */
	public void setName(String name) {
		this.claimName=name;
		notifyListeners();
	}

	/**
	 * Return ExpenseList of one Claim
	 * @return ExpenseList  the list of expenses for claim
	 */
	public ExpenseList getExpenseList(){
		return expenseList;
	}
	
	/**
	 * Set Claim Start Date
	 * @param StartDate		a Date to set claim start date to 
	 */
	public void setStartDate(Date date) {
		this.startDate = date;
		notifyListeners();	
	}
	
	/**
	 * Set Claim end Date
	 * @param EndDate	a date to set claim end date to 
	 */
	public void setEndDate(Date date) {
		this.endDate=date;
		notifyListeners();
	}
	
	/**
	 * Get Claim Start Date
	 * @return startDate	the Claim start date Date object
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Get Claim End Date
	 * @return endDate		the Claim end date Date object
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Add a Tag to Claim, no duplicate
	 * @param tagName	the name of the Tag to add to claim
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
	 * Remove one Tag for a Claim
	 * @param tagName		the name of the tag to remove from Claim
	 */
	public void removeTag(String tagName){
		this.tag=new Tag(tagName);
		if (tagList.contains(tagName)){
			tagList.removeTag(this.tag);
		}
		notifyListeners();
	}
	
	/**
	 * Get a list of all Tags for a Claim
	 * @return		returns a TagList containing all Tags for the claim
	 */
	public TagList getTagList(){
		return tagList;
	}
	
	/**
	 * Get a list of destination for one claim
	 * @return the DestinationList for the Claim
	 */
	public DestinationList getDestinationList() {
		return this.destinationList;
	}
	
	/**
	 * Gets the claim status
	 * @return claimStatus	 the current claim status String
	 */
	public String getClaimStatus() {
		return this.status;
	}
	
	/**
	 * Set claim status
	 * @param status	the String to set Claim status to 
	 */
	public void setStatus(String status) {
		this.status=status;
		notifyListeners();
	}

	/**
	 * Check claim status and return true if status is INPROGRESS or RETURNED
	 * @return	true if claim is editable and false otherwise
	 */
	public boolean isEditable() {
		if(claim.getClaimStatus().equals(INPROGRESS) || claim.getStatus().equals(RETURNED)){
			return true;
		}
		return false;
	}

	/**
	 * Set claim comment
	 * @param string	the String to set the Claim comment to 
	 */
	public void setComment(String string) {
		this.comment=string;
		notifyListeners();
	}

	/**
	 * Get claim comment
	 * @return		the String the Claim comment is
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Set claim approver
	 * @param approverName	the String to set Claim approver name as
	 */
	public void setApprover(String approverName) {
		this.approverName=approverName;
	}

	/**
	 * Get claim approver name
	 * @return approverName the String for claim approver name
	 */
	public String getApprover(){
		return this.approverName;	
	}

	/**
	 * Get claim status
	 * @return claimStatus	 the current Claim status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Add one expense for one claim into its expenseList
	 * @param expense the expense to add to Claim
	 */
	public void addExpense(Expense expense)
	{
		this.expenseList.addExpense(expense);
		notifyListeners();
	}

	/**
	 * Remove one expense for one claim from its expenseList
	 * @param expense	 the Expense to remove from Claim
	 */
	public void removeExpense(Expense expense)
	{
		this.expenseList.removeExpense(expense);
		notifyListeners();
	}

	/**
	 * Return one expense of a claim 
	 * @param expenseName (string)	the name of the expense to return
	 * @return expense (Expense)	the Expense from the claim
	 */
	public Expense getExpense(String string)
	{
		return this.expenseList.getExpense(string);	
	}
	
	/**
	 * Get the list of comments for this claim
	 * 
	 * @return 	the list of comments that have been added to this claim
	 */
	public ArrayList<String> getComments() {
		return comments;
	}
	
	/**
	 * Adds a comment to the list of comments for this claim
	 * 
	 * Adds comment to start of the list so that most recent comment is the item in list
	 * 
	 * @param comment the comment you want to add to the list 
	 */
	public void addComment(String comment){
		this.comments.add(0, comment);
		notifyListeners();
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
	 * gets String of claimant name
	 * @return claimantName (String)
	 */
	public String getClaimantName()
	{
	
		return claimantName;
	}

	
	/**
	 * sets String of claimant name
	 * @param claimantName
	 */
	public void setClaimantName(String claimantName)
	{
	
		this.claimantName = claimantName;
	}

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
	
	/**
	 * Adds a destination to Claim destination list
	 * 
	 * @param dest The {@link Destination} to add to the Claim
	 */
	public void addDestination(Destination dest){
		this.destinationList.addDestination(dest);
	}

	
}
