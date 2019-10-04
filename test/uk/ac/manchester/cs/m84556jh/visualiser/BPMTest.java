package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;

import org.junit.Test;

public class BPMTest {
	
	BPM bpm = new BPM(12,20,30);

	@Test
	public void testCalcBPM() {
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(2.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(11.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(2.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(-1800.0));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(11.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(1800/5.0));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(1800/6.5));
		assertTrue(((Double)bpm.calcBPM(11.0)).equals(1800/6.5));
		assertTrue(((Double)bpm.calcBPM(10.0)).equals(1800/6.5));
		assertTrue(((Double)bpm.calcBPM(3.0)).equals(1800/6.5));	
	}

}
