package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class ExpenseList {
	private Expense expense;
	protected transient ArrayList<Listener> listeners;
	
	
	private ArrayList<Expense> ExpenseList;
	
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
	
	
	public ArrayList<Expense> getExpenseList(){
		return null;
		
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
