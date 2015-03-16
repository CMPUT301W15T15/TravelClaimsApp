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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.test.AndroidTestCase;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import junit.framework.TestCase;

/**
 *	Tests for test editing {@link Claim}
 *
 */
public class EditClaimTest extends AndroidTestCase {

	

	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;

	protected void setUp() throws Exception {
		super.setUp();
		FileManager.initializeSaver(this.getContext());
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		ClaimListController.removeClaim(claim1);
		ClaimListController.removeClaim(claim2);
	}
	
	/**
	 * test case: EditClaimTest#1
	 */
	public void testEditClaimName(){

		try{
			claim1.setName("");
		}catch(IllegalArgumentException e){
			assertTrue("Claim name was set to equal empty string and not caught", e.getMessage().equals("Claim.setName was passed a illegal arguement"));
		}
		try{
			claim1.setName("Claim2");
		}catch(IllegalArgumentException e){
			assertTrue("Claim name was set to claim2's name and not caught", e.getMessage().equals("Claim.setName was passed a illegal argument"));
		}
	}
	
	/**
	 * test case: EditClaimTest#2
	 * @throws ParseException 
	 */
	public void testEditClaimDate() throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date = sdf.parse("01/16/2015");
		Date date2 = sdf.parse("01/15/2015");
		claim1.setEndDate(date);
	
		assertTrue("Claim end date set before claim start date", date.after(date2));
	}

}
