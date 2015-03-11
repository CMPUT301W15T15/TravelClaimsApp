package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor.ViewHolder;
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
	
	public ExpenseListAdaptor(Context context, int resource, ExpenseList expenses) {
		super(context, resource, expenses.toArrayList());
		this.context = context;
		this.resource = resource;
		this.expenseList = expenses.toArrayList();
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
			
	        viewHolder.claimName = (TextView) rowView.findViewById(R.id.claimAdaptor_display_name);
	        viewHolder.claimStatus = (TextView) rowView.findViewById(R.id.claimAdaptor_status);
	        viewHolder.claimStartDate = (TextView) rowView.findViewById(R.id.claimAdaptor_startDate);
	        
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.claimName.setText(claim.getName());
        viewHolder.claimStatus.setText(claim.getClaimStatus());
        
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy",Locale.CANADA);
        if(claim.getStartDate() == null){
        	viewHolder.claimStartDate.setText("");
        }else{
        	viewHolder.claimStartDate.setText(sdf.format(claim.getStartDate()));
        }
        
		return rowView;
	}
	

	private static class ViewHolder {
        public TextView claimName;
        public TextView claimStatus;
        public TextView claimStartDate;
    
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
