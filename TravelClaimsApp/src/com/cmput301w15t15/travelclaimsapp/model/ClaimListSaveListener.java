package com.cmput301w15t15.travelclaimsapp.model;

import com.cmput301w15t15.travelclaimsapp.ClaimListController;
import com.cmput301w15t15.travelclaimsapp.FileManager;
import com.cmput301w15t15.travelclaimsapp.UserController;

/**
 * A Listener class for adding to model classes you want to listen to
 * 
 * @author searn
 *
 */
public class ClaimListSaveListener implements Listener{

	@Override
	public void update() {
		save();
		
	}

	/**
	 * Saves claimlist to file using FileManager class 
	 */
	public void save(){
		FileManager.getSaver().saveClaimLInFile(ClaimListController.getClaimList(), UserController.getUser().getUsername());
	}
}
