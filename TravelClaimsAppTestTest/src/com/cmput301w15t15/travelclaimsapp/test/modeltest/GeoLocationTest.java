package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import junit.framework.TestCase;

public class GeoLocationTest extends TestCase {

	private GeoLocation gl;
	
	public GeoLocationTest() {
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		gl = new GeoLocation(53.544389,-113.490927);		
	}

	public void testgetterSetters(){
		
		assertEquals("constructor did not set anything", 53.544389, gl.getLatitude());
		assertEquals("constructor did not set anything", -113.490927, gl.getlongitude());
		
		gl.setLatitude(52.544389);
		gl.setLongitude(-112.490927);
		
		assertEquals("setter did not set", 52.544389, gl.getLatitude());
		assertEquals("setter did not set", -112.490927, gl.getlongitude());
	}
	
	
	
}
