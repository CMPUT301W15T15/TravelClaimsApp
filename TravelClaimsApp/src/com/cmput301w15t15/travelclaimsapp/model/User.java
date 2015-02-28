package com.cmput301w15t15.travelclaimsapp.model;

public class User {

	private String username;
	private byte[] pHash;
	
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
	
}
