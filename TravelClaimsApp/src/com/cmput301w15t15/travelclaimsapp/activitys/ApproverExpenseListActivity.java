package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.ApproverExpenseListAdaptor;
import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.SubmittedClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Activity for viewing the ExpenseList of a Submitted Claim.
 */
public class ApproverExpenseListActivity extends Activity {

	private String claimName;
	private ListView expenseListView;
	private ExpenseList expenseList;
	private ApproverExpenseListAdaptor expenseAdaptor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_list);
		claimName=this.getIntent().getExtras().getString("claimName");
		setContentView(R.layout.expense_list);
		expenseListView = (ListView) findViewById(R.id.CurrentExpenseList2);
		expenseList = SubmittedClaimListController.getClaimList().getClaim(claimName).getExpenseList();

		expenseAdaptor = new ApproverExpenseListAdaptor(this,R.layout.approve_expense_list_adaptor,expenseList.toArrayList());
		expenseAdaptor.notifyDataSetChanged();
		expenseListView.setAdapter(expenseAdaptor);
		registerForContextMenu(findViewById(R.id.CurrentExpenseList2));
		
		expenseListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		expenseListView.setOnItemClickListener(new OnItemClickListener() {
			
			 public void onItemClick(AdapterView<?> parent, View view,
				        int position, long id) 
			 {
				 		loadPhoto(position, 200, 100);
			 }
			
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_expense_list, menu);
		return true;
	}
	
	
	/**
	 * Called when dialog appears to show current expense photo.
	 * 
	 * Loads photo to be displayed.
	 * Position is the position of the Expense in the listview, height and width
	 * are of the size the the image will be displayed in.
	 * 
	 * @param int position
	 * @param int width
	 * @param int height
	 */
	private void loadPhoto(int position, int width, int height) {


        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.image_adapter,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        
        byte[] imageShowing = expenseList.getExpenseByIndex(position).getPicture();

        image.setImageBitmap(BitmapFactory.decodeByteArray(imageShowing,0,imageShowing.length) ); 
        imageDialog.setView(layout);
       
        imageDialog.setPositiveButton("Return", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        imageDialog.create();
        imageDialog.show();     
    }
	
	
	

}
