package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class KeyTest {
	
	//Array will store last 14 samples added
	private Key nt = new Key(14);
	private ColPal colP;
	
	
	
	@Before
	public void setup() {
		//Add 2 of each note found in D major scale (14 in total)
		nt.calc(new Note[] {new Note(18.35, 0)});
		nt.calc(new Note[] {new Note(18.35, 0)});
		nt.calc(new Note[] {new Note(20.60, 0)});
		nt.calc(new Note[] {new Note(20.60, 0)});
		nt.calc(new Note[] {new Note(23.12, 0)});
		nt.calc(new Note[] {new Note(23.12, 0)});
		nt.calc(new Note[] {new Note(24.50, 0)});
		nt.calc(new Note[] {new Note(24.50, 0)});
		nt.calc(new Note[] {new Note(27.50, 0)});
		nt.calc(new Note[] {new Note(27.50, 0)});
		nt.calc(new Note[] {new Note(30.87, 0)});
		nt.calc(new Note[] {new Note(30.87, 0)});
		nt.calc(new Note[] {new Note(34.65, 0)});
		nt.calc(new Note[] {new Note(34.65, 0)});
		try {
			colP = new ColPal(new File("colours.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testAddNote() {
		//Add a lot of C notes (a note not found in the D major scale)
		for(int i = 0; i < 30; i++) {
			nt.calc(new Note[] {new Note(16.35, 0)});
		}
		//Check that key is now not D
		assertFalse(nt.toString().equals("D"));
	}

	@Test
	public void testGetKey() {
		assertTrue(nt.toString().equals("D"));
	}
	
	@Test
	public void testGetCol() {
		Col ntCol = nt.getCol(colP);
		assertEquals(ntCol.getHue(), 240);
		assertEquals(ntCol.getSat(), 100);
		assertEquals(ntCol.getBri(), 50);
	}

}
