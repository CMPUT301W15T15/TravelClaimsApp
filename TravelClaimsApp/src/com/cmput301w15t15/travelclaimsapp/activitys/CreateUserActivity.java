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

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * CreateUserAcivity is used for creating a new User and pushing them to the server.
 * 
 * Can only be entered with an internet connection.
 */
public class CreateUserActivity extends Activity {
	
	private GeoLocation selectedGeoLocation; 
	
	/**
	 * Thread that closes the activity after finishing addUser.
	 */
	private Runnable doFinishAdd = new Runnable() {
		public void run() {
			Toast.makeText(CreateUserActivity.this, "Try your new account", Toast.LENGTH_SHORT).show();
			finish();
		}
	};
	
	/**
	 * Duplicate user.
	 */
	private Runnable popToast = new Runnable() {
		public void run() {
			Toast.makeText(CreateUserActivity.this, "Username Already Exists.", Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
		GeoLocationController.initializeLocationManager(this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_user, menu);
		return true;
	}

	
	/**
	 * On Clicking Create User, checks are made for empty fields, mismatching passwords and duplicate users on the server.
	 * 
	 * If all is successful a new user is stored to the server.
	 * @param v
	 */
	public void OnClickCreateUser(View v){
		EditText userText = (EditText) findViewById(R.id.NewUsernameEditText);
		EditText passText = (EditText) findViewById(R.id.NewPasswordEditText);
		EditText passAgainText = (EditText) findViewById(R.id.NewPassAEditText);
		CheckBox isApprover = (CheckBox) findViewById(R.id.ApproverCheckBox);
		MessageDigest md = null;
		byte[] passHash = null;
		
		
		//some error handling
		if(passText.getText().toString().equals("") || userText.getText().toString().equals("") || passAgainText.getText().toString().equals("")){
			Toast.makeText(this, "Invalid input fields.", Toast.LENGTH_LONG).show();
			return;
		}else if(!passText.getText().toString().equals(passAgainText.getText().toString())){
			Toast.makeText(this, "Mismatch Password.", Toast.LENGTH_LONG).show();
			return;
		}else if(selectedGeoLocation == null){
			Toast.makeText(this, "No home location selected.", Toast.LENGTH_LONG).show();
			return;
		}
		
		//hashes password
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			passHash = md.digest(passText.getEditableText().toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		User newUser = new User(userText.getText().toString(), passHash, isApprover.isChecked());
		GeoLocationController.setUserGeoLocation(newUser, selectedGeoLocation.getLatitude(), selectedGeoLocation.getLongitude());
		Thread thread = new tryToAddUserThread(newUser);
		thread.start();
		
	}
	
	public void selectHomeLocation(View v){
		Intent intent = GeoLocationController.pickLocationIntent(CreateUserActivity.this);
		startActivityForResult(intent, 20);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 20){
			switch (resultCode) {
			case RESULT_OK:
				Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();
				String geoString = data.getExtras().getString("geoLocation");
				selectedGeoLocation = GeoLocation.getFromString(geoString);
				break;
			case RESULT_CANCELED:
				Toast.makeText(this, "RESULT_CANCEL", Toast.LENGTH_SHORT).show();
				break;
				
			default:
				Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show();
				break;
			}
			
		}
		
		
	}
	
	/**
	 * Thread talks to server to see if given user was on it.
	 * 
	 * If no user is found, the newUser is added
	 *
	 */
	class tryToAddUserThread extends Thread {
		private User user;

		public tryToAddUserThread(User newUser) {
			this.user = newUser;
		}

		@Override
		public void run() {
			User checkUser = FileManager.getSaver().getUser(user.getUsername());
			
			if(checkUser == null){
				FileManager.getSaver().addUser(user);
				runOnUiThread(doFinishAdd);
			} else {
				runOnUiThread(popToast);
			}
			
		}
	}
	
	
}
