package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.net.Uri;
import junit.framework.TestCase;


public class takePhotoTest extends TestCase
{
	
	Uri imageFileUri;
	private Expense expense;
	protected void setUp() throws Exception
	{
	    expense = new Expense("Business", null, null, null, null, 0);
		super.setUp();
	}
	//TestCase: takePhotoTest#1
	 protected void testTakePhoto() {
		 Expense expense = new Expense("Business", null, null, null, null, 0);
		 expense.takeReceipt(imageFileUri);
		 //assertTrue("exist photo", expense.takeReceipt(imageFileUri) != null);
		 }
	//TestCase: takePhotoTest#2
	 protected void testDeletePhoto(){
		 expense.takeReceipt(imageFileUri);
		// assertTrue("exist photo", expense.takeReceipt(imageFileUri)!=null);
		 expense.deleteReceipt();
		 //assertTrue("delete a photo", expense.takeReceipt(imageFileUri) == null);
		 }
	//TestCase: takePhotoTest#3
	 protected void testViewPhoto(){
		 expense.takeReceipt(imageFileUri);
		 //assertTrue("view Receipt",expense.takeReceipt().equals(imageFileUri));
		 }
}
