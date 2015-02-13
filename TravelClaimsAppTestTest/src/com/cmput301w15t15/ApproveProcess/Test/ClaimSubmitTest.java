package com.cmput301w15t15.ApproveProcess.Test;

import com.cmput301w15t15.travelclaimsapp.Claim;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.widget.Button;
import junit.framework.TestCase;

public class ClaimSubmitTest extends TestCase {
	private String status1;
	private String status2;
	private Activity myActivity;
	private Activity nextActivity;
	private Claim Claim1;
	
	public ClaimSubmitTest() {
		super();	
	}
	

	

	protected void setUp() throws Exception {
		super.setUp();
		Claim1=new Claim("Claim1");
		status1=Claim1.getClaimStatus();
	   
	}
	

	public void testSubmitStatus(){
		assertEquals("initial status process",Claim1.getClaimStatus(),"Process");
		final Button button = (Button) myActivity.findViewById(com.cmput301w1t15.travelclaimsapp.R.id.open_next_activity);
		assertEquals("changed to submited?",Claim1.getClaimStatus(),"Submitted");
	}
	

	public void testSubmitButton(){
		
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NextActivity.class.getName(), null, false);

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
		
	}
	
	public void testSubmitViewDisplay(){
		
	}

}
