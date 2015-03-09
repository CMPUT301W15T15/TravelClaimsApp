package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class ExpenseList implements Listenable{
	
	protected transient ArrayList<Listener> listeners;
	private ArrayList<Expense> expenseList;
	
	public ExpenseList(){
		this.expenseList = new ArrayList<Expense>();
		this.listeners = new ArrayList<Listener>();
	}
	
	public void addExpense(Expense expense) {
		this.expenseList.add(expense);
		
	}
	
	public Expense getExpense(String string) {
		if (expenseList==null){
			return null;
		}
		else{
			for (int i=0; i<expenseList.size();i++){
				if (expenseList.get(i).toString().equals(string)){
					return expenseList.get(i);
				}
			}
		}
		return null;
	}

	public void removeExpense(Expense expense) {
		this.expenseList.remove(expense);
		
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return this.expenseList.size();
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
