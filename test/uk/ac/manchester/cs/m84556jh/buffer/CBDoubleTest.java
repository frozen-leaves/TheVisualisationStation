package uk.ac.manchester.cs.m84556jh.buffer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CBDoubleTest {
	
	CBDouble buf1 = new CBDouble(5);
	CBDouble buf2 = new CBDouble(4);
	
	@Before
	public void setup() {
		//Final add should overwrite first
		buf1.add(3.4);
		buf1.add(4.2);
		buf1.add(7.0);
		buf1.add(12.1);
		buf1.add(2.1);
		buf1.add(5.1);
		
	}

	@Test
	public void testRead() {
		buf1.setReadPoint();
		assertTrue(((Double)buf1.read()).equals(4.2));
		buf2.setReadPoint();
		assertTrue(((Double)buf2.read()).equals(0.0));
	}

	@Test
	public void testMin() {
		assertTrue(((Double)buf1.min()).equals(2.1));
	}

	@Test
	public void testMax() {
		assertTrue(((Double)buf1.max()).equals(12.1));
	}

	@Test
	public void testAvg() {
		assertTrue(((Double)buf1.avg()).equals(6.1));
	}

	@Test
	public void testVar() {
		assertTrue(((Double)buf1.var()).equals(11.484));
	}

}
