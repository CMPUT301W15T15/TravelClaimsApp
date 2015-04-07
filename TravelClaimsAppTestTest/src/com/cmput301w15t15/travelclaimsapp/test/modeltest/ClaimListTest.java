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
import java.util.Locale;

import android.test.AndroidTestCase;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;


/**
 * Tests adding, deleting and sorting a {@link ClaimList}
 *
 */
public class ClaimListTest extends AndroidTestCase{
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
	private Claim claim4;
	private Claim claim5;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		claimList = new ClaimList();
		
		claim1 = new Claim("Claim1");
		claim2 = new Claim("Claim2");
		claim3 = new Claim("Claim3");
		claim4 = new Claim("Claim4");
		claim5 = new Claim("Claim5");
	}
	/**
	 * test: AddClaimTest#1
	 */
	public void testAddClaim(){
		claimList.addClaim(claim1);
		assertEquals("The length of the claimList is not two", this.claimList.size(),1);
	}
	/**
	 * test: AddClaimTest#2
	 */
	public void testDeleteClaim(){
		claimList.addClaim(claim1);
		claimList.removeClaim(claim1);
		assertEquals("Claim was not removed from claimList", claimList.size(),0); 
	}
	/**
	 * test: AddClaimTest#3
	 */
	public void testAddClaimDuplicate(){
		claimList.addClaim(new Claim("Claim4"));
		claimList.addClaim(new Claim("Claim4"));
		
		assertEquals("Duplicate claimname add", 1, claimList.size());
	}
	/**
	 * test: AddClaimTest#4
	 */
	public void testClaimListSort(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		
		try {
			claim1.setStartDate(sdf.parse("11/21/2015"));
			claim2.setStartDate(sdf.parse("12/02/2013"));
			claim3.setStartDate(sdf.parse("12/04/2013"));
			claim4.setStartDate(sdf.parse("12/20/2015"));
			
		} catch (ParseException e) {
		
		}
		claimList.addClaim(claim1);
		claimList.addClaim(claim2);
		claimList.addClaim(claim5);
		claimList.addClaim(claim3);
		claimList.addClaim(claim4);
		
		claimList.sort();
		assertEquals("ClaimList not sorted", "Claim4",claimList.toArrayList().get(0).getName());
		assertEquals("ClaimList not sorted", "Claim1",claimList.toArrayList().get(1).getName());
		assertEquals("ClaimList not sorted", "Claim3",claimList.toArrayList().get(2).getName());
		assertEquals("ClaimList not sorted", "Claim5",claimList.toArrayList().get(4).getName());
	}
	
}
