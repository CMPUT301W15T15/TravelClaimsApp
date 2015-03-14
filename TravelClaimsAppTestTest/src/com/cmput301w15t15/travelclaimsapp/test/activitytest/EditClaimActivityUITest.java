package com.cmput301w15t15.travelclaimsapp.test.activitytest;



import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.activitys.EditClaimActivity;
import com.cmput301w15t15.travelclaimsapp.activitys.EditExpenseActivity;
import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class for testing some of the EditClaimActivity UI
 *
 */
public class EditClaimActivityUITest extends
		ActivityInstrumentationTestCase2<EditClaimActivity> {
	

	private Instrumentation instrumentation;
	private Activity activity;
	private EditText inputName;
	private EditText inputDestination;
	private EditText inputReason;
	private EditText inputStartDate;
	private EditText inputEndDate;
	boolean bool1;
	private ClaimList claimList;
	private Claim claim;
	private Intent intent;
	private Button b;
	private EditText destLocation;
	private EditText destReason;
	
	public EditClaimActivityUITest() {
		super(EditClaimActivity.class);
		//Need to change the id's once UI has been made
	}

	protected void setUp() throws Exception {
		super.setUp();

		claim = new Claim("Claim1");
		ClaimListController.addClaim(claim);

		

		intent = new Intent();
		intent.putExtra("claimName", "Claim1");
		setActivityIntent(intent);
		setActivityInitialTouchMode(true);
		activity = getActivity();
		
		instrumentation = getInstrumentation();
	
		inputName = (EditText) activity.findViewById(R.id.Edit_Claim_Name);
		inputStartDate = (EditText) activity.findViewById(R.id.ClaimStart);
		inputEndDate = (EditText) activity.findViewById(R.id.ClaimEnd);
		b = (Button) activity.findViewById(R.id.AddDestinationButton);
	}
	
	/**
	 * EditClaimActivityUITest#1
	 * 
	 * Testing setting edittext values
	 */
	public void testSettingValues(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				inputName.setText("Claim2");
				inputStartDate.setText("11/02/2014");
				inputEndDate.setText("12/20/2014");
			}
		});
		instrumentation.waitForIdleSync();
		assertEquals("Claim2", inputName.getText().toString());
		assertEquals("11/02/2014", inputStartDate.getText().toString());
		assertEquals("12/20/2014", inputEndDate.getText().toString());
		
		
	}



	
	/**
	 * Test add destination dialog popup
	 */
	public void testShowDestinationDialog(){
		instrumentation.runOnMainSync(new Runnable() {
			
			@Override
			public void run() {
				b.performClick();
				
			}
		});
		instrumentation.waitForIdleSync();
		
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		ClaimListController.removeClaim(claim);
		
	}
	

	
	



	

	
	

	
	
}
