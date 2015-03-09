package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.IOException;
import java.util.Date;
import java.util.Map;


import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;


import junit.framework.TestCase;

public class ApproveExpenseListTest extends TestCase {
	private Claim claim1;
	private Claim claim2;
	private Expense expense1;
	private Expense expense2;
	
	public ApproveExpenseListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		
		
	}
	
	//TestNumber:ApproveViewTest #4
	public void testViewSubmittedExpense() throws IOException {
		ClaimList approveClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		expense1 = new Expense("Expense1", null, null, null, null, 0);
		
		approveClaimList.addClaim(claim1);
		claim1.setStatus("Submitted");
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);

		assertTrue("name is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getName().toString().equals("Expense1"));
		
		Date date = new Date();
		expense1.setDate(date);
		assertTrue("date is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getDate().equals(date));
		
		expense1.setDes("Description");
		assertTrue("Description is equal", approveClaimList.toArrayList()
				.get(0).getExpenseList().getExpense("Expense1").getDes().equals("Description"));
	}
	
	//TestNumber:ReceiptViewTest #1
	public void testViewExpenseReceipt() throws IOException{
		ClaimList testClaimList = new ClaimList();
		claim1 = new Claim("Claim1");
		expense1 = new Expense("Expense1", null, null, null, null, 0);
		expense2 = new Expense("Expense2", null, null, null, null, 0);
		
		claim1.setStatus("Submitted");
		ExpenseList expenseList = claim1.getExpenseList();
		expenseList.addExpense(expense1);
		expenseList.addExpense(expense2);
		

		testClaimList.addClaim(claim1);
		testClaimList.addClaim(claim2);
		Expense item1 = new Expense("food", null, null, null, null, 0);
		Expense item2 = new Expense("meat", null, null, null, null, 0);
		Expense item3 = new Expense("drink", null, null, null, null, 0);
		
		expense1.takeReceipt(null);
		assertFalse("Image yes?", expense1.getReceipt().equals(null));
		assertTrue("Image no", expense2.getReceipt().equals(null));
		}

}
