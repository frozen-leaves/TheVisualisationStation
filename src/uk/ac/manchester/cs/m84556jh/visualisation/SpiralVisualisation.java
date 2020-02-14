package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class SpiralVisualisation extends ParticleVisualisation{

	private int emitAngle = 0;
	private int emitAngleInc = 5;
	
	public SpiralVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		drawKeyBorder(keyCol, 40);
	}

	@Override
	public void addParticle(Note note, BPM bpm, Col[] ampCol, double ampPerc) {
		particles.add(new Particle((int)(sizeFactor*ampPerc), app.width/2, app.height/2, emitAngle, note.getCol(cp), velMag));
		//Update the degree to emit the particle
		emitAngle = (emitAngle + emitAngleInc)%360;	
		
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		app.ellipse((float)(p.getX()), (float)(p.getY()), (float)(p.getR()), (float)((double)p.getR()*vertPerc));
		
	}

	@Override
	public void afterParticles(Col keyCol) {
		//DO NOTHING
	}
}

