package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.buffer.CBDouble;
import uk.ac.manchester.cs.m84556jh.buffer.CBInteger;

public class BPM {
	
	//INITIALLY ONLY ADDS SINGLE SAMPLE TO EACH BAND
	private CBDouble ampBuf;
	private CBInteger bpmBuf;
	private int frameRate;
	private int framesPassed;
	private boolean beat = false;
	
	public BPM(int ampBufSize, int bpmBufSize, int fps){
		this.ampBuf = new CBDouble(ampBufSize);
		this.bpmBuf = new CBInteger(bpmBufSize);
		this.frameRate = fps;
		this.framesPassed = 0;
	}
	
	public double calcBPM(double amplitude){
		beat = false;
		//Place amplitude in the buffer
		ampBuf.add(amplitude);
		//If there is a beat in last sample, add to buffer, then return BPM
		if(amplitude >(-0.0000015 * ampBuf.var() + 1.51) * ampBuf.avg()) {
			bpmBuf.add(framesPassed);
			beat = true;
		}	
		//Get average difference between 2 vals and convert to BPM
		framesPassed++;
		return (60*frameRate)/bpmBuf.dif();
	}
			
	public boolean isBeat() {
		return beat;
	}
	
	

}
