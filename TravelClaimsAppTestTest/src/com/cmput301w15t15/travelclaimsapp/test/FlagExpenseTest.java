package com.cmput301w15t15.travelclaimsapp.test;

import com.cmput301w15t15.travelclaimsapp.Claim;
import com.cmput301w15t15.travelclaimsapp.Expense;

import junit.framework.TestCase;


public class FlagExpenseTest extends TestCase
{
	private Expense expense1;
	private Expense expense2;
	protected void setUp() throws Exception
	{
		super.setUp();
		expense1=new Expense("taxi");
		expense2=new Expense("food");

	}
//TestCase: FlagExpenseTest#1
	public void flagExpenseTest(){
		expense2.addFlag();
		expense1.addFlag();
		assertFalse("This is expense is unflag",expense1.emptyFlag());
		expense2.removeFlag();
		assertTrue("This is expense still have flag",expense2.emptyFlag());
	}

}
