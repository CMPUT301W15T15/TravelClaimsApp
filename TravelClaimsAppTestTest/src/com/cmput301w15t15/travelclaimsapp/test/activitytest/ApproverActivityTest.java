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

import com.cmput301w15t15.travelclaimsapp.activitys.ApproverClaimListActivity;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;

public class ApproverActivityTest extends 
	ActivityInstrumentationTestCase2<ApproverClaimListActivity> {
	private Instrumentation instrumentation;
	private Activity activity;
	private Intent intent;
	
	public ApproverActivityTest() {
		super(ApproverClaimListActivity.class);
	}

	@Override
    protected void setUp() throws Exception {
        super.setUp();
        intent = new Intent();
		intent.putExtra("claimName", "testClaim");
		setActivityIntent(intent);
		
		activity = getActivity();
		instrumentation = getInstrumentation();
		
        

        setActivityInitialTouchMode(true);

        ApproverClaimListActivity mClickFunActivity = getActivity();
        //Button mClickMeButton = (Button) 
                //mClickFunActivity.findViewById(R.id.launch_next_activity_button);
    }

	//TestNumber:ApproveCheckTest #1
    public void testApproveButtonClick() {
        //View mClickMeButton = null;
		//TouchUtils.clickView(this, mClickMeButton);
        //Do some other testing afterward
    }

    //TestNumber:ApproveCheckTest #2
    public void testReturnButtonClick() {
        //View mClickMeButton = null;
		//TouchUtils.clickView(this, mClickMeButton);
        //Do some other testing afterward
		//comment field is not null
    }
    
    
    
    
    

}
