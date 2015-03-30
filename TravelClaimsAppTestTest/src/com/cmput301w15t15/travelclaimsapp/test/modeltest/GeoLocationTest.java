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
package com.cmput301w15t15.travelclaimsapp.test.modeltest;


import com.cmput301w15t15.travelclaimsapp.model.GeoLocation;

import junit.framework.TestCase;

/**
 * Tests for {@link GeoLocation}
 * 
 * @author searn
 *
 */
public class GeoLocationTest extends TestCase {

	private GeoLocation gl;
	
	public GeoLocationTest() {
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		gl = new GeoLocation(53.544389,-113.490927);		
	}

	/**
	 * Tests {@link GeoLocation} getters and setters
	 */
	public void testgetterSetters(){
		
		assertEquals("constructor did not set anything", 53.544389, gl.getLatitude());
		assertEquals("constructor did not set anything", -113.490927, gl.getLongitude());
		
		gl.setLatitude(52.544389);
		gl.setLongitude(-112.490927);
		
		assertEquals("setter did not set", 52.544389, gl.getLatitude());
		assertEquals("setter did not set", -112.490927, gl.getLongitude());
	}
	/**
	 * Test {@link GeoLocation} setLatLng and getString functions 
	 */
	public void testSetLatLng(){
		gl.setLatLng(51.544389, -111.490927);
		
		assertEquals("setter did not set", "51.544389,-111.490927", gl.getString());
	}
	
	public void testGetGeolocationFromString(){
		GeoLocation gl = GeoLocation.getFromString("50.45,-113.1");
		assertEquals("Function returned incorrect lat and long", 50.45,gl.getLatitude());
		assertEquals("Function returned incorrect lat and long", -113.1,gl.getLongitude());
	}
	
	
}
