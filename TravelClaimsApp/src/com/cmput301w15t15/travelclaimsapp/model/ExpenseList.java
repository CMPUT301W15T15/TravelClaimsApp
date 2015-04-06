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

/**
 * @author Henry
 * This the the expenseList model class that handles the expense List
 *
 */
public class ExpenseList {
	protected transient ArrayList<Listener> listeners;
	protected ArrayList<Expense> expenseList;
	
	/**
	 * expenseList contructor
	 * 
	 */
	public ExpenseList(){
		this.expenseList = new ArrayList<Expense>();
		this.listeners = new ArrayList<Listener>();
	}
	
	/**
	 * Add expense into expense list
	 * @param expense1 	the Expense to add to ExpenseList
	 */
	public void addExpense(Expense expense1) {
		this.expenseList.add(expense1);
		notifyListeners();
	}
	
	/**
	 * get Expense by index (ArrayList way)
	 * @param index		the index to get expense by 
	 * @return			the Expense at the given index
	 */
	public Expense getExpenseByIndex(int index){
		return this.expenseList.get(index);
	}
	
	/**
	 * Get expense by expense name.<br>
	 * 
	 * If Expense is not in ExpenseList then returns null
	 * 
	 * @param expenseName		the String name to search for
	 * @return					the Expense with passed name 
	 */
	public Expense getExpense(String expenseName) {
		if (expenseList==null){
			return null;
		}
		else{
			for (int i=0; i<expenseList.size();i++){
				if (expenseList.get(i).getName().toString().equals(expenseName)){
					return expenseList.get(i);
				}
			}
		}
		return null;
	}
	
	/**
	 * Remove expense by expense name
	 * @param expense1  the String name of Expense to remove from ExpenseList
	 */
	public void removeExpense(Expense expense1) {
		this.expenseList.remove(expense1);
		notifyListeners();
	}
	
	/**
	 * Get the size of expense list
	 * @return	 the number of Expense objects in ExpenseList
	 */
	public int size() {
		return this.expenseList.size();
	}
	
	/**
	 * Return expense list (ArrayList mode)
	 * @return expenseList the ExpenseList as a ArrayList of Expense objects
	 */
	public ArrayList<Expense> toArrayList(){
		return this.expenseList;
		
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
	

	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}
}
