package uk.ac.manchester.cs.m84556jh.visualiser;

import processing.core.PApplet;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps = 30;
	Spectrum spectrum; 
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
	    size(1000, 450);
    }
    
    public void setup() {
	    background(255);
	    frameRate(fps);
	    spectrum = new Spectrum(this, "Runaway.mp3", 4096);
    }
    
    //Display stats about the soundfile in real time
    public void printStats(Note note) {
		textSize(50);
		text("Note:"+note.toString(), 10, 50);
		text("Freq:"+note.getFreq(), 10, 100);
    }
	
    public void draw() {
    	// Set background color, noStroke and fill color
		background(204);
		fill(0, 0, 255);
		noStroke();
		
		spectrum.analyse();
		printStats(spectrum.getMaxFreq());
		
	}
}
