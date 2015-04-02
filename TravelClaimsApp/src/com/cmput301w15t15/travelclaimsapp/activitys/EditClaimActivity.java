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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.DestinationListAdaptor;
import com.cmput301w15t15.travelclaimsapp.GeoLocationController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SignOutController;
//import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.TagListAdaptor;
import com.cmput301w15t15.travelclaimsapp.UserController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.DestinationList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;
import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;

import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;


/**
 *	Activity that handles claim editing 
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
	private Button addTagButton;
	private static int GET_GEOLOCATION_CODE = 10;
	private int adaptorPos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		
		GeoLocationController.initializeLocationManager(this.getApplicationContext());
		
		claimStartDate = (EditText) findViewById(R.id.ClaimStart);
		claimEndDate = (EditText) findViewById(R.id.ClaimEnd);
		claimNameInput = (EditText) findViewById(R.id.Edit_Claim_Name);
		destListView = (ListView) findViewById(R.id.DestinationList);
		addDestButton = (Button) findViewById(R.id.AddDestinationButton);
		addTagButton = (Button) findViewById(R.id.AddTagbutton);
		tagListView = (ListView) findViewById(R.id.edit_claim_taglist);
		claimList = ClaimListController.getClaimList();
	
		//get the claim name passed with the intent 
		String claimName = getIntent().getExtras().getString("claimName");
		theClaim = claimList.getClaim(claimName);
		dests = theClaim.getDestinationList(); 
		tags = theClaim.getTagList();
		
		//create adaptors and set them 
		tagAdaptor = new TagListAdaptor(this, R.layout.tag_list_adaptor, tags.toArrayList());
		destAdaptor = new DestinationListAdaptor(this, R.layout.dest_list_adaptor, dests.toArrayList());
		destListView.setAdapter(destAdaptor);
		tagListView.setAdapter(tagAdaptor);
		
		//register listviews for the context menu popup
		registerForContextMenu(findViewById(R.id.DestinationList));
		registerForContextMenu(findViewById(R.id.edit_claim_taglist));
	}

	@Override
	protected void onStart() {
		super.onStart();
		//initially set edittext values if they are not null
		claimNameInput.setText(theClaim.getName());
		if(theClaim.getStartDate()!=null){
			claimStartDate.setText(sdf.format(theClaim.getStartDate()));
		}
		if(theClaim.getEndDate()!=null){
			claimEndDate.setText(sdf.format(theClaim.getEndDate()));
		}
		//if claim has been submitted or approved then don't allow any further edits 
		setEditable();
		destAdaptor.notifyDataSetChanged();
		tagAdaptor.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//set rest of EditTexts if values exist 
		destAdaptor.notifyDataSetChanged();
		tagAdaptor.notifyDataSetChanged();
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
        if(v.getId() == R.id.DestinationList){
        	getMenuInflater().inflate(R.menu.destinations_context_menu, menu);
        }else{
        	getMenuInflater().inflate(R.menu.edit_tag_contextmenu, menu);
        }
    }
   
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
   
        switch (item.getItemId()) {
            case R.id.cmenu_dlist_delete:
            	ClaimListController.removeDestination(destAdaptor.getItem(info.position), theClaim);
            	destAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_dlist_edit:
            	//show a dialog for editing destinations 
            	final EditText enterLocation = new EditText(this);
            	final EditText enterReason = new EditText(this);
            	
            	enterLocation.setText(destAdaptor.getItem(info.position).getLocation());
            	enterReason.setText(destAdaptor.getItem(info.position).getReason());
            
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
        				destAdaptor.getItem(info.position).setLocation(enterLocation.getText().toString());
        				destAdaptor.getItem(info.position).setReason(enterReason.getText().toString());
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
        		
            case R.id.cmenu_dlist_geolocation:
            	adaptorPos = info.position;
            	AlertDialog.Builder alertGl = new AlertDialog.Builder(this);
            	alertGl.setPositiveButton("GPS", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(GeoLocationController.checkGPSEnabled()){
							GeoLocationController.setDestinationGeoLocationGPS(destAdaptor.getItem(info.position));
							destAdaptor.notifyDataSetChanged();
						}
					}
				});
            	alertGl.setNegativeButton("Map", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Open map view \
						Intent intent = GeoLocationController.pickLocationIntent(EditClaimActivity.this);
				    	startActivityForResult(intent, GET_GEOLOCATION_CODE);
					}
				});
            	
            	alertGl.show();
            	return true;
            case R.id.cmenu_delete_tag:
            	ClaimListController.removeTag(theClaim, tagAdaptor.getItem(info.position));
            	tagAdaptor.notifyDataSetChanged();
            	return true;
            case R.id.cmenu_rename_tag:
            	//create a Alert dialog for editing tag name
              	final TextView enterTag = new AutoCompleteTextView(this);
            	
            	enterTag.setHint("Enter tag");
            	
            	AlertDialog.Builder alertTag = new AlertDialog.Builder(this);
            	
            	alertTag.setView(enterTag);
            
            	alertTag.setPositiveButton("Add", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				tagAdaptor.getItem(info.position).rename(enterTag.getText().toString());
        				tagAdaptor.notifyDataSetChanged();
        			}
        		});
        		alertTag.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog, int whichButton) {
        				dialog.cancel();
        			}
        		});
        	
        		alertTag.show(); 
            	
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
	
	/**
	 * sets onClickListeners for claimStartDate and claimEndDate edittexts 
	 */
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
	
	/**
	 * Function called when search button pressed in settings menu
	 * 
	 * @param menu
	 */
	public void SearchOption(MenuItem menu)
    {
    	Toast.makeText(this, "Going to Search", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	/**
	 * Function called when signout button pressed in settings menu 
	 * 
	 * @param menu
	 */
	public void SignOut(MenuItem menu)
    {
    	SignOutController.reset();
    	Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EditClaimActivity.this, MainMenuActivity.class);
    	startActivity(intent);
    }
	
	/**
	 * Function that is called when user presses the view expenses button
	 * 
	 * @param view
	 */
	public void viewExpensesButton(View view)
    {
		//need to make a expense 
    	Intent intent = new Intent(EditClaimActivity.this, ExpenseListActivity.class);
    	String claimName= theClaim.getName();
    	intent.putExtra("claimName", claimName);
    	startActivity(intent);   
    }

	/**
	 * Function that is called when you press the add destination
	 * 
	 * @param view
	 */
	public void addDestinationButton(View view){
		adaptorPos = destAdaptor.getCount();
		showDestinationAlert(new String(), new String());
		

	}
	
	/**
	 * Function that is called when you press the add tag
	 * 
	 * Creates a alert dialog that gives the user the option 
	 * of adding a previously added tag or entering a new tag name
	 * 
	 * @param view
	 */
	public void addTagButton(View view){
		final EditText enterTag = new EditText(this);
    	final Spinner tagSpinner = new Spinner(this);
    	//Linear layout that holds enterTag and tagSpinner views
    	LinearLayout ll = new LinearLayout(this);
    	ll.setOrientation(LinearLayout.VERTICAL);
    	tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

    		@Override
    		public void onItemSelected(AdapterView<?> parent,View arg1, int position, long id) {
    			enterTag.setText(parent.getItemAtPosition(position).toString());
    		}

    		@Override
    		public void onNothingSelected(AdapterView<?> arg0) {
    			// TODO Auto-generated method stub		
    		}
    	});
    	
    	enterTag.setHint("Enter tag");
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
    	
    	//get all the tags currently added to claims in application claimlist
    	ArrayList<Tag> tags = ClaimListController.getTagList();
    	String t[] = new String[tags.size()];
    	for(int i = 0; i<tags.size(); i++){
    		t[i] = tags.get(i).getName();
    	}
    	//create a arrayadaptor for displaying the tagSpinner view, and set it
    	ArrayAdapter<String> tagA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, t);
    	tagSpinner.setAdapter(tagA);
    	//add views to linear layout and set the Linear layout view as the alert dialog view 
    	ll.addView(tagSpinner);
    	ll.addView(enterTag);
    	alert.setView(ll);
    
    	alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Tag tag = new Tag(enterTag.getText().toString());
				if(theClaim.getTagList().getTag(enterTag.getText().toString())!=null){
					return;
				}
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
				//if length of name in edittext is 0 or if claim name is already in claimlist
				//then do not save changes. Otherwise update the claim name
				if(s.length() == 0 ){
					Toast.makeText(this, "Claim name cannot be null", Toast.LENGTH_LONG).show();
				}else if(claimList.getClaim(newName)!=null){
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
			int after) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}
	
   	/**
   	 * Functions turns off editing if claim is Submitted or Approved
   	 * otherwise it sets onClickListeners and text changed listeners
   	 */
   	public void setEditable(){
   		if(theClaim.getStatus().equals("Submitted") || theClaim.getStatus().equals("Approved")){
			claimNameInput.setFocusable(false);
			destListView.setClickable(false);
			destListView.setLongClickable(false);
			tagListView.setClickable(false);
			tagListView.setLongClickable(false);
			claimStartDate.setFocusable(false);
			claimEndDate.setFocusable(false);
			addTagButton.setClickable(false);
			addDestButton.setClickable(false);
			
		}else{
			set_on_click();
			//add text changed listeners 
			claimNameInput.addTextChangedListener(this);
			claimStartDate.addTextChangedListener(this);
			claimEndDate.addTextChangedListener(this);
		}
   	}
   	/**
   	 * Create a alert dialog for entering Destination values 
   	 * 
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
				
				//open Map activity and make user pick a geolocation
				Intent intent = GeoLocationController.pickLocationIntent(EditClaimActivity.this);
				startActivityForResult(intent, GET_GEOLOCATION_CODE);
				
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

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == GET_GEOLOCATION_CODE){
			switch (resultCode) {
			case RESULT_OK:
				Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();
				String geoString = data.getExtras().getString("geoLocation");
				//double[] latlng = getIntent().getExtras().getDoubleArray("LatLng2");
				GeoLocation gl = GeoLocation.getFromString(geoString);
				Destination dest = destAdaptor.getItem(adaptorPos);
				GeoLocationController.setDestinationGeoLocation(dest, gl.getLatitude(), gl.getLongitude());
				break;
			case RESULT_CANCELED:
				Toast.makeText(this, "RESULT_CANCEL", Toast.LENGTH_SHORT).show();
				break;
				
			default:
				Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show();
				break;
			}
			
		}
		
		
	}
   	
   	
}
