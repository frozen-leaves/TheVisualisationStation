package uk.ac.manchester.cs.m84556jh.visualisation;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.VertSize;

public class CircleVisualisation extends BufferVisualisation{
	

	public CircleVisualisation(PApplet parentApp, ColPal colPal) {
		super(parentApp, new VertSize(5, 0.8), colPal);
	}

	@Override
	public void beforeLoop(Col keyCol) {
		drawKeyBorder(keyCol, 60);
	}

	@Override
	public void drawShape(int size, double vertPerc) {
		app.ellipse((float)(app.width/2), (float)(app.height/2), (float)size, (float)(size*vertPerc));
	}
}
