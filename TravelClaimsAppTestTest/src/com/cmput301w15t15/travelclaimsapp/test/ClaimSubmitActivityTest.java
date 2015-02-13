package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.ClaimSubmitActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;

public class ClaimSubmitActivityTest extends
		ActivityInstrumentationTestCase2<ClaimSubmitActivity> {

	public ClaimSubmitActivityTest() {
		super(ClaimSubmitActivity.class);
	}

	
	@Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        ClaimSubmitActivity mClickFunActivity = getActivity();
        //Button mClickMeButton = (Button) 
                //mClickFunActivity.findViewById(R.id.launch_next_activity_button);
    }

	//TestNumber:7.1
    public void testSubmitButtonClick() {
        View mClickMeButton = null;
		TouchUtils.clickView(this, mClickMeButton);
        //Do some other testing afterward
		//all information valid
    }

}
