package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ExpenseListAdaptor extends ArrayAdapter<Expense> {

	public ExpenseListAdaptor(Context context,
			int resource, ArrayList<Expense> expenseList) {
		super(context, resource, expenseList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}

}
