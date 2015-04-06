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

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.UserController;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.test.AndroidTestCase;

public class UserControllerTest extends AndroidTestCase {

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	

	//test: UserTest#1
	/**
	 * Ensures user added to a file, is the same returned for the controller.
	 * @throws IOException
	 */
	public void testgetUser() throws IOException{
		String name1 = "Jon";
		String pass1 = "dog";
		
		User user1 = new User(name1, pass1);
		
		FileManager.getSaver().saveUserInFile(user1);
		User checkUser = FileManager.getSaver().loadUserFromFile();
		User checkUser2 = UserController.getUser();
		
		assertTrue("getUser works", checkUser2.getUsername().equals(checkUser.getUsername()));
	}
}
