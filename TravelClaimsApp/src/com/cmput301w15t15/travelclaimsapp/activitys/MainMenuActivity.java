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
package com.cmput301w15t15.travelclaimsapp.activitys;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.InternetController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.UserController;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

	// Thread that close the activity after finishing add
	private Runnable launchAddClaims = new Runnable() {
		public void run() {
			ClaimListController.initClaimListController();
	    	Toast.makeText(MainMenuActivity.this, "Claim List", Toast.LENGTH_SHORT).show();
	    	Intent intent = new Intent(MainMenuActivity.this, AddClaimActivity.class);
	    	startActivity(intent);
		}
	};
	
	
	/**
	 * Wrong username or password
	 */
	private Runnable popToast = new Runnable() {
		public void run() {
			Toast.makeText(MainMenuActivity.this, "Wrong username or password.", Toast.LENGTH_LONG).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		FileManager.initializeSaver(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	
	public void OnLoginClick(View v){
		EditText passText = (EditText) findViewById(R.id.PasswordEditText);
		EditText userText = (EditText) findViewById(R.id.UsernameEditText);
		MessageDigest md = null;
		byte[] passHash = null;
		String givenUsername = userText.getText().toString();
		
		//some error handling
		if(passText.getText().toString().equals("") && userText.getText().toString().equals("")){
			Toast.makeText(this, "No username or password given.", Toast.LENGTH_LONG).show();
			return;
		}else if(passText.getText().toString().equals("")){
			Toast.makeText(this, "No password given.", Toast.LENGTH_LONG).show();
			return;
		}else if(userText.getText().toString().equals("")){
			Toast.makeText(this, "No username given.", Toast.LENGTH_LONG).show();
			return;
		}
		
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		try {
			passHash = md.digest(passText.getEditableText().toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		
		if(InternetController.isInternetAvailable2(this)){
			Thread thread = new loginThread(givenUsername, passHash);
			thread.start();
			
		} else {
			User fileUser = UserController.getUser();
			
			if(fileUser.getUsername().equals(givenUsername)){
				if(Arrays.equals(fileUser.getpHash(), passHash)){
					runOnUiThread(launchAddClaims);
				} else {
					runOnUiThread(popToast);
				}
			} else {
				runOnUiThread(popToast);
			}
		}
		
	}
	
	public void SearchOption(MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	
	public void AddClaimMenu(MenuItem menu)
    {
    	Toast.makeText(this, "Going to Claims", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainMenuActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }
	
	public void ToNewUserActivity(MenuItem menu)
    {
		
		if(InternetController.isInternetAvailable2(this)){
			Toast.makeText(this, "Create User", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(MainMenuActivity.this, CreateUserActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Cannot Create User offline", Toast.LENGTH_SHORT).show();
		}
    }
	

	
	class loginThread extends Thread {
		
		private String givenUsername;
		private byte[] givenPassHash;
		
		public loginThread(String username, byte[] passHash){
			this.givenUsername = username;
			this.givenPassHash = passHash;
		}
		
		public void run() {
			User pulledUser = FileManager.getSaver().getUser(givenUsername);
			
			if(pulledUser.getUsername().equals(givenUsername)){
				if(Arrays.equals(pulledUser.getpHash(), givenPassHash)){
					FileManager.getSaver().saveUserInFile(pulledUser);
					ClaimList claimlist = FileManager.getSaver().getClaimList(givenUsername);
					FileManager.getSaver().saveClaimLInFile(claimlist, givenUsername);
					runOnUiThread(launchAddClaims);
				} else {
					runOnUiThread(popToast);
				}
			} else {
				runOnUiThread(popToast);
			}
			
		}
		
	}
}
