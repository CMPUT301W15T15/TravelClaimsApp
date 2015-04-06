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


import java.util.Date;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;




import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
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
	Claim claim1;
	ClaimList claimList = new ClaimList();
	private ListView listView;
	
	
	public AddClaimActivityUITest() {
		super(AddClaimActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);
		activity = getActivity();
		listView = (ListView) activity.findViewById(R.id.Claim_Listview);
		claimList = ClaimListController.getClaimList();
		instrumentation = getInstrumentation();
		newClaimButton = (Button) activity.findViewById(R.id.Add_Claim_Button2);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
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
	/**
	 * Test pressing the delete button in context menu
	 */
	public void testContextMenuDelete(){
		Claim claim2 = new Claim("c1");
		Claim claim3 = new Claim("c2");
		Claim claim4 = new Claim("c3");
		ClaimListController.addClaim(claim2);
		ClaimListController.addClaim(claim3);
		ClaimListController.addClaim(claim4);
		
		instrumentation.runOnMainSync(new Runnable() {
	

			@Override
			public void run() {
				listView.getChildAt(0).performLongClick();
				
			}
		});
		instrumentation.waitForIdleSync();
		instrumentation.invokeContextMenuAction(activity, R.id.cmenu_delete_claim, 0);
		
		assertEquals(claimList.size(),ClaimListController.getClaimList().size());
		
		ClaimListController.removeClaim(claim2);
		ClaimListController.removeClaim(claim3);
		ClaimListController.removeClaim(claim4);
	}

}
