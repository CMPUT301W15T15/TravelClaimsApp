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

import java.util.Arrays;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.InternetController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.UserController;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity handles setting up a user to access their claimlist.
 */
public class LoginActivity extends Activity {

	/**
	 * Thread that closes the activity after finishing addClaims.
	 */
	private Runnable launchAddClaims = new Runnable() {
		public void run() {
			ClaimListController.initClaimListController();
	    	Intent intent = new Intent(LoginActivity.this, AddClaimActivity.class);
	    	startActivity(intent);
	    	finish();
		}
	};
	
	
	/**
	 * Wrong username or password.
	 */
	private Runnable popToast = new Runnable() {
		public void run() {
			Toast.makeText(LoginActivity.this, "Wrong username or password.", Toast.LENGTH_LONG).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_screen);
		FileManager.initializeSaver(this);
		SignOutController.reset();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	
	/**
	 * On clicking Login, checks are made for valid fields.
	 * 
	 * If connected to the internet, given info is compared with server info.
	 * 
	 * If not connect to the internet, given info is compared with user file on hand. This means you can only signin as the last used user.
	 * @param v
	 */
	public void OnLoginClick(View v){
		EditText passText = (EditText) findViewById(R.id.PasswordField);
		EditText userText = (EditText) findViewById(R.id.LoginField);
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
		
		
		passHash = User.hashToSHA256(passText.getEditableText().toString());
		
		
		if(InternetController.isInternetAvailable2(this)){
			Thread thread = new loginThread(givenUsername, passHash);
			thread.start();
			
		} else {
			User fileUser = UserController.getUser();
			
			if(fileUser != null){
				if(fileUser.getUsername().equals(givenUsername)){
					if(Arrays.equals(fileUser.getpHash(), passHash)){
						runOnUiThread(launchAddClaims);
					} else {
						runOnUiThread(popToast);
					}
				} else {
					runOnUiThread(popToast);
				}
			} else {
				runOnUiThread(popToast);
			}
		}
		
	}
	
	/**
	 * Used to get to search activity when login was not working
	 * @param menu
	 */
	public void SearchOption(MenuItem menu)
    {
    	Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	
	/**
	 * Used to get to AddClaimActivity when login was not working
	 * @param menu
	 */
	public void AddClaimMenu(MenuItem menu)
    {
    	Intent intent = new Intent(LoginActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }
	
	/**
	 * Launches CreateUserActivity if connected to the internet.
	 * @param menu
	 */
	public void ToNewUserActivity(MenuItem menu)
    {
		
		if(InternetController.isInternetAvailable2(this)){
			Toast.makeText(this, "Create User", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Cannot Create User offline", Toast.LENGTH_SHORT).show();
		}
    }
	
	/**
	 * Checks for Internet to ensure the users can create an account.
	 * @param View v
	 */
	public void RegisterButton(View v)
    {
		
		if(InternetController.isInternetAvailable2(this)){
			Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Cannot Create User offline", Toast.LENGTH_SHORT).show();
		}
    }
	
	/**
	 * When connected to the internet, given info is compared with server info.
	 * 
	 * When not connect to the internet, given info is compared with user file on hand.
	 */
	class loginThread extends Thread {
		
		private String givenUsername;
		private byte[] givenPassHash;
		
		public loginThread(String username, byte[] passHash){
			this.givenUsername = username;
			this.givenPassHash = passHash;
		}
		
		public void run() {
			User pulledUser = FileManager.getSaver().getUser(givenUsername);
			
			if(pulledUser != null){
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
			} else {
				runOnUiThread(popToast);
			}
			
		}
		
	}
}
