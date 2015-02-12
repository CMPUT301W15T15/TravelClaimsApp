package com.cmput301w15t15.travelclaimsapp.test;

import com.cmputw15t15.travelclaimsapp.EditExpenseActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class EditExpenseActivityUITest extends
		ActivityInstrumentationTestCase2<EditExpenseActivity> {

	private Instrumentation instrumentation;
	private Activity activity;
	private EditText inputDate;
	private EditText inputDescription;
	private EditText inputAmount;
	private Spinner selectCategory;
	private Spinner selectCurrency;
	private SpinnerAdapter categoryAdaptor;
	private SpinnerAdapter currencyAdaptor;
	
	
	
	public EditExpenseActivityUITest() {
		super(EditExpenseActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		
		inputDate = (EditText) activity.findViewById(some_id);
		inputDescription = (EditText) activity.findViewById(some_id);
		inputAmount = (EditText) activity.findViewById(some_id);
		
		selectCategory = (Spinner) activity.findViewById(some_id);
		categoryAdaptor = selectCategory.getAdapter();
		
		selectCurrency = (Spinner) activity.findViewById(some_id);
		currencyAdaptor = selectCurrency.getAdapter();
		
	}
	//test #
	public void testEditTextInput(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				inputAmount.setText("30");
				inputDate.setText("2015-02-12");
				inputDescription.setText("test");
				
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("amount EditText not set", "30", inputAmount.getText().toString());
		assertEquals("date EditText not set", "2015-02-12", inputDate.getText().toString());
		assertEquals("description EditText not set", "test", inputDescription.getText().toString());
	}
	//test #
	public void testSpinners(){
		assertEquals("number of Categories does not equal 9", 9, categoryAdaptor.getCount());
		assertEquals("number of Currencies does not equal 7", 7, currencyAdaptor.getCount());
	}
	//test #
	public void testCategorySelect(){
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				selectCategory.requestFocus();
				selectCategory.setSelection(0);
				
			}
		});
		//from http://developer.android.com/tools/testing/activity_test.html 2015-02-12
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	    for (int i = 1; i <= 8; i++) {
	    	this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
		} 
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		
		
		assertEquals("Not selected", "Supplies", selectCategory.getItemAtPosition(selectCategory.getSelectedItemPosition()));
	}

}
