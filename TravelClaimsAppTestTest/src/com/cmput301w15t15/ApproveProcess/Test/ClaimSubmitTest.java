package com.cmput301w15t15.ApproveProcess.Test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.widget.Button;
import junit.framework.TestCase;

public class ClaimSubmitTest extends TestCase {
	private String status;
	private Activity myActivity;
	private Activity nextActivity;
	

	public ClaimSubmitTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();

	   
	}
	
//<<<<<<< HEAD:TravelClaimsAppTestTest/src/com/cmput301w15t15/ApproveProcess/Test/ClaimSubmitTest.java
	public void testSubmitStatus{
		git
	}
//=======
	public void testSubmitButton(){
		
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NextActivity.class.getName(), null, false);

		// open current activity.
		myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(com.cmput301w1t15.travelclaimsapp.R.id.open_next_activity);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
		NextActivity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		nextActivity .finish();
	}
	

	public void testSubmitedEditable(){
		
	}
	
	public void testSubmitViewDisplay(){
		
	}

}
