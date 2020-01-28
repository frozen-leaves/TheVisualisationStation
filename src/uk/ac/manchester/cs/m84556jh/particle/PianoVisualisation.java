package uk.ac.manchester.cs.m84556jh.particle;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class PianoVisualisation {
	
	private VertSize vs = new VertSize(5, 0.8);
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private PApplet app;
	private double sizeFactor;
	private int velMag = 5;
	
	public PianoVisualisation(PApplet app) {
		this.app = app;
		sizeFactor = app.height/40.0;
	}
	
	public void draw(Col keyCol, Col noteCol, double ampPerc, boolean isBeat, Note maxNote) {
		
		//Set background colour to key colour
		app.background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
			
		double vertPerc = vs.calcVertPerc(isBeat);
		//Display whole particle system
		//Start by adding latest particle to the ArrayList
		double barWidthPos = (((maxNote.getOctave()*12)+maxNote.getIndex())*app.width)/120.0 + app.width/240.0;
		particles.add(new Particle((int)(sizeFactor*ampPerc), (int)barWidthPos, app.height-1, 180, noteCol, velMag));
		
		//Move each particle
		//If the particle has no life left, or is outside of the screen area, remove it
		//Otherwise draw the particle as a circle on the screen
		Particle curPar;
		app.rectMode(PConstants.CENTER);
		for(int i=0; i < particles.size(); i++) {
			curPar = particles.get(i);
			curPar.move();
			app.fill(curPar.getColour().getHue(), curPar.getColour().getSat(), curPar.getColour().getBri());
			if(curPar.getLifetime() == 0) {
				particles.remove(i);
			}else if(curPar.getX() > app.width || curPar.getX() < 0) {
				particles.remove(i);
			}else if(curPar.getY() > app.height || curPar.getY() < 0) {
				particles.remove(i);
			}else {
				app.rect((float)(curPar.getX()), (float)(curPar.getY()), (float)(app.width/120.0), (float)((double)curPar.getR()*vertPerc));
			}
			
		}
		
		
	}

}
