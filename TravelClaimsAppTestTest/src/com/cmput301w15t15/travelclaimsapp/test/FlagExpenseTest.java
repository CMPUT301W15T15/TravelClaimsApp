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
//TestId:FlagExpenseTest
	public void flagExpenseTest(){
		assertTrue("This is expense is unflag",expense1.getFlag());
		expense2.getFlag();
		assertTrue("This is expense still have fag",expense2.removeFlag());
	}

}
