package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class BarsVisualisation extends ParticleVisualisation{
	
	public BarsVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5,0.8), colPal, app.height/200.0, 5);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		drawKeyBackground(keyCol);
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		particles.add(new Particle((int)(sizeFactor*ampSize), 1, app.height/2, 90, note.getCol(cp), velMag, 500));
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		app.rectMode(PConstants.CENTER);
		app.rect((float)(p.getX()), (float)(p.getY()), (float)(velMag), (float)((double)p.getR()*vertPerc));
	}

	@Override
	public void afterParticles(Col keyCol) {
		// DO NOTHING
		
	}

}
