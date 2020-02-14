package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.buffer.CBDouble;

public class Amplitude {
	
	private CBDouble ampBuf;
	private CBDouble ampPercBuf;
	private int minSize;
	
	public Amplitude(int ampBufSize, int minSize, int ampPercBufSize) {
		this.ampBuf = new CBDouble(ampBufSize);
		this.ampPercBuf = new CBDouble(ampPercBufSize);
		this.minSize = minSize;
	}
	
	public double calcSize(double curAmp) {
		//Add current amp to the buffer
		ampBuf.add(curAmp);
		//Get min and max amps in buffer
		double minAmp = ampBuf.min();
		double maxAmp = ampBuf.max();
		//Calculate the size of colour on the screen, between minSize percent and 100 percent,
		//based on the percentage difference between the min and max values that the current value is
		if(minAmp == maxAmp)
			ampPercBuf.add((double)(50 + minSize/2.0));
		else
			ampPercBuf.add(minSize + (((curAmp - minAmp)/(maxAmp - minAmp))*(100 - minSize))); 
		return ampPercBuf.avg();
	}

}
