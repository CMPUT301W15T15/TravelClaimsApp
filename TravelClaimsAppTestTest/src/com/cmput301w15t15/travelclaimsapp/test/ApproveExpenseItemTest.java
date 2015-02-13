package com.cmput301w15t15.travelclaimsapp.test;

import java.util.ArrayList;
import java.util.Date;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.Expense;
import com.cmput301w15t15.travelclaimsapp.ExpenseItem;

import junit.framework.TestCase;

public class ApproveExpenseItemTest extends TestCase {

	private Claim Claim1;
	private Claim Claim2;
	private Expense Expense1;
	private Expense Expense2;
	
	public ApproveExpenseItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	@SuppressWarnings("null")
	public void testViewExpenseItem(){
		ClaimList testClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		Expense1 = new Expense("Expense1");
		
		Claim1.setStatus("Submitted");
		Claim1.addExpense(Expense1);
		

		testClaimList.addClaim(Claim1);
		testClaimList.addClaim(Claim2);
		ArrayList<ExpenseItem> testItemList= new ArrayList<ExpenseItem>();
		ExpenseItem item1 = new ExpenseItem("food");
		ExpenseItem item2 = new ExpenseItem("meat");
		ExpenseItem item3 = new ExpenseItem("drink");
		testItemList.add(item1);
		testItemList.add(item2);
		testItemList.add(item3);
		
		Expense1.addItem(item1);
		Expense1.addItem(item2);
		Expense1.addItem(item3);
		
		assertTrue("Image no", Expense1.getItemList().equals(testItemList));
		
	}
	
	
}
