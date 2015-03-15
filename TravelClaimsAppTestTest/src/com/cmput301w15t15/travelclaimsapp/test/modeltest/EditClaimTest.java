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

import java.sql.Date;
import java.util.Map;
import java.util.Set;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import junit.framework.TestCase;

public class EditClaimTest extends TestCase {
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;

	protected void setUp() throws Exception {
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
		//claim1.setStartDate(2015, 2,5);
	}
	
	//test case: EditClaimTest#1
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
	
	//test case: EditClaimTest#2
	public void testEditClaimDate(){
		
	
		//claim1.setEndDate(new Date());
	
		//assertTrue("Claim end date set before claim start date", e.getMessage().equals("Claim.setEndDate was passed a illegal argument"));
		
		
		
		//claim1.setEndDate(2015, 3, 5);
		//claim1.setStartDate(2015, 4, 5);
	}

	
	
	
	
	

}
