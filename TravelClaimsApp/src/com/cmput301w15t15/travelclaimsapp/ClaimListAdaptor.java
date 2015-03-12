package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
	        viewHolder.claimStatus = (TextView) rowView.findViewById(R.id.claimAdaptor_status);
	        viewHolder.claimStartDate = (TextView) rowView.findViewById(R.id.claimAdaptor_startDate);
	        viewHolder.destinations = (LinearLayout) rowView.findViewById(R.id.linearLayout_destinations);
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.claimName.setText(claim.getName());
        viewHolder.claimStatus.setText(claim.getClaimStatus());
        ArrayList<Destination> dests = claim.getDestinationList().toArrayList();
        if(dests.size()>0){
        	TextView tv1 = new TextView(context);
        	tv1.setText("Destinations:");
        	viewHolder.destinations.addView(tv1);
        	for(Destination dest : dests){
            	TextView tv = new TextView(context);
            	tv.setText(dest.getLocation());
            	viewHolder.destinations.addView(tv);
            }
        }
        
        
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
        public LinearLayout destinations;
        public LinearLayout amounts;
        public LinearLayout tags;
    
    }



}
