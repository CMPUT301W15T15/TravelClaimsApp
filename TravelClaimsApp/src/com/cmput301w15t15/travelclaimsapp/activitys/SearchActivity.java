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

import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class SearchActivity extends Activity
{
	private Button searchButton;
	private ListView claimListView;
	private EditText searchField;
	private ClaimListAdaptor claimListAdaptor;
	private ClaimList claimList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		searchButton = (Button) this.findViewById(R.id.Search_Button);
		claimListView = (ListView) this.findViewById(R.id.Search_List);
		searchField = (EditText) this.findViewById(R.id.Search_Text);
		claimList = new ClaimList();
		claimListView.setTextFilterEnabled(true);
		claimListAdaptor = new ClaimListAdaptor(this,R.layout.claim_list_adaptor, claimList.toArrayList());
		claimListView.setAdapter(claimListAdaptor);
		registerForContextMenu(claimListView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_activity, menu);
		return true;
	}
	
	public void SignOut(MenuItem menu)
    {
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(SearchActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }
	
	/**
	 * Function that is called when search button pressed 
	 * 
	 * Clears the claimListAdaptor, then gets the filtered claim list and updates adapter
	 * 
	 * @param view
	 */
	public void searchButton(View view){
		Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show();
		claimListAdaptor.clear();
		ArrayList<Claim> claims = ClaimListController.getFilteredClaimList(searchField.getText().toString());
		claimListAdaptor.addAll(claims);
		claimListAdaptor.notifyDataSetChanged();
		
		
	}
	
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.add_claim_context_menu, menu);
        //menu.findItem(3).setVisible(false);
       
        //menu.getItem(R.id.cmenu_delete_claim).setVisible(false);
        //menu.getItem(R.id.cmenu_dummyClaim).setVisible(false);
        
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(false);
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        //get the claim the user selected 
        Intent intent;
        
        final Claim claim = claimListAdaptor.getItem(info.position);
        
        switch (item.getItemId()) {
            case R.id.cmenu_submit_claim:
            	////////////
            	//DO something to upload to elasticsearch server
            	///////////
            	claim.setStatus("Submitted");
            	claimListAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_addExpense:
            	intent= new Intent(SearchActivity.this, EditExpenseActivity.class);
            	//create new expense with default name and add to claimlist
            	Expense expense = new Expense("Expense"+claim.getExpenseList().size());
            	ClaimListController.addExpense(expense, claim);
            	// attach claim name and expense name to intent 
            	intent.putExtra("expenseName", expense.getName());
            	intent.putExtra("claimName", claim.getName());
            	startActivity(intent);  
            	return true;
            case R.id.cmenu_editExpense:
            	//pass the claim selected to new activity 
            	intent = new Intent(SearchActivity.this, EditClaimActivity.class);
            	intent.putExtra("claimName", claim.getName());
            	startActivity(intent);   
            	return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
	

}
