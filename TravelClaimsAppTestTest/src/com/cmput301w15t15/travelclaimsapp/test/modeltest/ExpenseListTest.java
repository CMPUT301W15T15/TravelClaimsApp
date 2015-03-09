package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.util.Collection;

import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import junit.framework.TestCase;


public class ExpenseListTest extends TestCase
{
	public void testExpenseList()
	{
		ExpenseList expenseList = new ExpenseList();
		Collection<Expense> expenses = (Collection<Expense>) expenseList.getExpense(null);
		assertTrue("Empty Expense List", expenses.size() == 0);
	}
}
