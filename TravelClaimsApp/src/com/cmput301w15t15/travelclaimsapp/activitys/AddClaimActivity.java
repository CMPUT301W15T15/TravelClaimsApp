package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

public class AddClaimActivity extends Activity {

	private static final int LENGTH_SHORT = 0;

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
    	return super.onContextItemSelected(item);
//        switch (item.getItemId()) {
//            case R.id.cmenu_delete_claim:
//            
//            case R.id.cmenu_submit_claim:
//            	
//            case R.id.cmenu_addExpense:
//            
//            default:
//                return super.onContextItemSelected(item);
//        }
    }
    
    public void SearchOption (MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddClaimActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
    
    public void SignOut(MenuItem menu)
    {
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddClaimActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }

    public void AddClaimButton(View view)
    {
    	Toast.makeText(this, "Creating a Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddClaimActivity.this, EditClaimActivity.class);
    	startActivity(intent);   
    }
	
}
