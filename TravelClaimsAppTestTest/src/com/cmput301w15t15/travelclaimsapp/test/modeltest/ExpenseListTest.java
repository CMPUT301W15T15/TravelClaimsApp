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
		Collection<Expense> expenses = (Collection<Expense>) expenseList.getExpense();
		assertTrue("Empty Expense List", expenses.size() == 0);
	}
	public void testAddExpense()
	{
		ExpenseList expenseList = new ExpenseList();
		String x = "New Expense" ;
		Expense testexpense = new Expense(x);
		expenseList.addExpense(testexpense);
		Collection<Expense> expenses = (Collection<Expense>) expenseList.getExpense();
		assertTrue("expense List Size not big enough", expenses.size() == 1);
		assertTrue("Test expense not contain", expenses.contains(testexpense));
	}
	public void testRemoveExpense()
	{
		ExpenseList expenseList = new ExpenseList();
		String x = "New Expense" ;
		Expense testexpense = new Expense(x);
		expenseList.addExpense(testexpense);
		Collection<Expense> expenses = (Collection<Expense>) expenseList.getExpense();
		assertTrue("expense List Size isnt small enough", expenses.size() == 1);
		assertTrue("", expenses.contains(testexpense));
		expenseList.removeExpense(testexpense);
		expenses = (Collection<Expense>) expenseList.getExpense();
		assertTrue("expense List Size", expenses.size() == 0);
		assertFalse("Test expense contain", expenses.contains(testexpense));
	}
}
