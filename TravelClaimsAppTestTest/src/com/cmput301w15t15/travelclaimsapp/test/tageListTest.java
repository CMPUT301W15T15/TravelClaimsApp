package com.cmput301w15t15.travelclaimsapp.test;

import com.cmputw15t15.travelclaimsapp.Tag;
import com.cmputw15t15.travelclaimsapp.TagList;
import com.cmputw15t15.travelclaimsapp.TagListController;

import junit.framework.TestCase;

public class tageListTest extends TestCase {
	private TagList tagList;
	private Tag tag1;
	private Tag tag2;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tagList=TagListController.getTagList();
		tag1=new Tag("Business");
		tag2=new Tag("Personal");
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		
	}
	public tageListTest(){
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Business") == this.tag1);
	}
	public void removeTagTest(){
		tagList.removeTag("Business");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==1);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == this.tag2);
	}
	
}