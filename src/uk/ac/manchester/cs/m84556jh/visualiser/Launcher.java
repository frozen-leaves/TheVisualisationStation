package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	Amplitude amp;
	Key key;
	BPM bpm;
	String visType;
	Visualisation vis;
	CBCol pixelBuffer;
	Welcome w;
	//RANDOM IMPLEMENTATION
	int framesPassed;
	String[] visTypes = {"Bars", "Circle", "DyingStars", "Piano", "RainingParticle", "RandomTriangle", "RandomParticle", "Rectangle", "Spiral"};
	Random ran = new Random();
	int eachVisSeconds = 20;
	//END
	ArrayList<Spectrum> spectrums = new ArrayList<Spectrum>();
	ArrayList<File> files = new ArrayList<File>();
	PlaylistLoader pll;
	
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
    	
    	//If drawing a circle, size of buffer must be minimum of width and height
    	if(visType == "Circle")
    		pixelBuffer = new CBCol(min(width,height));
    	else
    		pixelBuffer = new CBCol(width);
    	amp = new Amplitude(w.ampBufSecs*fps, w.ampMinSize, (int)(w.ampPerBufSecs*fps));
    	key = new Key(w.keyBufSecs*fps);
    	bpm = new BPM(3*fps, w.bpmBufSize, fps, 32, 10);
    	if(w.useDefaultColFile)
    		populateNoteCols(new File("colours.txt"));
    	
    	if(visType == "ran") {
    		setVisualisation(visTypes[ran.nextInt(visTypes.length)]);
    	} else {
    		setVisualisation(visType);
    	}
    	
    	//Load spectrum for first file in array
    	spectrum = new Spectrum(this, files.get(0).getAbsolutePath(), 4096);
    	//Remove first file from file array
    	files.remove(0);
    	//Pass file array and spectrum array to second thread PlaylistLoader
    	pll = new PlaylistLoader(this, files, spectrums);
    	new Thread(pll).start();
    	//Play audio file
    	spectrum.play();
    	
    }
    
    public void audioFileSelected(File audioFile) {
    	//Add file to file arraylist
    	files.add(audioFile);
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
		noCursor();
		colorMode(HSB, 255, 100, 100);
		
		
		
		//RANDOM VISUALISATION IMPLEMENTATION
		//Change visualisation if random and eachVisSeconds seconds has passed
		framesPassed++;
		if(visType == "ran" && framesPassed == eachVisSeconds*fps) {
			framesPassed = 0;
			setVisualisation(visTypes[ran.nextInt(visTypes.length)]);
		}
		
		//END
		
		if(spectrum != null && noteCols != null) {
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
		}
	}
}
