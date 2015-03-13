package com.cmput301w15t15.travelclaimsapp.activitys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.DestinationListAdaptor;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.TagListAdaptor;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.DestinationList;
import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;


import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * Activity to handle editing claim 
 *
 * TODO:
 *  -Add tags
 *  -Fix window 
 *
 */
public class EditClaimActivity extends FragmentActivity implements TextWatcher {
	
	private static ListView destListView;
	private static ListView tagListView;
	private static EditText claimStartDate;
	private static EditText claimEndDate;
	private static EditText claimNameInput;
	private static boolean Start;
	private Claim theClaim; 
	private ClaimList claimList;
	private SimpleDateFormat sdf; 
	private DestinationListAdaptor destAdaptor;
	private TagListAdaptor tagAdaptor;
	private DestinationList dests;
	private TagList tags;
	private Button addDestButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		
		sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		claimStartDate = (EditText) findViewById(R.id.ClaimStart);
		claimEndDate = (EditText) findViewById(R.id.ClaimEnd);
		claimNameInput = (EditText) findViewById(R.id.Edit_Claim_Name);
		destListView = (ListView) findViewById(R.id.DestinationList);
		addDestButton = (Button) findViewById(R.id.AddDestinationButton);
		tagListView = (ListView) findViewById(R.id.edit_claim_taglist);
		claimList = ClaimListController.getClaimList();
		
		//get the claim name passed with the intent 
		String claimName = getIntent().getExtras().getString("claimName");
		theClaim = claimList.getClaim(claimName);
		dests = theClaim.getDestinationList(); 
		tags = theClaim.getTagList();
		
		claimNameInput.addTextChangedListener(this);
		claimStartDate.addTextChangedListener(this);
		claimEndDate.addTextChangedListener(this);
		
		tagAdaptor = new TagListAdaptor(this, R.layout.tag_list_adaptor, tags.toArrayList());
		destAdaptor = new DestinationListAdaptor(this, R.layout.dest_list_adaptor, dests.toArrayList());
		destListView.setAdapter(destAdaptor);
		tagListView.setAdapter(tagAdaptor);
		
		
		registerForContextMenu(findViewById(R.id.DestinationList));
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
		if(theClaim.getStatus().equals("Submitted") || theClaim.getStatus().equals("Approved")){
			claimNameInput.setFocusable(false);
			destListView.setClickable(false);
			destListView.setLongClickable(false);
			claimStartDate.setFocusable(false);
			claimEndDate.setFocusable(false);
			
		}
		
		destAdaptor.notifyDataSetChanged();
		tagAdaptor.notifyDataSetChanged();
		setEditable();
		
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//set rest of EditTexts if values exist 
		destAdaptor.notifyDataSetChanged();
		
	}





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
		return true;
	}
	
	
  @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.destinations_context_menu, menu);
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        

        final Destination dest = destAdaptor.getItem(info.position);
    
        switch (item.getItemId()) {
            case R.id.cmenu_dlist_delete:
            	dests.deleteDestination(dest);
            	destAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_dlist_edit:
            	
            	final EditText enterLocation = new EditText(this);
            	final EditText enterReason = new EditText(this);
            	
            	enterLocation.setText(dest.getLocation());
            	enterReason.setText(dest.getReason());
            
            	enterLocation.setHint("Enter location");
            	enterReason.setHint("Enter reason");
          
            	LinearLayout linearLayout = new LinearLayout(this);
            	linearLayout.setOrientation(LinearLayout.VERTICAL);
            	linearLayout.addView(enterLocation);
            	linearLayout.addView(enterReason);
            	
            	AlertDialog.Builder alert = new AlertDialog.Builder(this);
            	
            	alert.setView(linearLayout);
            
            	alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				dest.setLocation(enterLocation.getText().toString());
        				dest.setReason(enterReason.getText().toString());
        				destAdaptor.notifyDataSetChanged();
        			}
        		});
        		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				dialog.cancel();
        			}
        		});
        	
        		alert.show(); 
            	
            	return true;
            default:
                return super.onContextItemSelected(item);
        }
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
	
	public void viewExpensesButton(View view)
    {
		//need to make a expense 
    	Toast.makeText(this, "Viewing Current Expenses", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, ExpenseListActivity.class);
    	startActivity(intent);   
    }

	/**
	 * Function that is called when you press the add destination
	 * 
	 * @param view
	 */
	public void addDestinationButton(View view){
	
		showDestinationAlert(new String(), new String());
	}
	
	/**
	 * Function that is called when you press the add tag
	 * 
	 * @param view
	 */
	public void addTagButton(View view){
	  	final TextView enterTag = new AutoCompleteTextView(this);
    	
    	
    	enterTag.setHint("Enter tag");
    	
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
    	
    			
    	alert.setView(enterTag);
    
    	alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Tag tag = new Tag(enterTag.getText().toString());
				ClaimListController.addTag(theClaim, tag);
				tagAdaptor.notifyDataSetChanged();
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});
	
		alert.show(); 
    	
		
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
					Toast.makeText(this, sdf.parse(claimStartDate.getText().toString()).toString(), Toast.LENGTH_LONG).show();
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
   	/**
   	 * Create a alert dialog for entering Destination values 
   	 */
   	private void showDestinationAlert(final String dlocation, final String dreason){
    	final EditText enterLocation = new EditText(this);
    	final EditText enterReason = new EditText(this);
    	
    	if(!dlocation.equals("")){
    		enterLocation.setText(dlocation);
    		
    	}
    	if(!dreason.equals("")){
    		enterReason.setText(dreason);
    	}
    	
  
    	enterLocation.setHint("Enter location");
    	enterReason.setHint("Enter reason");
  
    	LinearLayout linearLayout = new LinearLayout(this);
    	linearLayout.setOrientation(LinearLayout.VERTICAL);
    	linearLayout.addView(enterLocation);
    	linearLayout.addView(enterReason);
    	
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
    	
    	alert.setView(linearLayout);
    
    	alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Destination dest = new Destination(enterLocation.getText().toString(), enterReason.getText().toString());
				ClaimListController.addDestination(dest, theClaim);
				destAdaptor.notifyDataSetChanged();
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.cancel();
			}
		});
	
		alert.show(); 
   	}
}
