package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
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
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent=new Intent();
		Bundle b=new Bundle();
		b=this.getIntent().getExtras();
		String claimName=b.getString("claimName");
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
<<<<<<< HEAD
=======

		ListView listView = (ListView) findViewById(R.id.CurrentExpenseList);
>>>>>>> 97c3505093c34fafdeeaf194759358229c041bf4

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
    	Intent intent = new Intent(ExpenseListActivity.this, EditExpenseActivity.class);
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
            	expenseList.removeExpense(expense);
            	expenseAdaptor.notifyDataSetChanged();
                
            case R.id.expenseListViewMenuDelete:
            	
            case R.id.expenseListViewMenuFlag:
            
            case R.id.expenseListViewMenuUnflag:
            	
            default:
                return super.onContextItemSelected(item);
        }
	}

}
