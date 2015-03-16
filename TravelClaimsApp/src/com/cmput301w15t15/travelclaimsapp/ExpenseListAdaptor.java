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
package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Filter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * ExpenseListAdaptor is used to show expense values in th
 * @author bzhou2
 *
 */
public class ExpenseListAdaptor extends ArrayAdapter<Expense> {
	
	private Context context;
	private int resource;
	private ArrayList<Expense> expenseList;
	private ViewHolder viewHolder;
	private Filter tagFilter;
	
	
	/**
	 * class constructor
	 * 
	 * @param context
	 * @param resource
	 * @param expenses
	 */
	public ExpenseListAdaptor(Context context, int resource, ArrayList<Expense> expenses) {
		super(context, resource, expenses);
		this.context = context;
		this.resource = resource;
		this.expenseList = expenses;
	}

	/**
	 *  
	 *  get format view for one row of expense list
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		Expense expense = expenseList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.expenseCurrency = (TextView) rowView.findViewById(R.id.expenseAdaptor_currency);
	        viewHolder.expenseCategory= (TextView) rowView.findViewById(R.id.expenseAdaptor_category);
	        viewHolder.expenseAmount = (TextView) rowView.findViewById(R.id.expenseAdaptor_amount);
	        viewHolder.expenseDate = (TextView) rowView.findViewById(R.id.expenseAdaptor_date);
 	        viewHolder.expenseName = (TextView) rowView.findViewById(R.id.expenseAdaptor_Name);
	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.expenseCurrency.setText(expense.getCurr());
        viewHolder.expenseCategory.setText(expense.getCat());
        viewHolder.expenseAmount.setText(expense.getCost().toString());
        viewHolder.expenseName.setText(expense.getName());
        
        SimpleDateFormat defaultExpenseDate = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
        if(expense.getDate() == null){
        	viewHolder.expenseDate.setText("No Date");
        }else{
        	viewHolder.expenseDate.setText(defaultExpenseDate.format(expense.getDate()));
        }
        
		return rowView;
	}
	

	/**
	 * 
	 * all format view for expenselist view
	 * @author bzhou2
	 *
	 */
	private static class ViewHolder {
        public TextView expenseName;
		public TextView expenseDate;
		public TextView expenseAmount;
		public TextView expenseCategory;
		public TextView expenseCurrency;
    }
	
	
/**************
	public ExpenseListAdaptor(Context context,
			int resource, ExpenseList expenseList) {
		super(context, resource, expenseList.toArrayList());
		// TODO Auto-generated constructor stub
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
*************/


}
