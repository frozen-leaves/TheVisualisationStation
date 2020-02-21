package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.buffer.CBCol;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.Amplitude;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public abstract class BufferVisualisation extends Visualisation{

	public BufferVisualisation(PApplet parentApp, VertSize vertSize, ColPal colPal) {
		super(parentApp, vertSize, colPal);
	}
	
	public abstract void beforeLoop(Col keyCol);
	
	public abstract void drawShape(int size, double vertPerc);
	
	@Override
	public void draw(Note[] notes, Key key, BPM bpm, double totAmp, Amplitude amp, int oct, CBCol pixelBuffer) {
		
		beforeLoop(key.getCol(cp));
		
		double vertPerc = vs.calcVertPerc(bpm.isBeat());
		
		//Add total amplitude to pixelBuffer and get pixel buffer array
		Col[] ampCol = pixelBuffer.calcPixelBuf(notes[0].getCol(cp), amp.calcSize(totAmp));
		Col lastCol = null;
		
		//Display shape of colours from colour array
		for(int i = 0; i < ampCol.length; i++) {
			//Set colour to the colour in the array element
			app.fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
			if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
				drawShape(ampCol.length-i, vertPerc);
				lastCol = ampCol[i];
			}  
		}
	}

}
