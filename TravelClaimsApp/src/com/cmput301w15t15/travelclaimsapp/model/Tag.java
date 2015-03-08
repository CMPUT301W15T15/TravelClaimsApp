package com.cmput301w15t15.travelclaimsapp.model;

public class Tag {
	protected String tagName;
	
	public Tag(String tagName1){
		this.tagName=tagName1;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return this.tagName;
	}

	public void rename(String tagName2) {
		// TODO Auto-generated method stub
		this.tagName=tagName2;
	}
}