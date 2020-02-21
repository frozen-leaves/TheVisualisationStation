package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import processing.core.PConstants;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class RectangleVisualisation extends BufferVisualisation{

	public RectangleVisualisation(PApplet parentApp, ColPal colPal) {
		super(parentApp, new VertSize(5, 0.8), colPal);
	}
	
	@Override
	public void beforeLoop(Col keyCol) {
		drawKeyBackground(keyCol);
		app.rectMode(PConstants.CENTER);
	}

	@Override
	public void drawShape(int size, double vertPerc) {
		app.rect((float)(app.width/2),(float)(app.height/2),(float)size,(float)(app.height*vertPerc));
	}
}
