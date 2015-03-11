package com.cmput301w15t15.travelclaimsapp.test.activitytest;



import java.util.Date;
import java.util.GregorianCalendar;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;






import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

public class EditClaimActivityUITest extends
		ActivityInstrumentationTestCase2<EditClaimActivity> {
	

	private Instrumentation instrumentation;
	private Activity activity;
	private EditText inputName;
	private EditText inputDestination;
	private EditText inputReason;
	private EditText inputStartDate;
	private EditText inputEndDate;

	private ClaimList claimList;
	private Claim claim;
	private Intent intent;
	
	public EditClaimActivityUITest() {
		super(EditClaimActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		intent = new Intent();
		intent.putExtra("claimName", "testClaim");
		setActivityIntent(intent);
		setActivityInitialTouchMode(true);
		activity = getActivity();
		instrumentation = getInstrumentation();
		
		//Need to change the id's once UI has been made
		inputName = (EditText) activity.findViewById(R.id.Edit_Claim_Name);

		
		inputStartDate = (EditText) activity.findViewById(R.id.ClaimStart);
		inputEndDate = (EditText) activity.findViewById(R.id.ClaimEnd);
		
		FileManager.initializeSaver(activity);
		
		claimList = new ClaimList();
		claim = new Claim("The Claim");
		claimList.addClaim(claim);
		claimList.addClaim(new Claim("testClaim"));
	}
	//test case: EditClaimActivityUITest#1
	public void testSettingValues(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				inputName.setText("Claim1");
				inputStartDate.setText("11/02/2014");
				inputEndDate.setText("12/20/2014");
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("Claim1", inputName.getText().toString());
		assertEquals("2015-02-11", inputStartDate.getText().toString());
		assertEquals("2015-03-11", inputEndDate.getText().toString());
	}

	//test case: EditClaimActivityUITest#3
	public void testAddDestination(String destination, String reason){
		ClaimList claimL = ClaimListController.getClaimList();
		String selectedClaimName = activity.getIntent().getExtras().getString("claimName");
		Claim testClaim = claimL.getClaim(selectedClaimName);
		
		instrumentation.runOnMainSync(new Runnable() {
				
		@Override
		public void run() {

			}
		});
		instrumentation.waitForIdleSync();
		
		//assertEquals("destination was not added to claim with button press", "test1", testClaim.getDestinationList().get("test"));
		
		
	}
	//test case: EditClaimActivityUITest#4
	public void testSubmittedStatus(){
		String selectedClaimName = activity.getIntent().getExtras().getString("claimName");
		Claim testClaim = claimList.getClaim(selectedClaimName);
		testClaim.setStatus("Submitted");
		
		assertFalse(inputName.isFocusable());
		assertFalse(inputDestination.isFocusable());
		assertFalse(inputReason.isFocusable());
		assertFalse(inputEndDate.isClickable());
		assertFalse(inputStartDate.isClickable());
		
	}
	

	
	
}
