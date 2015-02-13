package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Expense;
import com.cmput301w15t15.travelclaimsapp.ExpenseItem;

import android.net.Uri;
import junit.framework.TestCase;

public class ExpenseReceiptTest extends TestCase {

	private Uri getImage;
	private ExpenseItem ExpenseItem1;
	

	
	public ExpenseReceiptTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void viewReceiptTest(){
		ExpenseItem1  = new ExpenseItem("ExpenseItem1");
		ExpenseItem1.takeReceipt();
		assertEquals("view Receipt",ExpenseItem1.getReceipt(),imageFileUri));
	}
	
}
