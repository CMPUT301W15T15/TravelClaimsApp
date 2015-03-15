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


import android.nfc.Tag;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;




import junit.framework.TestCase;


public class FilterTagTest extends TestCase
{
	private ClaimList claimList;
	private Claim claim1;
	private Claim claim2;
	private Claim claim3;
//	private Tag tag1=new Tag("Business");
	
	protected void setUp() throws Exception
	{
		super.setUp();
		claimList = ClaimListController.getClaimList();
		claim1 = new Claim("Claim1");
		claimList.addClaim(claim1);
		claim2 = new Claim("Claim2");
		claimList.addClaim(claim2);
		claim3 = new Claim("Claim3");
		claimList.addClaim(claim3);
		//claim1.addTag(tag1);
		//claim2.addTag(tag1);
	}
	//TestCase: FilterTagTest#1
	public void filterTagTest(){
//		assertTrue("the size is not correct!",this.claimList.filterTag(tag1).size()==2);
//		assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim1")==this.claim1);
//		assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim2")==this.claim2);
		//assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim1").getTag()==this.tag1);
		//assertTrue("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim2").getTag()==this.tag1);
//		assertFalse("this is not corret",this.claimList.filterTag(tag1).getClaim("Claim3")==this.claim3);
	}

}
