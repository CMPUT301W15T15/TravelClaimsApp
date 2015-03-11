package com.cmput301w15t15.travelclaimsapp.model;

/**
 * @author machinsk
 *
 * User Class: intended to store usernames, hashed passwords, and approver boolean for a given user. 
 *
 */
public class User {

	private String username;
	
	//private ClaimList claimList;
	private byte[] pHash;
	private boolean approver;
	
	
	/**
	 * To construct a user, username and a password hash needed, approver defualts to false.
	 *
	 * @param username
	 * @param password
	 * @param approver
	 */
	public User(String username, byte[] password, boolean approver){
		
		this.username = username;
		this.pHash = password;
		this.approver = approver;
		
	}
	
	
	/**
	 * To construct a user, username and a password hash needed, approver defaults to false.
	 *
	 * @param username
	 * @param password
	 */
	public User(String username, byte[] password){
		
		this.username = username;
		this.pHash = password;
		this.approver = false;
		
	}

	
	/**
	 * Returns String of username. This attribute is read only from the constructor.
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	
	/**
	 * Returns byte array of the password hash. This hash is read only from the constructor.
	 * 
	 * @return
	 */
	public byte[] getpHash() {
		return pHash;
	}


	/**
	 * Returns if boolean whether user is an Approver. This attribute is read only from the constructor.
	 * 
	 * @return
	 */
	public boolean isApprover() {
		return approver;
	}

	
}
