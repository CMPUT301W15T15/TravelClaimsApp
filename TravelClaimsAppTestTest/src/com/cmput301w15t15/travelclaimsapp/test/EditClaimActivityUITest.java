package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
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
	
	
	public EditClaimActivityUITest() {
		super(EditClaimActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();
		
		inputName = (EditText) activity.findViewById(id);
		inputDestination = (EditText) activity.findViewById(id);
		inputReason = (EditText) activity.findViewById(id);
		inputStartDate = (EditText) activity.findViewById(id);
		inputEndDate = (EditText) activity.findViewById(id);
		
		claimList = ClaimListController.getClaimList();
		claim = new Claim("The Claim");
		claimList.addClaim(claim);
	}
	//test #
	public void testSettingValues(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				inputName.setText("Claim1");
				inputDestination.setText("Rome");
				inputReason.setText("Work");
				inputStartDate.setText("2015-02-11");
				inputEndDate.setText("2015-03-11");
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("Claim1", inputName.getText().toString());
		assertEquals("Rome", inputDestination.getText().toString());
		assertEquals("Work", inputReason.getText().toString());
		assertEquals("2015-02-11", inputStartDate.getText().toString());
		assertEquals("2015-03-11", inputEndDate.getText().toString());
	}
	// test #
	public void testAddExpenseButton(){
		
	}
	
	
	private void addDestination(String destination, String reason){
		
	}
	
}
