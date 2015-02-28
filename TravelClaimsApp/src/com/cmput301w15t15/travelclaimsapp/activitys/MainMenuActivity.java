package com.cmput301w15t15.travelclaimsapp.activitys;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
		MessageDigest md = null;
		byte[] passHash = null;
		
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
		
		
		
	}
	
}
