package com.cmputw15t15.travelclaimsapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ApproverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver, menu);
		return true;
	}

}