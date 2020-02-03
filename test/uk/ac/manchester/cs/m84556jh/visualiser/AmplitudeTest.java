package uk.ac.manchester.cs.m84556jh.visualiser;

import static org.junit.Assert.*;
import org.junit.Test;

public class AmplitudeTest {
	
	Amplitude amp = new Amplitude(5,20,20,3);

	@Test
	public void testCalcSize() {
		amp.calcSize(10);
		assertTrue(((Double)amp.getSize()).equals(60.0));
		amp.calcSize(20);
		assertTrue(((Double)amp.getSize()).equals(80.0));
		amp.calcSize(15);
		assertTrue(((Double)amp.getSize()).equals(220/3.0));
		amp.calcSize(30);
		assertTrue(((Double)amp.getSize()).equals(260/3.0));
	}

}
