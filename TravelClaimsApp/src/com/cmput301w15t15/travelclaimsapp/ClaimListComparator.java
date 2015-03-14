package com.cmput301w15t15.travelclaimsapp;

import java.util.Comparator;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;

/**
 * Comparator for {@link ClaimList}
 * 
 * Compares claims returns 1 if first claim occurred before second claim
 * 0 if they are equal and -1 otherwise
 */
public class ClaimListComparator implements Comparator<Claim> {

	
	@Override
	public int compare(Claim lhs, Claim rhs) {
		if(lhs.getStartDate() == null){
			return 1;
		}else if(rhs.getStartDate() == null){
			return -1;
		}else if(lhs.getStartDate().after(rhs.getStartDate())){
			return -1;
		}
		else if (lhs.getStartDate().before(rhs.getStartDate())) {
			return 1;
		}
		else{
			return 0;
		}
	}

	

}
