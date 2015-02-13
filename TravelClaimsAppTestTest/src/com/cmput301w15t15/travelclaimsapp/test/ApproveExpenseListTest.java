package com.cmput301w15t15.travelclaimsapp.test;

import java.util.Date;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.Expense;
import com.cmput301w15t15.travelclaimsapp.ExpenseItem;

import junit.framework.TestCase;

public class ApproveExpenseListTest extends TestCase {
	private Claim Claim1;
	private Claim Claim2;
	private Expense Expense1;
	private Expense Expense2;
	
	public ApproveExpenseListTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		
		
	}
	
	//TestNumber:8.4
	public void testViewSubmittedExpense() {
		ClaimList ApproveClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		Expense1 = new Expense("Expense1");
		
		ApproveClaimList.addClaim(Claim1);
		Claim1.setStatus("Submitted");
		Claim1.addExpense(Expense1);

		assertTrue("name is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getName().toString().equals("Expense1"));
		
		Date date = new Date();
		Expense1.setDate(date);
		assertTrue("date is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getDate().equals(date));
		
		Expense1.setDescription("Description");
		assertTrue("Description is equal", ApproveClaimList.getClaimList()
				.get(0).getExpenseList().get(0).getDescription().equals("Description"));
	}
	
	//TestNumber:8.5
	public void testViewExpenseReceipt(){
		ClaimList testClaimList = new ClaimList();
		Claim1 = new Claim("Claim1");
		Expense1 = new Expense("Expense1");
		Expense2 = new Expense("Expense2");
		
		Claim1.setStatus("Submitted");
		Claim1.addExpense(Expense1);
		Claim1.addExpense(Expense2);
		

		testClaimList.addClaim(Claim1);
		testClaimList.addClaim(Claim2);
		ExpenseItem item1 = new ExpenseItem("food");
		ExpenseItem item2 = new ExpenseItem("meat");
		ExpenseItem item3 = new ExpenseItem("drink");
		Expense1.addItem(item1);
		Expense1.addItem(item2);
		Expense2.addItem(item3);
		
		Expense1.takeReceipt();
		assertFalse("Image yes?", Expense1.getReceipt().equals(null));
		assertTrue("Image no", Expense2.getReceipt().equals(null));
		}

}
