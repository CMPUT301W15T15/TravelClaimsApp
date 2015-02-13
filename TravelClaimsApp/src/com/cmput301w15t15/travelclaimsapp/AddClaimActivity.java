package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class AddClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		registerForContextMenu(findViewById(R.id.claim_list_listview));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_claim, menu);
		return true;
	}
	
	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.add_claim_context_menu, menu);
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        
        switch (item.getItemId()) {
            case R.id.cmenu_delete_claim:
              
            case R.id.cmenu_submit_claim:
            	
            case R.id.cmenu_addExpense:
           
            default:
                return super.onContextItemSelected(item);
        }
    }
	
}
