package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;
import org.junit.Test;

public class AmplitudeTest {
	
	Amplitude amp = new Amplitude(5,20,3);

	@Test
	public void testCalcSize() {
		assertTrue(((Double)amp.calcSize(10.0)).equals(60.0));
		assertTrue(((Double)amp.calcSize(20.0)).equals(80.0));
		assertTrue(((Double)amp.calcSize(15.0)).equals(220/3.0));
		assertTrue(((Double)amp.calcSize(30.0)).equals(260/3.0));
	}

}
