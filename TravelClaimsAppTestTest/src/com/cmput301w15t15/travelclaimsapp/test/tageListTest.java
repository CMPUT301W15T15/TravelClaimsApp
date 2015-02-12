//add
package com.cmput301w15t15.travelclaimsapp.test;

import junit.framework.TestCase;

public class tageListTest extends TestCase {
	private TagList tagList;
	private String tag1="Business";
	private String tag2="Personal";
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tagList=TagListController.getTagList();
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		
	}
	//Test number: # tagListTest-addTagTest 
	public tageListTest(){
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("Business") == tag1);
	}

	//TestID: tagListTest-removeTagTest 
	public void removeTagTest(){
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.removeTag("Business");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==1);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
	}
	//TestID: tagList-renameTagTest
	public void renameTagTest(){
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.renameTag(tag1,"School");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("School") == "School");
		
		
	}
	
}