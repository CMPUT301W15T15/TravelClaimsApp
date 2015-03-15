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
		Expense1  = new Expense("Expense1", null, null, null, null, 0);
		Expense1.takeReceipt(null);
		assertEquals("view Receipt",Expense1.getReceipt(),receiptImage);
	}
	
	
	
	
	
}
