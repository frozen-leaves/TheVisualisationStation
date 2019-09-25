package uk.ac.manchester.cs.m84556jh.colour;

import static org.junit.Assert.*;

import org.junit.Test;

public class Col3Test {
	
	Col3 colA = new Col3(20,30,102);
	Col3 colB = new Col3(1,2,3);
	Col3 colB2 = new Col3(1,2,3);

	@Test
	public void testGetHue() {
		assertEquals(20, colA.getHue());
		assertEquals(1, colB.getHue());
	}

	@Test
	public void testGetSat() {
		assertEquals(30, colA.getSat());
		assertEquals(2, colB.getSat());
	}

	@Test
	public void testGetBri() {
		assertEquals(100, colA.getBri());
		assertEquals(3, colB.getBri());
	}

	@Test
	public void testEqualsCol3() {
		assertTrue(colB.equals(colB2));
		assertFalse(colB.equals(colA));
	}
	
	@Test
	public void testToString() {
		assertTrue(colA.toString().equals("(20,30,100)"));
		assertTrue(colB.toString().equals("(1,2,3)"));
	}

}
