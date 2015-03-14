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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.TagList;
import com.cmput301w15t15.travelclaimsapp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;

public class FileManager {
	

	private static final String USER_SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/user/_search";
	private static final String USER_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/user/";
	private static final String CLAIMLIST_SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/claimlist/_search";
	private static final String CLAIMLIST_RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301w15t15/claimlist/";
	private static final String USER_TAG = "UserSearch";
	private static final String CLAIMLIST_TAG = "ClaimListSearch";
	private static final String USERFILENAME = "user.sav";
	private static final String CLAIMLISTFILENAME = "claimlist.sav";
	private static final String EXPENSELIST_RESOURCE_URL = null;
	private static final String EXPENSELISTFILENAME = "expenseList.sav";
	private static final String EXPENSELIST_TAG = null;
	
	private Gson gson;
	private Context context;
	private static FileManager fm = null;
	
	public FileManager(Context context) {
		this.context = context;
		gson = new Gson();
	}
	
	//creates a FileManager if it does not already exist 
	public static void initializeSaver(Context context){
		if(fm == null){
			if(context == null){
				throw new RuntimeException("missing context for FileManager");
			}
			fm = new FileManager(context);
		}
	}
	
	
	public static FileManager getSaver(){
		if(fm == null){
			throw new RuntimeException("FileManager was not initialized");
		}
		return fm;
	}
	
	
	public User getUser(String Username) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(USER_RESOURCE_URL + Username);

		HttpResponse response;
		String json;

		try {
			response = httpClient.execute(httpGet);
			json = getEntityContent(response);
			Type searchResponseType = new TypeToken<User>(){}.getType();
			
			User esResponse = gson.fromJson(json, searchResponseType);
			return esResponse;

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
	
	
	
	/**
	 * Adds a new user
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
	
	
	public ClaimList getClaimList(String Username) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(CLAIMLIST_RESOURCE_URL + Username);

		HttpResponse response;
		String json;

		try {
			response = httpClient.execute(httpGet);
			//SearchHit<Movie> sr = parseMovieHit(response);
			json = getEntityContent(response);
			Type searchResponseType = new TypeToken<ClaimList>(){}.getType();
			
			ClaimList esResponse = gson.fromJson(json, searchResponseType);
			return esResponse;

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;
	}
	
	
	
	/**
	 * Adds a new ClaimList
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
	
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public ClaimList loadClaimLFromFile() {
		ClaimList claims = new ClaimList();
		//Gson gson = new Gson();
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
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (claims.size()==0){
			return new ClaimList();
		}
		return claims;
	}
	
	
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public void saveClaimLInFile(ClaimList claimList) {
		//Gson gson = new Gson();
		
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
	 * TODO: Do we need these methods anymore ?
	 * commented out as they were causing errors
	 */
	public User loadUserFromFile() {
		User user;
		Gson gson = new Gson();
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
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public void saveUserInFile(User user) {
		Gson gson = new Gson();
		
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

}
