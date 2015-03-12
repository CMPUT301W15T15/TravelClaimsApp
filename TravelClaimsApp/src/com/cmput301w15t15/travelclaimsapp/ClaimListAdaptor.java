package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Tag;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 	Custom ArrayAdaptor for displaying the claimlist 
 *  
 */
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
	        viewHolder.tags = (LinearLayout) rowView.findViewById(R.id.LinearLayout_tags);
	        viewHolder.destinationTitle = (TextView) rowView.findViewById(id.claimAdaptor_destinations_title);
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.claimName.setText(claim.getName());
        viewHolder.claimStatus.setText(claim.getClaimStatus());
        //add destinations to viewholder
        ArrayList<Destination> dests = claim.getDestinationList().toArrayList();
        viewHolder.destinations.removeAllViews();
        if(dests.size()>0){
        	viewHolder.destinations.setVisibility(android.view.View.VISIBLE);
        	for(Destination dest : dests){
            	TextView tv = new TextView(context);
            	tv.setText(dest.getLocation()+": "+dest.getReason());
            	viewHolder.destinations.addView(tv);
            }
        }else{
        	viewHolder.destinations.setVisibility(android.view.View.INVISIBLE);	
        }
        //add tags to viewholder
        ArrayList<Tag> taglist = claim.getTagList().toArrayList();
        viewHolder.tags.removeAllViews();
        if(taglist.size()>0){
        	TextView tv1 = new TextView(context);
        	tv1.setText("Tags:");
        	viewHolder.tags.addView(tv1);
        	for(Tag t : taglist){
            	TextView tv = new TextView(context);
            	tv.setText(" "+t.getName());
            	viewHolder.tags.addView(tv);
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
        public TextView destinationTitle;
    
    }



}
