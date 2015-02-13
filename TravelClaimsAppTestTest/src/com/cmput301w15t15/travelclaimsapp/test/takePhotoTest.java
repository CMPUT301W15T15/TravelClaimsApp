package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Expense;

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
	//TestID:takePhotoTest-takePhotoTest
	 protected void takePhotoTest() {
		 Expense expense = new Expense("Business");
		 expense.takeAPhoto();
		 assertTrue("exist photo", expense.takeAPhoto() != null);
		 }
	//TestID:takePhotoTest-deletePhotoTest
	 protected void deletePhotoTest(){
		 expense.takeAPhoto();
		 assertTrue("exist photo", expense.takeAPhoto()!=null);
		 expense.deleteAPhoto();
		 assertTrue("delete a photo", expense.takeAPhoto()==null);
		 }
	//TestID:takePhotoTest-viewPhotoTest
	 protected void viewPhotoTest(){
		 expense.takeAPhoto();
		 assertTrue("view Receipt",expense.takeAPhoto().equals(imageFileUri));
		 }
}
