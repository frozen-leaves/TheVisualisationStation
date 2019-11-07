package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import processing.core.PImage;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class Launcher extends PApplet {
	
	// Global Variables 
	int fps;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int)screenSize.getHeight();
	ColPal noteCols;
	Spectrum spectrum;
	Amplitude amp;
	Key key;
	BPM bpm;
	String visType;
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
	    size(width, height);
    }
    
    public void setup() {
	    background(255);
	    PImage icon = loadImage("icon.png");
	    surface.setIcon(icon);
	    surface.setTitle("Visualiser");
	    //Set parameters from parameter dialog
	    Parameters p = new Parameters();  
    	fps = p.fps;
    	frameRate(fps);
    	//Get user to choose visualisation type
    	Style s = new Style();
    	visType = s.style;
    	//If drawing a circle, size of buffer must be min of width and height
    	if(visType == "rect")
    		amp = new Amplitude(p.ampBufSecs*fps, p.ampMinSize, width/2, (int)(p.ampPerBufSecs*fps));
    	else
    		amp = new Amplitude(p.ampBufSecs*fps, p.ampMinSize, min(width,height), (int)(p.ampPerBufSecs*fps));
    		
    	key = new Key(p.keyBufSecs*fps);
    	bpm = new BPM(3*fps, p.bpmBufSize, fps);
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
    public void printStats(Note note, String noteKey, double curBPM) {
		textSize(50);
		text("Note:"+note.toString(), 10, 300);
		text("Freq:"+note.getFreq(), 10, 350);
		text("Max Oct:"+spectrum.getMaxOctave(), 10, 400);
		text("Key:"+noteKey, 10, 450);
		text("BPM:"+curBPM, 10, 500); 
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		colorMode(HSB, 255, 100, 100);
		
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			String noteKey = key.calc(spectrum.getMaxFreq().getIndex());
			double curBPM = bpm.calcBPM(spectrum.getBeatAmp());
			if(visType == "stats")
				printStats(spectrum.getMaxFreq(), noteKey, curBPM);
			Col noteCol = spectrum.getMaxFreq().getCol(noteCols);
			Col[] ampCol = amp.getPixelBuf(noteCol, spectrum.getTotAmp());
			
			//If not printing stats (for main program), set the background colour to the key
			Col keyCol = key.getCol(noteCols);
			if(visType != "stats")
				background(keyCol.getHue(), keyCol.getSat(), keyCol.getBri());
			
			Col lastCol = null;
			//Display shape of colours from colour array
			for(int i = 0; i < ampCol.length; i++) {
				//Set colour to the colour in the array element
				fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
				//Print the lines on the left and right of the display
				rectMode(CENTER);
				if(visType == "stats") {
					rect((float)(width/2 + 1 - ampCol.length + i),(float)100.0,(float)1.0,(float)180.0);
					rect((float)(width/2 + ampCol.length - i),(float)100.0,(float)1.0,(float)180.0);
				}else if(visType == "rect"){
					rect((float)(width/2 + 1 - ampCol.length + i),(float)(height/2),(float)1.0,(float)height);
					rect((float)(width/2 + ampCol.length - i),(float)(height/2),(float)1.0,(float)height);
				}else {
					if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
						circle((float)(width/2), (float)(height/2), (float)(ampCol.length - i));
						lastCol = ampCol[i];
					}  
				}
			}
			
			//Display a red circle on each beat
			if(bpm.isBeat()) {
				if(visType == "stats") {
					fill(0, 100, 50);
					circle((float)(width/2), (float)(height-50), (float)50.0);
				} else {
					fill(0, 0, 100);
					circle((float)(width/2), (float)(height-200), (float)200.0);
				}
				
			}
		}
	}
}
