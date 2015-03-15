package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.test.AndroidTestCase;

public class UserTest extends AndroidTestCase {
	private User user1;
	private User user2;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}
	//test: UserTest#1
	public void testAddUser() throws IOException{
		String name1 = "Jon";
		String name2 = null;
		String pass1 = "dog";
		String pass2 = null;
		
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