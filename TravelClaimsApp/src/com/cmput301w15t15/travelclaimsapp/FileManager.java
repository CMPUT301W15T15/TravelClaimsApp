package com.cmput301w15t15.travelclaimsapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.UserList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

public class FileManager {
	private static final String CLAIMLISTFILENAME = "claimlist.sav";
	private static final String USERLISTFILENAME = "userlist.sav";
	private static final String TAGLISTFILENAME = "taglist.sav";
	private Context context;
	private static FileManager fm = null;
	
	private FileManager(Context context) {
		this.context = context;
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
	
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public ClaimList loadClaimLFromFile() {
		ClaimList claims = new ClaimList();
		Gson gson = new Gson();
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
		if (claims.getSize()==0){
			return new ClaimList();
		}
		return claims;
	}
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public void saveClaimLInFile(ClaimList claimList) {
		Gson gson = new Gson();
		
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
	
	public UserList loadUserLFromFile() {
		UserList users = new UserList();
		Gson gson = new Gson();
		try {
			//openFileInput only takes a a filename
			FileInputStream fis = context.openFileInput(USERLISTFILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			//Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html 2015-19-01
			Type typeOfT = new TypeToken<UserList>(){}.getType();
			users = gson.fromJson(in, typeOfT);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (users.getSize()==0){
			return new UserList();
		}
		return users;
	}
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public void saveUserLInFile(UserList userList) {
		Gson gson = new Gson();
		
		try {
			//openFileOutput is a Activity method
			FileOutputStream fos = context.openFileOutput(USERLISTFILENAME,0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(userList, osw);
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
	
	public TagList loadTagLFromFile() {
		TagList tags = new TagList();
		Gson gson = new Gson();
		try {
			//openFileInput only takes a a filename
			FileInputStream fis = context.openFileInput(TAGLISTFILENAME);
			InputStreamReader in = new InputStreamReader(fis);
			//Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html 2015-19-01
			Type typeOfT = new TypeToken<ClaimList>(){}.getType();
			tags = gson.fromJson(in, typeOfT);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tags.getSize()==0){
			return new TagList();
		}
		return tags;
	}
	//from https://github.com/scheidemanS/lonelyTwitter/blob/master/src/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java on 2015-01-25
	public void saveTagLInFile(TagList tagList) {
		Gson gson = new Gson();
		
		try {
			//openFileOutput is a Activity method
			FileOutputStream fos = context.openFileOutput(TAGLISTFILENAME,0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(tagList, osw);
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
	
}
