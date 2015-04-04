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

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.InternetController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.SubmittedClaimListController;
import com.cmput301w15t15.travelclaimsapp.UserController;
//import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.activitys.AddClaimActivity.initApproverActivityThread;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;


/**
 * @author bzhou2
 *
 */


/**
 * Activity to handle editing expense 
 *
 */

public class ExpenseListActivity extends Activity
{


	private ExpenseListAdaptor expenseAdaptor;
	private ExpenseList expenseList;
	private ListView expenseListView;
	private String claimName;
	private Expense expense;
	private int adaptorPos;
	private static final int GET_GEOLOCATION_CODE = 10;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent=new Intent();
		claimName=this.getIntent().getExtras().getString("claimName");
		setContentView(R.layout.expense_list);
		FileManager.initializeSaver(this);
		expenseListView = (ListView) findViewById(R.id.CurrentExpenseList2);
		expenseList = ClaimListController.getClaimList().getClaim(claimName).getExpenseList();

		expenseAdaptor = new ExpenseListAdaptor(this,R.layout.expense_list_adaptor,expenseList.toArrayList());
		expenseAdaptor.notifyDataSetChanged();
		expenseListView.setAdapter(expenseAdaptor);
		registerForContextMenu(findViewById(R.id.CurrentExpenseList2));
		ListView listView = (ListView) findViewById(R.id.CurrentExpenseList2);
		//set_on_click();

	}

	/**
	 *  initialize activity valus
	 * 
	 * @author bzhou2
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart(){
		super.onStart();
		expenseAdaptor.notifyDataSetChanged();
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume(){
		super.onResume();
		expenseAdaptor.notifyDataSetChanged();
		
		
	}
	
	/**
	 *  (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list, menu);
		return true;
	}
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.expense_context_menu, menu);
        
    }
	/** Function that is called when Search menu item is clicked
	 * @author Henry
	 * @param menu
	 */
    public void SearchOption (MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
    /**Function that is called when the "Sign Out" menu item is clicked
	 * @author Henry
	 * @param menu
	 */
    public void SignOut(MenuItem menu)
    {
    	SignOutController.reset();
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, LoginActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Function that is called when "Return to claim list" menu item clicked
     * 
     * @param menu		menu item that was clicked
     */
    public void backToClaimList(MenuItem menu)
    {
    	Intent intent = new Intent(ExpenseListActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }
 
    public void MenuApprover(MenuItem menu){
    	if(!UserController.getUser().isApprover()){
    		Toast.makeText(this, "Not an Approver", Toast.LENGTH_LONG).show();
    		return;
    	}
    	if(InternetController.isInternetAvailable2(this)){
    		Thread thread = new initApproverActivityThread(this);
			thread.start();
    	} else {
    		Toast.makeText(this, "Internet Connection Needed", Toast.LENGTH_LONG).show();
    		return;
    	}

    }

    
    /**Function that is called when the "Add new Expense" Button is clicked in UI
     * @author Henry Bo
     * @param view
     */
    public void AddExpenseButton(View view)
    {
    	Toast.makeText(this, "Going to Add Expense", Toast.LENGTH_SHORT).show();
    	
    	//startActivity(intent); 
    	
    	Claim claim = ClaimListController.getClaimList().getClaim(claimName);
    	int i = expenseList.size();
    	while(ClaimListController.getClaimList().getClaim(claimName).getExpense("Expense"+i)!=null){
    		i++;
    	}
    	Expense expense = new Expense("Expense"+i);
		//ClaimListController.getClaimList().getClaim(claimName).addExpense(expense);
		ClaimListController.addExpense(expense, claim);
		
    	Toast.makeText(this, "Creating a Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, EditExpenseActivity.class);

    	intent.putExtra("claimName",claimName );
    	intent.putExtra("expenseName", expense.getName());
    	startActivity(intent);   
    }
    
    	
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//		Expense expense2= expenseList.get(position);
//    	if (view.equals(viewHolder.expenseFlag)){
//    		expense2.setFlag(1-expense.getFlag());
//    		notifyDataSetChanged();
//    	}
//    }

    
	/**
	 * save value anytime when you change values
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        
		Intent intent;
		final Expense expense = expenseAdaptor.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.expenseListViewMenuEdit:
            	Toast.makeText(this, "Editing an expense", Toast.LENGTH_SHORT).show();
            	intent = new Intent(ExpenseListActivity.this, EditExpenseActivity.class);

            	Bundle bundle=new Bundle();
            	intent.putExtra("claimName", claimName);
            	intent.putExtra("expenseName", expense.getName());

            	startActivity(intent);   
            	
            	expenseAdaptor.notifyDataSetChanged();
            	return true;
                
            case R.id.expenseListViewMenuDelete:
            	expenseList.removeExpense(expense);
            	expenseAdaptor.notifyDataSetChanged();
            	return true;
            	
            case R.id.expenseListViewMenuGeoLocation:
            	adaptorPos = info.position;
            	AlertDialog.Builder alertGl = new AlertDialog.Builder(this);
            	alertGl.setPositiveButton("GPS", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(GeoLocationController.checkGPSEnabled()){
							GeoLocationController.setExpenseGeoLocationGPS(expense);
							expenseAdaptor.notifyDataSetChanged();
						}
					}
				});
            	alertGl.setNegativeButton("Map", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Open map view \
						Intent intent = GeoLocationController.pickLocationIntent(ExpenseListActivity.this);
				    	startActivityForResult(intent, GET_GEOLOCATION_CODE);
					}
				});
            	alertGl.show();
            	
            	return true;
            	
//            case R.id.expenseListViewMenuFlag:
//            	int flagStatus=expense.getFlag();
//            	if (flagStatus==1){
//            		Toast.makeText(this, "Already Flaged", Toast.LENGTH_SHORT).show();
//            	}
//            	else{
//            		expense.addFlag();
//            		expenseAdaptor.notifyDataSetChanged();
//            		Toast.makeText(this, "Expense Flaged", Toast.LENGTH_SHORT).show();
//            	}
//            	return true;
//            case R.id.expenseListViewMenuUnflag:
//            	int flagStatus2=expense.getFlag();
//            	if (flagStatus2==1){
//            		expense.removeFlag();
//            		expenseAdaptor.notifyDataSetChanged();
//            		Toast.makeText(this, "Expense unflaged", Toast.LENGTH_SHORT).show();
//            	}
//            	else{
//            		Toast.makeText(this, "No Flag", Toast.LENGTH_SHORT).show();
//            	}
            default:
                return super.onContextItemSelected(item);
        }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == GET_GEOLOCATION_CODE){
			switch (resultCode) {
			case RESULT_OK:
				Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();
				String geoString = data.getExtras().getString("geoLocation");
				GeoLocation gl = GeoLocation.getFromString(geoString);
				Expense e = expenseAdaptor.getItem(adaptorPos);
				GeoLocationController.setExpenseGeoLocation(e, gl.getLatitude(), gl.getLongitude());
				break;
			case RESULT_CANCELED:
				Toast.makeText(this, "RESULT_CANCEL", Toast.LENGTH_SHORT).show();
				break;
				
			default:
				Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show();
				break;
			}
			
		}
	}
    
    /**
	 * Thread that closes the activity after Submitted ClaimList has loaded.
	 */
	private Runnable launchApprover = new Runnable() {
		public void run() {
			Intent intent = new Intent(ExpenseListActivity.this, ApproverClaimListActivity.class);
	    	startActivity(intent);
		}
	};
	
	class initApproverActivityThread extends Thread {
		
		Context context;
		
		public initApproverActivityThread(Context context) {

			this.context = context;
		}

		public void run() {
			if(!SubmittedClaimListController.initSubmittedClaimListController()){
				Toast.makeText(context, "Internet connection needed", Toast.LENGTH_SHORT).show();
			} else {
				runOnUiThread(launchApprover);
			}
		
		}
		
	}

	
	

}
