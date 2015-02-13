//add
package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.Tag;
import com.cmput301w15t15.travelclaimsapp.TagList;
import com.cmput301w15t15.travelclaimsapp.TagListController;


import junit.framework.TestCase;

public class tageListTest extends TestCase {
	private TagList tagList;
	private String tag1="Business";
	private String tag2="Personal";
	private Claim claim1;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tagList=TagListController.getTagList();
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		claim1.addTag(tag1);

		
	}
	//Test number: # tagListTest-addTagTest 
	public void addClaimTag(){
		claim1.addTag(tag1);
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("Business") == tag1);
		assertTrue("claim1 was not added", this.claim1.getTag() == tag1);
	}

	//TestID: tagListTest-removeTagTest 	
	public void removeTagTest(){
		claim1.addTag(tag1);
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.removeTag("Business");
		claim1.removeTag();
		assertTrue("The length of the claimList is not two",  this.tagList.size()==1);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.claim1.getTag() == null);
	}
	//TestID: tagList-renameTagTest
	public void renameTagTest(){
		claim1.addTag(tag1);
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.renameTag(tag1,"School");
		claim1.renameTag("School");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("School") == "School");
		assertTrue("claim1 was not added", this.claim1.getTag() =="School");
		
		
	}
	
}