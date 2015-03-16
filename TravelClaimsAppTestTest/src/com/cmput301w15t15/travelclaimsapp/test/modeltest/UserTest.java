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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.test.AndroidTestCase;

/**
 * Tests for User class.
 *
 */
public class UserTest extends AndroidTestCase {
	private User user1;
	private User user2;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}
	//test: UserTest#1
	/**
	 * Ensures users can be added.
	 * @throws IOException
	 */
	public void testAddUser() throws IOException{
		String name1 = "Jon";
		String pass1 = "dog";
		
		MessageDigest md = null;
		byte[] passHash = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			passHash = md.digest(pass1.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user1 = new User(name1, passHash);
		
		assertTrue("user1 was added", user1.getUsername().equals(name1));
		assertFalse("user2 was not added", user1 == user2);
		assertTrue("user1 is not an Approver", user1.isApprover()==false);
	}
	

	//test: UserTest#4
	/**
	 * Ensures approvers can be added.
	 * @throws IOException
	 */
	public void testAddUserApprover() throws IOException{
		String name1 = "Shelby";
		String pass1 = "Sunshine";
		Boolean isApprover = true;
		
		MessageDigest md = null;
		byte[] passHash = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			passHash = md.digest(pass1.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user1 = new User(name1, passHash, isApprover);
		
		assertTrue("user1 was added", user1.getUsername().equals(name1));
		assertFalse("user1 is an approver", user1.isApprover() == false);
	}
}
