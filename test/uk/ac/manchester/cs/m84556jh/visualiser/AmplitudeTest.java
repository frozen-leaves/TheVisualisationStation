package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class AmplitudeTest {
	
	Amplitude amp = new Amplitude(5,20,20);

	@Test
	public void testCalcSize() {
		assertTrue(((Double)amp.calcSize(10)).equals(60.0));
		assertTrue(((Double)amp.calcSize(20)).equals(100.0));
		assertTrue(((Double)amp.calcSize(15)).equals(60.0));
		assertTrue(((Double)amp.calcSize(30)).equals(100.0));
		assertTrue(((Double)amp.calcSize(24)).equals(76.0));
		assertTrue(((Double)amp.calcSize(55)).equals(100.0));
		assertTrue(((Double)amp.calcSize(24)).equals(38.0));
	}

	@Test
	public void testGetPixelBuf() {
		Col colA = new Col(1,2,3);
		Col colB = new Col(4,5,6);
		Col colC = new Col(7,8,9);
		Col colD = new Col(10,11,12);
		Col[] bufA = new Col[] {colA, colA, colA, colA, colA, colA, colA, colA};
		Col[] bufB = new Col[] {colB, colB, colB, colB, colB, colA, colA};
		Col[] bufC = new Col[] {colC, colC, colC, colC};
		Col[] bufD = new Col[] {colD, colD, colD, colD, colD, colD, colC, colC, colC, colC};
		assertTrue(Arrays.equals(amp.getPixelBuf(colA, 40.0), bufA));
		assertTrue(Arrays.equals(amp.getPixelBuf(colB, 35.0), bufB));
		assertTrue(Arrays.equals(amp.getPixelBuf(colC, 20.0), bufC));
		assertTrue(Arrays.equals(amp.getPixelBuf(colD, 50.0), bufD));
	}

}
