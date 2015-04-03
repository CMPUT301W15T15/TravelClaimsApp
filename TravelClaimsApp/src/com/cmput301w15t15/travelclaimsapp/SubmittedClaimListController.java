package com.cmput301w15t15.travelclaimsapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import com.cmput301w15t15.travelclaimsapp.model.Claim;
import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.ExpenseList;
import com.cmput301w15t15.travelclaimsapp.model.Listener;
import com.cmput301w15t15.travelclaimsapp.model.Tag;
import com.cmput301w15t15.travelclaimsapp.model.TagList;


public class SubmittedClaimListController
{
	private static ClaimList submittedClaimList = null;
	private static ExpenseList submittedExpenseList = null;
	
	public static boolean initSubmittedClaimListController() {
		reset();
		if(submittedClaimList == null){
			submittedClaimList = FileManager.getSaver().getSumbittedClaimList();
			if(submittedClaimList == null){
				return false;
			}
			submittedClaimList.setListeners();
			submittedClaimList.addListener(new Listener() {
				
				@Override
				public void update() {
					save();
				}
			});
			//add a listener to each claim in loaded claimlist
			for(Claim claim : submittedClaimList.toArrayList()){
				claim.setListeners(); 
				addClaimListeners(claim);
			}
		}
		return true;
	}
	
	/**
	 * Returns the global application claimList
	 * 
	 * If claimList is null it will load the claimList from the android file system
	 * and returns claimList
	 * 
	 * @return the application claimList
	 */
	static public ClaimList getClaimList() {
		if(submittedClaimList == null){
			initSubmittedClaimListController();
		}
		return submittedClaimList;
	}
	
	/**
	 * Returns a ArrayList<Tag> containing all unique tags in claimlist
	 * 
	 * Searches through each claim in singleton claimlist and returns list
	 * of unique tags.
	 * 
	 * @return all unique tags in the singleton claimlist
	 */
	public static ArrayList<Tag> getTagList(){
		TagList tags = new TagList();
		
		for(Claim claim : submittedClaimList.toArrayList()){
			TagList tmp = claim.getTagList();
			for(Tag tag : tmp.toArrayList()){
				if(!tags.contains(tag.getName())){
					tags.addTag(tag);
				}
			}
		}
		return tags.toArrayList();
	}
	
	/**
	 * Uses ClaimList.addClaim to add claim with listeners to claimList. 
	 * 
	 * @param claim claim to be added to claimlist 
	 * @throws IOException 
	 */
	public static void addClaim(Claim claim){
		addClaimListeners(claim);
		getClaimList().addClaim(claim);
	}
	/**
	 * Uses ClaimList.removeClaim to remove a claim from claimList. 
	 * 
	 * @param claim claim to be removed from claimlist 
	 */
	public static void removeClaim(Claim claim){
		
		getClaimList().removeClaim(claim);
	}
	
	
	
	/**
	 * Saves claimlist to file using FileManager class 
	 */
	public static void save(){
		FileManager.getSaver().saveSubmittedClaimLInFile(getClaimList());
	}
	
	/**
	 * Sets the application {@link ClaimList} to new empty {@link ClaimList}
	 */
	public static void removeAllClaims(){
		submittedClaimList = new ClaimList();
		save();
	}
	
	/**
	 * Adds listeners to the claim as well as the claims expenselist and expenses 
	 * 
	 * @param claim 	the Claim to add listeners to
	 */
	private static void addClaimListeners(Claim claim){
		//first add a listener to the claim
		claim.addListener(new Listener() {
			@Override
			public void update() {
				save();
			}
		});
	}

	/**
	 * Returns the current ClaimList filtered as a ArrayList<Claim>
	 * 
	 * Takes a string containing comma separated search words and 
	 * returns a ArrayList of the claims that contain all search terms
	 * 
	 * @param filterString string to filter claimlist with
	 * @return	ArrayList<Claim> containing claims that match filter
	 */
	public static ArrayList<Claim> getFilteredClaimList(String filterString) {
		final ArrayList<Claim> claims = getClaimList().toArrayList();
	    ArrayList<Claim> newClaims = new ArrayList<Claim>(claims.size());
	    String[] searchWords = filterString.split(",");
        int wordCount = searchWords.length;
        boolean match;
        for (Claim claim : claims) {
        	match = false;
            ArrayList<Tag> tagList = claim.getTagList().toArrayList();
            
	            for(Tag tag : tagList){
	            	String tagName = tag.getName().toString().toLowerCase(Locale.CANADA);
	            	// First match against string
	                if (tagName.equals(filterString.toLowerCase(Locale.CANADA))){
	                    newClaims.add(claim);
	                    match = true;
	                    break;
	                //if whole does that match then split searchString by commas 
	                //and check if all search word match
	                }else{
	                	for(String word : searchWords){
	                		if(tagName.equals(word.trim().toLowerCase(Locale.CANADA))){
	                			newClaims.add(claim);
	                			match = true;
	    	                    break;
	                		}
	                	}	
	                }
	                if(match == true){
	                	break;
	                }
            	}
            }         
		return newClaims;
	}

	public static void reset(){
		submittedClaimList = null;
		submittedExpenseList = null;
	}
}
