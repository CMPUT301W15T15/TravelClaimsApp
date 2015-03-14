package com.cmput301w15t15.travelclaimsapp;


import com.cmput301w15t15.travelclaimsapp.model.Listener;
import com.cmput301w15t15.travelclaimsapp.model.User;

public class UserController {
	
	private static User user = null;
	
	static public User getUserWithoutInternet() {
		if(user == null){
			user = FileManager.getSaver().loadUserFromFile();
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
	
	static public User getUserWithInternet() {
		if(user == null){
			user = FileManager.getSaver().loadUserFromFile();
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
	
	
	static public boolean pleaseAddUser(User newUser){
		User checkUser = FileManager.getSaver().getUser(newUser.getUsername());
		
		if(checkUser == null){
			FileManager.getSaver().addUser(newUser);
			return true;
		}
		
		return false;
	}
	
	public static void save(){
		FileManager.getSaver().saveUserInFile(getUserWithoutInternet());
	}
	
	
}
