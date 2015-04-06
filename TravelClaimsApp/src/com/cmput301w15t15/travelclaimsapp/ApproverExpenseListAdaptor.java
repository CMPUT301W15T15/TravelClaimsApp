
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * 	Custom ArrayAdaptor for displaying the expenselist within a claim in the submitted claimlist.
 *  
 *  Builds a Array list to hold Expenses in and a ViewHolder to display them.
 *  	
 */
public class ApproverExpenseListAdaptor extends ArrayAdapter<Expense> {

	public ApproverExpenseListAdaptor(Context context, int resource,
			ArrayList<Expense> expenses) {
		super(context, resource, expenses);
		this.context = context;
		this.resource = resource;
		this.expenseList = expenses;
	}
	
	private Context context;
	private int resource;
	private ArrayList<Expense> expenseList;
	private ViewHolder viewHolder;
	private Expense expense;
	


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if(rowView == null){
		    viewHolder = new ViewHolder();
		    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		    rowView = inflater.inflate(resource, parent, false);

		    viewHolder.expenseCurrency = (TextView) rowView.findViewById(R.id.expenseAdaptor_currency_approve);
		    viewHolder.expenseCategory= (TextView) rowView.findViewById(R.id.expenseAdaptor_category_approve);
		    viewHolder.expenseAmount = (TextView) rowView.findViewById(R.id.expenseAdaptor_amount_approve);
		    viewHolder.expenseDate = (TextView) rowView.findViewById(R.id.expenseAdaptor_date_approve);
		    viewHolder.expenseName = (TextView) rowView.findViewById(R.id.expenseAdaptor_Name_approve);
		    //viewHolder.expenseFlag = (ImageView) rowView.findViewById(R.id.expenseAdaptor_flag_approve);
		    viewHolder.expenseMap = (ImageView) rowView.findViewById(R.id.expenseAdaptor_map_approve);
		    viewHolder.expenseDescription = (LinearLayout) rowView.findViewById(R.id.expenseAdaptor_description_approve);
		    viewHolder.expenseImageAttach = (ImageView) rowView.findViewById(R.id.expenseAdaptor_attachment_approve);
		    rowView.setTag(viewHolder);

		}else{
		    viewHolder = (ViewHolder) rowView.getTag();
		}
		
		//viewHolder.expenseFlag.setTag(position);
		expense = expenseList.get(position); 
		viewHolder.expenseCurrency.setText(expense.getCurr());
		viewHolder.expenseCategory.setText(expense.getCat());
		viewHolder.expenseAmount.setText(expense.getCost().toString());
		viewHolder.expenseName.setText(expense.getName());
		
		//Set flag to invisible approver does not need to see it 
		//viewHolder.expenseFlag.setVisibility(android.view.View.INVISIBLE);
		
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

		SimpleDateFormat defaultExpenseDate = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		if(expense.getDate() == null){
		    viewHolder.expenseDate.setText("No Date");
		}else{
		    viewHolder.expenseDate.setText(defaultExpenseDate.format(expense.getDate()));
		}

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
        	viewHolder.expenseImageAttach.setVisibility(android.view.View.VISIBLE);
        	viewHolder.expenseImageAttach.setClickable(false);
        }else{
        	viewHolder.expenseImageAttach.setVisibility(android.view.View.INVISIBLE);
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
		//public ImageView expenseFlag;
		public TextView expenseName;
		public TextView expenseDate;
		public TextView expenseAmount;
		public TextView expenseCategory;
		public TextView expenseCurrency;
    }

	
	
	
}
