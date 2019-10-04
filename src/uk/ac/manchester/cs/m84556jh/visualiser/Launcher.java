package uk.ac.manchester.cs.m84556jh.visualiser;

import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col3;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Amplitude;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps = 30;
	ColPal noteCols;
	Spectrum spectrum;
	Amplitude amp = new Amplitude(10*fps);
	Key key = new Key(10*fps);
	BPM bpm = new BPM(60,30,fps);
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
	    size(1000, 600);
    }
    
    public void setup() {
	    background(255);
	    frameRate(fps);
	    selectInput("Select an MP3 file to use:", "mp3Selected");
	    selectInput("Select a colour file to use:", "colsSelected");
    }
    
    public void mp3Selected(File mp3) {
    	spectrum = new Spectrum(this, mp3.getAbsolutePath(), 4096);
    }
    
    public void colsSelected(File cols) {
    	try {
			noteCols = new ColPal(cols);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    //Display stats about the soundfile in real time
    public void printStats(Note note) {
		textSize(50);
		text("Note:"+note.toString(), 10, 150);
		text("Freq:"+note.getFreq(), 10, 200);
		text("Max Oct:"+spectrum.getMaxOctave(), 10, 250);
		text("Max Amp:"+note.getAmp(), 10, 300);
		text("NoteCol:"+note.getCol(noteCols).toString(), 10, 350);
		text("Key:"+key.calc(note.getIndex()), 10, 450);
		text("BPM:"+bpm.calcBPM(spectrum.getBeatAmp()), 10, 500); 
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		colorMode(HSB, 255, 255, 100);
		
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			printStats(spectrum.getMaxFreq());
			double totAmp = amp.calcSize(spectrum.getTotAmp());
			text("Tot Amp:"+totAmp, 10, 400);
			
			//Display rectangle of note colour, with size determined by max amp
			Col3 noteCol = spectrum.getMaxFreq().getCol(noteCols);
			fill(noteCol.getHue(), noteCol.getSat(), noteCol.getBri());
			rectMode(CENTER);
			rect((float)500.0,(float)50.0,(float)(10*totAmp),(float)90.0);
		}
	}
}
