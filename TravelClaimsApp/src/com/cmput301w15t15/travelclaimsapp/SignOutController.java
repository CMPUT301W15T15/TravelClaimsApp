package com.cmput301w15t15.travelclaimsapp;



/**
 * Used when signing out. Ensures singletons are reset.
 */
public class SignOutController {
	
	/**
	 * Ensures all singleton Controllers are reset.
	 */
	public static void reset() {
		ClaimListController.getClaimList().notifyListeners();
		ClaimListController.resetClaimListController();
		SubmittedClaimListController.reset();
		UserController.resetUserController();
		GeoLocationController.resetGeoLocationController();
	}
	
}
