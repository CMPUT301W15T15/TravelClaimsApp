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
	 * 
	 */
	static public void initUserController() {
		if(user == null){
			user = FileManager.getSaver().loadUserFromFile();
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
	 * 
	 */
	public static void save(){
		FileManager.getSaver().saveUserInFile(getUser());
	}
	
	
	
	
	
}
