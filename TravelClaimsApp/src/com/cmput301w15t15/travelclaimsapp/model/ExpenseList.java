package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;


public class ExpenseList {
	
	private int totalValue;
	
	protected transient ArrayList<Listener> listeners;
	
	
	private ArrayList<Expense> expenseList;
	
	public ExpenseList(){
		this.expenseList = new ArrayList<Expense>();
		this.listeners = new ArrayList<Listener>();
	}
	
	public void addExpense(Expense expense1) {
		// TODO Auto-generated method stub
		
		
	}
	
	public Expense getExpense(int position) {
		return this.expenseList.get(position);
	
	}
	
	public Expense getExpense(String expenseName) {
		if (expenseList==null){
			return null;
		}
		else{
			for (int i=0; i<expenseList.size();i++){
				if (expenseList.get(i).toString().equals(expenseName)){
					return expenseList.get(i);
				}
			}
		}
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
	
	public int getTotalValue(){
		this.totalValue=0;
		for (int i=0;i<this.expenseList.size();i++){
			this.totalValue=this.totalValue+expenseList.get(i).getValue();
		}
		return this.totalValue;
	}


	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}
	
}
