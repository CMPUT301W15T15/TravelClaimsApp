package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class ExpenseList {
	
	protected transient ArrayList<Listener> listeners;
	
	
	private ArrayList<Expense> expenseList;
	
	public ExpenseList(){
		this.expenseList = new ArrayList<Expense>();
		this.listeners = new ArrayList<Listener>();
	}
	
	public void addExpense(Expense expense1) {
		// TODO Auto-generated method stub
		
	}
	
	public Expense getExpense(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeExpense(Expense expense1) {
		// TODO Auto-generated method stub
		
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public ArrayList<Expense> toArrayList(){
		return this.expenseList;
		
	}
	
	public ExpenseList noImageExpenseList(){
		return null;
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
