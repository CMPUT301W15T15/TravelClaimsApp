package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.model.Claim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClaimListAdaptor extends ArrayAdapter<Claim> {

	private Context context;
	private int resource;
	private ArrayList<Claim> claimList;
	
	public ClaimListAdaptor(Context context, int resource, ArrayList<Claim> claims) {
		super(context, resource, claims);
		this.context = context;
		this.resource = resource;
		this.claimList = claims;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		ViewHolder viewHolder;
		Claim claim = claimList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.claimName = (TextView) rowView.findViewById(R.id.claimAdaptor_display_name);
	       
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.claimName.setText(claim.getName());

		return rowView;
	}
	

	private static class ViewHolder {
        public TextView claimName;
    
    }



}
