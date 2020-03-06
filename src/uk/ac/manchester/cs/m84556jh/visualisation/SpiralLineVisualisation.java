package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class SpiralLineVisualisation extends ParticleVisualisation{

	private int emitAngle = 0;
	private int emitAngleInc = 5;
	private long pDrawn;
	private double prevX;
	private double prevY;
	
	public SpiralLineVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}

	@Override
	public void beforeParticles(Col keyCol) {
		drawKeyBorder(keyCol, 40);
	}

	@Override
	public void addParticle(Note note, double ampSize) {
		particles.add(new Particle((int)(sizeFactor*ampSize), app.width/2, app.height/2, emitAngle, note.getCol(cp), velMag, 500));
	}

	@Override
	public void drawParticle(Particle p, double vertPerc) {
		if(pDrawn == 0) {
			prevX = p.getX();
			prevY = p.getY();
		} else {
			app.stroke(126);
			System.out.printf("Draw line from %f,%f to %f,%f\n", prevX, prevY, p.getX(), p.getY());
			app.line((float)prevX, (float)prevY, (float)p.getX(), (float)p.getY());
			prevX = p.getX();
			prevY = p.getY();
		}
		pDrawn++;
		
	}

	@Override
	public void afterParticles(Col keyCol) {
		//Update the degree to emit the particle
		emitAngle = (emitAngle + emitAngleInc)%360;	
	}
}

