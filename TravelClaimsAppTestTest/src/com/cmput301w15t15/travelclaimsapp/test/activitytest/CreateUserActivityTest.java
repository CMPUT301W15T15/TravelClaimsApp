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
package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.CreateUserActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.MainMenuActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.MapActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Tests for the CreateUserActivity
 *
 */
public class CreateUserActivityTest extends
		ActivityInstrumentationTestCase2<CreateUserActivity> {

	private Instrumentation instrumentation;
	private Button create;
	private EditText username;
	private EditText password;
	private EditText passwordAgain;
	private CheckBox ifApprover;
	private Activity activity;
	private ImageButton geolocation;
	
	public CreateUserActivityTest() {
		super(CreateUserActivity.class);
	}
	

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		FileManager.initializeSaver(activity);
		
		//need to change ids once UI has been created 
		create = (Button) activity.findViewById(R.id.CreateUserButton);
		username = (EditText) activity.findViewById(R.id.NewUsernameEditText);
		password = (EditText) activity.findViewById(R.id.NewPasswordEditText);
		passwordAgain = (EditText) activity.findViewById(R.id.NewPassAEditText);
		ifApprover = (CheckBox) activity.findViewById(R.id.ApproverCheckBox);
		geolocation = (ImageButton) activity.findViewById(R.id.NewUserGeoLocationButton);
		
	}
	
	//Test for valid inputs
		//Test Case: CreateUserActivityTest#1
			/**
			 * Checks correct input works as intended.
			 */
//			public void testCorrectInput(){
//				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
//				instrumentation.addMonitor(activityMonitor);
//				instrumentation.runOnMainSync(new Runnable() {
//					
//					@Override
//					public void run() {
//						username.setText("Shelby");
//						password.setText("Sunshine");
//						passwordAgain.setText("Sunshine");
//						create.performClick();
//						
//					}
//				});
//				instrumentation.waitForIdleSync();
//				
//				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
//				assertNotNull(nextActivity);
//			}
			
			//Test Case: CreateUserActivityTest#2
			/**
			 * Checks mismatch passwords do not create users.
			 */
			public void testMismatchPassInput(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("Shelby");
						password.setText("Sunshine");
						passwordAgain.setText("Sunshinee");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#3
			/**
			 * Checks when no password given, do not create users.
			 */
			public void testNoPassInput(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("Shelby");
						password.setText("");
						passwordAgain.setText("Sunshinee");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#4
			/**
			 * Checks when no passwordAgain given, do not create users.
			 */
			public void testNoPass2Input(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("Shelby");
						password.setText("Sunshine");
						passwordAgain.setText("");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#5
			/**
			 * Checks when no password fields given, do not create users.
			 */
			public void testNoPass3Input(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("Shelby");
						password.setText("");
						passwordAgain.setText("");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#6
			/**
			 * Checks when no username given, do not create users.
			 */
			public void testNoUserInput(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("");
						password.setText("Sunshine");
						passwordAgain.setText("Sunshine");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#7
			/**
			 * Checks when no no input fields are given, do not create users.
			 */
			public void testNoNothingInput(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("");
						password.setText("");
						passwordAgain.setText("");
						create.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test case: CreateUserActivityTest#8
			/**
			 * Tests what happens when the geolocation button is selected
			 */
			public void testPressGeoLocationButton(){
				ActivityMonitor activityMonitor = new ActivityMonitor(MapActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("");
						password.setText("");
						passwordAgain.setText("");
						geolocation.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNotNull(nextActivity);
			}
			
			//Test Case: CreateUserActivityTest#8
			/**
			 * Checks Approver users can be created.
			 */
//			public void testApproverInput(){
//				ActivityMonitor activityMonitor = new ActivityMonitor(MainMenuActivity.class.getName(), null, false);
//				instrumentation.addMonitor(activityMonitor);
//				instrumentation.runOnMainSync(new Runnable() {
//					
//					@Override
//					public void run() {
//						username.setText("Jeremy");
//						password.setText("Clarkson");
//						passwordAgain.setText("Clarkson");
//						create.performClick();
//						ifApprover.setChecked(true);
//						
//					}
//				});
//				instrumentation.waitForIdleSync();
//				
//				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
//				assertNotNull(nextActivity);
//			}
//	
}
