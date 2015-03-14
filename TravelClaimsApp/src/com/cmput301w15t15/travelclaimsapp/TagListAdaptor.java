package com.cmput301w15t15.travelclaimsapp;

import java.util.ArrayList;
import java.util.List;

import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Tag;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TagListAdaptor extends ArrayAdapter<Tag> {
	

	private Context context;
	private int resource;
	private ArrayList<Tag> tagList;
	
	public TagListAdaptor(Context context, int resource, ArrayList<Tag> tags) {
		super(context, resource, tags);
		this.context = context;
		this.resource = resource;
		this.tagList = tags;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		ViewHolder viewHolder;
		Tag d = tagList.get(position);
		
		if(rowView == null){
			viewHolder = new ViewHolder();
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        rowView = inflater.inflate(resource, parent, false);
			
	        viewHolder.tagName = (TextView) rowView.findViewById(R.id.tagAdaptor_tagName);
	       
 	        rowView.setTag(viewHolder);
 	        
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}
		
    
        viewHolder.tagName.setText(d.getName());  
        
		return rowView;
	}
	
	
	private static class ViewHolder {
        public TextView tagName;
    }
	
}