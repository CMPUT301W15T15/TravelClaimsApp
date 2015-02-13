package com.cmput301w15t15.travelclaimsapp.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import com.cmput301w15t15.travelclaimsapp.ApproverActivity;
import com.cmput301w15t15.travelclaimsapp.Claim;

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
	public void testSubmitStatus(){
		Claim1.setApprover("MyBoss");
		assertEquals("The approver is not matched",Claim1.getApprover(),"MyBoss");

	}
	
	
	//TestNumber:SubmitStatusTest #2
	public void testSubmitClaimEditable(){
		Claim2 = new Claim("Claim2");
		if (Claim2.getStatus().equals("Submitted")) {
			assertTrue("A claim in Submitted status should not be editable",Claim2.getEditable() == false);
		}
		
		else if (Claim2.getStatus().equals("Process")) {
			assertTrue("A claim in Process status should be editable", Claim2.getEditable() == true);
		} 
		
		
		else if (Claim2.getStatus().equals("Returned")) {
			assertTrue("A claim in Return status should be editable", Claim2.getEditable() == true);
		}
	}
	
	
}



























