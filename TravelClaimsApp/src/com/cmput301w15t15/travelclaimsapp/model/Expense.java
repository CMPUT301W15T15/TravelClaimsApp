package com.cmput301w15t15.travelclaimsapp.model;


import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;

public class Expense implements Listenable{
	private int price;
	protected transient ArrayList<Listener> listeners;
	private String description;
	
	public Expense(String string) {
		this.listeners = new ArrayList<Listener>();
	}

	public int getValue(){
		return price;
	}
	
	public void setValue(int value){
		this.price=value;
	}
	
	public void addFlag(){
		// TODO
	}
	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeFlag()
	{

		// TODO Auto-generated method stub
		//return false;
	}
	
	public boolean emptyFlag(){
		return true;
	}


	public void takeReceipt() {
		// TODO Auto-generated method stub
		
	}
	

	public Uri getReceipt() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteReceipt() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean emptyReceipt() {
		// TODO Auto-generated method stub
		return false;
	}

	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setDescription(String string) {
		this.description = string;
		
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}


	public Uri takeAPhoto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAPhoto() {
		// TODO Auto-generated method stub
		
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
