package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import processing.core.PApplet;
import processing.core.PImage;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualisation.BarsVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.CircleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.PianoVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.RectangleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.SpiralVisualisation;

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
	Visualisation vis = new Visualisation(this);
	SpiralVisualisation spiralVis;
	BarsVisualisation barsVis;
	PianoVisualisation pianoVis;
	CircleVisualisation circleVis;
	RectangleVisualisation rectVis;
	
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
	    surface.setTitle("The Visualisation Station");
	    //Set parameters from parameter dialog
	    Parameters p = new Parameters();  
    	fps = p.fps;
    	frameRate(fps);
    	//Get user to choose visualisation type
    	Style s = new Style();
    	visType = s.style;
    	//If drawing a circle, size of buffer must be min of width and height
    	if(visType == "circle")
    		amp = new Amplitude(p.ampBufSecs*fps, p.ampMinSize, min(width,height), (int)(p.ampPerBufSecs*fps));
    	else
    		amp = new Amplitude(p.ampBufSecs*fps, p.ampMinSize, width, (int)(p.ampPerBufSecs*fps));
    	key = new Key(p.keyBufSecs*fps);
    	bpm = new BPM(3*fps, p.bpmBufSize, fps, 32, 10);
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
    	spiralVis = new SpiralVisualisation(this, noteCols);
    	pianoVis = new PianoVisualisation(this, noteCols);
    	barsVis = new BarsVisualisation(this, noteCols);
    	circleVis = new CircleVisualisation(this, noteCols);
    	rectVis = new RectangleVisualisation(this, noteCols);
    	selectInput("Select an MP3 file to use:", "mp3Selected");
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		colorMode(HSB, 255, 100, 100);
		
		
		if(spectrum != null && noteCols != null) {
			spectrum.analyse();
			
			if(visType == "spiral") {
				key.calc(spectrum.getMaxFreq().getIndex());
				bpm.calcBPM(spectrum);
				spiralVis.draw(spectrum.getMaxFreq(),
						       key,
						       bpm,
						       amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
						       amp.getSize(),
						       spectrum.getMaxOctave());
			} else if(visType == "bars") {
				key.calc(spectrum.getMaxFreq().getIndex());
				bpm.calcBPM(spectrum);
				barsVis.draw(spectrum.getMaxFreq(),
					       key,
					       bpm,
					       amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
					       amp.getSize(),
					       spectrum.getMaxOctave());
			} else if(visType == "piano") {
				key.calc(spectrum.getMaxFreq().getIndex());
				bpm.calcBPM(spectrum);
				pianoVis.draw(spectrum.getMaxFreq(),
					       key,
					       bpm,
					       amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
					       amp.getSize(),
					       spectrum.getMaxOctave());
			} else if(visType == "circle") {
				key.calc(spectrum.getMaxFreq().getIndex());
				bpm.calcBPM(spectrum);
				circleVis.draw(spectrum.getMaxFreq(),
					       key,
					       bpm,
					       amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
					       amp.getSize(),
					       spectrum.getMaxOctave());
			} else if(visType == "rect") {
				key.calc(spectrum.getMaxFreq().getIndex());
				bpm.calcBPM(spectrum);
				rectVis.draw(spectrum.getMaxFreq(),
					       key,
					       bpm,
					       amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), spectrum.getTotAmp()), 
					       amp.getSize(),
					       spectrum.getMaxOctave());
			} else {
				vis.draw(visType, 
						 key.calc(spectrum.getMaxFreq().getIndex()), 
						 key.getCol(noteCols), 
						 bpm.calcBPM(spectrum),
						 amp.getPixelBuf(spectrum.getMaxFreq().getCol(noteCols), 
						 spectrum.getTotAmp()), 
						 bpm.isBeat(),
						 spectrum.getMaxFreq(),
						 spectrum.getMaxOctave());
			}
			
		}
	}
}
