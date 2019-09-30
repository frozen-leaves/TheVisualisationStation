package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KeyTest {
	
	//Array will store last 14 samples added
	private Key nt = new Key(14);
	
	@Before
	public void setup() {
		//Add 2 of each note found in D major scale (14 in total)
		nt.calc(2);
		nt.calc(2);
		nt.calc(4);
		nt.calc(4);
		nt.calc(6);
		nt.calc(6);
		nt.calc(7);
		nt.calc(7);
		nt.calc(9);
		nt.calc(9);
		nt.calc(11);
		nt.calc(11);
		nt.calc(1);
		nt.calc(1);
	}

	@Test
	public void testAddNote() {
		//Add a lot of C notes (a note not found in the D major scale)
		for(int i = 0; i < 30; i++) {
			nt.calc(0);
		}
		//Check that key is now not D
		assertFalse(nt.toString().equals("D"));
	}

	@Test
	public void testGetKey() {
		assertTrue(nt.toString().equals("D"));
	}

}
