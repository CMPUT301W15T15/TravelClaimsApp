package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.Tag;

import junit.framework.TestCase;

public class tagTest extends TestCase {
	
	public void testTag(){
		String tagName="Business";
		Tag tag = new Tag(tagName);
		assertTrue("The tag name is not equal to",tagName.equals(tag.getName()));
		String tagName2="Personal";
		tag.rename(tagName2);
		assertTrue("The tag name is not equal to",tagName2.equals(tag.getName()));
		}
}
