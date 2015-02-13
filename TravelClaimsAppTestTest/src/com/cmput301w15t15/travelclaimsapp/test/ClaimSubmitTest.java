package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.widget.Button;

import junit.framework.TestCase;

public class ClaimSubmitTest extends TestCase {
	private Activity myActivity;
	
	

	public ClaimSubmitTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSubmitButton(){
	
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NextActivity.class.getName(), null, false);

		  // open current activity.
		  MyActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(com.company.R.id.open_next_activity);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		  nextActivity .finish();
	}
	

}
