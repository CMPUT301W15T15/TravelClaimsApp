package com.cmput301w15t15.travelclaimsapp.model;

/**
 * @author machinsk
 *
 * User Class: intended to store usernames and hashed passwords. 
 *
 */
public class User {

	private String username;
	private ClaimList claimList;
	
	private byte[] pHash;
	private boolean approver;
	
	/**
	 * To construct a user, both username and a password hash needed.
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, byte[] password){
		
		this.username = username;
		this.pHash = password;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getpHash() {
		return pHash;
	}

	public void setpHash(byte[] pHash) {
		this.pHash = pHash;
	}

	public boolean isApprover() {
		return approver;
	}

	public void setApprover(boolean approver) {
		this.approver = approver;
	}
	
}
