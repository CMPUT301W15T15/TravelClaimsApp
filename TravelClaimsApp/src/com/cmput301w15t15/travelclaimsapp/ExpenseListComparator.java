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