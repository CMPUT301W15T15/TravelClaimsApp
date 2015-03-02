package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;


import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;

public class AddClaimActivityUITest extends ActivityInstrumentationTestCase2<AddClaimActivity> {

	private Activity activity;
	private Instrumentation instrumentation;
	private Button newClaimButton;
	private ClaimList claimList;
	private ListView listView;
	private ClaimListAdaptor adaptor;
	
	public AddClaimActivityUITest() {
		super(AddClaimActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		claimList = new ClaimList();
		instrumentation = getInstrumentation();
		listView = (ListView) activity.findViewById(R.id.claim_list_listview);
		adaptor = new ClaimListAdaptor(activity, R.id.claim_list_listview, claimList.getClaimList());
		newClaimButton = (Button) activity.findViewById(R.id.add_claim_button);
	}
	//test #

	public void testContextMenuPopupDelete(){
		Claim claim1 = new Claim("t1");
		claimList.addClaim(claim1);

		adaptor.notifyDataSetChanged();
		

		listView.requestFocus();
		listView.setSelection(0);

		boolean test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_delete_claim, 0);
		
		instrumentation.waitForIdleSync();
	
		assertTrue(test);
		assertNull("claim 't1' was not deleted", claimList.getClaim("t1"));
		
	}
	//test #

	public void testContextMenuPopupAddExpense(){
		//add a monitor that waits for AddExpenseActivity to start
		ActivityMonitor activityMonitor = new ActivityMonitor("EditExpenseActivity", null, false);

		instrumentation.addMonitor(activityMonitor);
		
		Claim claim1 = new Claim("t1");
		claimList.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		

		listView.requestFocus();
		listView.setSelection(0);

		
		boolean test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_addExpense, 0);
		instrumentation.waitForIdleSync();
	
		Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
		assertTrue(test);
		assertNotNull("The Activity is null", nextActivity);
		
	}

	//test #
	public void testContextMenuPopupSubmitClaim(){
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SEND);
		//add a monitor that waits for a Intent.ACTION_SEND and blocks email attempt
		ActivityMonitor activityMonitor = new ActivityMonitor(intentFilter, null, true);
		instrumentation.addMonitor(activityMonitor);
		
		Claim claim1 = new Claim("t1");
		claimList.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		
		listView.requestFocus();
		listView.setSelection(0);
	
		boolean test = instrumentation.invokeContextMenuAction(activity, R.id.cmenu_submit_claim, 0);
		instrumentation.waitForIdleSync();
		
		Activity activity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
		assertTrue(test);
		
		assertNotNull(activity);	
	}
	//test #
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
