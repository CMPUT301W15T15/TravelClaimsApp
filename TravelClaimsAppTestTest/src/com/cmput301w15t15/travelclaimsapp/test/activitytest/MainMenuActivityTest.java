package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.MainMenuActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

public class MainMenuActivityTest extends
		ActivityInstrumentationTestCase2<MainMenuActivity> {

	private Instrumentation instrumentation;
	private Button login;
	private EditText username;
	private EditText password;
	private Activity activity;
	
	public MainMenuActivityTest() {
		super(MainMenuActivity.class);
	}
	

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		FileManager.initializeSaver(activity);
		
		//need to change ids once UI has been created 
		login = (Button) activity.findViewById(R.id.LoginButton);
		username = (EditText) activity.findViewById(R.id.UsernameEditText);
		password = (EditText) activity.findViewById(R.id.PasswordEditText);
		
		
	}
	
	//Test for valid inputs
	//Test Case: MainMenuActivityTest#1
		public void testRealUser(){
			ActivityMonitor activityMonitor = new ActivityMonitor(AddClaimActivity.class.getName(), null, false);
			instrumentation.addMonitor(activityMonitor);
			instrumentation.runOnMainSync(new Runnable() {
				
				@Override
				public void run() {
					username.setText("Shelby");
					password.setText("Sunshine");
					login.performClick();
					
				}
			});
			instrumentation.waitForIdleSync();
			
			Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
			assertNotNull(nextActivity);
		}
		
		//Test for valid inputs
		//Test Case: MainMenuActivityTest#2
			public void testFakeUser(){
				ActivityMonitor activityMonitor = new ActivityMonitor(AddClaimActivity.class.getName(), null, false);
				instrumentation.addMonitor(activityMonitor);
				instrumentation.runOnMainSync(new Runnable() {
					
					@Override
					public void run() {
						username.setText("Stig");
						password.setText("The");
						login.performClick();
						
					}
				});
				instrumentation.waitForIdleSync();
				
				Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
				assertNull(nextActivity);
			}
			
			//Test for valid inputs
			//Test Case: MainMenuActivityTest#3
				public void testRealUserBadPass(){
					ActivityMonitor activityMonitor = new ActivityMonitor(AddClaimActivity.class.getName(), null, false);
					instrumentation.addMonitor(activityMonitor);
					instrumentation.runOnMainSync(new Runnable() {
						
						@Override
						public void run() {
							username.setText("Shelby");
							password.setText("Mustang");
							login.performClick();
							
						}
					});
					instrumentation.waitForIdleSync();
					
					Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
					assertNull(nextActivity);
				}

				//Test for valid inputs
				//Test Case: MainMenuActivityTest#4
					public void testRealUserNoPass(){
						ActivityMonitor activityMonitor = new ActivityMonitor(AddClaimActivity.class.getName(), null, false);
						instrumentation.addMonitor(activityMonitor);
						instrumentation.runOnMainSync(new Runnable() {
							
							@Override
							public void run() {
								username.setText("Shelby");
								password.setText("");
								login.performClick();
								
							}
						});
						instrumentation.waitForIdleSync();
						
						Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
						assertNull(nextActivity);
					}

					//Test for valid inputs
					//Test Case: MainMenuActivityTest#5
						public void testRealUserNoUsername(){
							ActivityMonitor activityMonitor = new ActivityMonitor(AddClaimActivity.class.getName(), null, false);
							instrumentation.addMonitor(activityMonitor);
							instrumentation.runOnMainSync(new Runnable() {
								
								@Override
								public void run() {
									username.setText("");
									password.setText("Sunshine");
									login.performClick();
									
								}
							});
							instrumentation.waitForIdleSync();
							
							Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
							assertNull(nextActivity);
						}
	
}
