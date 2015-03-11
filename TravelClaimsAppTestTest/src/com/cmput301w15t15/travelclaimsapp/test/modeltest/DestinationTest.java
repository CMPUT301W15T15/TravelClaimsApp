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
