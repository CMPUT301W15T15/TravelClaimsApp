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
	    expense = new Expense("Business");
		super.setUp();
	}
	//TestCase: takePhotoTest#1
	 protected void testTakePhoto() {
		 Expense expense = new Expense("Business");
		 expense.takeAPhoto();
		 assertTrue("exist photo", expense.takeAPhoto() != null);
		 }
	//TestCase: takePhotoTest#2
	 protected void testDeletePhoto(){
		 expense.takeAPhoto();
		 assertTrue("exist photo", expense.takeAPhoto()!=null);
		 expense.deleteAPhoto();
		 assertTrue("delete a photo", expense.takeAPhoto()==null);
		 }
	//TestCase: takePhotoTest#3
	 protected void testViewPhoto(){
		 expense.takeAPhoto();
		 assertTrue("view Receipt",expense.takeAPhoto().equals(imageFileUri));
		 }
}
