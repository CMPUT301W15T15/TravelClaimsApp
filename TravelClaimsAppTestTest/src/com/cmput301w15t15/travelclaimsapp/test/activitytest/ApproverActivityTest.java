package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.activitys.ApproverActivity;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;

public class ApproverActivityTest extends
		ActivityInstrumentationTestCase2<ApproverActivity> {
	private Instrumentation instrumentation;
	private Activity activity;
	private Intent intent;
	
	public ApproverActivityTest() {
		super(ApproverActivity.class);
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

        ApproverActivity mClickFunActivity = getActivity();
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
