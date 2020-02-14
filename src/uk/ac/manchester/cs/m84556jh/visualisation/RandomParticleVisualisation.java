package uk.ac.manchester.cs.m84556jh.visualisation;

import java.util.Random;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class RandomParticleVisualisation extends ParticleVisualisation{
	
	private Random ran = new Random();
	private int emitAngle;
	
	public RandomParticleVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		emitAngle = ran.nextInt(360);
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		particles.add(new Particle((int)(sizeFactor*ampSize), app.width/2, app.height/2, emitAngle, note.getCol(cp), velMag));
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		app.ellipse((float)(p.getX()), (float)(p.getY()), (float)(p.getR()), (float)((double)p.getR()*vertPerc));
	}

	@Override
	public void afterParticles(Col keyCol) {
		drawKeyBorder(keyCol, 60);
	}
}

