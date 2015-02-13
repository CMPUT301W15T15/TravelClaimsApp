package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import com.cmput301w15t15.travelclaimsapp.ApproverActivity;
import com.cmput301w15t15.travelclaimsapp.Claim;

import junit.framework.TestCase;

public class ClaimStatusTest extends TestCase {
	private String preStatus;
	private String afterStatus2;
	private Activity myActivity;
	private Claim Claim1;
	private Activity activity;
	private ApproverActivity approve;
	public ClaimStatusTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Claim1=new Claim("Claim1");
	}

	public void testSubmitStatus(){
		assertEquals("initial status process",Claim1.getClaimStatus(),"Process");
		final Button button = (Button) myActivity.findViewById(com.cmput301w15t15.travelclaimsapp.R.id.SUBMIT);
		assertEquals("changed to submited?",Claim1.getClaimStatus(),"Submitted");
	}
	
	
}
