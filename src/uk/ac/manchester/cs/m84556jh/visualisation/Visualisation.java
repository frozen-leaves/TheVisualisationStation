package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public abstract class Visualisation {
	
	protected PApplet app;
	protected VertSize vs;
	protected ColPal cp;
	
	public Visualisation(PApplet parentApp, VertSize vertSize, ColPal colPal) {
		this.app = parentApp;
		this.vs = vertSize;
		this.cp = colPal;
	}
	
	public abstract void draw(Note[] notes, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct);
	
	public void drawKeyBorder(Col keyCol, int width) {
		app.fill(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
		app.rectMode(PConstants.CENTER);
		app.rect((float)(app.width/2),(float)(width/2),(float)(app.width),(float)(width));
		app.rect((float)(app.width/2),(float)(app.height-(width/2)),(float)(app.width),(float)(width));
		app.rect((float)(width/2),(float)(app.height/2),(float)(width),(float)(app.height));
		app.rect((float)(app.width-(width/2)),(float)(app.height/2),(float)(width),(float)(app.height));
	}
	
	public void drawKeyBackground(Col keyCol) {
		app.background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
	}

}