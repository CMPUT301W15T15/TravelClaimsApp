/*
 *TravelClaimsApp
 *Copyright (C) 2015 Jon Machinski, Bo Zhou, Henry Ha, Chris Wang, Sean Scheideman
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.cmput301w15t15.travelclaimsapp.R.drawable;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.R;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import junit.framework.TestCase;


public class takePhotoTest extends TestCase
{
	
	Uri imageFileUri;
	private Expense expense;
	private byte[] testPicture;
	
	
	protected void setUp() throws Exception
	{
	    expense = new Expense("Business");
		super.setUp();
	}
<<<<<<< HEAD
//	//TestCase: takePhotoTest#1	
//	 protected void testTakePhoto() {
//			 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//			 String folder = Environment.getExternalStorageDirectory()
//			 .getAbsolutePath() + "/tmp";
//			 File folderF = new File(folder);
//			 if (!folderF.exists()) {
//			 folderF.mkdir();
//			 }
//			 String imageFilePath = folder + "/"
//			 + String.valueOf(System.currentTimeMillis()) + "jpg";
//			 File imageFile = new File(imageFilePath);
//			 imageFileUri = Uri.fromFile(imageFile);
=======
	//TestCase: takePhotoTest#1
	 protected void testTakePhoto() {
		 Expense expense = new Expense("Business");

>>>>>>> 6b000230bd4005c748c7fbcd91df358479d9e7fe
		 
		 
<<<<<<< HEAD
=======
		// expense.takePicture(imageFileUri);

		 //assertTrue("exist photo", expense.takeReceipt(imageFileUri) != null);
		 }
	//TestCase: takePhotoTest#2
>>>>>>> 6b000230bd4005c748c7fbcd91df358479d9e7fe
	 protected void testDeletePhoto(){
		 //expense.takeReceipt(imageFileUri);
		// assertTrue("exist photo", expense.takeReceipt(imageFileUri)!=null);
		 //expense.deleteReceipt();
		 //assertTrue("delete a photo", expense.takeReceipt(imageFileUri) == null);
		 }
	//TestCase: takePhotoTest#3
	 protected void testViewPhoto(){
		 //expense.takeReceipt(imageFileUri);
		 //assertTrue("view Receipt",expense.takeReceipt().equals(imageFileUri));
		 }
}
