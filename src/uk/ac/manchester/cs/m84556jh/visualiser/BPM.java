package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.buffer.CBDouble;
import uk.ac.manchester.cs.m84556jh.buffer.CBInteger;

public class BPM {
	
	private CBDouble[] ampBuf;
	private CBInteger bpmBuf;
	private int fSubBands;
	private int isBeatNumBands;
	private int frameRate;
	private int framesPassed;
	private boolean beat = false;
	private double bpm;
	
	
	public BPM(int ampBufSize, int bpmBufSize, int fps, int fSubBands, int isBeatNumBands){
		this.ampBuf = new CBDouble[fSubBands];
		this.bpmBuf = new CBInteger(bpmBufSize);
		this.fSubBands = fSubBands;
		for(int i = 0; i < fSubBands; i++) {
			this.ampBuf[i] = new CBDouble(ampBufSize);
		}
		this.isBeatNumBands = isBeatNumBands;
		this.frameRate = fps;
		this.framesPassed = 0;
	}
	
	public double calcBPM(Spectrum spectrum){
		beat = false;
		int numFBandsBeat = 0;
		//Calculate amplitude for each frequency sub-band and add to array
		for(int i = 0; i < fSubBands; i++) {
			double sbAmp = spectrum.getFSubBand(i, fSubBands);
			ampBuf[i].add(sbAmp);
			if(sbAmp > ((-0.0027 * ampBuf[i].var() + 1.55) * ampBuf[i].avg()))
				numFBandsBeat++;
		}
		
		if(numFBandsBeat > isBeatNumBands) {
			bpmBuf.add(framesPassed);
			beat = true;
		}
		bpm = (60*frameRate)/bpmBuf.dif();
		return bpm;
	}
	
	public boolean isBeat() {
		return beat;
	}
	
	public double getBPM() {
		return bpm;
	}

}
