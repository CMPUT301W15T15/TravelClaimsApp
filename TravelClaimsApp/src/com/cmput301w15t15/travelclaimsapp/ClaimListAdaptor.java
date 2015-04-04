
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.Tag;

import android.R.color;
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
 *  
 *  	
 */

public class ClaimListAdaptor extends ArrayAdapter<Claim>{

	private Context context;
	private int resource;
	private ArrayList<Claim> claimList;
	private ViewHolder viewHolder;
	
	public ClaimListAdaptor(Context context, int resource, ArrayList<Claim> claims) {
		super(context, resource, claims);
		this.context = context;
		this.resource = resource;
		this.claimList = claims;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
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
	        viewHolder.amounts = (LinearLayout) rowView.findViewById(R.id.LinearLayout_amounts);
	        viewHolder.distColor = (TextView) rowView.findViewById(R.id.claim_color_code);
 	        rowView.setTag(viewHolder);
 	        

 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
	
        viewHolder.claimName.setText(claim.getName());
        //change text color of of status edittext depending on status
        if(claim.getClaimStatus().equals("Submitted")){
        	viewHolder.claimStatus.setTextColor(context.getResources().getColor(color.holo_red_light));
        }else{
        	viewHolder.claimStatus.setTextColor(context.getResources().getColor(color.primary_text_dark));
        }
        viewHolder.claimStatus.setText(claim.getClaimStatus());
        
        //add destinations to viewholder
        ArrayList<Destination> dests = claim.getDestinationList().toArrayList();
        
       
        if(dests.size()>0){
        	int[] colorDistance = GeoLocationController.getFirstDestinationColorCode(claim);
        	
            viewHolder.distColor.setBackgroundColor(colorDistance[0]);
    		viewHolder.distColor.setTextColor(colorDistance[0]);
            viewHolder.distColor.setText(Integer.toString(colorDistance[1]));
           
        	viewHolder.destinations.removeAllViews();
        	viewHolder.destinations.setVisibility(android.view.View.VISIBLE);
        	TextView tv1 = new TextView(context);
        	tv1.setTextColor(context.getResources().getColor(color.primary_text_dark));
        	tv1.setText("Destinations");
        	viewHolder.destinations.addView(tv1);
        	for(Destination dest : dests){
            	TextView tv = new TextView(context);
            	tv.setTextColor(context.getResources().getColor(color.primary_text_dark));
            	tv.setText(dest.getLocation()+": "+dest.getReason());
            	viewHolder.destinations.addView(tv);
            }
        }else{
        	viewHolder.destinations.removeAllViews();
        	viewHolder.destinations.setVisibility(android.view.View.INVISIBLE);	
        }
        //add tags to viewholder
        ArrayList<Tag> taglist = claim.getTagList().toArrayList();
        
        if(taglist.size()>0){
        	viewHolder.tags.removeAllViews();
        	TextView tv1 = new TextView(context);
        	tv1.setTextColor(context.getResources().getColor(color.primary_text_dark));
        	tv1.setText("Tags:");
        	viewHolder.tags.setVisibility(android.view.View.VISIBLE);
        	viewHolder.tags.addView(tv1);
        	for(int i = 0; i<taglist.size(); i++){
            	TextView tv = new TextView(context);
            	tv.setTextColor(context.getResources().getColor(color.primary_text_dark));
            	if(i == 0){
            		tv.setText(" "+taglist.get(i).getName());
            	}else{
            		tv.setText(", "+taglist.get(i).getName());
            	}
            	//tv.setText(" "+taglist.get(i).getName());
            	viewHolder.tags.addView(tv);
            }
        }else{
        	//if tagList is empty then set LinearLayout for tags to invisible
        	viewHolder.tags.removeAllViews();
        	viewHolder.tags.setVisibility(android.view.View.INVISIBLE);	
        }
        //Get amounts with currencies from expenselist
        ArrayList<Expense> expenseList = claim.getExpenseList().toArrayList();
        Map<String, Integer> totals = getAmountTotals(expenseList);
        
        //add amounts to viewholder 
        if(expenseList.size()>0){
        	viewHolder.amounts.removeAllViews();
        	viewHolder.amounts.setVisibility(android.view.View.VISIBLE);
        	//for each amount add a TextView to linearlayout with the currency 
        	//concatenated with amount 
        	for(String s : totals.keySet()){
				TextView tv = new TextView(context);
				tv.setTextColor(context.getResources().getColor(color.primary_text_dark));
				tv.setText(s +": "+totals.get(s));
				viewHolder.amounts.addView(tv);
            }
        }else{
        	viewHolder.amounts.removeAllViews();
        	viewHolder.amounts.setVisibility(android.view.View.INVISIBLE);	
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
        if(claim.getStartDate() == null){
        	viewHolder.claimStartDate.setVisibility(View.INVISIBLE);
        	viewHolder.claimStartDate.setHeight(0);
        }else{
        	viewHolder.claimStartDate.setVisibility(View.VISIBLE);
        	viewHolder.claimStartDate.setText(sdf.format(claim.getStartDate()));
        	viewHolder.claimStartDate.setHeight(45);
        }
		return rowView;
	}
	/**
	 * Takes a arraylist of expenses and returns a map containing the total 
	 * currency amounts for all expenses
	 * 
	 * Only returns currencies with amounts greater than zero
	 * 
	 * @param expenses ArrayList<Expense> to get totals from
	 * @return returns a map with key = currencies (String) and values = totals (Integer)
	 */
	private Map<String, Integer> getAmountTotals(ArrayList<Expense> expenses){
		
		Map<String, Integer> totalAmounts = new HashMap<String, Integer>();
		
		for(Expense expense : expenses){
			String cur = expense.getCurr();
			Integer amount = expense.getCost();
			
			if(amount == null){
				continue;
			}
			
			if(totalAmounts.containsKey(cur)){
				totalAmounts.put(cur, amount + totalAmounts.get(cur));
			}else if(amount != 0){
				totalAmounts.put(cur,amount);
			}
		}
		return totalAmounts;
	}
	
	private static class ViewHolder {
        public TextView claimName;
        public TextView claimStatus;
        public TextView claimStartDate;
        public LinearLayout destinations;
        public LinearLayout amounts;
        public LinearLayout tags;
        public TextView distColor;
    
    }
	

	
	
	

}
