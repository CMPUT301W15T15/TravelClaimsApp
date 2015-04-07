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

/**
 * Used when signing out. Ensures singletons are reset.
 */
public class SignOutController {
	
	/**
	 * Ensures all singleton Controllers are reset.
	 * 
	 * The Singletons getting reset are ClaimListController, 
	 * Submitted ClaimListController, UserController, and GeoLocationController
	 */
	public static void reset() {
		ClaimListController.getClaimList().notifyListeners();
		ClaimListController.resetClaimListController();
		SubmittedClaimListController.reset();
		UserController.resetUserController();
		GeoLocationController.resetGeoLocationController();
	}
	
	/**
	 * Reset all singleton controllers on start 
	 */
	public static void resetOnStart() {
		ClaimListController.resetClaimListController();
		SubmittedClaimListController.reset();
		UserController.resetUserController();
		GeoLocationController.resetGeoLocationController();
	}
	
}
