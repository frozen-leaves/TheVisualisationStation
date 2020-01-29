package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class SpiralVisualisation extends ParticleVisualisation{
	

	private int emitAngle = 0;
	private int emitAngleInc = 5;
	
	
	public SpiralVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5, 0.8), colPal, 0.4, 3);
	}
	
	public void draw(Note note, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct) {
		
		//Set border colour to key colour
		drawKeyBorder(key.getCol(cp), 40);
			
		double vertPerc = vs.calcVertPerc(bpm.isBeat());
		//Display whole particle system
		//Start by adding latest particle to the ArrayList
		particles.add(new Particle((int)(sizeFactor*ampPerc), app.width/2, app.height/2, emitAngle, note.getCol(cp), velMag));
		
		//Move each particle
		//If the particle has no life left, or is outside of the screen area, remove it
		//Otherwise draw the particle as a circle on the screen
		Particle curPar;
		for(int i=0; i < particles.size(); i++) {
			curPar = particles.get(i);
			curPar.move();
			app.fill(curPar.getColour().getHue(), curPar.getColour().getSat(), curPar.getColour().getBri());
			if(curPar.getLifetime() == 0 || curPar.getX() > app.width || curPar.getX() < 0 
		       || curPar.getY() > app.height || curPar.getY() < 0) {
				particles.remove(i);
			}else {
				app.ellipse((float)(curPar.getX()), (float)(curPar.getY()), (float)(curPar.getR()), (float)((double)curPar.getR()*vertPerc));
			}
			
		}
		
		//Update the degree to emit the particle
		emitAngle = (emitAngle + emitAngleInc)%360;
		
	}
}

