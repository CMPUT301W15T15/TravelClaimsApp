package com.cmput301w15t15.travelclaimsapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
	
	public DestinationListAdaptor(Context context, int resource, ArrayList<Destination> dests) {
		super(context, resource, dests);
		this.context = context;
		this.resource = resource;
		this.destList = dests;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		ViewHolder viewHolder;
		Destination d = destList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.destLocation = (TextView) rowView.findViewById(R.id.destAdaptor_location);
	        viewHolder.destReason = (TextView) rowView.findViewById(R.id.destAdaptor_reason);
	        
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
        viewHolder.destLocation.setText(d.getLocation());
        viewHolder.destReason.setText(d.getReason());  
        
		return rowView;
	}
	
	
	private static class ViewHolder {
        public TextView destLocation;
        public TextView destReason;
      
    
    }
	
	
}
