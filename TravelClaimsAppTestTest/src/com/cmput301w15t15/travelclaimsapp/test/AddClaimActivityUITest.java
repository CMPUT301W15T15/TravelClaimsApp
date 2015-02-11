package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

public class AddClaimActivityUITest extends ActivityInstrumentationTestCase2<AddClaimActivity> {

	private Activity activity;
	private Instrumentation instrumentation;
	private Button newClaimButton;
	private ClaimList claimList;
	private ListView listView;
	private ClaimListAdaptor adaptor;
	
	public AddClaimActivityUITest() {
		super(AddClaimActivity.claim);
		
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		claimList = ClaimListController.getClaimList();
		instrumentation = getInstrumentation();
		listView = (ListView) activity.findViewById(id);
		adaptor = new ClaimListAdaptor(activity.this, R.id, claimList);
	
	//test #
	public void testContextMenuPopupDelete(){
		Claim claim1 = new Claim("t1");
		claimList.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		
		listView.setSelection(0);
		listView.requestFocus();
		
		instrumentation.invokeContextMenuAction(activity, 0, 0);
		instrumentation.waitForIdleSync();
		
		assertNull("claim 't1' was not deleted", claimList.getClaim("t1"));
		
	}
	//test #
	public void testContextMenuPopupAddExpense(){
		//add a monitor that waits for AddExpenseActivity to start
		ActivityMonitor activityMonitor = new ActivityMonitor(AddExpenseActivity.class, null, false);
		instrumentation.addMonitor(activityMonitor);
		
		Claim claim1 = new Claim("t1");
		claimList.addClaim(claim1);
		adaptor.notifyDataSetChanged();
		
		instrumentation.invokeContextMenuAction(activity, 0, 0);
		
		AddExpenseActivity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
		assertNotNull("The AddExpenseActivity is null", nextActivity);
		
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
		
		instrumentation.invokeContextMenuAction(activity, 0, 0);
		
		AddExpenseActivity activity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
		assertNotNull(activity);
		
	}
	

}
