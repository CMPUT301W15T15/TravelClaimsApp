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

		    viewHolder.expenseCurrency = (TextView) rowView.findViewById(R.id.expenseAdaptor_currency);
		    viewHolder.expenseCategory= (TextView) rowView.findViewById(R.id.expenseAdaptor_category);
		    viewHolder.expenseAmount = (TextView) rowView.findViewById(R.id.expenseAdaptor_amount);
		    viewHolder.expenseDate = (TextView) rowView.findViewById(R.id.expenseAdaptor_date);
		    viewHolder.expenseName = (TextView) rowView.findViewById(R.id.expenseAdaptor_Name);
		    viewHolder.expenseFlag = (ImageView) rowView.findViewById(R.id.expenseAdaptor_flag);
		    viewHolder.expenseMap = (ImageView) rowView.findViewById(R.id.expenseAdaptor_map);
		    viewHolder.expenseDescription = (LinearLayout) rowView.findViewById(R.id.expenseAdaptor_description);
		    viewHolder.expenseImageAttach = (ImageView) rowView.findViewById(R.id.expenseAdaptor_attachment);
		    rowView.setTag(viewHolder);

		}else{
		    viewHolder = (ViewHolder) rowView.getTag();
		}
		
		viewHolder.expenseFlag.setTag(position);
		expense = expenseList.get(position); 
		viewHolder.expenseCurrency.setText(expense.getCurr());
		viewHolder.expenseCategory.setText(expense.getCat());
		viewHolder.expenseAmount.setText(expense.getCost().toString());
		viewHolder.expenseName.setText(expense.getName());
		
		//Set flag to invisible approver does not need to see it 
		viewHolder.expenseFlag.setVisibility(android.view.View.INVISIBLE);
		
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
		
		if(expense.getReceipt() == null){
        	viewHolder.expenseImageAttach.setVisibility(android.view.View.INVISIBLE);
        	viewHolder.expenseImageAttach.setClickable(false);
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
		public ImageView expenseFlag;
		public TextView expenseName;
		public TextView expenseDate;
		public TextView expenseAmount;
		public TextView expenseCategory;
		public TextView expenseCurrency;
    }

	
	
	
}
