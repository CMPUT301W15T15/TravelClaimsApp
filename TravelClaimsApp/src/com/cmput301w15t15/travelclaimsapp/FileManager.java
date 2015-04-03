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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.cmput301w15t15.travelclaimsapp.elasticsearch.SearchHit;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import com.cmput301w15t15.travelclaimsapp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;

/**
 * FileManager is used to manage both loads/saves to a local file and pushes/pulls to a server
 * 
 * Users and ClaimLists are being handled within.
 *
 */
public class FileManager {
	

	private static final String USER_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/user/";
	private static final String CLAIMLIST_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/claimlist/";
	private static final String SUBMITTED_CLAIMLIST_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/submitted_claimlist/";
	
	private static final String USER_TAG = "UserSearch";
	private static final String CLAIMLIST_TAG = "ClaimListSearch";
	private static final String SUBMITTED_CLAIMLIST_TAG = "SubmittedClaimListSearch";
	private static final String USERFILENAME = "user.sav";
	private static final String CLAIMLISTFILENAME = "claimlist.sav";
	private static final String SUBMITTED_CLAIMLISTFILENAME = "submitted_claimlist.sav";
	private static final String CLAIMANT_CLAIMLISTFILENAME = "claimant_claimlist.sav";
	
	private Gson gson;
	private Context context;
	private static FileManager fm = null;
	
	/**
	 * Initial FileManager needs to be given context from where called.
	 * @param context
	 */
	public FileManager(Context context) {
		this.context = context;
		gson = new Gson();
	}
	
