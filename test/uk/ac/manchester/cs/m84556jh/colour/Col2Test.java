package uk.ac.manchester.cs.m84556jh.colour;

import static org.junit.Assert.*;

import org.junit.Test;

public class Col2Test {
	
	Col2 colA = new Col2(20,30);
	Col2 colB = new Col2(1,2);

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

}
