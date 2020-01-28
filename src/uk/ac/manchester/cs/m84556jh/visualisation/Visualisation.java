package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
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
	
	public abstract void draw(Note note, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct);

}