	//creates a FileManager if it does not already exist 
	/**
	 * Initializes FileManager with given context, if FileManager is null.
	 * @param context
	 */
	public static void initializeSaver(Context context){
		if(fm == null){
			if(context == null){
				throw new RuntimeException("missing context for FileManager");
			}
			fm = new FileManager(context);
		}
	}
	
	
	/**
	 * Gets FileManager for saving or loading functions.
	 * @return
	 */
	public static FileManager getSaver(){
		if(fm == null){
			throw new RuntimeException("FileManager was not initialized");
		}
		return fm;
	}
	
	
	/**
	 * Gets user from server.
	 * @param Username
	 * @return
	 */
	public User getUser(String Username) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(USER_RESOURCE_URL + Username);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			SearchHit<User> sr = parseUserHit(response);
			return sr.getSource();

		} catch (Exception e) {
			return null;
		} 

	}
	
	
	
	/**
	 * Adds a new user to server.
	 */
	public void addUser(User newUser) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(USER_RESOURCE_URL + newUser.getUsername());

			StringEntity stringEntity = new StringEntity(gson.toJson(newUser));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(USER_TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Deletes the specific user
	 */
	public void deleteUser(User newUser) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(USER_RESOURCE_URL + newUser.getUsername());
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(USER_TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Gets claimlist from server.
	 * @param Username
	 * @return
	 */
	public ClaimList getClaimList(String Username) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(CLAIMLIST_RESOURCE_URL + Username);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			SearchHit<ClaimList> sr = parseClaimListHit(response);
			return sr.getSource();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
	
	
	/**
	 * Gets claimlist from server.
	 * @param Username
	 * @return
	 */
	public ClaimList getSumbittedClaimList() {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(SUBMITTED_CLAIMLIST_RESOURCE_URL + "main");

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
			SearchHit<ClaimList> sr = parseClaimListHit(response);
			if(sr.getSource() == null){
				return new ClaimList();
			} else {
				return sr.getSource();
			}

		} catch (Exception e) {
			return null;
		} 
		
	}
	
	
	/**
	 * Adds a new ClaimList to server.
	 */
	public void addClaimList(ClaimList newClaimList, String Username) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(CLAIMLIST_RESOURCE_URL + Username);
			
			StringEntity stringEntity = new StringEntity(gson.toJson(newClaimList));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");
			
			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(CLAIMLIST_TAG, status);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Add submitted claim to server by updating the submitted claimlist.
	 * 
	 * @param newClaimList
	 */
	public void addSubmittedClaimL(ClaimList newClaimList) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(SUBMITTED_CLAIMLIST_RESOURCE_URL + "main");

			StringEntity stringEntity = new StringEntity(gson.toJson(newClaimList));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(SUBMITTED_CLAIMLIST_TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	/**
	 * 
	 * Gets a claimlist from local file.
	 * @return
	 */
	public ClaimList loadClaimLFromFile() {
		ClaimList claims = new ClaimList();
		
		try {
			//openFileInput only takes a a filename
			FileInputStream fis = context.openFileInput(CLAIMLISTFILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			//Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html 2015-19-01
			Type typeOfT = new TypeToken<ClaimList>(){}.getType();
			claims = gson.fromJson(in, typeOfT);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		if (claims == null){
			return new ClaimList();
		}
		return claims;
	}
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	/**
	 * Saves claimlist to file and attempts to save online if there is an internet connection.
	 * @param claimList
	 * @param username
	 */
	public void saveClaimLInFile(ClaimList claimList, String username) {
		Thread thread = new onlineSaveClaimListThread(claimList, username);
		thread.start();
		
		try {
			//openFileOutput is a Activity method
			FileOutputStream fos = context.openFileOutput(CLAIMLISTFILENAME,0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(claimList, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Saves Submitted claimlist to file and attempts to save online if there is an internet connection.
	 * @param claimList
	 * @param username
	 */
	public void saveSubmittedClaimLInFile(ClaimList claimList) {
		Thread thread = new onlineSaveSubmittedClaimLThread(claimList);
		thread.start();

	}
	
	
	
	/**
	 * Loads user from file.
	 */
	public User loadUserFromFile() {
		User user;
		
		try {
			//openFileInput only takes a a filename
			FileInputStream fis = context.openFileInput(USERFILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			//Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html 2015-19-01
			Type typeOfT = new TypeToken<User>(){}.getType();
			user = gson.fromJson(in, typeOfT);
			fis.close();
			return user;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	/**
	 * Saves user to file and attempts to save online if there is an internet connection.
	 * @param user
	 */
	public void saveUserInFile(User user) {
		Thread thread = new onlineSaveUserThread(user);
		thread.start();
		
		try {
			//openFileOutput is a Activity method
			FileOutputStream fos = context.openFileOutput(USERFILENAME,0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(user, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets content from an HTTP response
	 */
	public String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets rid of elastic search header and returns saved claimlist.
	 * @param response
	 * @return
	 */
	private SearchHit<ClaimList> parseClaimListHit(HttpResponse response) {
		
		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<SearchHit<ClaimList>>() {}.getType();
			
			SearchHit<ClaimList> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets rid of elastic search header and returns saved user.
	 * @param response
	 * @return
	 */
	private SearchHit<User> parseUserHit(HttpResponse response) {

		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<SearchHit<User>>() {}.getType();
			
			SearchHit<User> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Thread for running http calls for claimlist when save() is called in controller
	 *
	 */
	class onlineSaveClaimListThread extends Thread {
		
		private ClaimList claimlist;
		private String username;
		
		public onlineSaveClaimListThread(ClaimList claimlist, String username){
			this.claimlist = claimlist;
			this.username = username;
		}
		
		@Override
		public void run() {
			
			if(InternetController.isInternetAvailable2(context)){
				addClaimList(claimlist, username);
			}
			
		}
		
	}
	
	class onlineSaveSubmittedClaimLThread extends Thread {
		private ClaimList claimlist;
		
		public onlineSaveSubmittedClaimLThread(ClaimList claimlist){
			this.claimlist = claimlist;
		}
		
		@Override
		public void run() {
			
			if(InternetController.isInternetAvailable2(context)){
				addSubmittedClaimL(claimlist);
			}
			
		}
	}

	/**
	 * 
	 * Thread for running http calls for user when save() is called in controller
	 *
	 */
	class onlineSaveUserThread extends Thread {
		
		private User user;
		
		public onlineSaveUserThread(User user){
			this.user = user;
		}
		
		@Override
		public void run() {
			
			if(InternetController.isInternetAvailable2(context)){
				addUser(user);
			}
			
		}
		
	}

	/**
	 * Thread for running http calls for user when save() is called in controller
	 *
	 */
	class onlineDeleteUserThread extends Thread {
		
		private User user;
		
		public onlineDeleteUserThread(User user){
			this.user = user;
		}
		
		@Override
		public void run() {
			
			if(InternetController.isInternetAvailable2(context)){
				deleteUser(user);
			}
			
		}
		
	}
	
}
