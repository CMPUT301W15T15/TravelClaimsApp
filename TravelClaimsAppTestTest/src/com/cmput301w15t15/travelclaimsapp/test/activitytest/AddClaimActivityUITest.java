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
		newClaimButton = (Button) activity.findViewById(R.id.add_claim_button);
	}
	
	
	
	//test #

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		ClaimListController.removeClaim(claim1);
	}
	/**
	 * AddClaimActivityUITest#1
	 * 
	 * Tests the deleting claim from context menu
	 * 
	 * @throws IOException
	 */
	public void testContextMenuPopupDelete() throws IOException{
		claim1 = new Claim("t1");
		ClaimListController.addClaim(claim1);
		boolean test;
		adaptor.notifyDataSetChanged();
		
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				listView.requestFocus();
				listView.setSelection(ClaimListController.getClaimList().size()-1);
				
				}
		});
		test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_delete_claim, 0);
		instrumentation.waitForIdleSync();
		

		//assertTrue(test);
		assertNull("claim 't1' was not deleted", claimList.getClaim("t1"));
		ClaimListController.removeClaim(claim1);
	}
	//test #
	/**
	 * AddClaimActivityUITest#2
	 * 
	 * Tests adding expense functionality from context menu
	 * 
	 * @throws IOException
	 */
	public void testContextMenuPopupAddExpense() throws IOException{
		//add a monitor that waits for AddExpenseActivity to start
		ActivityMonitor activityMonitor = new ActivityMonitor(EditExpenseActivity.class.getName(), null, false);

		instrumentation.addMonitor(activityMonitor);
		
		claim1 = new Claim("t1");
		ClaimListController.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				listView.requestFocus();
				listView.setSelection(ClaimListController.getClaimList().size());	
				}
		});
		boolean test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_addExpense, 0);
		instrumentation.waitForIdleSync();
	
		
		assertEquals("expense not added",1, claim1.getExpenseList().size());
		ClaimListController.removeClaim(claim1);
		
	}

	/**
	 * AddClaimActivityUITest#3
	 * 
	 * Tests submit claim functionality from context memu
	 * 
	 * @throws IOException
	 */
	public void testContextMenuPopupSubmitClaim() throws IOException{

		claim1 = new Claim("t1");
		ClaimListController.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				listView.requestFocus();
				listView.setSelection(ClaimListController.getClaimList().size()-1);	
				}
		});
		boolean test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_submit_claim, 0);
		instrumentation.waitForIdleSync();
		assertEquals("Claim status did not change", "Submitted", claim1.getStatus());
		
		ClaimListController.removeClaim(claim1);
	}
	/**
	 * AddClaimActivityUITest#4
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
