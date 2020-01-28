package uk.ac.manchester.cs.m84556jh.particle;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.particle.Particle;

public class ParticleTest {
	
	private Col c1 = new Col(50,50,50);
	private Particle p1 = new Particle(5, 0, 100, 90, c1, 5);

	@Test
	public void test() {
		assertTrue(((Double)p1.getX()).equals(0.0));
		assertTrue(((Double)p1.getY()).equals(100.0));
		assertEquals(p1.getLifetime(), 500);
		assertEquals(p1.getR(), 5);
		assertTrue(p1.getColour().equals(c1));
		p1.move();
		assertTrue(((Double)p1.getX()).equals(5.0));
		assertTrue(((Double)p1.getY()).equals(100.0));
	}

}
