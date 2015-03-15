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

import com.cmput301w15t15.travelclaimsapp.model.Destination;
import com.cmput301w15t15.travelclaimsapp.model.DestinationList;

import junit.framework.TestCase;

/**
 * Tests for {@link Destination} and {@link DestinationList}
 *
 */
public class DestinationTest extends TestCase {

	private DestinationList dlist;
	private Destination dest1;

	protected void setUp() throws Exception {
		super.setUp();
		this.dest1 = new Destination("Edmonton", "Work");
		this.dlist = new DestinationList();
	}
	
	/**
	 * DestinationTest#1, for testing Destination objects functionality
	 */
	public void testDestination(){
		assertEquals("Constructor did not set location", "Edmonton", dest1.getLocation());
		assertEquals("Constructor did not set reason", "Work", dest1.getReason());
		
		dest1.setLocation("Stony Plain");
		dest1.setReason("Stuff");
		
		assertEquals("location was not set", "Stony Plain", dest1.getLocation());
		assertEquals("reason was not set", "Stuff", dest1.getReason());
	}
	
	/**
	 * DestinationTest#2, for testing DestinationList functionality
	 */
	public void testDestinationList(){
		dlist.addDestination(dest1);
		
		Destination d = dlist.toArrayList().get(0);
		
		assertEquals("Destination not added to list", dest1, d);
		
		dlist.deleteDestination(d);
		
		assertEquals("Destination was not removed", dlist.toArrayList().size(), 0);
	}
}
