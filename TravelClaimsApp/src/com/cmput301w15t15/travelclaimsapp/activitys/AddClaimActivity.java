package com.cmput301w15t15.travelclaimsapp.activitys;

import java.io.IOException;
import java.util.ArrayList;

import com.cmput301w15t15.travelclaimsapp.ClaimListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.Expense;




import com.cmput301w15t15.travelclaimsapp.model.Tag;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Activity for the AddClaim/ClaimList view 
 * 
 * TODO:
 *	-
 *
 */
public class AddClaimActivity extends Activity {

	private static final int LENGTH_SHORT = 0;
	private ClaimListAdaptor claimAdaptor;
	private ListView claimListView;
	private ClaimList claimList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_claim);
		FileManager.initializeSaver(this);
		claimListView = (ListView) findViewById(R.id.claim_list_listview);
		claimList = ClaimListController.getClaimList();
		
		//create a adaptor for claim list and set it
		claimAdaptor = new ClaimListAdaptor(this,R.layout.claim_list_adaptor, claimList.toArrayList());
		claimAdaptor.notifyDataSetChanged();
        claimListView.setAdapter(claimAdaptor);
        
		registerForContextMenu(findViewById(R.id.claim_list_listview));
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
        //get the claim the user selected 
        Intent intent;
        final Claim claim = claimAdaptor.getItem(info.position);
    
        switch (item.getItemId()) {
            case R.id.cmenu_delete_claim:
            	claimList.removeClaim(claim);
            	claimAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_submit_claim:
            	////////////
            	//DO something to upload to elasticsearch server
            	///////////
            	claim.setStatus("Submitted");
            	claimAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_addExpense:
            	
            	intent= new Intent(AddClaimActivity.this, EditExpenseActivity.class);
            	
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
            	intent = new Intent(AddClaimActivity.this, EditClaimActivity.class);
            	intent.putExtra("claimName", claim.getName());
            	startActivity(intent);   
            	return true;
            case R.id.cmenu_addTag:
            	final AutoCompleteTextView enterTag = new AutoCompleteTextView(this);
            	
            	enterTag.showDropDown();
            	enterTag.setHint("Enter tag");
            	
            	AlertDialog.Builder alert = new AlertDialog.Builder(this);
            	
            	ArrayList<Tag> tags = claim.getTagList().toArrayList();
            	ArrayAdapter<Tag> tagAdaptor = new ArrayAdapter<Tag>(this, android.R.layout.simple_list_item_1, tags);
            	enterTag.setAdapter(tagAdaptor);
            			
            	alert.setView(enterTag);
            
            	alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				Tag tag = new Tag(enterTag.getText().toString());
        				ClaimListController.addTag(claim, tag);
        				claimAdaptor.notifyDataSetChanged();
        				
        				
        			}
        		});
        		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				dialog.cancel();
        			}
        		});
        	
        		alert.show(); 
            	tagAdaptor.notifyDataSetChanged();
            	
            	return true;
            case R.id.cmenu_dummyClaim:
            	Claim c = new Claim("Claim"+ClaimListController.getClaimList().size());
            	c.addExpense(new Expense("t1", null, "EUR", null, null, 40));
            	c.addExpense(new Expense("t2", null, "GBP", null, null, 100));
            	c.addExpense(new Expense("t3", null, "CAD", null, null, 200));
            	c.addExpense(new Expense("t4", null, "USD", null, null, 1000));
            	c.addExpense(new Expense("t5", null, "EUR", null, null, 40));
            	ClaimListController.addClaim(c);
            	claimAdaptor.notifyDataSetChanged();
            	
            default:
                return super.onContextItemSelected(item);
        }
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
    	int i = claimList.size();
    	while(ClaimListController.getClaimList().getClaim("Claim"+i)!=null){
    		i++;
    	}
    	Claim claim = new Claim("Claim"+i);
		ClaimListController.addClaim(claim);
		
    	Toast.makeText(this, "Creating a Claim", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(AddClaimActivity.this, EditClaimActivity.class);
    	//attach claim name to intent 
    	intent.putExtra("claimName", claim.getName());
    	startActivity(intent);   
    }
 
    
	
}
