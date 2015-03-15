package com.cmput301w15t15.travelclaimsapp.activitys;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends Activity {

	// Thread that close the activity after finishing add
	private Runnable doFinishAdd = new Runnable() {
		public void run() {
			Toast.makeText(CreateUserActivity.this, "Try your new account", Toast.LENGTH_SHORT).show();
			finish();
		}
	};
	
	//duplicate user
	private Runnable popToast = new Runnable() {
		public void run() {
			Toast.makeText(CreateUserActivity.this, "Username Already Exists.", Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_user, menu);
		return true;
	}

	
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
		}
		
		
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
		Thread thread = new tryToAddUserThread(newUser);
		thread.start();
		
	}
	

	
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
