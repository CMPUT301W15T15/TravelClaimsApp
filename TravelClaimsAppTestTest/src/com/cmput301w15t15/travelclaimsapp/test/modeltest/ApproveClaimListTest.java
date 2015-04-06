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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


import com.cmput301w15t15.travelclaimsapp.SubmittedListComparator;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import junit.framework.TestCase;

/**
 * Tests for list of submitted claims
 *
 */
public class ApproveClaimListTest extends TestCase {
	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
	private Claim claim4;
	private ClaimList claimList;
	
	
	public ApproveClaimListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	
	/**
	 * ApproveClaimListTest 1 
	 * 
	 * Tests commenting a claim
	 */
	public void testCommentSubmittedClaims() throws IOException {
		claimList = new ClaimList();
		claim1 = new Claim("Claim1");
		claim2 = new Claim("Claim2");
		claim3 = new Claim("Claim3");
		claim4 = new Claim("Claim4");
		claimList.addClaim(claim1);
		claimList.addClaim(claim2);
		claimList.addClaim(claim3);
		claimList.addClaim(claim4);
		claim1.setStatus("Submitted");
		claim2.setStatus("Submitted");
		claim3.setStatus("Submitted");
		claim4.setStatus("Submitted");
		ArrayList<Claim> testList = new ArrayList<Claim>();
		testList.add(claim1);
		testList.add(claim2);
		testList.add(claim3);
		testList.add(claim4);
		claim1.setComment("Too much spending on taxi");
		assertTrue("comment is added", claim1.getComment()=="Too much spending on taxi");	
		
	}
	
	/**
	 * ApproveClaimListTest 2
	 * 
	 * Tests sorting a list of claims using SubmittedListComparator
	 */
	public void testApproverListSort() throws IOException {
		claim1 = new Claim("Claim1");
		claim2 = new Claim("Claim2");
		claim3 = new Claim("Claim3");
		claim4 = new Claim("Claim4");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
		try{
			claim1.setStartDate(sdf.parse("4/5/2015"));
			claim2.setStartDate(sdf.parse("4/3/2015"));
			claim3.setStartDate(sdf.parse("4/4/2015"));
			claim4.setStartDate(sdf.parse("4/2/2015"));
		} catch (ParseException e) {
			
		}
		claimList = new ClaimList();
		claimList.addClaim(claim1);
		claimList.addClaim(claim2);
		claimList.addClaim(claim3);
		claimList.addClaim(claim4);
		
		ArrayList<Claim> cl = claimList.toArrayList();
		
		Collections.sort(cl, new SubmittedListComparator());
		
		assertTrue(cl.get(0).getName().equals(claim4.getName()));
		assertTrue(cl.get(1).getName().equals(claim2.getName()));
		assertTrue(cl.get(2).getName().equals(claim3.getName()));
		assertTrue(cl.get(3).getName().equals(claim1.getName()));
	}


	
	
}
