package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class StatsVisualisation extends Visualisation{

	public StatsVisualisation(PApplet parentApp, ColPal colPal) {
		super(parentApp, new VertSize(5, 0.8), colPal);
	}
	
	//Display stats about the soundfile in real time
    public void printStats(Note note, String noteKey, double curBPM, int maxOctave) {
		app.textSize(50);
		app.text("Note:"+note.toString(), 10, 300);
		app.text("Freq:"+note.getFreq(), 10, 350);
		app.text("Max Oct:"+maxOctave, 10, 400);
		app.text("Key:"+noteKey, 10, 450);
		app.text("BPM:"+curBPM, 10, 500); 
    }

	@Override
	public void draw(Note note, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct) {
		//Print stats to the screen
		printStats(note, key.toString(), bpm.getBPM(), oct);
			
		Col lastCol = null;
		//Display shape of colours from colour array
		for(int i = 0; i < ampCol.length; i++) {
			//Set colour to the colour in the array element
			app.fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
			app.rectMode(PConstants.CENTER);
			if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
				app.rect((float)(app.width/2),(float)(100.0),(float)ampCol.length - i,(float)(180.0));
				lastCol = ampCol[i];
			}
		}

	}
}
