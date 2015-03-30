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
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.MapActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.DestinationList;

/**
 * Custom list adaptor for {@link DestinationList}
 * 
 */
public class DestinationListAdaptor extends ArrayAdapter<Destination>{

	private Context context;
	private int resource;
	private ArrayList<Destination> destList;

	/**
	 * class contructor
	 * @param context
	 * @param resource
	 * @param dests
	 */
	public DestinationListAdaptor(Context context, int resource, ArrayList<Destination> dests) {
		super(context, resource, dests);
		this.context = context;
		this.resource = resource;
		this.destList = dests;
	}

	/**
	 * return format view for list viewing
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		ViewHolder viewHolder;
		final Destination d = destList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.destLocation = (TextView) rowView.findViewById(R.id.destAdaptor_location);
	        viewHolder.destReason = (TextView) rowView.findViewById(R.id.destAdaptor_reason);
	        viewHolder.destMap = (ImageView) rowView.findViewById(R.id.destAdaptor_map_image);
	        
	        
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.destLocation.setText(d.getLocation());
        viewHolder.destReason.setText(d.getReason());  
        
        if(d.getGeoLocation() == null){
        	viewHolder.destMap.setVisibility(android.view.View.INVISIBLE);
        	viewHolder.destMap.setClickable(false);
        }else{
        	viewHolder.destMap.setVisibility(android.view.View.VISIBLE);
        }
        viewHolder.destMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = GeoLocationController.viewLocationIntent(context, d.getGeoLocation());
		    	context.startActivity(intent);
			}
		});
        
		return rowView;
	}
	
	
	/**
	 * 
	 * all format view for destination list view
	 * @author
	 *
	 */
	private static class ViewHolder {
        public TextView destLocation;
        public TextView destReason;
        public ImageView destMap;
    
    }
	
	
}
