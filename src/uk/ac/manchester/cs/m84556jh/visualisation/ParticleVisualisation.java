package uk.ac.manchester.cs.m84556jh.visualisation;

import java.util.ArrayList;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.buffer.CBCol;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.particle.Particle;
import uk.ac.manchester.cs.m84556jh.visualiser.Amplitude;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public abstract class ParticleVisualisation extends Visualisation{
	
	protected ArrayList<Particle> particles = new ArrayList<Particle>();
	protected double sizeFactor;
	protected int velMag;

	public ParticleVisualisation(PApplet parentApp, VertSize vertSize, ColPal colPal, double sf, int vm) {
		super(parentApp, vertSize, colPal);
		this.sizeFactor = sf;
		this.velMag = vm;
	}
	
	public abstract void beforeParticles(Col keyCol);
	
	public abstract void addParticle(Note note, double ampSize);
	
	public abstract void drawParticle(Particle p, double vertPerc);
	
	public abstract void afterParticles(Col keyCol);
	
	public void draw(Note[] notes, Key key, BPM bpm, double totAmp, Amplitude amp, int oct, CBCol pixelBuffer) {
		
		//Do before drawing particles, may be drawing key
		beforeParticles(key.getCol(cp));
		
		double vertPerc = vs.calcVertPerc(bpm.isBeat());
			
		//Display whole particle system
		//Start by adding latest particle to the ArrayList
		for(Note n: notes) {
			addParticle(n, amp.calcSize(n.getAmp()));
		}
		//Move each particle
		//If the particle has no life left, or is outside of the screen area, remove it
		//Otherwise draw the particle on the screen
		Particle curPar;
		for(int i=0; i < particles.size(); i++) {
			curPar = particles.get(i);
			curPar.move();
			app.fill(curPar.getColour().getHue(), curPar.getColour().getSat(), curPar.getColour().getBri());
			if(curPar.getLifetime() == 0 || curPar.getX() > app.width || curPar.getX() < 0 
			   || curPar.getY() > app.height || curPar.getY() < 0) {
				particles.remove(i);
			} else {
				drawParticle(curPar, vertPerc);
			}
			
		}
		
		//Do after drawing particles, may draw key
		afterParticles(key.getCol(cp));
		
		
		
		
	}

}
