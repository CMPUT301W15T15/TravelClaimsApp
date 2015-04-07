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


import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;






import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class for testing some of the EditClaimActivity UI
 *
 */
public class EditClaimActivityUITest extends
		ActivityInstrumentationTestCase2<EditClaimActivity> {
	

	private Instrumentation instrumentation;
	private Activity activity;
	private EditText inputName;
	private EditText inputStartDate;
	private EditText inputEndDate;
	boolean bool1;
	private Claim claim;
	private Intent intent;
	private Button b;
	
	public EditClaimActivityUITest() {
		super(EditClaimActivity.class);
		//Need to change the id's once UI has been made
	}

	protected void setUp() throws Exception {
		super.setUp();

		claim = new Claim("Claim1");
		ClaimListController.addClaim(claim);

		intent = new Intent();
		intent.putExtra("claimName", "Claim1");
		setActivityIntent(intent);
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		instrumentation = getInstrumentation();
		GeoLocationController.initializeLocationManager(activity.getApplicationContext());
		inputName = (EditText) activity.findViewById(R.id.Edit_Claim_Name2);
		inputStartDate = (EditText) activity.findViewById(R.id.Claim_Start_Date);
		inputEndDate = (EditText) activity.findViewById(R.id.Claim_Finish_Date);
		b = (Button) activity.findViewById(R.id.AddDestinationButton2);
	}
	
	/**
	 * EditClaimActivityUITest#1
	 * 
	 * Testing setting edittext values
	 */
	public void testSettingValues(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				inputName.setText("Claim2");
				inputStartDate.setText("11/02/2014");
				inputEndDate.setText("12/20/2014");
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("Claim2", inputName.getText().toString());
		assertEquals("11/02/2014", inputStartDate.getText().toString());
		assertEquals("12/20/2014", inputEndDate.getText().toString());
		
		
	}
	/**
	 * EditClaimActivityUITest#2
	 * 
	 * Test add destination dialog popup
	 * 
	 * TODO:
	 * 	Currently does nothing, need to find a way
	 * 	to check that a alert dialog has been created 
	 */
	public void testShowDestinationDialog(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				b.performClick();
				
			}
		});
		instrumentation.waitForIdleSync();
		
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		ClaimListController.removeClaim(claim);
		
	}
	



	

	
	

	
	
}
