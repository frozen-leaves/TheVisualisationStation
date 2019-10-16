package uk.ac.manchester.cs.m84556jh.buffer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class CBColTest {
	
	CBCol buf1 = new CBCol(5);
	
	@Before
	public void setup() {
		buf1.add(new Col(3,4,5));
		buf1.add(new Col(31,24,15));
		buf1.add(new Col(15,2,3));
		buf1.add(new Col(8,5,5));
		buf1.add(new Col(2,2,2));
	}

	@Test
	public void testRead() {
		buf1.setReadPoint();
		assertTrue(buf1.read().equals(new Col(2,2,2)));
	}

}
