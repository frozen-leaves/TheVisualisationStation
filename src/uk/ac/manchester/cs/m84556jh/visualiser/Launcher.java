package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.UIManager;

import processing.core.PApplet;
import processing.core.PImage;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualisation.BarsVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.CircleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.PianoVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.RandomParticleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.RectangleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.SpiralVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.StatsVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.Visualisation;

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
	Visualisation vis;
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
    	fullScreen();
    }
    
    public void selectVis() {
    	switch(visType) {
    	case "spiral":
    		vis = new SpiralVisualisation(this, noteCols);
    		break;
    	case "piano":
    		vis = new PianoVisualisation(this, noteCols);
    		break;
    	case "bars":
    		vis = new BarsVisualisation(this, noteCols);
    		break;
    	case "ranPar":
    		vis = new RandomParticleVisualisation(this, noteCols);
    		break;
    	case "circle":
    		vis = new CircleVisualisation(this, noteCols);
    		break;
    	case "rect":
    		vis = new RectangleVisualisation(this, noteCols);
    		break;
    	case "stats":
    		vis = new StatsVisualisation(this, noteCols);
    		break;
    	default:
    		vis = new CircleVisualisation(this, noteCols);
    		break;
    	}
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
	    //Set parameters from parameter dialog
	    Welcome w = new Welcome(this);
	    visType = w.style;
    	fps = w.fps;
    	frameRate(fps);
    	
    	//If drawing a circle, size of buffer must be min of width and height
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
    	selectVis();
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
			key.calc(spectrum.getMaxFreq().getIndex());
			bpm.calcBPM(spectrum);
			vis.draw(spectrum.getMaxFreq(),
				     key,
					 bpm,
					 amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
					 amp.getSize(),
					 spectrum.getMaxOctave());
		}
	}
}
