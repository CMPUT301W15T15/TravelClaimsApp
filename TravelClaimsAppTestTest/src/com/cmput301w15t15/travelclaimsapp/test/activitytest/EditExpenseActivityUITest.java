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
package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Tests for EditExpenseActivity UI 
 *
 * The test cases for this class are not working, need to fix
 * 
 * TODO:
 * 	fix null pointer exception caused by activity using null expense
 */
public class EditExpenseActivityUITest extends
		ActivityInstrumentationTestCase2<EditExpenseActivity> {

	private Instrumentation instrumentation;
	private Activity activity;
	private EditText inputDescription;
	private EditText inputAmount;
	private Spinner selectCategory;
	private Spinner selectCurrency;
	private SpinnerAdapter categoryAdaptor;
	private SpinnerAdapter currencyAdaptor;
	private Intent intent;
	private Claim claim1 = new Claim("testClaim1");
	private Expense expense1 = new Expense("testExpense1");
	
	public EditExpenseActivityUITest() {
		super(EditExpenseActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		intent = new Intent();
		intent.putExtra("claimName", "testClaim1");
		intent.putExtra("expenseName", "testExpense1");
		setActivityIntent(intent);
		setActivityInitialTouchMode(true);
		activity = getActivity();
		FileManager.initializeSaver(activity);
		instrumentation = getInstrumentation();
		ClaimListController.addClaim(claim1);
		claim1.addExpense(expense1);
		ExpenseListController e1 = new ExpenseListController(claim1.getName(), false);
		e1.addExpense(expense1);
		
		//need to change ids once UI has been created 
		inputDescription = (EditText) activity.findViewById(R.id.Edit_Expense_Description2);
		inputAmount = (EditText) activity.findViewById(R.id.Edit_Expense_Cost2);
		
		selectCategory = (Spinner) activity.findViewById(R.id.CategorySpinner2);
		categoryAdaptor = selectCategory.getAdapter();
		
		selectCurrency = (Spinner) activity.findViewById(R.id.CurrencySpinner2);
		currencyAdaptor = selectCurrency.getAdapter();
			
		
	}
	/**
	 * Test Case: EditExpenseActivityUITest#1
	 */
//	public void testEditTextInput(){
//		instrumentation.runOnMainSync(new Runnable() {
//			
//			@Override
//			public void run() {
//				inputAmount.setText("30");
//				inputDescription.setText("test");
//				
//			}
//		});
//		instrumentation.waitForIdleSync();
//		assertEquals("amount EditText not set", "30", inputAmount.getText().toString());
//		assertEquals("description EditText not set", "test", inputDescription.getText().toString());
//	}
	/**
	 * Test Case: EditExpenseActivityUITest#2
	 */
//	public void testSpinners(){
//		assertEquals("number of Categories does not equal 10", 10, categoryAdaptor.getCount());
//		assertEquals("number of Currencies does not equal 7", 7, currencyAdaptor.getCount());
//	}
	/**
	 * Test Case: EditExpenseActivityUITest#3
	 */
//	public void testCategorySelect(){
//		instrumentation.runOnMainSync(new Runnable() {
//			@Override
//			public void run() {
//				selectCategory.requestFocus();
//				selectCategory.setSelection(0);
//			}
//		});
//		//from http://developer.android.com/tools/testing/activity_test.html 2015-02-12
//		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
//	    for (int i = 1; i <= 8; i++) {
//	    	this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
//		} 
//		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
//		
//		
//		assertEquals("Not selected", "Air Fare", selectCategory.getItemAtPosition(selectCategory.getSelectedItemPosition()));
//	}
	/**
	 * Test Case: EditExpenseActivityUITest#4
	 */
/*	public void testCurrencySelect(){
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				selectCategory.requestFocus();
				selectCategory.setSelection(0);
				
			}
		});
		//from http://developer.android.com/tools/testing/activity_test.html 2015-02-12
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	    for (int i = 1; i <= 5; i++) {
	    	this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
		} 
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		
		
		assertEquals("Not selected", "CAD", selectCurrency.getItemAtPosition(selectCurrency.getSelectedItemPosition()));
	}*/
}
