package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.UIManager;

import processing.core.PApplet;
import processing.core.PImage;
import uk.ac.manchester.cs.m84556jh.buffer.CBCol;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualisation.ParticleVisualisation;
import uk.ac.manchester.cs.m84556jh.visualisation.Visualisation;
import uk.ac.manchester.cs.m84556jh.visualiser.gui.Welcome;

public class Launcher extends PApplet {
	
	int fps;
	int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	ColPal noteCols;
	
	Spectrum spectrum;
	public volatile Spectrum nextSpectrum = null;
	Amplitude amp;
	Key key;
	BPM bpm;
	String visType;
	Visualisation vis;
	CBCol pixelBuffer;
	Welcome w;
	int framesPassed;
	Random ran = new Random();
	int eachVisSeconds;
	String curVis;
	String nextVis;
	public LinkedList<File> files = new LinkedList<File>();
	SpectrumLoader specLoader;
	Boolean endOfPlaylist = false;
	Boolean useDefaultColFile = true;
	String[] visTypes = {"Bars", "Circle", "DyingStars", "Piano", "RainingParticle", "RandomTriangle", "RandomParticle", "Rectangle", "Spiral"};
	
	public static void main(String[] args) {
	    PApplet.main("uk.ac.manchester.cs.m84556jh.visualiser.Launcher");
    }
  
    public void settings() {
    	fullScreen();
    }
    
    public void setup() {
	    background(255);
        try {
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (Exception f) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception g) {
					e.printStackTrace();
				}
			}
			
		}
	    PImage icon = loadImage("icon.png");
	    surface.setIcon(icon);
	    surface.setTitle("The Visualisation Station");
	    
	    //Set parameters from Welcome dialog
	    w = new Welcome(this);
	    visType = w.style;
    	fps = w.fps;
    	frameRate(fps);
    	eachVisSeconds = w.shuffleNumSeconds;
    	
    	//If drawing a circle, size of buffer must be minimum of width and height
    	if(visType == "Circle")
    		pixelBuffer = new CBCol(min(width,height));
    	else
    		pixelBuffer = new CBCol(width);
    	amp = new Amplitude(w.ampBufSecs*fps, w.ampMinSize, (int)(w.ampPerBufSecs*fps));
    	key = new Key(w.keyBufSecs*fps);
    	bpm = new BPM(3*fps, w.bpmBufSize, fps, 32, 10);
    	if(useDefaultColFile)
    		populateNoteCols(new File("colours.txt"));
    	
    	if(visType == "ran") {
    		nextVis = visTypes[ran.nextInt(visTypes.length)];
    		setVisualisation(nextVis);
    	} else {
    		setVisualisation(visType);
    	}
    	
    	loadNextFileSpectrum();
    }
    
    public void loadNextFileSpectrum(){
    	//Pass file array and spectrum array to second thread PlaylistLoader
    	nextSpectrum = null;
    	if(files.isEmpty()) {
    		endOfPlaylist = true;
    	} else {
    		specLoader = new SpectrumLoader(this, files.remove());
        	new Thread(specLoader).start();
    	}
    }
    
    public void setVisualisation(String visName) {
    	String className = "uk.ac.manchester.cs.m84556jh.visualisation." + visName + "Visualisation";
    	try {
			vis = (Visualisation)Class.forName(className).getDeclaredConstructor(new Class[] {PApplet.class, ColPal.class}).newInstance(this, noteCols);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void populateNoteCols(File cols) {
    	try {
    		if(cols != null && cols.isFile()) {
    			noteCols = new ColPal(cols);
    			useDefaultColFile = false;
    		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
    public void draw() {
    	// Setup for colours
		background(204);
		fill(0, 0, 0);
		noStroke();
		noCursor();
		colorMode(HSB, 255, 100, 100);
		
		//If a song is playing, run the visualisation as normal
		if(spectrum != null && spectrum.isPlaying()) {
			//Change visualisation if random and eachVisSeconds seconds has passed
			framesPassed++;
			if(visType == "ran" && framesPassed == eachVisSeconds*fps) {
				framesPassed = 0;
				curVis = nextVis;
				//Change next visualisation until different to last one
				while((nextVis = visTypes[ran.nextInt(visTypes.length)]).equals(curVis));
				setVisualisation(nextVis);
			}
			
			spectrum.analyse();
			//Get max notes in spectrum determined by user preference
			//If not a ParticleVisualisation, only get one note
			Note[] maxNotes = new Note[0];
			if(vis instanceof ParticleVisualisation) {
				switch(w.numPType) {
				case 0:
					maxNotes = spectrum.getMaxFreq();
					break;
				case 1:
					maxNotes = spectrum.getNMaxFreqs(w.numMaxP);
					break;
				case 2:
					maxNotes = spectrum.getMaxFreqs(w.percMaxP);
					break;
				}
			} else {
				maxNotes = spectrum.getMaxFreq();
			}
			
			key.calc(maxNotes);
			bpm.calcBPM(spectrum);
			vis.draw(maxNotes,
				     key,
					 bpm,
					 spectrum.getTotAmp(),
					 amp,
					 spectrum.getMaxOctave(),
					 pixelBuffer);
			
		} else {
			if((spectrum = nextSpectrum) != null) {
				loadNextFileSpectrum();
				spectrum.play();
			} else {
				if(endOfPlaylist) {
					System.exit(0);
				} else {
					textSize(64);
					textAlign(CENTER);
					text("Loading visualisation...", width/2, height/2);
				}
				
			}
		}	
	}
}
