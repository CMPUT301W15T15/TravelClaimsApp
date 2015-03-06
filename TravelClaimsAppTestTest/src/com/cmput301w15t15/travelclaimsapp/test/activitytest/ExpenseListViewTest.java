package com.cmput301w15t15.travelclaimsapp.test.activitytest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.ExpenseListActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;



public class ExpenseListViewTest extends
ActivityInstrumentationTestCase2<ExpenseListActivity> {

private ExpenseListActivity activity;
private ListView view;
private ExpenseListAdaptor adaptor;
private ClaimList claimList;
private Claim claim;

	public ExpenseListViewTest() {
		super(ExpenseListActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		claim = new Claim("testClaim");
		activity = getActivity();
		
		claimList = ClaimListController.getClaimList();
		claimList.addClaim(claim);
		view = (ListView) activity.findViewById(R.id.CurrentExpenseList);
		adaptor = new ExpenseListAdaptor(activity, R.id.CurrentExpenseList, claim.getExpenseList());
	}

	//Test Case: ExpenseListViewTest#1
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
