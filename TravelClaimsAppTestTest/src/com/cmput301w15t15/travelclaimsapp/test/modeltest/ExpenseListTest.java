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
package com.cmput301w15t15.travelclaimsapp.test.modeltest;

import java.util.ArrayList;
import java.util.Collection;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

import junit.framework.TestCase;



public class ExpenseListTest extends TestCase
{
	/**Test case that tests if a list we create is indeed created and empty
	 * 
	 */
	public void testExpenseList()
	{
		ExpenseList expenseList = new ExpenseList();
		ArrayList<Expense> expenses = expenseList.toArrayList();
		assertTrue("Empty Expense List", expenses.size() == 0);
	}
	/**Test case that tests that when an object is added to the expenseList
	 * is it actually added and shows the correct number of items in the expenseList
	 * 
	 */
	public void testAddExpense()
	{
		ExpenseList expenseList = new ExpenseList();
		String x = "New Expense" ;
		Expense testexpense = new Expense(x);
		expenseList.addExpense(testexpense);
		Collection<Expense> expenses = (Collection<Expense>) expenseList.toArrayList();
		assertTrue("expense List Size not big enough", expenses.size() == 1);
		assertTrue("Test expense not contain", expenses.contains(testexpense));
	}
	/**Test case that tests that if an object is deleted from the list
	 * is it actually deleted and shows the correct number of items in the list
	 * 
	 */
	public void testRemoveExpense()
	{
		ExpenseList expenseList = new ExpenseList();
		String x = "New Expense" ;
		Expense testexpense = new Expense(x);
		expenseList.addExpense(testexpense);
		Collection<Expense> expenses = (Collection<Expense>) expenseList.toArrayList();
		assertTrue("expense List Size isnt small enough", expenses.size() == 1);
		assertTrue("", expenses.contains(testexpense));
		expenseList.removeExpense(testexpense);
		expenses = (Collection<Expense>) expenseList.toArrayList();
		assertTrue("expense List Size", expenses.size() == 0);
		assertFalse("Test expense contain", expenses.contains(testexpense));
	}
}
