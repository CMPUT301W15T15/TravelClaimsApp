package com.cmput301w15t15.travelclaimsapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.Expense;
import com.cmput301w15t15.travelclaimsapp.ExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ExpenseListViewActivity;
import com.cmput301w15t15.travelclaimsapp.R;



public class ExpenseListViewTest extends
ActivityInstrumentationTestCase2<ExpenseListViewActivity> {

private ExpenseListViewActivity activity;
private ListView view;
private ExpenseListAdaptor adaptor;
private ClaimList claimList;
private Claim claim;

	public ExpenseListViewTest() {
		super(ExpenseListViewActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		claim = new Claim("testClaim");
		activity = getActivity();
		
		claimList = ClaimListController.getClaimList();
		claimList.addClaim(claim);
		view = (ListView) activity.findViewById(R.id.expenseListView);
		adaptor = new ExpenseListAdaptor(activity, R.id.expenseListView, claim.getExpenseList());
	}

	//test #
	public void testClaimListViewUpdate(){
		Expense e = new Expense("stuff");
		claim.addExpense(e);
		adaptor.notifyDataSetChanged();
		assertEquals("expense was not added to adaptor",1, adaptor.getCount());
		claim.removeExpense(e);
		adaptor.notifyDataSetChanged();
		assertNull("Claim not removed from adaptor", adaptor.getItem(0));
	
	}




}