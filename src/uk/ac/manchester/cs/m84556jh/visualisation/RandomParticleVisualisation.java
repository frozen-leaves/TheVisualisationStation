package uk.ac.manchester.cs.m84556jh.visualisation;

import java.util.Random;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class RandomParticleVisualisation extends ParticleVisualisation{
	
	private Random ran = new Random();
	
	public RandomParticleVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		// DO NOTHING
	}

	@Override
	public void addParticle(Note note, BPM bpm, Col[] ampCol, double ampPerc) {
		particles.add(new Particle((int)(sizeFactor*ampPerc), app.width/2, app.height/2, ran.nextInt(360), note.getCol(cp), velMag));
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

