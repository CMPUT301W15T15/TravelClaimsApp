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

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SignOutController;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditExpenseActivity extends FragmentActivity implements TextWatcher {
	private Claim claim;
	private ClaimList claimList;
	private Expense expense;
	private ExpenseList expenseList;
	private static EditText expenseNameInput;
	private static EditText date;
	private static EditText expenseCostInput;
	private static EditText expenseDescriptionInput;
	private Spinner currencySpinner;
	private static Spinner categorySpinner;
	private SimpleDateFormat sdf; 
	private static boolean Start;
	private String expenseName;
	private String claimName;
	private byte[] imgShow;
	private ImageView expenseReceiptView;
	
	private Integer sizeNum=0;
	private String size="0";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.edit_expense);
		
		sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.CANADA);
		expenseName=this.getIntent().getExtras().getString("expenseName");
		claimName=this.getIntent().getExtras().getString("claimName");
		
		date = (EditText) findViewById(R.id.Edit_Expense_Date2);
		expenseNameInput=(EditText) findViewById(R.id.Edit_Expense_Name2);
		expenseCostInput=(EditText) findViewById(R.id.Edit_Expense_Cost2);
		expenseDescriptionInput=(EditText) findViewById(R.id.Edit_Expense_Description2);
		currencySpinner = (Spinner) findViewById(R.id.CurrencySpinner2);
		categorySpinner=(Spinner) findViewById(R.id.CategorySpinner2);
		expenseReceiptView = (ImageView) findViewById(R.id.Edit_Expense_Image2);
		
		claimList = ClaimListController.getClaimList();
		claim=claimList.getClaim(claimName);
		expenseList=claim.getExpenseList();
		expense=expenseList.getExpense(expenseName);
		
		// show initial image
		if (expense.getPicture()!=null){
			imgShow = expense.getPicture();
			
			
	        Bitmap bm = BitmapFactory.decodeByteArray(imgShow, 0, imgShow.length);
	        sizeNum=imgShow.length;
	        size=sizeNum.toString();
	        DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);

	        expenseReceiptView.setMinimumHeight(dm.heightPixels);
	        expenseReceiptView.setMinimumWidth(dm.widthPixels);
	        expenseReceiptView.setImageBitmap(bm);
		}
		
		expenseReceiptView.setOnLongClickListener(new OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View v) {
				// Create the Intent for Image Gallery.
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
		        startActivityForResult(i, 1);
		        return true;
			}
		});
		
		expenseReceiptView.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	loadPhoto((ImageView) v,100,100);   
		    }
		});
		
		currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,View view, int position, long id){
				expense.setCurr(parent.getItemAtPosition(position).toString());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		
		});
		
		
		categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,View view, int position, long id){
				expense.setCat(parent.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		
		});
		set_on_click();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ClaimListController.saveInEditActivities();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		ClaimListController.saveInEditActivities();
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
			Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            expenseReceiptView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            
            Bitmap imgToSave = BitmapFactory.decodeFile(imagePath);
            imgShow = getBytesFromBitmap(imgToSave);
            cursor.close();
		}
	}
	
	/**
	 * Returns given bitmap as a byte array. Rescales large images to correct size.
	 * 
	 * @param bitmap
	 * @return byte[]
	 */
	public byte[] getBytesFromBitmap(Bitmap bitmap) {
	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
	    byte[] tempImg;
	    Bitmap tempMap = bitmap;
	    Bitmap resizedMap; 
	    
	    double initWidth = Double.valueOf(bitmap.getWidth());
	    double initHeight = Double.valueOf(bitmap.getHeight());
	    
	    double resizeWidth = initWidth;
	    double resizeHeight = initHeight ;
	    
	    while (resizeWidth*resizeHeight >= 65536 ){
	    	resizeWidth=resizeWidth*0.9;
	    	resizeHeight=resizeHeight*0.9;	
	    }
	    
	    tempMap.compress(CompressFormat.JPEG, 70, stream2);
	    tempImg=stream2.toByteArray();
	    if (tempImg.length > 65536){
	    	resizedMap = Bitmap.createScaledBitmap(bitmap, (int) resizeWidth, (int) resizeHeight, false);
	    	resizedMap.compress(CompressFormat.JPEG, 100, stream);
	    	tempImg=stream.toByteArray();
	    	expense.setScale("Rescaled");
	    }
	    
	    else {
		    resizedMap = Bitmap.createScaledBitmap(tempMap, (int) resizeWidth, (int) resizeHeight, false);
	    	resizedMap.compress(CompressFormat.JPEG, 70, stream);
	    	tempImg=stream.toByteArray();
	    	expense.setScale("Original");
	    }
	        
	    sizeNum = tempImg.length;
	    size = sizeNum.toString();
	   
	    if(sizeNum>65536){
	    	Toast.makeText(this, "Image Too Large", Toast.LENGTH_LONG).show();
	    	return null;
	    }
	    else {
	    	return tempImg;
	    }
	    
	}
	
	@Override
	protected void onStart()
	{

		super.onStart();
		
		if (expense.getName()!=null){
			expenseNameInput.setText(expense.getName());
		}
		else{
			expenseNameInput.setText(expenseName);
		}

		if (expense.getDes()!=null){
			expenseDescriptionInput.setText(expense.getDes().toString());
		}

		if(expense.getCost()!=null){
			expenseCostInput.setText(expense.getCost().toString());
		}
		if(expense.getDate()!=null){
			date.setText(sdf.format(expense.getDate()));
		}

		expenseNameInput.addTextChangedListener(this);

		
		if(expense.getDate()!=null){
			date.setText(sdf.format(expense.getDate()));
		}

		ArrayAdapter<CharSequence> currencyAdaptor=ArrayAdapter.createFromResource(this, R.array.CurrencyArray, R.layout.spinner_item);
		currencyAdaptor.setDropDownViewResource(R.layout.spinner_dropdown_item);
		currencySpinner.setAdapter(currencyAdaptor);
		
		ArrayAdapter<CharSequence> categoryAdaptor=ArrayAdapter.createFromResource(this, R.array.CategoryArray, R.layout.spinner_item);
		categoryAdaptor.setDropDownViewResource(R.layout.spinner_dropdown_item);
		categorySpinner.setAdapter(categoryAdaptor);
		
		
		currencySpinner.setSelection(currencyAdaptor.getPosition(expense.getCurr()));
		categorySpinner.setSelection(categoryAdaptor.getPosition(expense.getCat()));
		
		setEditable();
	}

	
	/**
	 * Called when dialog appears to show current expense photo.
	 * 
	 * Loads photo to be displayed.
	 * ImageView is the location for the image to be displayed, height and width
	 * are of the size the the image will be displayed in.
	 * 
	 * @param ImageView
	 * @param int width
	 * @param int height
	 */
	private void loadPhoto(ImageView imageView, int width, int height) {

        ImageView tempImageView = imageView;

        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.image_adapter,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        image.setImageDrawable(tempImageView.getDrawable());
        imageDialog.setView(layout);
        String showDate = null;
        if (expense.getDate()!=null){
        	showDate = sdf.format(expense.getDate());
        }
        else{
        	showDate ="";
        }
        imageDialog.setPositiveButton(expense.getName() + " "+ showDate+" "+expense.getScale()+" "+"Size: "+size+" Byte", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        imageDialog.create();
        imageDialog.show();     
    }
	
	/**
	 * check if the status of a claim is editable 
	 */	
	private void setEditable() {
		// TODO Auto-generated method stub
		if(claim.getStatus().equals("Submitted") || claim.getStatus().equals("Approved")){
			expenseNameInput.setFocusable(false);
			date.setEnabled(false);
			expenseCostInput.setFocusable(false);
			currencySpinner.setClickable(false);
			categorySpinner.setClickable(false);
			expenseDescriptionInput.setFocusable(false);
			expenseReceiptView.setLongClickable(false);
			
		}else{
			set_on_click();
			//add text changed listeners 
			expenseNameInput.addTextChangedListener(this);
			date.addTextChangedListener(this);
			expenseCostInput.addTextChangedListener(this);
			expenseDescriptionInput.addTextChangedListener(this);
		}
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
	
	//Retrieved on February 28, 2015 from http://developer.android.com/guide/topics/ui/controls/pickers.html
	/**This method creates a new instance of datepickerfragment and the condition statements 
	 * sets what date will be start date and which one is end
	 * @author Henry
	 * @param v
	 */
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
	//Retrieved on February 28, 2015 from http://developer.android.com/guide/topics/ui/controls/pickers.html
	/**
	 * This defines dialogfragment by using the onCreateDialog method
	 */
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
				date.setText((month + 1) + "/" + day + "/" + year);
			}
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
	
	/** Function that is called when "Search" menu item is clicked
	 * and switches to the searchactivity
	 * @param menu
	 */
	public void SearchOption(MenuItem menu)
    {
    	Intent intent = new Intent(EditExpenseActivity.this, SearchActivity.class);
    	startActivity(intent);
    }
	
	/**Function that is called when the "Sign Out" menu item is clicked
	 * and switches to the mainscreenactivity
	 * @param menu
	 */
	public void SignOut(MenuItem menu)
    {
    	SignOutController.reset();
    	Intent intent = new Intent(EditExpenseActivity.this, LoginActivity.class);
    	startActivity(intent);
    }
	
	 /**
     * Function that is called when "Return to claim list" menu item clicked
     * 
     * @param menu		menu item that was clicked
     */
    public void backToClaimList(MenuItem menu)
    {
    	Intent intent = new Intent(EditExpenseActivity.this, AddClaimActivity.class);
    	startActivity(intent);
    }

	
	/** Function that is called when the "Ok" Button is clicked and switches 
	 * to the ExpenseListActivity
	 * @param view
	 */
	public void CreateExpense(View view)
    {
    	Intent intent = new Intent(EditExpenseActivity.this, ExpenseListActivity.class);

    	String claimName= claim.getName();
    	intent.putExtra("claimName", claimName);

    	String expenseName=expense.getName();
    	expense.takePicture(imgShow);
    	intent.putExtra("expenseName", expenseName);

    	startActivity(intent);   
    }

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		switch(getCurrentFocus().getId()){
		case R.id.Edit_Expense_Name2:
			if(s.length() == 0 ){
				//Toast.makeText(this, "Expense name cannot be null", Toast.LENGTH_LONG).show();
			}else{
				expense.setName(expenseNameInput.getText().toString());
			}
		case R.id.Edit_Expense_Date2:
			try{
				expense.setDate(sdf.parse(date.getText().toString()));
			}catch(ParseException e){
				//do nothing 
			}

		case R.id.Edit_Expense_Cost2:
			try{
				expense.setCost(Integer.parseInt(expenseCostInput.getText().toString()));
			}catch(NumberFormatException e){
				//do nothing
			}
		case R.id.Edit_Expense_Description2:
			expense.setDes(expenseDescriptionInput.getText().toString());
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}
	
	
	
	
}
