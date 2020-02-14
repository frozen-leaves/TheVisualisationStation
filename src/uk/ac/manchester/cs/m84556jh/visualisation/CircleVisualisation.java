package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.BPM;
import uk.ac.manchester.cs.m84556jh.visualiser.Key;
import uk.ac.manchester.cs.m84556jh.visualiser.Note;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class CircleVisualisation extends Visualisation{

	public CircleVisualisation(PApplet parentApp, ColPal colPal) {
		super(parentApp, new VertSize(5, 0.8), colPal);
	}

	@Override
	public void draw(Note[] notes, Key key, BPM bpm, Col[] ampCol, double ampPerc, int oct) {
		//Set border colour to the key colour
		drawKeyBorder(key.getCol(cp), 60);
			
		Col lastCol = null;
		double vertPerc = vs.calcVertPerc(bpm.isBeat());
		//Display shape of colours from colour array
		for(int i = 0; i < ampCol.length; i++) {
			//Set colour to the colour in the array element
			app.fill(ampCol[i].getHue(), ampCol[i].getSat(), ampCol[i].getBri());
			if(lastCol == null || !(lastCol.equals(ampCol[i]))) {
				app.ellipse((float)(app.width/2), (float)(app.height/2), (float)(ampCol.length - i), (float)((ampCol.length - i)*vertPerc));
				lastCol = ampCol[i];
			}  
		}

	}
}
