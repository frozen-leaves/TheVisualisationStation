package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.buffer.CBCol;
import uk.ac.manchester.cs.m84556jh.buffer.CBDouble;
import uk.ac.manchester.cs.m84556jh.colour.Col;

public class Amplitude {
	
	private CBDouble ampBuf;
	private CBDouble ampPercBuf;
	private CBCol pixelBuf;
	private int minSize;
	private int prevSize;
	private int curSize;
	private double size = 0.0;
	
	public Amplitude(int ampBufSize, int minSize, int pixelBufSize, int ampPercBufSize) {
		this.ampBuf = new CBDouble(ampBufSize);
		this.ampPercBuf = new CBDouble(ampPercBufSize);
		this.minSize = minSize;
		this.prevSize = 0;
		this.curSize = 0;
		this.pixelBuf = new CBCol(pixelBufSize);
	}
	
	public void calcSize(double curAmp) {
		//Add current amp to the buffer
		ampBuf.add(curAmp);
		//Get min and max amps in buffer
		double minAmp = ampBuf.min();
		double maxAmp = ampBuf.max();
		//Calculate the size of colour on the screen, between minSize percent and 100 percent,
		//based on the percentage difference between the min and max values that the current value is
		if(minAmp == maxAmp)
			ampPercBuf.add((double)(minSize + ((100-minSize)/2)));
		ampPercBuf.add(minSize + (((curAmp - minAmp)/(maxAmp - minAmp))*(100 - minSize))); 
		size = ampPercBuf.avg();
	}
	
	//Take the current colour and amplitude and return the new pixel buffer
	//with the correct number of elements for the current amplitude
	public Col[] getPixelBuf(Col curCol, double curAmp) {
		//Keep track of the previous size
		prevSize = curSize;
		//Get the current size of the amplitude, convert to number of pixels
		calcSize(curAmp);
		curSize = (int)(size/100*pixelBuf.getBufSize());
		//If bigger than previous size, top up pixel buffer with pixels 
		//of new colour equal to difference
		//Otherwise, add 5 pixels of current colour
		if(curSize > prevSize) {
			for(int i = 0; i < curSize-prevSize; i++)
				pixelBuf.add(curCol);
		} else {
			for(int i = 0; i < 5; i++)
				pixelBuf.add(curCol);
		}
		//Produce array from pixel buffer of size required
		Col[] array = new Col[curSize];
		pixelBuf.setReadPoint();
		for(int i = 0; i < curSize; i++) {
			array[i] = pixelBuf.read();
		}
		return array;
	}
	
	public double getSize() {
		return size;
	}

}
