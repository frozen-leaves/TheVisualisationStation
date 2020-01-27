package uk.ac.manchester.cs.m84556jh.particle;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class BarsVisualisation {
	
	private VertSize vs = new VertSize(5, 0.8);
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	private PApplet app;
	private double sizeFactor;
	private int velMag = 5;
	
	
	public BarsVisualisation(PApplet app) {
		this.app = app;
		sizeFactor = app.height/20.0;
	}
	
	public void draw(Col keyCol, Col noteCol, double ampPerc, boolean isBeat) {
		
		//Set background colour to key colour
		app.background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
			
		double vertPerc = vs.calcVertPerc(isBeat);
		//Display whole particle system
		//Start by adding latest particle to the ArrayList
		particles.add(new Particle((int)(sizeFactor*ampPerc), 1, app.height/2, 90, noteCol, velMag));
		
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
				app.rect((float)(curPar.getX()), (float)(curPar.getY()), (float)(velMag), (float)((double)curPar.getR()*vertPerc));
			}
			
		}
		
	}

}
