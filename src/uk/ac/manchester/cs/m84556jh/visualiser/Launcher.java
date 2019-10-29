package uk.ac.manchester.cs.m84556jh.visualiser;

import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps = 30;
	int width = 1000;
	int height = 600;
	ColPal noteCols;
	Spectrum spectrum;
	Amplitude amp = new Amplitude(10*fps, 20, width/2, (int)(fps/3));
	Key key = new Key(10*fps);
	BPM bpm = new BPM(60,30,fps);
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
	    size(width, height);
    }
    
    public void setup() {
	    background(255);
	    frameRate(fps);
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
    	selectInput("Select an MP3 file to use:", "mp3Selected");
    }
    
    //Display stats about the soundfile in real time
    public void printStats(Note note) {
		textSize(50);
		text("Note:"+note.toString(), 10, 300);
		text("Freq:"+note.getFreq(), 10, 350);
		text("Max Oct:"+spectrum.getMaxOctave(), 10, 400);
		//text("Max Amp:"+note.getAmp(), 10, 300);
		//text("NoteCol:"+note.getCol(noteCols).toString(), 10, 350);
		text("Key:"+key.calc(note.getIndex()), 10, 450);
		text("BPM:"+bpm.calcBPM(spectrum.getBeatAmp()), 10, 500); 
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		colorMode(HSB, 255, 100, 100);
		
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			printStats(spectrum.getMaxFreq());
			Col noteCol = spectrum.getMaxFreq().getCol(noteCols);
			Col[] ampCol = amp.getPixelBuf(noteCol, spectrum.getTotAmp());
			
			//Display rectangle of colours from colour array
			for(int i = 0; i < ampCol.length; i++) {
				//Set colour to the colour in the array element
				fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
				//Print the lines on the left and right of the display
				rectMode(CENTER);
				rect((float)(width/2 + 1 - ampCol.length + i),(float)100.0,(float)1.0,(float)180.0);
				rect((float)(width/2 + ampCol.length - i),(float)100.0,(float)1.0,(float)180.0);
				//line((float)(width/2 + 1 - ampCol.length + i),(float)5.0,(float)(width/2 + 1 - ampCol.length + i),(float)95.0);
				//line((float)(width/2 + ampCol.length - i),(float)5.0,(float)(width/2 + ampCol.length - i),(float)95.0);
			}
			
			//Display a red circle on each beat
			if(bpm.isBeat()) {
				fill(0, 100, 50);
				circle((float)500.0, (float)550.0, (float)50.0);
			}
		}
	}
}
