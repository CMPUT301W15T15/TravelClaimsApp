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

import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if(rowView == null){
		    viewHolder = new ViewHolder();
		    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		    rowView = inflater.inflate(resource, parent, false);

		    viewHolder.expenseCurrency = (TextView) rowView.findViewById(R.id.expenseAdaptor_currency);
		    viewHolder.expenseCategory= (TextView) rowView.findViewById(R.id.expenseAdaptor_category);
		    viewHolder.expenseAmount = (TextView) rowView.findViewById(R.id.expenseAdaptor_amount);
		    viewHolder.expenseDate = (TextView) rowView.findViewById(R.id.expenseAdaptor_date);
		    viewHolder.expenseName = (TextView) rowView.findViewById(R.id.expenseAdaptor_Name);
		    ViewHolder.expenseFlag = (ImageView) rowView.findViewById(R.id.expenseAdaptor_flag);
		    viewHolder.expenseMap = (ImageView) rowView.findViewById(R.id.expenseAdaptor_map);
		    viewHolder.expenseDescription = (LinearLayout) rowView.findViewById(R.id.expenseAdaptor_description);
		    viewHolder.expenseImageAttach = (ImageView) rowView.findViewById(R.id.expenseAdaptor_attachment);
		    rowView.setTag(viewHolder);

		}else{
		    viewHolder = (ViewHolder) rowView.getTag();
		}
		
		ViewHolder.expenseFlag.setTag(position);
		final Expense expense = expenseList.get(position);  ///<-- add I'm not sure what you named that class so change EXPENSELISTITEM to whatever you call it

		viewHolder.expenseCurrency.setText(expense.getCurr());
		viewHolder.expenseCategory.setText(expense.getCat());
		viewHolder.expenseAmount.setText(expense.getCost().toString());
		viewHolder.expenseName.setText(expense.getName());
		
		viewHolder.expenseDescription.removeAllViews();
		if(expense.getDes() != null){
			TextView tv = new TextView(context);
			tv.setText("Description: "+expense.getDes());
			tv.setTextColor(Color.WHITE);
			viewHolder.expenseDescription.addView(tv);
			viewHolder.expenseDescription.setVisibility(android.view.View.VISIBLE);
		}else{
			viewHolder.expenseDescription.setVisibility(android.view.View.INVISIBLE);
		}

		if (expense.getFlag()==0){
		    ViewHolder.expenseFlag.setImageResource(R.drawable.ic_toggle_star_outline);
		}
		else{
		    ViewHolder.expenseFlag.setImageResource(R.drawable.ic_toggle_star);
		}


		SimpleDateFormat defaultExpenseDate = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		if(expense.getDate() == null){
		    viewHolder.expenseDate.setText("No Date");
		}else{
		    viewHolder.expenseDate.setText(defaultExpenseDate.format(expense.getDate()));
		}

		ViewHolder.expenseFlag.setOnClickListener(new OnClickListener()
		{
		    @Override
			public void onClick(View v){
		    	ImageView i= (ImageView) v;
		    	int pos = (Integer) v.getTag();
		    	Expense expense1 = expenseList.get(pos);
		        expense1.setFlag(1-expense1.getFlag());
		        if (expense1.getFlag()==0){
				    i.setImageResource(R.drawable.ic_toggle_star_outline);
				}
				else{
				    i.setImageResource(R.drawable.ic_toggle_star);
				} 
		    }
		});
		if(expense.getGeoLocation() == null){
        	viewHolder.expenseMap.setVisibility(android.view.View.INVISIBLE);
        	viewHolder.expenseMap.setClickable(false);
        }else{
        	viewHolder.expenseMap.setVisibility(android.view.View.VISIBLE);
        }
		viewHolder.expenseMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = GeoLocationController.viewLocationIntent(context, expense.getGeoLocation());
				context.startActivity(intent);
			}
		});
		
		if(expense.getPicture() == null){
        	viewHolder.expenseImageAttach.setVisibility(android.view.View.INVISIBLE);
        }else{
        	viewHolder.expenseImageAttach.setVisibility(android.view.View.VISIBLE);
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
        public LinearLayout expenseDescription;
		public ImageView expenseMap;
		public ImageView expenseImageAttach;
		public static ImageView expenseFlag;
		public TextView expenseName;
		public TextView expenseDate;
		public TextView expenseAmount;
		public TextView expenseCategory;
		public TextView expenseCurrency;
    }


}
