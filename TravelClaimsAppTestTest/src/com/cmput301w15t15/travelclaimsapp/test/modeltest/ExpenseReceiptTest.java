package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.net.Uri;
import junit.framework.TestCase;

public class ExpenseReceiptTest extends TestCase {

	private Uri receiptImage;
	private Expense Expense1;
	

	
	public ExpenseReceiptTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void viewReceiptTest(){
		Expense1  = new Expense("Expense1");
		Expense1.takeReceipt();
		assertEquals("view Receipt",Expense1.getReceipt(),receiptImage);
	}
	
	
	
	
	
}
