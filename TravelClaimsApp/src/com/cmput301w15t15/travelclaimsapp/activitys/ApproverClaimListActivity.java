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
package com.cmput301w15t15.travelclaimsapp.activitys;

import java.util.Arrays;

import com.cmput301w15t15.travelclaimsapp.ApproverClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.InternetController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.SubmittedClaimListController;
import com.cmput301w15t15.travelclaimsapp.UserController;
import com.cmput301w15t15.travelclaimsapp.activitys.LoginActivity.loginThread;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity for the AddClaim/ClaimList view 
 * 
 * TODO:
 */
public class ApproverClaimListActivity extends Activity {

	private static final int LENGTH_SHORT = 0;
	private ApproverClaimListAdaptor claimAdaptor;
	private ListView claimListView;
	public ClaimList claimList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_list);
		FileManager.initializeSaver(this);
		claimListView = (ListView) findViewById(R.id.approve_claim_list_view);
		claimList = SubmittedClaimListController.getClaimList();
		//create a adaptor for claim list and set it
		claimAdaptor = new ApproverClaimListAdaptor(this,R.layout.claim_list_adaptor, claimList.toArrayList());
		claimAdaptor.notifyDataSetChanged();
        claimListView.setAdapter(claimAdaptor);
        
		registerForContextMenu(findViewById(R.id.approve_claim_list_view));
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		claimAdaptor.notifyDataSetChanged();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		claimAdaptor.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_list, menu);
		return true;
	}

	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.submitted_claimlist_context_menu, menu);
       
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        //get the claim the user selected 
        Intent intent;
        final Claim claim = claimAdaptor.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.cmenu_submittedClaimexpenses:
            	intent = new Intent(ApproverClaimListActivity.this, ApproverExpenseListActivity.class);
            	intent.putExtra("claimName", claim.getName());
            	startActivity(intent);
            	return true;
            case R.id.cmenu_comment:
            	if(!canApprove(claim)){
            		return true;
            	}
            	final EditText tv = new EditText(this);
            	tv.setHeight(240);
            	if(claim.getComment() != null){
            		tv.setText(claim.getComment());
            	}
            	AlertDialog.Builder alertd = new AlertDialog.Builder(this);
            	
            	alertd.setView(tv);
            	
            	alertd.setPositiveButton("Ok", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						claim.setComment(tv.getText().toString());
						claim.setApprover(UserController.getUser().getUsername());
						claimAdaptor.notifyDataSetChanged();
						return;
					}
				});
            	alertd.setNegativeButton("Cancel", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});
            	alertd.show();
            	return true;
            case R.id.cmenu_approve:
            	if(canApprove(claim)){
            		claim.setStatus(Claim.APPROVED);
            		claim.addComment(claim.getComment());
            		claimList.removeClaim(claimList.getClaim(claim.getName()));
            		claimAdaptor.notifyDataSetChanged();
            		Thread approvedThread = new saveChangesThread(claimList, claim, this);
            		approvedThread.start();
            	}
            
            case R.id.cmenu_return:
            	if(canApprove(claim)){
            		claim.setStatus(Claim.RETURNED);
            		claim.addComment(claim.getComment());
            		claimList.removeClaim(claimList.getClaim(claim.getName()));
            		claimAdaptor.notifyDataSetChanged();
            		Thread returnedThread = new saveChangesThread(claimList, claim, this);
            		returnedThread.start();
            	}
            default:
                return super.onContextItemSelected(item);
        }
    }
    /** Function that is called when "Search" menu item is clicked
	 * and switches to the searchactivity
	 * @author Henry
	 * @param menu
	 */
    public void SearchOption (MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ApproverClaimListActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
    /**Function that is called when the "Sign Out" menu item is clicked
	 * and switches to the mainscreenactivity
	 * @author Henry
	 * @param menu
	 */
    public void SignOut(MenuItem menu)
    {
    	SignOutController.reset();
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ApproverClaimListActivity.this, LoginActivity.class);
    	startActivity(intent);
    }

	class saveChangesThread extends Thread {
		
		private ClaimList newSubmittedClaimList;
		private Claim modifiedClaim;
		private Context context;
		
		public saveChangesThread(ClaimList claimlist, Claim claim, Context context){
			newSubmittedClaimList = claimlist;
			modifiedClaim = claim;
			this.context = context;
		}
		
		public void run() {
			if(!InternetController.isInternetAvailable2(context)){
				runOnUiThread(popToast);
			} else {
				String modUsername = modifiedClaim.getClaimantName();
				modifiedClaim.setApprover(UserController.getUser().getUsername());
				ClaimList claimantClaimList = FileManager.getSaver().getClaimList(modUsername);
				claimantClaimList.removeClaim(claimantClaimList.getClaim(modifiedClaim.getName()));
				claimantClaimList.addClaim(modifiedClaim);
				FileManager.getSaver().addClaimList(claimantClaimList, modUsername);
				FileManager.getSaver().addSubmittedClaimL(newSubmittedClaimList);
			}
		
		}
		
	}
	
	/**
	 * Warning when no internet connection found.
	 */
	private Runnable popToast = new Runnable() {
		public void run() {
			Toast.makeText(ApproverClaimListActivity.this, "Internet Connection Needed", Toast.LENGTH_LONG).show();
		}
	};

	private boolean canApprove(Claim claim){
		if(claim.getClaimantName().equals(UserController.getUser().getUsername())){
			Toast.makeText(this, "No approver priviledge for this claim", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(claim.getApprover() != null && claim.getApprover().equals(UserController.getUser().getUsername())){
    		Toast.makeText(this, "You are not the approver for this claim", Toast.LENGTH_SHORT).show();
    		return false;
    	}
		
		return true;
	}
    
	
}
