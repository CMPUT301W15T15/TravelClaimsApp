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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;
import com.cmput301w15t15.travelclaimsapp.model.Expense;

public class ExpenseTest extends TestCase
{
	/**Test case that that tests whether or not the info enter in the expenseName
	 * field is correct
	 * 
	 */
	public void testExpenseName()
	{
		String expenseName = "expense1";
		Expense expense = new Expense(expenseName);
		assertTrue("Expense name is not equal", expenseName.equals(expense.getName()));
	}
	/**Test case that that tests whether or not the info enter in the expenseCost
	 * field is correct
	 * 
	 */
	public void testExpenseCost()
	{
		Integer expenseCost = 12;
		Expense expense = new Expense("expense2");
		expense.setCost(expenseCost);
		assertTrue("Expense cost is not equal", expenseCost.equals(expense.getCost()));
	}
	/**Test case that that tests whether or not the info enter in the expenseCurr
	 * field is correct
	 * 
	 */
	public void testExpenseCurr()
	{
		String expenseCurr = "CAD";
		Expense expense = new Expense("expense3");
		expense.setCurr(expenseCurr);
		assertTrue("Expense curr is not equal", expenseCurr.equals(expense.getCurr()));
	}
	/**Test case that that tests whether or not the info enter in the expenseDes
	 * field is correct
	 * 
	 */
	public void testExpenseDes()
	{
		String expenseDes = "hello";
		Expense expense = new Expense("expense4");
		expense.setDes(expenseDes);
		assertTrue("Expense curr is not equal", expenseDes.equals(expense.getDes()));
	}
	/**Test case that that tests whether or not the info enter in the expenseCat
	 * field is correct
	 * 
	 */
	public void testExpenseCat()
	{
		String expenseCat = "Air fare";
		Expense expense = new Expense("expense5");
		expense.setCat(expenseCat);
		assertTrue("Expense curr is not equal", expenseCat.equals(expense.getCat()));
	}
	/**Test case that that tests whether or not the info enter in the expenseDate
	 * field is correct
	 * 
	 */
	public void testExpenseDate() throws ParseException
	{
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		Date expenseDate = fmt.parse("03-10-2015");
		Expense expense = new Expense("expense6");
		expense.setDate(expenseDate);
		assertTrue("Expense date is not equal", expenseDate.equals(expense.getDate()));
	}
	
}
