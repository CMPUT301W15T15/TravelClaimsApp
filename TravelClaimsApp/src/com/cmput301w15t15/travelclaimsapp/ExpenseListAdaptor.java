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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExpenseListAdaptor extends ArrayAdapter<Expense> {
	
	private Context context;
	private int resource;
	private ArrayList<Expense> expenseList;
	
	public ExpenseListAdaptor(Context context, int resource, ArrayList<Expense> expenses) {
		super(context, resource, expenses);
		this.context = context;
		this.resource = resource;
		this.expenseList = expenses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		ViewHolder viewHolder;
		Expense expense = expenseList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.expenseName = (TextView) rowView.findViewById(R.id.expenseAdaptor_currency);
	        viewHolder.expenseCategory= (TextView) rowView.findViewById(R.id.expenseAdaptor_category);
	        viewHolder.expenseAmount = (TextView) rowView.findViewById(R.id.expenseAdaptor_amount);
	        viewHolder.expenseDate = (TextView) rowView.findViewById(R.id.expenseAdaptor_date);
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.expenseName.setText(expense.getName());
        viewHolder.expenseCategory.setText(expense.getCat());
        viewHolder.expenseAmount.setText(expense.getCost().toString());
        
        
        SimpleDateFormat defaultExpenseDate = new SimpleDateFormat("mm/dd/yyyy",Locale.CANADA);
        if(expense.getDate() == null){
        	viewHolder.expenseDate.setText("Date Not Set");
        }else{
        	viewHolder.expenseDate.setText(defaultExpenseDate.format(expense.getDate()));
        }
        
		return rowView;
	}
	

	private static class ViewHolder {
        public TextView expenseDate;
		public TextView expenseAmount;
		public TextView expenseCategory;
		public TextView expenseName;
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
