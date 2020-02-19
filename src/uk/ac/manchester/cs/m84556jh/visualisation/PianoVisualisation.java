package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class PianoVisualisation extends ParticleVisualisation{
	
	public PianoVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, app.height/200.0, 5);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		//Set background colour to key colour
		drawKeyBackground(keyCol);
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		double barWidthPos = (((note.getOctave()*12)+note.getIndex())*app.width)/120.0 + app.width/240.0;
		particles.add(new Particle((int)(sizeFactor*ampSize), (int)barWidthPos, app.height-1, 180, note.getCol(cp), velMag, 500));
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		app.rectMode(PConstants.CENTER);
		app.rect((float)(p.getX()), (float)(p.getY()), (float)(app.width/120.0), (float)((double)p.getR()*vertPerc));
	}

	@Override
	public void afterParticles(Col keyCol) {
		// DO NOTHING
	}

}
