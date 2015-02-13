package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Instrumentation.ActivityMonitor;
import android.widget.Button;

import junit.framework.TestCase;

public class ClaimSubmitTest extends TestCase {

	public ClaimSubmitTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

public void testSubmitButton(){
		
		
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = approve.getInstrumentation().addMonitor(NextActivity.class.getName(), null, false);

		// open current activity.
		myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(com.cmput301w1t15.travelclaimsapp.R.id.SUBMIT);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		nextActivity.finish();
	}
	


	public void testSubmitedEditable(){
		
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				button.performClick();
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("amount EditText not set", "30", inputAmount.getText().toString());
		assertEquals("date EditText not set", "2015-02-12", inputDate.getText().toString());
		assertEquals("description EditText not set", "test", inputDescription.getText().toString());
	}
		
		
		
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = Approve.getInstrumentation().addMonitor(NextActivity.class.getName(), null, false);

		// open current activity.
		myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(com.cmput301w1t15.travelclaimsapp.R.id.SUBMIT);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		// next activity is opened and captured.
		assertNull(nextActivity);
		nextActivity.finish();
	}

	public void testSubmitViewDisplay(){
	
	}
}
