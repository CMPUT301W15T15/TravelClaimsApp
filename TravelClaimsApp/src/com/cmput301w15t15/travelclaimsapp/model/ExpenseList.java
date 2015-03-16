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
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.ExpenseListComparator;


/**
 * @author Henry
 * This the the expenseList model class that handles the expense List
 *
 */
public class ExpenseList {
	
	private int totalValue;
	
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
	 * add expense into expense list
	 * @param expense1
	 */
	public void addExpense(Expense expense1) {
		this.expenseList.add(expense1);
		notifyListeners();
	}
	
	/**
	 * get expense list (Collection mode)
	 * @return expenseList
	 */
	public Collection<Expense> getExpenseList() {
		return this.expenseList;
	
	}
	
	/**
	 * get Expense by index (ArrayList way)
	 * @param index
	 * @return
	 */
	public Expense getExpenseByIndex(int index){
		return this.expenseList.get(index);
	}
	
	/**
	 * get expense by expense name
	 * @param expenseName
	 * @return
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
	 * remove expense by expense name
	 * @param expense1
	 */
	public void removeExpense(Expense expense1) {
		// TODO Auto-generated method stub
		this.expenseList.remove(expense1);
		notifyListeners();
	}
	
	/**
	 * get the size of expense list
	 * @return
	 */
	public int size() {
		// TODO Auto-generated method stub
		return this.expenseList.size();
	}
	
	
	/**
	 * return expense list (ArrayList mode)
	 * @return expenseList (ArrayList<Expense>)
	 */
	public ArrayList<Expense> toArrayList(){
		return this.expenseList;
		
	}
	
	/**
	 * get expenseList which contains the expenses without receipt
	 * @return
	 */
	public ExpenseList noImageExpenseList(){
		return null;
	}

	/**
	 * Listener
	 */
	public void notifyListeners() {
		for (Listener listener : listeners) {
			listener.update();
		}
	}
	
	/**
	 * add Listener
	 * @param listener
	 */
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	/**
	 * delete listener
	 * @param listener
	 */
	public void deleteListener(Listener listener){
		listeners.remove(listener);
	}
	
	/**
	 * get total cost of all expenses in the expense list
	 * @return totalValue (int)
	 */
	public int getTotalValue(){
		this.totalValue=0;
		for (int i=0;i<this.expenseList.size();i++){
			this.totalValue=this.totalValue+expenseList.get(i).getValue();
		}
		return this.totalValue;
	}
	



	/**
	 * set listener
	 */
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}

	/**
	 * sort expenseList: sort expense by date
	 */
	public void sort() {
		// TODO Auto-generated method stub
		Collections.sort(expenseList, new ExpenseListComparator());
	}
	
}
