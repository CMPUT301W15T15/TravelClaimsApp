package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import com.cmput301w15t15.travelclaimsapp.activitys.ApproverActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;

import junit.framework.TestCase;

public class ClaimStatusTest extends TestCase {
	private Claim Claim1;
	private Claim Claim2;

	
	public ClaimStatusTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Claim1=new Claim("Claim1");
	}

	
	//TestNumber:SubmitStatusTest #1
	public void testSubmitClaimEditable(){
		Claim2 = new Claim("Claim2");
		if (Claim2.getStatus().equals("Submitted")) {
			assertTrue("A claim in Submitted status should not be editable",Claim2.isEditable() == false);
		}
		
		else if (Claim2.getStatus().equals("Process")) {
			assertTrue("A claim in Process status should be editable", Claim2.isEditable() == true);
		} 
		
		
		else if (Claim2.getStatus().equals("Returned")) {
			assertTrue("A claim in Return status should be editable", Claim2.isEditable() == true);
		}
	}
	
	
}



























