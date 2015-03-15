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
package com.cmput301w15t15.travelclaimsapp;

import java.util.Comparator;

import com.cmput301w15t15.travelclaimsapp.model.Expense;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;

public class ExpenseListComparator implements Comparator<Expense> {

//	public ExpenseListComparator() {
//		// TODO Auto-generated constructor stub
//	}


	@Override
	public int compare(Expense lhs, Expense rhs) {
		// TODO Auto-generated method stub
		if (lhs.getDate()==null){
			return 1;
		}
		else if(rhs.getDate() == null){
			return -1;
			
		}
		else if(lhs.getDate().after(rhs.getDate())){
			return -1;
			
		}
		else if (lhs.getDate().before(rhs.getDate())){
			return 1;
		}
		else{
			return 0;
		}
	}
}
