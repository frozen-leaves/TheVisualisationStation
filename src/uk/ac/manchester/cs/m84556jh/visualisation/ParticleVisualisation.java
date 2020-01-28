package uk.ac.manchester.cs.m84556jh.visualisation;

import java.util.ArrayList;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public abstract class ParticleVisualisation extends Visualisation{
	
	protected ArrayList<Particle> particles = new ArrayList<Particle>();
	protected double sizeFactor;
	protected int velMag;

	public ParticleVisualisation(PApplet parentApp, VertSize vertSize, ColPal colPal, double sf, int vm) {
		super(parentApp, vertSize, colPal);
		this.sizeFactor = sf;
		this.velMag = vm;
	}

}
