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


public class ExpenseList {
	
	private int totalValue;
	
	protected transient ArrayList<Listener> listeners;
	protected ArrayList<Expense> expenseList;
	
	public ExpenseList(){
		this.expenseList = new ArrayList<Expense>();
		this.listeners = new ArrayList<Listener>();
	}
	
	public void addExpense(Expense expense1) {
		this.expenseList.add(expense1);
		notifyListeners();
	}
	
	public Collection<Expense> getExpenseList() {
		return this.expenseList;
	
	}
	
	public Expense getExpenseByIndex(int index){
		return this.expenseList.get(index);
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
		this.expenseList.remove(expense1);
		notifyListeners();
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

	public void sort() {
		// TODO Auto-generated method stub
		Collections.sort(expenseList, new ExpenseListComparator());
	}
	
}
