package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ExpenseListViewActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_list_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_list_view, menu);
		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        
        switch (item.getItemId()) {
            case R.id.expenseListViewMenuEdit:
              
            case R.id.expenseListViewMenuDelete:
            	
            case R.id.expenseListViewMenuFlag:
            
            case R.id.expenseListViewMenuUnflag:
            	
            default:
                return super.onContextItemSelected(item);
        }
	}
	

}
