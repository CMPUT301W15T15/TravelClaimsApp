package com.cmput301w15t15.travelclaimsapp;



public class SignOutController {
	
	public static void reset() {
		ClaimListController.getClaimList().notifyListeners();
		ClaimListController.resetClaimListController();
		SubmittedClaimListController.resetSubmittedClaimListController();
		UserController.resetUserController();
		GeoLocationController.resetGeoLocationController();
	}
	
}
