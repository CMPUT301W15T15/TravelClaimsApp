package com.cmput301w15t15.travelclaimsapp.activitys;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
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
			e1.printStackTrace();
		}
		
		try {
			passHash = md.digest(passText.getEditableText().toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//try to get user then try to compare hashes
//		try{
//			
//		} catch () {
//			
//		}
		
		
		
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
}
