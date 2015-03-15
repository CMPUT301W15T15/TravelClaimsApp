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
package com.cmput301w15t15.travelclaimsapp.model;

import java.io.IOException;
import java.util.ArrayList;



public class TagList implements Listenable{
	//private String tagListName;
	protected ArrayList<Tag> tagList;
	private transient ArrayList<Listener> listeners;
	
	public TagList(){
		tagList=new ArrayList<Tag>();
		this.listeners = new ArrayList<Listener>();
	}
	
	/*****************
	public void setTagListName(String tagListName){
		this.tagListName=tagListName;
	}
	*******************/
	
	public void addTag(Tag tag) {
		this.tagList.add(tag);
		notifyListeners();
	}

	public ArrayList<Tag> toArrayList(){
		return this.tagList;
	}

	public int size() {
		// TODO Auto-generated method stub
		return tagList.size();
	}

	public void removeTag(Tag tag) {
		tagList.remove(tag);
		notifyListeners();
		
	}
	
	
	public boolean contains(String tagName){
		if (tagList.size()==0){
			return false;
		}
		else{
			for (int i=0; i<tagList.size();i++){
				if (tagList.get(i).getName().equals(tagName)){
					return true;
				}
			}
			return false;
		}
		
	}
	
	
	public Tag getTag(String tagName)
	{

		
		if (tagList.size()==0){
			return null;
		}
		else{
			for (int i=0; i<tagList.size();i++){
				if (tagList.get(i).getName().equals(tagName)){
					return tagList.get(i);
				}
			}
			return null;
		}

	}


	public void renameTag(String tagName, String newTagName)
	{

		for (int i=0; i<tagList.size();i++){
			if (tagList.get(i).getName() == tagName){
				tagList.get(i).rename(newTagName);
			}
		}
		notifyListeners();
		
	}
	@Override
	public void notifyListeners() {
		for(Listener listener : listeners){
			listener.update();
		}
		
	}

	@Override
	public void addListener(Listener listener) {
		this.listeners.add(listener);
		
	}

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}

	@Override
	public void deleteListener(Listener listener) {
		this.listeners.remove(listener);
		
	}

}
