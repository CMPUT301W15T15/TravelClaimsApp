package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ClaimListAdaptor extends ArrayAdapter<Claim> {

	public ClaimListAdaptor(Context context, int resource, ArrayList<Claim> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}



}
