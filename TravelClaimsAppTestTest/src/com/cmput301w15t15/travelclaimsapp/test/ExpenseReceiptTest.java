package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Expense;

import android.net.Uri;
import junit.framework.TestCase;

public class ExpenseReceiptTest extends TestCase {

	private Uri imageFileUri;
	private Expense Expense1;
	
	

	
	public ExpenseReceiptTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void viewReceiptTest(){
		Expense1  = new Expense("test");
		Expense1.takeReceipt();
		assertTrue("view Receipt",item.getPhoto().equals(imageFileUri));
	}
	
}
