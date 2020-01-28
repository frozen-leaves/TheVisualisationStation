package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class BarsVisualisation extends ParticleVisualisation{
	
	public BarsVisualisation(PApplet app, ColPal colPal) {
		super(app, new VertSize(5,0.8), colPal, app.height/200.0, 5);
	}
	
	public void draw(Note note, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct) {
		
		//Set background colour to key colour
		Col keyCol = key.getCol(cp);
		app.background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
			
		double vertPerc = vs.calcVertPerc(bpm.isBeat());
		//Display whole particle system
		//Start by adding latest particle to the ArrayList
		particles.add(new Particle((int)(sizeFactor*ampPerc), 1, app.height/2, 90, note.getCol(cp), velMag));
		
		//Move each particle
		//If the particle has no life left, or is outside of the screen area, remove it
		//Otherwise draw the particle as a circle on the screen
		Particle curPar;
		app.rectMode(PConstants.CENTER);
		for(int i=0; i < particles.size(); i++) {
			curPar = particles.get(i);
			curPar.move();
			app.fill(curPar.getColour().getHue(), curPar.getColour().getSat(), curPar.getColour().getBri());
			if(curPar.getLifetime() == 0 || curPar.getX() > app.width || curPar.getX() < 0 
			   || curPar.getY() > app.height || curPar.getY() < 0) {
				particles.remove(i);
			}else {
				app.rect((float)(curPar.getX()), (float)(curPar.getY()), (float)(velMag), (float)((double)curPar.getR()*vertPerc));
			}
			
		}
		
	}

}
