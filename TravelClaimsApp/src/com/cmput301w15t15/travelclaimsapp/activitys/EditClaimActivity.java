package com.cmput301w15t15.travelclaimsapp.activitys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class EditClaimActivity extends FragmentActivity {
	
	private static EditText claimStartDate;
	private static EditText claimEndDate;
	private static EditText claimNameInput;
	private static boolean Start;
	private Claim theClaim; 
	private ClaimList claimList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		
		claimStartDate = (EditText) findViewById(R.id.ClaimStart);
		claimEndDate = (EditText) findViewById(R.id.ClaimEnd);
		claimNameInput = (EditText) findViewById(R.id.Edit_Claim_Name);
		
		claimList = ClaimListController.getClaimList();
		
		//get the claim name passed with the intent 
		String claimName = getIntent().getExtras().getString("claimName");
		theClaim = claimList.getClaim(claimName);
		
		
		set_on_click();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//set rest of EditTexts if values exist 
		if(theClaim.getName() != null){
			claimNameInput.setText(theClaim.getName());
		}
		
		
	}





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
		return true;
	}
	//Retrieved on February 28, 2015 from http://developer.android.com/guide/topics/ui/controls/pickers.html
	public void showTruitonDatePickerDialog(View v)
	{
		if (v == claimStartDate)
		{
			Start = true;
		}
		else
		{
			Start = false;
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
			if (Start)
			{
				claimStartDate.setText((month + 1) + "/" + day + "/" + year);
			}
			else
			{
				claimEndDate.setText((month + 1) + "/" + day + "/" + year);
			}
		}	
	}
	
	private void set_on_click()
	{
		claimStartDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				showTruitonDatePickerDialog(v);
			}
		});
		claimEndDate.setOnClickListener(new View.OnClickListener() {
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
    	Intent intent = new Intent(EditClaimActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	
	public void SignOut(MenuItem menu)
    {
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }
	
	public void AddExpenseButton(View view)
    {
    	Toast.makeText(this, "Viewing Current Expenses", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, ExpenseListActivity.class);
    	startActivity(intent);   
    	}
}
