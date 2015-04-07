package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.R;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;

public class EditExpenseActTest extends
		ActivityInstrumentationTestCase2<EditExpenseActivity> {

	protected Expense expense1;
	
	
	public EditExpenseActTest() {
		super(EditExpenseActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testExpenseName(){
		expense1.setName("Expense test 1");
		assertEquals("No Name","Expense test 1", expense1.getName());
	}
	
	public void testAddReceipt(){
		//Resources res = getResources();
		 //String expectedInfoText = mClickFunActivity.getString(R.string.info_text);
//		View v = inflater.inflate();
//		View innerView = v.findViewById(id_number_of_view_inside_v);
//		    TouchUtils.clickView(this,);
//		    assertTrue(View.VISIBLE == mInfoTextView.getVisibility());
//		    assertEquals(expectedInfoText, mInfoTextView.getText());

	}
	
	
	
	
	
}
