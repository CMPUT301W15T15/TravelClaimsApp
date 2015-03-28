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
 * 
 * @author Henry
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

	/**
	 * expense constructor
	 * @param expenseName
	 */
	public Expense(String expenseName){
		this.expenseName = expenseName;
		this.expenseCurr = null;
		this.expenseCat = null;
		this.listeners = new ArrayList<Listener>();
	}

	/**
	 * get one expense value (same as getCost) (may change soon)
	 * @return
	 */
	public int getValue(){
		return price;
	}
	
	/**
	 * set one expense cost (same as setCost) (may change soon)
	 * @param value
	 */
	public void setValue(int value){
		this.price=value;
	}
	
	/**
	 * set expense flagged
	 */
	public void addFlag(){
		this.flag=1;
		notifyListeners();
		// TODO
	}
	
	/**
	 * get flag status
	 * @return
	 */
	public int getFlag(){
		return this.flag;
	}
	
	/**
	 * set expense name
	 * @param expenseName
	 */
	public void setName(String expenseName) {
		this.expenseName = expenseName;
		notifyListeners();
	}
	
	/**
	 * get expense name
	 * @return
	 */
	public String getName() {
		return this.expenseName;
	}
	
	/**
	 * set cost of a expense (same as setValue) (may change soon)
	 * @param expenseCost
	 */
	public void setCost(int expenseCost){
		this.expenseCost = expenseCost;
		notifyListeners();
	}
	
	/**
	 * get expense cost (same as getValue) (may change soon)
	 * @return expenseCost
	 */
	public Integer getCost()
	{
		return this.expenseCost;
	}
	
	/**
	 * set expense cost currency
	 * @param expenseCurr
	 */
	public void setCurr(String expenseCurr)
	{
		this.expenseCurr = expenseCurr;
		notifyListeners();
	}
	
	/**
	 * get expense currency
	 * @return expenseCurr(String)
	 */
	public String getCurr(){
		return this.expenseCurr;
	}
	
	/**
	 * set expense category
	 * @param expenseCat
	 */
	public void setCat(String expenseCat){
		this.expenseCat = expenseCat;
		notifyListeners();
	}
	
	/**
	 * get expense category
	 * @return
	 */
	public String getCat(){
		return this.expenseCat;
	}

	/**
	 * remove one expense flag
	 * 
	 */
	public void removeFlag()
	{
		this.flag=0;
		notifyListeners();
		// TODO Auto-generated method stub
		//return false;
	}
	
	/**
	 * return true if expense has no flag
	 * @return
	 */
	public boolean emptyFlag(){
		if(this.flag==0){
			return true;
		}
		else{
			return false;
		}
	}


	/**
	 * set expense receipt
	 * @param expenseReceipt
	 */
	public void takeReceipt(Uri expenseReceipt) {
		this.expenseReceipt = expenseReceipt;
	}
	

	/**
	 * get expense receipt
	 * @return expenseReceipt (Uri)
	 */
	public Uri getReceipt() {
		// TODO Auto-generated method stub
		return this.expenseReceipt;
	}
	
	/**
	 * delete recepit from one expense
	 * 
	 */
	public void deleteReceipt() {
		this.expenseReceipt = null;
		
	}
	
	/**
	 * return true if expense has no receipt
	 * @return boolean
	 */
	public boolean emptyReceipt() {
		if (this.expenseReceipt == null){
		return true;
		}
		else{
			return false;
		}
	}

	/**
	 * get expense start date
	 * @return
	 */
	public Date getDate() {
		return this.expenseDate;
	}

	/**
	 * set expense date
	 * @param expenseDate
	 */
	public void setDate(Date expenseDate) {
		this.expenseDate = expenseDate;
		notifyListeners();
	}

	/**
	 * set expense description
	 * @param expenseDes
	 */
	public void setDes(String expenseDes) {
		this.expenseDes = expenseDes;
		notifyListeners();
	}

	/**
	 * get expense description
	 * @return expenseDes (string)
	 */
	public String getDes() {
		return this.expenseDes;
	}
	

	public GeoLocation getGeoLocation() {
		// TODO Auto-generated method stub
		return this.geoLocation;
	}
	
	public void setGeoLocation(GeoLocation gl) {
		this.geoLocation = gl;
		
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

	public void setFlag(int value) {
		// TODO Auto-generated method stub
		this.flag=value;
		notifyListeners();
	}

	

	
	
	
}
