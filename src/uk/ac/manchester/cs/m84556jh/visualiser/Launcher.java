package uk.ac.manchester.cs.m84556jh.visualiser;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.visualiser.Amplitude;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps = 30;
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
	    spectrum = new Spectrum(this, "Runaway.mp3", 4096);
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
		
		spectrum.analyse();
		printStats(spectrum.getMaxFreq());
	}
}
