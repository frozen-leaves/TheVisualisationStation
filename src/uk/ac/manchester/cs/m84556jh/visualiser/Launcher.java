package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.UIManager;

import processing.core.PApplet;
import processing.core.PImage;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualisation.Visualisation;

public class Launcher extends PApplet {
	
	int fps;
	int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	ColPal noteCols;
	Spectrum spectrum;
	Amplitude amp;
	Key key;
	BPM bpm;
	String visType;
	Visualisation vis;
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
    	fullScreen();
    }
    
    public void setup() {
	    background(255);
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    PImage icon = loadImage("icon.png");
	    surface.setIcon(icon);
	    surface.setTitle("The Visualisation Station");
	    
	    //Set parameters from Welcome dialog
	    Welcome w = new Welcome(this);
	    visType = w.style;
    	fps = w.fps;
    	frameRate(fps);
    	
    	//If drawing a circle, size of buffer must be minimum of width and height
    	if(visType == "circle")
    		amp = new Amplitude(w.ampBufSecs*fps, w.ampMinSize, min(width,height), (int)(w.ampPerBufSecs*fps));
    	else
    		amp = new Amplitude(w.ampBufSecs*fps, w.ampMinSize, width, (int)(w.ampPerBufSecs*fps));
    	key = new Key(w.keyBufSecs*fps);
    	bpm = new BPM(3*fps, w.bpmBufSize, fps, 32, 10);
    	if(w.useDefaultColFile)
    		populateNoteCols(new File("colours.txt"));
    		
    	selectInput("Select an MP3 file to use:", "mp3Selected");
    }
    
    public void mp3Selected(File mp3) {
    	String className = "uk.ac.manchester.cs.m84556jh.visualisation." + visType + "Visualisation";
    	try {
			vis = (Visualisation)Class.forName(className).getDeclaredConstructor(new Class[] {PApplet.class, ColPal.class}).newInstance(this, noteCols);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	spectrum = new Spectrum(this, mp3.getAbsolutePath(), 4096);
    }
    
    public void populateNoteCols(File cols) {
    	try {
			noteCols = new ColPal(cols);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		colorMode(HSB, 255, 100, 100);
		
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			Note maxNote = spectrum.getMaxFreq();
			key.calc(maxNote.getIndex());
			bpm.calcBPM(spectrum);
			amp.calcPixelBuf(maxNote.getCol(noteCols), spectrum.getTotAmp());
			vis.draw(maxNote,
				     key,
					 bpm,
					 amp.getPixelBufArray(), 
					 amp.getSize(),
					 spectrum.getMaxOctave());
		}
	}
}
