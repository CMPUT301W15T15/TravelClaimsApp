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

import com.cmput301w15t15.travelclaimsapp.model.Claim;

/**
 * Comparator for the Submitted claim list
 * 
 * Compares claims returns -1 if first claim occurred before second claim
 * 0 if they are equal and 1 otherwise
 */
public class SubmittedListComparator implements Comparator<Claim> {

	
	@Override
	public int compare(Claim lhs, Claim rhs) {
		if(lhs.getStartDate() == null){
			return 1;
		}else if(rhs.getStartDate() == null){
			return -1;
		}else if(lhs.getStartDate().after(rhs.getStartDate())){
			return 1;
		}
		else if (lhs.getStartDate().before(rhs.getStartDate())) {
			return -1;
		}
		else{
			return 0;
		}
	}

	

}

