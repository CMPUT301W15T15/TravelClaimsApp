package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.activitys.ExpenseListViewActivity;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ExpenseListAdaptor extends ArrayAdapter<Expense> {

	public ExpenseListAdaptor(Context context,
			int resource, ExpenseList expenseList) {
		super(context, resource, expenseList);
		// TODO Auto-generated constructor stub
	}

	public ExpenseListAdaptor(ExpenseListViewActivity activity,
			int expenselistview, ExpenseList expenseList)
	{

		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}

}
