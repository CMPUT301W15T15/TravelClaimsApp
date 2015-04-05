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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.Tag;
import android.test.AndroidTestCase;

/**
 * Tests class for testing {@link ClaimListController}
 */
public class ClaimListControllerTest extends AndroidTestCase {

	private ClaimList claimList;
	private ClaimList claimList2;
	private Claim claim;
	private Claim claim2;
	private Claim claim3;
	private Claim claim4;
	private Expense expense;
	private int currentSize; 

	
	public ClaimListControllerTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		mContext = getContext();
		FileManager.initializeSaver(mContext);
		ClaimListController.removeAllClaims();
		claimList = ClaimListController.getClaimList();
		
		currentSize = claimList.toArrayList().size();
		claim = new Claim("c1");
		claim2 = new Claim("c2");
		claim3 = new Claim("c3");
		claim4 = new Claim("c4");

		expense = new Expense("e1");	

	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		ClaimListController.removeClaim(claim);
		ClaimListController.removeClaim(claim4);
		ClaimListController.removeClaim(claim2);
		ClaimListController.removeClaim(claim3);
	}
	
	/**
	 * ClaimListControllerTest #1
	 * 
	 */
	public void testClaimListListeners(){

		ClaimListController.addClaim(claim);
		claimList2 = ClaimListController.getClaimList();
		
		assertEquals("ClaimList loaded from file not the same as current", claimList.getClaim("c1"), claimList2.getClaim("c1"));
	}
	
	/**
	 * ClaimListControllerTest #2
	 */
	public void testClaimListeners(){
		ClaimListController.addClaim(claim3);
		claim3.setName("test");
		claimList2 = ClaimListController.getClaimList();
		
		Claim claim2 = claimList2.getClaim("test");
		assertNotNull(claim2);
		
		assertEquals("not the same size", currentSize+1, claimList2.size());
		assertEquals("Claim loaded from file not the same as current", claim2.getName(), claim3.getName());
		
		
	}
	/**
	 * ClaimListControllerTest #3
	 */
	public void testExpenseListeners() throws IOException{
		ClaimListController.addClaim(claim4);
		ExpenseListController elc = new ExpenseListController(claim4.getName(),false);
		elc.addExpense(expense);
		claimList2 = ClaimListController.getClaimList();
		Claim claim2 = claimList2.getClaim("c4");
	
		assertEquals("Claim loaded from file not the same as current", claim4.getName(), claim2.getName());
		
		expense.setDes("Something");
	

		Expense expense2 = ClaimListController.getClaimList().getClaim("c4").getExpenseList().toArrayList().get(0);
		if(!expense.getDes().equals("Something")){
			fail("description was not set");
		}else{
			assertEquals("Expense loaded from file not the same as current", expense, expense2);
		}
	}
	
	/**
	 * ClaimListControllerTest #4
	 * 
	 * Test getting a list of all unique tags in claimlist
	 */
	public void testGetClaimListTagList(){
		ClaimListController.addClaim(claim);
		ClaimListController.addClaim(claim2);
		ClaimListController.addClaim(claim3);
		ClaimListController.addClaim(claim4);
		Tag tag1 = new Tag("a");
		Tag tag2 = new Tag("b");
		
		ClaimListController.addTag(claim, tag1);
		ClaimListController.addTag(claim, new Tag("c"));
		ClaimListController.addTag(claim, tag2);
		
		ClaimListController.addTag(claim2, new Tag("a"));
		ClaimListController.addTag(claim2, new Tag("e"));
		
		ClaimListController.addTag(claim3, new Tag("c"));
		
		ArrayList<Tag> tags = ClaimListController.getTagList();
		
		assertEquals("Number of tags returned not correct",4,tags.size());
		
		ClaimListController.removeTag(claim, tag1);
		tags = ClaimListController.getTagList();
		assertEquals("Number of tags returned not correct",4,tags.size());
		
		ClaimListController.removeTag(claim, tag2);
		tags = ClaimListController.getTagList();
		assertEquals("Number of tags returned not correct",3,tags.size());
	}
	
	/**
	 * ClaimListControllerTest #5
	 * 
	 * Tests filtering claim list by a single tag or multiple tags 
	 */
	public void testFilterClaimList(){
		String filterString;
		ClaimListController.addClaim(claim);
		ClaimListController.addClaim(claim2);
		ClaimListController.addClaim(claim3);
		ClaimListController.addClaim(claim4);
		Tag tag1 = new Tag("a");
		Tag tag2 = new Tag("b");
		
		ClaimListController.addTag(claim, tag1);			//a,c,v
		ClaimListController.addTag(claim, new Tag("c"));
		ClaimListController.addTag(claim, tag2);
		
		ClaimListController.addTag(claim2, new Tag("a"));	//a, e
		ClaimListController.addTag(claim2, new Tag("e"));
		
		ClaimListController.addTag(claim3, new Tag("c"));	//c
		
		ClaimListController.addTag(claim4, new Tag("f"));	//f
		
		ArrayList<Tag> tags = ClaimListController.getTagList();
		
		//give a filter that has 2 matches 
		filterString = new String("a");
		
		ArrayList<Claim> filtered = ClaimListController.getFilteredClaimList(filterString);
		
		assertEquals("ClaimList was not filtered", 2, filtered.size());
		assertTrue("ClaimList was not filtered correctly", filtered.indexOf(claim)>-1);
		assertTrue("ClaimList was not filtered correctly", filtered.indexOf(claim2)>-1);
		
		//give a filter that has three match
		filterString = new String("a, c");
		
		filtered = ClaimListController.getFilteredClaimList(filterString);
		
		assertEquals("ClaimList was not filtered", 3, filtered.size());
		assertTrue("ClaimList was not filtered correctly", filtered.indexOf(claim)>-1);
		
		//give a filter that has no matches
		filterString = new String("g,h");
		
		filtered = ClaimListController.getFilteredClaimList(filterString);
		
		assertEquals("ClaimList was not filtered", 0, filtered.size());
		assertTrue("ClaimList was not filtered correctly", filtered.indexOf(claim)==-1);
		
		//give a filter that has one matche
		filterString = new String("f, z,  r, m,k  ");
		
		filtered = ClaimListController.getFilteredClaimList(filterString);
		
		assertEquals("ClaimList was not filtered", 1, filtered.size());
		assertTrue("ClaimList was not filtered correctly", filtered.indexOf(claim4)>-1);
	}
	
	/**
	 * ClaimListController test #6
	 * 
	 * Test the incompleteFields function
	 */
	public void testincompleteFields(){
		ClaimListController.addClaim(claim);
		
		claim.setEndDate(new Date());	//test incomplete claim fields
		assertTrue(ClaimListController.incompleteFields(claim));
		
		claim.setStartDate(new Date());	//test complete claim fields
		assertFalse(ClaimListController.incompleteFields(claim));
		
		ExpenseListController elc = new ExpenseListController(claim.getName(),false);
		elc.addExpense(expense);
		
		expense.setFlag(1);				//test setting flag
		assertTrue(ClaimListController.incompleteFields(claim));
		
		expense.setFlag(0);				//test unsetting flag
		assertFalse(ClaimListController.incompleteFields(claim));
		
		
		
	}
}
