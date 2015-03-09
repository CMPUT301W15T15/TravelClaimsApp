package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

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
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		FileManager.initializeSaver(activity);
		
		//need to change ids once UI has been created 
		inputDate = (EditText) activity.findViewById(R.id.Edit_Expense_Date);
		inputDescription = (EditText) activity.findViewById(R.id.Edit_Expense_Description);
		inputAmount = (EditText) activity.findViewById(R.id.Edit_Expense_Cost);
		
		selectCategory = (Spinner) activity.findViewById(R.id.CategorySpinner);
		categoryAdaptor = selectCategory.getAdapter();
		
		selectCurrency = (Spinner) activity.findViewById(R.id.CurrencySpinner);
		currencyAdaptor = selectCurrency.getAdapter();
		
	}
	//Test Case: EditExpenseActivityUITest#1
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
	//Test Case: EditExpenseActivityUITest#2
	public void testSpinners(){
		assertEquals("number of Categories does not equal 9", 9, categoryAdaptor.getCount());
		assertEquals("number of Currencies does not equal 7", 7, currencyAdaptor.getCount());
	}
	//Test Case: EditExpenseActivityUITest#3
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
	//Test Case: EditExpenseActivityUITest#4
	public void testCurrencySelect(){
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
		
		
		assertEquals("Not selected", "JPY", selectCurrency.getItemAtPosition(selectCurrency.getSelectedItemPosition()));
	}
	//Test Case: EditExpenseActivityUITest#5
	public void testDataPersistance(){
		ClaimList claimL = ClaimListController.getClaimList();
		String selectedClaimName = activity.getIntent().getExtras().getString("claimName");
		Claim testClaim = claimL.getClaim(selectedClaimName);
		ExpenseList expenseList = new ExpenseList();
		Expense testExpense = new Expense("test", selectedClaimName, selectedClaimName, selectedClaimName, null, 0);
		expenseList.addExpense(testExpense);
		testExpense.setName("stuff");
		//activity.finish();
		//activity = getActivity();
		claimL = ClaimListController.getClaimList();
		testClaim = claimL.getClaim(selectedClaimName);
		testExpense = expenseList.getExpense("stuff");
		assertNotNull(testExpense);
		
	}

}
