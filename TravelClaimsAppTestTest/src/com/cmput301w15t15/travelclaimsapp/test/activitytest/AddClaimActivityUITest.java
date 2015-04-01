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

import java.io.IOException;

import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.test.modeltest.ClaimListControllerTest;



import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

/**
 * Tests the AddClaimActivity
 *
 */
public class AddClaimActivityUITest extends ActivityInstrumentationTestCase2<AddClaimActivity> {

	private Activity activity;
	private Instrumentation instrumentation;
	private Button newClaimButton;
	private ClaimList claimList;
	private ListView listView;
	private ClaimListAdaptor adaptor;
	Claim claim1;
	
	public AddClaimActivityUITest() {
		super(AddClaimActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);
		activity = getActivity();
		claimList = ClaimListController.getClaimList();
	
		instrumentation = getInstrumentation();
		listView = (ListView) activity.findViewById(R.id.claim_list_listview);
		adaptor = new ClaimListAdaptor(activity, R.id.claim_list_listview, claimList.toArrayList());
		newClaimButton = (Button) activity.findViewById(R.id.Add_Claim_Button2);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		ClaimListController.removeClaim(claim1);
	}
	
	/**
	 * AddClaimActivityUITest#1
	 * 
	 * Tests that the add claim activity button starts the EditClaimActivity 
	 */
	public void testNewClaimButtonPress(){
		ActivityMonitor activityMonitor = new ActivityMonitor(EditClaimActivity.class.getName(), null, false);
		instrumentation.addMonitor(activityMonitor);
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				newClaimButton.performClick();
			}
		});
		instrumentation.waitForIdleSync();
		
		Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
		assertNotNull(nextActivity);	
		
		
	}
}
