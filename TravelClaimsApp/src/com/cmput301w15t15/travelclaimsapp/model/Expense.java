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

import android.net.Uri;

/**
 *This is the expense model class that creates the expense object
 */
public class Expense implements Listenable{
	protected int price;
	protected transient ArrayList<Listener> listeners;
	protected String expenseDes=null;
	protected String expenseName=null;
	protected Date expenseDate;
	protected int expenseCost=0;
	protected String expenseCurr;
	protected String expenseCat;
	protected Uri expenseReceipt;
	protected int flag=0;
	protected GeoLocation geoLocation;
	protected byte[] picture;
	protected String isRescale="";
	
	/**
	 * Expense constructor
	 * @param expenseName	the name to of the Expense to create 
	 */
	public Expense(String expenseName){
		this.expenseName = expenseName;
		this.expenseCurr = null;
		this.expenseCat = null;
		this.picture=null;
		this.listeners = new ArrayList<Listener>();
	}

	/**
	 * Get flag status (1 == flagged, 0 == not flagged)
	 * @return	the integer value of Expense flag
	 */
	public int getFlag(){
		return this.flag;
	}
	
	/**
	 * Set expense name
	 * @param expenseName	the name to set Expense name to
	 */
	public void setName(String expenseName) {
		this.expenseName = expenseName;
		//notifyListeners();
	}
	
	/**
	 * Get expense name
	 * @return	the expense name String
	 */
	public String getName() {
		return this.expenseName;
	}
	
	/**
	 * Set cost of a expense 
	 * @param expenseCost 	a integer to set cost to 
	 */
	public void setCost(int expenseCost){
		this.expenseCost = expenseCost;
		notifyListeners();
	}
	
	/**
	 * Get expense cost 
	 * @return expenseCost the integer Expense cost is set to 
	 */
	public Integer getCost()
	{
		return this.expenseCost;
	}
	
	/**
	 * Set expense cost currency
	 * @param expenseCurr	the currency code to set Expense currency to 
	 */
	public void setCurr(String expenseCurr)
	{
		this.expenseCurr = expenseCurr;
		notifyListeners();
	}
	
	/**
	 * Get expense currency
	 * @return expenseCurr String currency code of Expense
	 */
	public String getCurr(){
		return this.expenseCurr;
	}
	
	/**
	 * Set expense category
	 * @param expenseCat the String to set expense category to 
	 */
	public void setCat(String expenseCat){
		this.expenseCat = expenseCat;
		notifyListeners();
	}
	
	/**
	 * Get expense category
	 * @return		the String category of the Expense
	 */
	public String getCat(){
		return this.expenseCat;
	}

	/**
	 * Get expense start date
	 * @return		the expense Date for Expense
	 */
	public Date getDate() {
		return this.expenseDate;
	}

	/**
	 * Set expense date
	 * @param expenseDate  the Date value to set expense date to 
	 */
	public void setDate(Date expenseDate) {
		this.expenseDate = expenseDate;
		notifyListeners();
	}

	/**
	 * Set expense description
	 * @param expenseDes  the String to set Expense description to 
	 */
	public void setDes(String expenseDes) {
		this.expenseDes = expenseDes;
		//notifyListeners();
	}

	/**
	 * Get expense description
	 * @return expenseDes the String description for Expense 
	 */
	public String getDes() {
		return this.expenseDes;
	}
	
	/**
	 * Get the current GeoLocation for the expense
	 * 
	 * @return  the Expenses GeoLocation
	 */
	public GeoLocation getGeoLocation() {
		return this.geoLocation;
	}
	
	/**
	 * Set the GeoLocation for the current Expense
	 * 
	 * @param gl	the GeoLocation to set it to
	 */
	public void setGeoLocation(GeoLocation gl) {
		this.geoLocation = gl;
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

	/* (non-Javadoc)
	 * @see com.cmput301w15t15.travelclaimsapp.model.Listenable#setListeners()
	 */
	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}
	/**
	 * Set flag for this expense
	 * 
	 * @param value
	 */
	public void setFlag(int value) {
		this.flag=value;
		notifyListeners();
	}

	/**
	 * Get the picture attached to Expense
	 * 
	 * @return  the attached picture as a byte array
	 */
	public byte[] getPicture(){
		return this.picture;
	}
	/**
	 * Set the expense picture to passed byte array
	 * 
	 * @param picture	the byte array to set as expense picture
	 */
	public void takePicture(byte[] picture){
		this.picture=picture;
		notifyListeners();
	}

	/**
	 * Get the pictures current scale
	 * 
	 * @return	the scale of the picture
	 */
	public String getScale(){
		return this.isRescale;
	}
	
	/**
	 * Set the scale for the picture
	 * 
	 * @param scale		the scale to set the picture to 
	 */
	public void setScale(String scale){
		this.isRescale = scale;
		notifyListeners();
	}
}
