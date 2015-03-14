package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.IOException;
import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
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
		claimList = ClaimListController.getClaimList();
		//ArrayList<Claim> cl = ClaimListController.getClaimList().toArrayList();
		//for(Claim claim : cl){
		//	ClaimListController.deleteClaim(claim);
		//}
		
		currentSize = claimList.toArrayList().size();
		claim = new Claim("c1");
		claim2 = new Claim("c2");
		claim3 = new Claim("c3");
		claim4 = new Claim("c4");

		expense = new Expense("e1", null, null, null, null, 0);	

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
		
		assertEquals("ClaimList loaded from file not the same as current", claimList, claimList2);
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
		ClaimListController.addExpense(expense, claim4);
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
	
	
	
}
