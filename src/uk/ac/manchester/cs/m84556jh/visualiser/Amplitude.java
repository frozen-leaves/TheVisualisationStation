package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.buffer.CBDouble;

public class Amplitude {
	
	private CBDouble ampBuf;
	
	public Amplitude(int ampBufSize) {
		this.ampBuf = new CBDouble(ampBufSize);
	}
	
	public double calcSize(double curAmp) {
		//Add current amp to the buffer
		ampBuf.add(curAmp);
		//Get min and max amps in buffer
		double minAmp = ampBuf.min();
		double maxAmp = ampBuf.max();
		//Return the percentage of the difference between the minimum and maximum 
		//values that the current value is
		if(minAmp == maxAmp)
			return 50.0;
		return ((curAmp - minAmp)/(maxAmp - minAmp)) * 100;
	}

}
