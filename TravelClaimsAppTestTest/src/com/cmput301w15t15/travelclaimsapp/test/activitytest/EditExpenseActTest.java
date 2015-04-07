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
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

//import android.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EditExpenseActTest extends
		ActivityInstrumentationTestCase2<EditExpenseActivity> {

	protected Expense expense1 = new Expense("e1");
	private Instrumentation instrumentation;
	private ImageView imageView;
	private Activity activity;
	private Intent intent;
	private Claim claim1 = new Claim("c1");
	
	public EditExpenseActTest() {
		super(EditExpenseActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(true);
		//activity = getActivity();
		//FileManager.initializeSaver(activity);
		instrumentation = getInstrumentation();
	    
	}

	public void testExpenseName(){
		Claim claim1= new Claim("claim1");
		expense1 =  new Expense("expense1");
		expense1.setName("test 1");
		assertTrue("No Name",expense1.getName()!=null);
	}
	
//	public void testAddReceipt(){
//		
//		
//	
//		imageView = (ImageView) activity.findViewById(R.id.Edit_Expense_Image2);
//		ActivityMonitor activityMonitor = new ActivityMonitor(EditExpenseActivity.class.getName(), null, false);
//		instrumentation.addMonitor(activityMonitor);
//		instrumentation.runOnMainSync(new Runnable() {
//			
//			@Override
//			public void run() {
//	
//				imageView.performLongClick();
//				
//			}
//		});
//		instrumentation.waitForIdleSync();
//		
//		Activity nextActivity = instrumentation.waitForMonitorWithTimeout(activityMonitor, 3000);
//		assertNotNull(nextActivity);
//		
//
//	}
	
	
	
	
	
}
