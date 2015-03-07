//add
package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.io.IOException;

import com.cmput301w15t15.travelclaimsapp.model.Claim;



import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;

import junit.framework.TestCase;

public class tagListTest extends TestCase {
	private TagList tagList;
	private String tag1="Business";
	private String tag2="Personal";
	private String tag3="school";
	private Claim claim1;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tagList= new TagList();
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		//claim1.addTag(tag1);

		
	}
	//Test: tagListTest#1 
	public void addClaimTag() throws IOException{
		tagList.addTag(tag3);
		//tagList.addTag("school");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==3);
		//assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		//assertTrue("claim1 was not added", this.tagList.getTag("Business") == tag1);
		//assertTrue("claim1 was not added", this.claim1.getTag() == tag1);
	}

	//Test: tagListTest#2	
	public void removeTagTest() throws IOException{
		//claim1.addTag(tag1);
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.removeTag("Business");
		//claim1.removeTag();
		assertTrue("The length of the claimList is not two",  this.tagList.size()==1);
		//assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		//assertTrue("claim1 was not added", this.claim1.getTag() == null);
	}
	//Test: tagListTest#3
	public void renameTagTest() throws IOException{
		//claim1.addTag(tag1);
		tagList.addTag(tag1);
		tagList.addTag(tag2);
		tagList.renameTag(tag1,"School");
		claim1.renameTag("School");
		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		//assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		//assertTrue("claim1 was not added", this.tagList.getTag("School") == "School");
		//assertTrue("claim1 was not added", this.claim1.getTag() =="School");
		
		
	}
	
}