package com.cmput301w15t15.travelclaimsapp.model;

import java.io.IOException;
import java.util.ArrayList;



public class TagList {
	//private String tagListName;
	protected ArrayList<Tag> tagList;
	
	public TagList(){
		tagList=new ArrayList<Tag>();
	}
	
	/*****************
	public void setTagListName(String tagListName){
		this.tagListName=tagListName;
	}
	*******************/
	
	public void addTag(Tag tag) {
		this.tagList.add(tag);
		
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
		
	}
	
	
	public boolean contains(String tagName){
		if (tagList.size()==0){
			return false;
		}
		else{
			for (int i=0; i<tagList.size();i++){
				if (tagList.get(i).getName() == tagName){
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
				if (tagList.get(i).getName() == tagName){
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
		
	}

}
