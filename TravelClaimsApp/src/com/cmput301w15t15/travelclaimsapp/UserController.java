package com.cmput301w15t15.travelclaimsapp;

import ca.ualberta.ssrg.movies.es.Movie;

import com.cmput301w15t15.travelclaimsapp.model.Listener;
import com.cmput301w15t15.travelclaimsapp.model.User;

public class UserController {
	
	private static User user = null;
	
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
	
	
	static public boolean pleaseAddUser(User newUser){
		User checkUser = FileManager.getSaver().getUser(newUser.getUsername());
		
		if(checkUser == null){
			FileManager.getSaver().addUser(newUser);
			return true;
		}
		
		return false;
	}
	
	public static void save(){
		FileManager.getSaver().saveUserInFile(getUser());
	}
	
	
	class SearchThread extends Thread {
		
		private String search;
		
		public SearchThread(String search){
			this.search = search;
		}
		
		public void run() {
			movies.addAll(movieManager.searchMovies("", null));
			
			runOnUiThread(doUpdateGUIList);
		}
		
	}
	
	class AddThread extends Thread {
		private Movie movie;

		public AddThread(Movie movie) {
			this.movie = movie;
		}

		@Override
		public void run() {
			movieManager.addMovie(movie);
			
			// Give some time to get updated info
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			runOnUiThread(doFinishAdd);
		}
	}
	
	
}
