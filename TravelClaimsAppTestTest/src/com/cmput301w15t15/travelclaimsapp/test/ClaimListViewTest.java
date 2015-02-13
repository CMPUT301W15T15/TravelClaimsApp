package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.AddClaimActivity;
import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.ClaimList;
import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ListView;

public class ClaimListViewTest extends
		ActivityInstrumentationTestCase2<AddClaimActivity> {

	private AddClaimActivity activity;
	private ListView view;
	private ClaimListAdaptor adaptor;
	private ClaimList claimList;
	
	public ClaimListViewTest() {
		super(AddClaimActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		
		claimList = new ClaimList();
		view = (ListView) activity.findViewById(R.id.claim_list_listview);
		adaptor = new ClaimListAdaptor(activity, R.id.claim_list_listview, claimList.getClaimList());
	}
	//test #
	public void testListViewDisplay(){
		ViewAsserts.assertOnScreen(activity.getWindow().getDecorView(), view);
	}
	//test #
	public void testClaimListViewUpdate(){
		Claim claim = new Claim("test");
		claimList.addClaim(claim);
		adaptor.notifyDataSetChanged();
		assertEquals("Claim was not added to adaptor",1, adaptor.getCount());
		claimList.removeClaim(claim);
		adaptor.notifyDataSetChanged();
		assertNull("Claim not removed from adaptor", adaptor.getItem(0));
		
	}
	//test #
	public void testClaimListViewSort(){
		Claim claim1 = new Claim("t1");
		Claim claim2 = new Claim("t2");
		claim1.setStartDate(2015,2,11);
		claim1.setStartDate(2015,1,11);
		claimList.addClaim(claim1);
		claimList.addClaim(claim2);
		adaptor.notifyDataSetChanged();
		assertEquals("Claims were not sorted by start date", claim1, adaptor.getItem(1));
		assertEquals("Claims were not sorted by start date", claim2, adaptor.getItem(0));
		
	}
	


}
