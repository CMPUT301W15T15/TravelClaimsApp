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
package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import java.io.IOException;

import com.cmput301w15t15.travelclaimsapp.model.Claim;



import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;

import junit.framework.TestCase;
/**
 * 
 * Tests for TagList
 *
 */
public class tagListTest extends TestCase {
	private TagList tagList;

	private Tag tag1=new Tag("Business");
	private Tag tag2=new Tag("Personal");
	private Tag tag3=new Tag("school");

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		tagList= new TagList();
		tagList.addTag(tag1);
		tagList.addTag(tag2);
	
	}
	//Test: tagListTest#1 
	public void testaddClaimTag() throws IOException{
		tagList.addTag(tag3);
	
		assertTrue("The length of the claimList is not two",  this.tagList.size()==3);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("Business") == tag1);
	}

	//Test: tagListTest#2	
	public void testremoveTagTest() throws IOException{

		tagList.removeTag(tag1);

		assertTrue("The length of the claimList is not two",  this.tagList.size()==1);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
	}
	//Test: tagListTest#3
	public void testrenameTagTest() throws IOException{

		tag2.setName("Personal");
		tag1.setName("School");

		assertTrue("The length of the claimList is not two",  this.tagList.size()==2);
		assertTrue("claim1 was not added", this.tagList.getTag("Personal") == tag2);
		assertTrue("claim1 was not added", this.tagList.getTag("School") ==tag1);	
	}
}
