package uk.ac.manchester.cs.m84556jh.visualisation;

import java.util.Random;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class DyingStarsVisualisation extends ParticleVisualisation{
	
	private Random ran = new Random();
	private int parXLoc;
	private int parYLoc;
	private double lifeTime = 100.0;
	
	public DyingStarsVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.5, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		drawKeyBackground(keyCol);
		parXLoc = ran.nextInt(app.width);
		parYLoc = ran.nextInt(app.height);
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		particles.add(new Particle((int)(sizeFactor*ampSize), parXLoc, parYLoc, 0, note.getCol(cp), 0, (int)lifeTime));
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		double radius = p.getR()*(p.getLifetime()/(lifeTime+1));
		app.ellipse((float)(p.getX()), (float)(p.getY()), (float)(radius), (float)((double)radius*vertPerc));
		
	}

	@Override
	public void afterParticles(Col keyCol) {
		//DO NOTHING
	}

}
