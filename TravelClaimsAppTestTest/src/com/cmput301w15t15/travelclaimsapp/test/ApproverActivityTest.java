package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.ApproverActivity;
import com.cmput301w15t15.travelclaimsapp.ClaimSubmitActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;

public class ApproverActivityTest extends
		ActivityInstrumentationTestCase2<ApproverActivity> {

	public ApproverActivityTest() {
		super(ApproverActivity.class);
	}

	@Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        ApproverActivity mClickFunActivity = getActivity();
        //Button mClickMeButton = (Button) 
                //mClickFunActivity.findViewById(R.id.launch_next_activity_button);
    }

	//TestNumber:8.6
    public void testApproveButtonClick() {
        View mClickMeButton = null;
		TouchUtils.clickView(this, mClickMeButton);
        //Do some other testing afterward
    }

    //TestNumber:8.7
    public void testReturnButtonClick() {
        View mClickMeButton = null;
		TouchUtils.clickView(this, mClickMeButton);
        //Do some other testing afterward
		//comment field is not null
    }
    
    

}
