package com.cmput301w15t15.travelclaimsapp.activitys;

import java.text.ParseException;
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
import android.content.res.Resources.Theme;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity to handle editing claim 
 *
 * TODO:
 *	-Add destinations 
 *	-Show destination list
 *
 */
public class EditClaimActivity extends FragmentActivity implements TextWatcher {
	
	private static EditText claimStartDate;
	private static EditText claimEndDate;
	private static EditText claimNameInput;
	private static boolean Start;
	private Claim theClaim; 
	private ClaimList claimList;
	private SimpleDateFormat sdf; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		
		sdf = new SimpleDateFormat("mm/dd/yyyy",Locale.CANADA);
		claimStartDate = (EditText) findViewById(R.id.ClaimStart);
		claimEndDate = (EditText) findViewById(R.id.ClaimEnd);
		claimNameInput = (EditText) findViewById(R.id.Edit_Claim_Name);
		
		claimList = ClaimListController.getClaimList();
		
		//get the claim name passed with the intent 
		String claimName = getIntent().getExtras().getString("claimName");
		theClaim = claimList.getClaim(claimName);
		
		claimNameInput.addTextChangedListener(this);
		claimStartDate.addTextChangedListener(this);
		claimEndDate.addTextChangedListener(this);
		
		set_on_click();
	}

	
		
	@Override
	protected void onStart() {
		super.onStart();
		claimNameInput.setText(theClaim.getName());
		if(theClaim.getStartDate()!=null){
			claimStartDate.setText(sdf.format(theClaim.getStartDate()));
		}
		if(theClaim.getEndDate()!=null){
			claimEndDate.setText(sdf.format(theClaim.getEndDate()));
		}
		
		setEditable();
		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//set rest of EditTexts if values exist 
		
		
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
		//need to make a expense 
    	Toast.makeText(this, "Viewing Current Expenses", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, ExpenseListActivity.class);
    	startActivity(intent);   
    }

	@Override
	public void afterTextChanged(Editable s) {
		
		switch(getCurrentFocus().getId()){
			case R.id.Edit_Claim_Name:
				String newName = s.toString();
				if(s.length() == 0 ){
					//claimNameInput.removeTextChangedListener(this);
					//claimNameInput.setText(theClaim.getName());
					//claimNameInput.addTextChangedListener(this);
					Toast.makeText(this, "Claim name cannot be null", Toast.LENGTH_LONG).show();
				}else if(claimList.getClaim(newName)!=null){
					//claimNameInput.removeTextChangedListener(this);
					//claimNameInput.setText(theClaim.getName());
					//claimNameInput.addTextChangedListener(this);
					Toast.makeText(this, "Claim name cannot be duplicate of another claim", Toast.LENGTH_LONG).show();
				}else{
					theClaim.setName(claimNameInput.getText().toString());
				}
			case R.id.ClaimStart:
				try{
					theClaim.setStartDate(sdf.parse(claimStartDate.getText().toString()));
				}catch(ParseException e){
					//do nothing 
				}
			case R.id.ClaimEnd:
				try{
					theClaim.setEndDate(sdf.parse(claimEndDate.getText().toString()));
				}catch(ParseException e){
					//do nothing
				}
			
				
		}
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
		
	}
   	public void setEditable(){
   		if(theClaim.getClaimStatus() == "Submitted" || theClaim.getClaimStatus() == "Approved"){
   			claimNameInput.setFocusable(false);
   		}
   	}
}
