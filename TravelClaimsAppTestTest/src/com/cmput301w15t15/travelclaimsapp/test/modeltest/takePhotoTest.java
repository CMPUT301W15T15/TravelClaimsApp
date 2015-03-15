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
