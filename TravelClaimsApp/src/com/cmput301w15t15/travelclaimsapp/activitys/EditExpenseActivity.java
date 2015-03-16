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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.ExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.ExpenseListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.id;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity.DatePickerFragment;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class EditExpenseActivity extends FragmentActivity{
	private Claim claim;
	private ClaimList claimList;
	private Expense expense;
	private ExpenseList expenseList;
	private ExpenseListAdaptor expenseListAdaptor;
	private int expenseCost=0;
	private String expenseDescription; 
	private static EditText expenseNameInput;
	private static EditText date;
	private static EditText expenseCostInput;
	private static EditText expenseDescriptionInput;
	private static Spinner currencySpinner;
	private static Spinner categorySpinner;
	private SimpleDateFormat sdf; 
	private static boolean Start;
	private String expenseName;
	private String claimName;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_edit_expense);
		
		sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		expenseName=this.getIntent().getExtras().getString("expenseName");
		claimName=this.getIntent().getExtras().getString("claimName");
		date = (EditText) findViewById(R.id.Edit_Expense_Date);
		
		expenseNameInput=(EditText) findViewById(R.id.Edit_Expense_Name);
		expenseCostInput=(EditText) findViewById(R.id.Edit_Expense_Cost);
		expenseDescriptionInput=(EditText) findViewById(R.id.Edit_Expense_Description);
		currencySpinner = (Spinner) findViewById(R.id.CurrencySpinner);
		categorySpinner=(Spinner) findViewById(R.id.CategorySpinner);
		
		//String expenseName= getIntent().getExtras().getString("expenseName");
		//String claimName= getIntent().getExtras().getString("claimName");
		claimList = ClaimListController.getClaimList();
		claim=claimList.getClaim(claimName);
		expenseList=claim.getExpenseList();
		expense=expenseList.getExpense(expenseName);
		//expenseCost = expense.getCost();
//		if (expense.getDes()!=null){
//			expenseDescription = expense.getDes();
//		}
//		else{
//			expenseDescription="None";
//		}
		set_on_click();
		//registerForContextMenu(findViewById(R.id.));
		//registerForContextMenu(findViewById(R.id.edit_claim_taglist));
	}
	
	@Override
	protected void onStart()
	{

		// TODO Auto-generated method stub
		super.onStart();
		expenseNameInput.setText(expenseName);
		if(expense.getDate()!=null){
			date.setText(sdf.format(expense.getDate()));
		}
		//setEditable();
	}

	@Override
	protected void onResume()
	{

		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense, menu);
		return true;
	}
	
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	
		
		
	
		//adapter.remove(adapter.getItem(info.position));
		return true;
	}
	
	public void showTruitonDatePickerDialog(View v)
	{	
		if (v==date){
			Start=true;
		}else{
			Start=false;
		}
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public static class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		@Override

		public void onDateSet(DatePicker view, int year, int month, int day) 
		{
			// Do something with the date chosen by the user
			date.setText((month+1) + "/" + day + "/" + year);
		}	
	}
	
	private void set_on_click()
	{
		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				showTruitonDatePickerDialog(v);
			}
		});
	}
	
	public void SearchOption(MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditExpenseActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	
	public void SignOut(MenuItem menu)
    {
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditExpenseActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }
	
	public void CreateExpense(View view)
    {
    	Toast.makeText(this, "Creating an expense", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditExpenseActivity.this, ExpenseListActivity.class);
//    	Bundle bundle=new Bundle();
//    	bundle.putString("expenseName", this.expense.getName());
//    	bundle.putInt("expenseCost", this.expenseCost);
//    	bundle.putString("expenseDescription", this.expenseDescription);
    	Bundle bundle=new Bundle();
    	String claimName= claim.getName();
    	intent.putExtra("claimName", claimName);
    	startActivity(intent);   
    	
    }
}
