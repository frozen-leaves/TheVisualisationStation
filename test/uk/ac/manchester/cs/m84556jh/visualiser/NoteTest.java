package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import uk.ac.manchester.cs.m84556jh.colour.Col3;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class NoteTest {

	private Note noteA4 = new Note(440.0, 20.43);
	private Note noteG7 = new Note(3135.96, 30.35);
	ColPal noteCols;
	
	@Before
	public void setup() {
		try {
			noteCols = new ColPal(new File("colours.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetOctave() {
		assertEquals(noteA4.getOctave(), 4);
		assertEquals(noteG7.getOctave(), 7);
	}
	
	@Test
	public void testgetIndex() {
		assertEquals(noteA4.getIndex(), 9);
		assertEquals(noteG7.getIndex(), 7);
	}
	
	@Test
	public void testGetFreq() {
		assertTrue(((Double)noteA4.getFreq()).equals(440.0));
		assertTrue(((Double)noteG7.getFreq()).equals(3135.96));
	}
	
	@Test
	public void testGetAmp() {
		assertTrue(((Double)noteA4.getAmp()).equals(20.43));
		assertTrue(((Double)noteG7.getAmp()).equals(30.35));
	}
	
	@Test
	public void testGetCol() {
		assertTrue(noteA4.getCol(noteCols).equals(new Col3(64,275,48)));
		assertTrue(noteG7.getCol(noteCols).equals(new Col3(0,0,76)));
	}
	
	@Test
	public void testToString() {
		assertTrue(noteA4.toString().equals("A4"));
		assertTrue(noteG7.toString().equals("G7"));
	}

}
