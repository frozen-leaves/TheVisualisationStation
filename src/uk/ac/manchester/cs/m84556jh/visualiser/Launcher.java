package uk.ac.manchester.cs.m84556jh.visualiser;

import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.Amplitude;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps = 30;
	ColPal noteCols;
	Spectrum spectrum;
	Amplitude amp = new Amplitude(10*fps);
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
	    size(1000, 450);
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
		text("Note:"+note.toString(), 10, 50);
		text("Freq:"+note.getFreq(), 10, 100);
		text("Max Oct:"+spectrum.getMaxOctave(), 10, 150);
		text("Max Amp:"+note.getAmp(), 10, 200);
		text("Tot Amp:"+amp.calcSize(spectrum.getTotAmp()), 10, 250);
    }
	
    public void draw() {
    	// Set background color, noStroke and fill color
		background(204);
		fill(0, 0, 255);
		noStroke();
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			printStats(spectrum.getMaxFreq());
		}
	}
}
