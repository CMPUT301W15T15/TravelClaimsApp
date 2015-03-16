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
	

	/*public Expense(String expenseName, String expenseDes, String expenseCurr, String expenseCat, Date expenseDate, Integer expenseCost) {
		this.expenseName = expenseName;
		this.expenseDes = expenseDes;
		this.expenseDate = expenseDate;
		this.expenseCost = expenseCost;
		this.expenseCurr = expenseCurr;
		this.expenseCat = expenseCat;
		this.listeners = new ArrayList<Listener>();
	}*/
	

	public Expense(String expenseName){
		this.expenseName = expenseName;
		this.expenseCurr = null;
		this.expenseCat = null;
		this.listeners = new ArrayList<Listener>();
	}

	public int getValue(){
		return price;
	}
	
	public void setValue(int value){
		this.price=value;
	}
	
	public void addFlag(){
		this.flag=1;
		notifyListeners();
		// TODO
	}
	
	public int getFlag(){
		return this.flag;
	}
	
	public void setName(String expenseName) {
		this.expenseName = expenseName;
		notifyListeners();
	}
	
	public String getName() {
		return this.expenseName;
	}
	
	public void setCost(int expenseCost){
		this.expenseCost = expenseCost;
		notifyListeners();
	}
	
	public Integer getCost()
	{
		return this.expenseCost;
	}
	
	public void setCurr(String expenseCurr)
	{
		this.expenseCurr = expenseCurr;
		notifyListeners();
	}
	
	public String getCurr(){
		return this.expenseCurr;
	}
	
	public void setCat(String expenseCat){
		this.expenseCat = expenseCat;
		notifyListeners();
	}
	public String getCat(){
		return this.expenseCat;
	}

	public void removeFlag()
	{
		this.flag=0;
		notifyListeners();
		// TODO Auto-generated method stub
		//return false;
	}
	
//	public boolean emptyFlag(){
//		return true;
//	}


	public void takeReceipt(Uri expenseReceipt) {
		this.expenseReceipt = expenseReceipt;
	}
	

	public Uri getReceipt() {
		// TODO Auto-generated method stub
		return this.expenseReceipt;
	}
	
	public void deleteReceipt() {
		this.expenseReceipt = null;
		
	}
	
	public boolean emptyReceipt() {
		return false;
	}

	public Date getDate() {
		return this.expenseDate;
	}

	public void setDate(Date expenseDate) {
		this.expenseDate = expenseDate;
		notifyListeners();
	}

	public void setDes(String expenseDes) {
		this.expenseDes = expenseDes;
		notifyListeners();
	}

	public String getDes() {
		return this.expenseDes;
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

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}
	
	
}
