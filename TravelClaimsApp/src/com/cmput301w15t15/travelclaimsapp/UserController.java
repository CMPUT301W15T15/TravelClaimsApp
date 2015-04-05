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
package com.cmput301w15t15.travelclaimsapp;



import com.cmput301w15t15.travelclaimsapp.model.Listener;
import com.cmput301w15t15.travelclaimsapp.model.User;

/**
 * Singleton class used for getting static application User
 *
 */
public class UserController {
	
	private static User user = null;
	
	/**
	 * Pulls User from file on android, if no User, then returns user.
	 * 
	 * @return
	 */
	static public User getUser() {
		if(user == null){
			user = FileManager.getSaver().loadUserFromFile();
			user.setListeners();
			user.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
					
				}
			});
			return user;
		}
		return user;
	}
	
	/**
	 * Pulls user from file for controller to use.
	 */
	static public void initUserController() {
		if(user == null){
			user = FileManager.getSaver().loadUserFromFile();
			user.setListeners();
			user.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
					
				}
			});
		}
	}
	
	
	
	/**
	 * Used to save controller user.
	 */
	public static void save(){
		FileManager.getSaver().saveUserInFile(getUser());
	}
	
	
	
	/**
	 * Resets UserController Singleton.
	 */
	public static void resetUserController(){
		user = null;
	}
	
	
	
	
}
