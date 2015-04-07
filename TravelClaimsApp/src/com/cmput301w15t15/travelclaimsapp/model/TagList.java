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

import java.util.ArrayList;


/**
 * Contains a list of Tag Objects. Used as field in Claim object
 * @author chri xinchao swang
 *
 */
public class TagList implements Listenable{
	protected ArrayList<Tag> tagList;
	private transient ArrayList<Listener> listeners;
	/**
	 * TagList constructor
	 */
	public TagList(){
		tagList=new ArrayList<Tag>();
		this.listeners = new ArrayList<Listener>();
	}
	
	/**
	 * Add new tag to tagList
	 * @param tag	
	 */
	public void addTag(Tag tag) {
		this.tagList.add(tag);
		notifyListeners();
	}
	/**
	 * Get the TagList as ArrayList of Tags
	 * @return		a ArrayList of Tags
	 */
	public ArrayList<Tag> toArrayList(){
		return this.tagList;
	}
	/**
	 * Return the size of a TagList
	 * @return	 the number of Tag objects in TagList
	 */
	public int size() {
		return tagList.size();
	}
	/**
	 * Delete a tag from a tagList
	 * @param tag	the Tag object to delete from TagList
	 */
	public void removeTag(Tag tag) {
		tagList.remove(tag);
		notifyListeners();
		
	}
	/**
	 * Check if the tagList has a certain tag	
	 * @param tagName	name of the Tag to search for
	 * @return true for the tagList has the certain tag; false for the tagList does not contain the certain tag
	 */
	
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
	
	/**
	 * Get the tag from calling the tagName
	 * @param tagName	the String name of the Tag to search for 
	 * @return null:does not have the certain tag or size of tagList is 0; certain tag.
	 */
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
