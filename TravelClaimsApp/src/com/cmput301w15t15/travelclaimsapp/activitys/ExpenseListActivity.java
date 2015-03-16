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
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ExpenseListActivity extends Activity
{
	private ExpenseListAdaptor expenseAdaptor;
	private ExpenseList expenseList;
	private ListView expenseListView;
	private String claimName;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent=new Intent();
		//Bundle b=new Bundle();
		//b=this.getIntent().getExtras();
		claimName=this.getIntent().getExtras().getString("claimName");
		setContentView(R.layout.activity_expense_list);
		FileManager.initializeSaver(this);
		//registerForContextMenu(findViewById(R.id.CurrentExpenseList));
		expenseListView = (ListView) findViewById(R.id.CurrentExpenseList);
		expenseList = ClaimListController.getClaimList().getClaim(claimName).getExpenseList();
		//ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this,android.R.layout.simple_expandable_list_item_1);
		
		expenseAdaptor = new ExpenseListAdaptor(this,R.layout.expense_list_adaptor,expenseList.toArrayList());
		expenseAdaptor.notifyDataSetChanged();
		expenseListView.setAdapter(expenseAdaptor);
		
		registerForContextMenu(findViewById(R.id.CurrentExpenseList));


		ListView listView = (ListView) findViewById(R.id.CurrentExpenseList);


		//ListView listView = (ListView) findViewById(R.id.CurrentExpenseList);

		//ArrayAdapter<Expense> expenseAdapter = new ArrayAdapter<Expense>(this,android.R.layout.simple_expandable_list_item_1);

	}

	@Override
	protected void onStart(){
		super.onStart();
		expenseAdaptor.notifyDataSetChanged();
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		expenseAdaptor.notifyDataSetChanged();
		
		
	}
	
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
	
    public void SearchOption (MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
    
    public void SignOut(MenuItem menu)
    {
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }

    public void AddExpenseButton(View view)
    {
    	Toast.makeText(this, "Going to Add Expense", Toast.LENGTH_SHORT).show();
    	
    	//startActivity(intent); 
    	
    	
    	int i = expenseList.size();
    	while(ClaimListController.getClaimList().getClaim(claimName).getExpense("Expense"+i)!=null){
    		i++;
    	}
    	Expense expense = new Expense("Expense"+i);
		ClaimListController.getClaimList().getClaim(claimName).addExpense(expense);
		
    	Toast.makeText(this, "Creating a Expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(ExpenseListActivity.this, EditExpenseActivity.class);
    	//Intent intent = new Intent(AddClaimActivity.this, EditClaimActivity.class);
    	//attach claim name to intent and start activity
    	intent.putExtra("claimName",claimName );
    	intent.putExtra("expenseName", expense.getName());
    	startActivity(intent);   
    }
    
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        
		Intent intent;
		final Expense expense = expenseAdaptor.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.expenseListViewMenuEdit:
            	//expenseList.removeExpense(expense);
            	Toast.makeText(this, "Editing an expense", Toast.LENGTH_SHORT).show();
            	intent = new Intent(ExpenseListActivity.this, EditExpenseActivity.class);
//            	Bundle bundle=new Bundle();
//            	bundle.putString("expenseName", this.expense.getName());
//            	bundle.putInt("expenseCost", this.expenseCost);
//            	bundle.putString("expenseDescription", this.expenseDescription);
            	Bundle bundle=new Bundle();
            	intent.putExtra("claimName", claimName);
            	intent.putExtra("expenseName", expense.getName());
//            	String expenseName=expense.getName();
//            	intent.putExtra("expenseName", expenseName);
//            	expenseAdaptor.notifyDataSetChanged();

            	//ClaimListController.addExpense(expense, claim);
            	//Toast.makeText(this, expense.getCurr(), Toast.LENGTH_SHORT).show();///test////////
            	
            	startActivity(intent);   
            	
            	expenseAdaptor.notifyDataSetChanged();
            	return true;
                
            case R.id.expenseListViewMenuDelete:
            	expenseList.removeExpense(expense);
            	expenseAdaptor.notifyDataSetChanged();
            	return true;
            	
            case R.id.expenseListViewMenuFlag:
            	int flagStatus=expense.getFlag();
            	if (flagStatus==1){
            		Toast.makeText(this, "Already Flaged", Toast.LENGTH_SHORT).show();
            	}
            	else{
            		expense.addFlag();
            		Toast.makeText(this, "Expense Flaged", Toast.LENGTH_SHORT).show();
            	}
            	return true;
            case R.id.expenseListViewMenuUnflag:
            	int flagStatus2=expense.getFlag();
            	if (flagStatus2==1){
            		expense.removeFlag();
            		Toast.makeText(this, "Expense unflaged", Toast.LENGTH_SHORT).show();
            	}
            	else{
            		Toast.makeText(this, "No Flag", Toast.LENGTH_SHORT).show();
            	}
            default:
                return super.onContextItemSelected(item);
        }
	}

}
