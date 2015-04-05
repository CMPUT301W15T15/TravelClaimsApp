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

import java.util.ArrayList;
import com.cmput301w15t15.travelclaimsapp.model.Tag;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Custom ArrayAdaptor for displaying tag names in listview
 *
 */
public class TagListAdaptor extends ArrayAdapter<Tag> {

	private Context context;
	private int resource;
	private ArrayList<Tag> tagList;
	
	/**
	 * 
	 * class constructor
	 * @param context
	 * @param resource
	 * @param tags
	 */
	public TagListAdaptor(Context context, int resource, ArrayList<Tag> tags) {
		super(context, resource, tags);
		this.context = context;
		this.resource = resource;
		this.tagList = tags;
	}

	/**
	 * get format view for one row
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
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
		
		viewHolder.tagName.setTextColor(Color.WHITE);
        viewHolder.tagName.setText(d.getName());  
        
		return rowView;
	}
	
	
	/**
	 * all format views
	 */
	private static class ViewHolder {
        public TextView tagName;
    }
	
}