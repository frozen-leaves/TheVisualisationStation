package uk.ac.manchester.cs.m84556jh.visualiser;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import uk.ac.manchester.cs.m84556jh.colour.Col;

public class Visualisation {
	
	private PApplet app;
	private PGraphics pg;
	
	public Visualisation(PApplet app) {
		this.app = app;
		this.pg = app.createGraphics(app.width, app.height);
		pg.smooth();
	}
	
	//Display stats about the soundfile in real time
    public void printStats(Note note, String noteKey, double curBPM, int maxOctave) {
		pg.textSize(50);
		pg.text("Note:"+note.toString(), 10, 300);
		pg.text("Freq:"+note.getFreq(), 10, 350);
		pg.text("Max Oct:"+maxOctave, 10, 400);
		pg.text("Key:"+noteKey, 10, 450);
		pg.text("BPM:"+curBPM, 10, 500); 
    }
	
	public PGraphics draw(String type, String key, Col keyCol, double bpm, Col[] ampCol, boolean isBeat, Note maxNote, int maxOctave) {
		//Print stats if that type, otherwise set background colour to the key colour
		pg.beginDraw();
		pg.background(204);
		pg.fill(0, 0, 0);
		pg.noStroke();
		pg.colorMode(PConstants.HSB, 255, 100, 100);
		if(type == "stats")
			printStats(maxNote, key, bpm, maxOctave);
		else
			pg.background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
			
		Col lastCol = null;
		//Display shape of colours from colour array
		for(int i = 0; i < ampCol.length; i++) {
			//Set colour to the colour in the array element
			pg.fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
			//Print the correct type of visualisation, based on type
			pg.rectMode(PConstants.CENTER);
			if(type == "stats") {
				if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
					pg.rect((float)(app.width/2),(float)(100.0),(float)ampCol.length - i,(float)(180.0));
					lastCol = ampCol[i];
				}
			}else if(type == "rect"){
				if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
					pg.rect((float)(app.width/2),(float)(app.height/2),(float)ampCol.length - i,(float)app.height);
					lastCol = ampCol[i];
				}
			}else {
				if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
					pg.circle((float)(app.width/2), (float)(app.height/2), (float)(ampCol.length - i));
					lastCol = ampCol[i];
				}  
			}
		}
		
		//Display a white circle on each beat
		if(isBeat) {
			if(type == "stats") {
				pg.fill(0, 100, 50);
				pg.circle((float)(app.width/2), (float)(app.height-50), (float)50.0);
			} else {
				pg.fill(0, 0, 100);
				pg.circle((float)(app.width/2), (float)(app.height-200), (float)200.0);
			}
		}
		
		//End draw and write to display
		pg.endDraw();
		return pg;
	}
}
