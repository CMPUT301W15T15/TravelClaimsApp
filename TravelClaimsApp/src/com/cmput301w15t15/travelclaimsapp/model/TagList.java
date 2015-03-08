package com.cmput301w15t15.travelclaimsapp.model;

import java.io.IOException;
import java.util.ArrayList;



public class TagList {
	protected ArrayList<String> tagList;
	
	public TagList(){
		tagList=new ArrayList<String>();
	}
	
	public void addTag(String tag) throws IOException{
		if (tagList.size()==0){
			tagList.add(tag);
		}else{
			for (int i=0;i<tagList.size();i++){
				if (tagList.get(i)!=tag) {
					tagList.add(tag);
				}
			}
		}
		
	}


	public int size() {
		// TODO Auto-generated method stub
		return tagList.size();
	}

	public void removeTag(String string) {
		tagList.remove(string);
		
	}

	public String getTag(String string)
	{

		if (tagList==null){
			return null;
		}
		else{
			for (int i=0; i<tagList.size();i++){
				if (tagList.get(i).equals(string)){
					return tagList.get(i);
				}
			}
		}
		return null;

	}


	public void renameTag(String tag1, String string)
	{

		tagList.remove(string);
		tagList.add(string);
		
	}

}
