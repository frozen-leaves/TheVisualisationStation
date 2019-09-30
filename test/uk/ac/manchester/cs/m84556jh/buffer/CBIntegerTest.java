package uk.ac.manchester.cs.m84556jh.buffer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CBIntegerTest {
	
	CBInteger buf1 = new CBInteger(14);
	CBInteger buf2 = new CBInteger(5);
	CBInteger buf3 = new CBInteger(4);
	CBInteger buf4 = new CBInteger(15);
	
	@Before
	public void setup() {
		buf1.add(2);
		buf1.add(7);
		buf1.add(8);
		buf1.add(9);
		buf1.add(15);
		buf1.add(16);
		buf1.add(21);
		buf1.add(22);
		buf1.add(23);
		buf1.add(29);
		buf1.add(30);
		buf1.add(31);
		buf1.add(36);
		buf1.add(37);
		buf2.add(5);
		buf2.add(6);
		buf2.add(7);
		buf2.add(8);
		buf2.add(9);
		buf2.add(10);
		buf2.add(11);
		buf3.add(2);
		buf3.add(4);
	}

	@Test
	public void testRead() {
		buf2.setReadPoint();
		assertTrue(buf2.read().equals(7));
		buf4.setReadPoint();
		assertTrue(buf4.read().equals(0));
	}

	@Test
	public void testDif() {
		buf3.setReadPoint();
		assertTrue(((Double)buf1.dif()).equals(7.15));
		assertTrue(((Double)buf2.dif()).equals(-1.0));
		assertTrue(((Double)buf3.dif()).equals(2.0));
	}

}
