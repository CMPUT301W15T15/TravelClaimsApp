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



























