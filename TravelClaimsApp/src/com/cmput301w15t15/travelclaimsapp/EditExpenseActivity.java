package com.cmput301w15t15.travelclaimsapp;

import com.cmput301w15t15.travelclaimsapp.R;
import com.cmput301w15t15.travelclaimsapp.R.layout;
import com.cmput301w15t15.travelclaimsapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditExpenseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_expense);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_expense, menu);
		return true;
	}
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()) {
		case R.id./*
		 * This is an Application template. Change the name
		 * of the class (class_name) appropriately, and put
		 * your code in the main method.
		 */

		// Replace class_name with the first
		// part of the file name.  For example:
		//    class_name: MyClass
		//    file name : MyClass.java
		// Here's an incorrect example:
		//    class_name: MyClass
		//    file name : MyFile.java
		public class class_name
		{

			// This is the main method.
			public static void main(String[] arguments)
			{

				// Put your code in this method.
				// Here's a sample statement.
				System.out.println("Hi.");
				

			}
		}:
		adapter.remove(adapter.getItem(info.position));
		return true;
}
