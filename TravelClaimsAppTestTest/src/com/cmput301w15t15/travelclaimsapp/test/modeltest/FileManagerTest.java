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

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.test.AndroidTestCase;

public class FileManagerTest extends AndroidTestCase {

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		FileManager.initializeSaver(getContext());

	}
	

	//test: FileManagerTest#1
	/**
	 * Ensures users can be added to server.
	 * @throws IOException
	 */
	public void testAddUser() throws IOException{
		String name1 = "Jon";
		String pass1 = "dog";
		
		User user1 = new User(name1, pass1);
		
		FileManager.getSaver().addUser(user1);
		User checkUser = FileManager.getSaver().getUser(user1.getUsername());
		
		assertNotNull("user1 was added", user1.getUsername());
	}
	

	//test: FileManagerTest#2
	/**
	 * Ensures claimlists can be added to server.
	 * @throws IOException
	 */
	public void testAddClaimlist() throws IOException{
		ClaimList claimlist = new ClaimList();
		ClaimListController.getClaimList();
		Claim claim1 = new Claim("Claim1");
		claimlist.addClaim(claim1);
		ClaimListController.addClaim(claim1);
		String name1 = "Jon";
		String pass1 = "dog";
		
		User user1 = new User(name1, pass1);
		
		ClaimList checkClaimList = FileManager.getSaver().getClaimList(user1.getUsername());
		
		assertFalse("claimlist was added", claim1.getName() == null);
	}
	

	//test: FileManagerTest#3
	/**
	 * Ensures users can be added to file.
	 * @throws IOException
	 */
	public void testAddUser2() throws IOException{
		String name1 = "Jon";
		String pass1 = "dog";
		
		User user1 = new User(name1, pass1);
		
		FileManager.getSaver().saveUserInFile(user1);
		User checkUser = FileManager.getSaver().loadUserFromFile();
		
		assertTrue("user1 was added", user1.getUsername().equals(checkUser.getUsername()));
	}
	

	//test: FileManagerTest#2
	/**
	 * Ensures claimlists can be added to file.
	 * @throws IOException
	 */
	public void testAddClaimlist2() throws IOException{
		ClaimList claimlist = new ClaimList();
		Claim claim1 = new Claim("Claim1");
		claimlist.addClaim(claim1);
		String name1 = "Jon";
		String pass1 = "dog";
		
		User user1 = new User(name1, pass1);
		
		FileManager.getSaver().saveClaimLInFile(claimlist, user1.getUsername());
		ClaimList checkClaimList = FileManager.getSaver().loadClaimLFromFile();
		
		assertTrue("claimlist was added", claim1.getName().equals(checkClaimList.getClaim("Claim1").getName()));
	}
	
}
