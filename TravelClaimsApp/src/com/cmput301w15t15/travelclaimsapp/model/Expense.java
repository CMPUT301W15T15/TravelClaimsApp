package com.cmput301w15t15.travelclaimsapp.model;


import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;

public class Expense implements Listenable{
	private int price;
	protected transient ArrayList<Listener> listeners;
	protected String expenseDes;
	protected String expenseName;
	protected Date expenseDate;
	protected Integer expenseCost;
	protected String expenseCurr;
	protected String expenseCat;
	protected Uri expenseReceipt;
	protected int flag=0;
	
	public Expense(String expenseName, String expenseDes, String expenseCurr, String expenseCat, Date expenseDate, Integer expenseCost) {
		this.expenseName = expenseName;
		this.expenseDes = expenseDes;
		this.expenseDate = expenseDate;
		this.expenseCost = expenseCost;
		this.expenseCurr = expenseCurr;
		this.expenseCat = expenseCat;
		this.listeners = new ArrayList<Listener>();
	}
	
	public Expense(String expenseName){
		this.expenseName = expenseName;
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
		// TODO
	}
	
	public int getFlag(){
		return this.flag;
	}
	
	public void setName(String expenseName) {
		this.expenseName = expenseName;
	}
	
	public String getName() {
		return this.expenseName;
	}
	
	public void setCost(int expenseCost){
		this.expenseCost = expenseCost;
	}
	
	public Integer getCost()
	{
		return this.expenseCost;
	}
	
	public void setCurr(String expenseCurr)
	{
		this.expenseCurr = expenseCurr;
	}
	
	public String getCurr(){
		return this.expenseCurr;
	}
	
	public void setCat(String expenseCat){
		this.expenseCat = expenseCat;		
	}
	public String getCat(){
		return this.expenseCat;
	}

	public void removeFlag()
	{
		this.flag=0;
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
		
	}

	public void setDes(String expenseDes) {
		this.expenseDes = expenseDes;
		
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
