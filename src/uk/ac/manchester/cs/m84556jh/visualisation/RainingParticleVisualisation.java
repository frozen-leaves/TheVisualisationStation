package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class RainingParticleVisualisation extends ParticleVisualisation{

	public RainingParticleVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		//DO NOTHING
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		double particleWidthPos = (((note.getOctave()*12)+note.getIndex())*app.width)/120.0 + app.width/240.0;
		particles.add(new Particle((int)(sizeFactor*ampSize), (int)particleWidthPos, 60, 0, note.getCol(cp), velMag, 500));
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
