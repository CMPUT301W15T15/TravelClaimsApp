package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateUserActivity extends Activity {

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

}
