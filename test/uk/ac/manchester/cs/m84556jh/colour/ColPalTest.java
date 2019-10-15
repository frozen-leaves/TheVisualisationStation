package uk.ac.manchester.cs.m84556jh.colour;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ColPalTest {
	
	ColPal noteCols = null;
	
	@Test(expected = FileNotFoundException.class)
	public void testInvalidFileName() throws FileNotFoundException {
		noteCols = new ColPal(new File("notafile.txt"));
	}

	@Test
	public void testGetCol() throws FileNotFoundException {
		noteCols = new ColPal(new File("colours.txt"));
		assertTrue(((Integer)noteCols.getCol(2).getHue()).equals(240));
	}

}
