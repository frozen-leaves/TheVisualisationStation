package uk.ac.manchester.cs.m84556jh.visualiser;

import processing.core.PApplet;
import processing.sound.FFT;
import processing.sound.SoundFile;

public class Spectrum {
	
	private SoundFile music;
	private FFT fft;
	private int samFreq;
	private float[] spec;
	private int bands;
	
	public Spectrum(PApplet app, String fileName, int bands) {
		music = new SoundFile(app, fileName);
	    music.loop();
	    samFreq = (int)(music.frames()/music.duration());
	    spec = new float[bands];
	    fft = new FFT(app, bands);
	    fft.input(music);
	    this.bands = bands;
	}
	
	public void analyse() {
		fft.analyze(spec);
	}
	
	//Determine the total amplitude of the bands that represent the snare and
	//kick drums, which are usually used to mark out the beat of a song
	public double getBeatAmp(){
		double hzPerBand = 22050/(double)spec.length;
		int lowKickBand = (int)Math.floor(60/hzPerBand);
		int highKickBand = (int)Math.ceil(130/hzPerBand);
		int lowSnareBand = (int)Math.floor(301/hzPerBand);
		int highSnareBand = (int)Math.ceil(750/hzPerBand);
		double beatAmpSum = 0.0;
		for(int k = lowKickBand; k < highKickBand; k++)
			beatAmpSum += spec[k];
		for(int s = lowSnareBand; s < highSnareBand; s++)
			beatAmpSum += spec[s];
		return beatAmpSum;
	}	
	
	//Determine the frequency band which has the highest amplitude in the FFT
	public Note getMaxFreq(){
		int curMaxIndex = 0;
		double curMaxAmp = 0;
		for (int i = 0; i < spec.length; i++){
			if(spec[i] > curMaxAmp){
				curMaxIndex = i;
				curMaxAmp = spec[i];
			}  
		}
		//Convert index into lower end of freq band
		return new Note((double)samFreq/2/spec.length*curMaxIndex, curMaxAmp);
	}
	
	//Determine which octave in the FFT has the highest amplitude
	public int getMaxOctave(){
		double curMaxOctaveAmp = 0;
		int maxOctave = 0;
		//First look at the highest octave band, from C9 to B9
		int upperBound = (int)Math.floor(((16.35*Math.pow(2, 9))/(samFreq/2))*bands);
		int lowerBound = (int)Math.floor(upperBound/2);
		for(int oct = 8; oct >= 0; oct--){
			double curOctaveAmp = 0;
			for(int i = lowerBound; i < upperBound; i++)
				curOctaveAmp += spec[i];
			if(curOctaveAmp > curMaxOctaveAmp){
				curMaxOctaveAmp = curOctaveAmp;
				maxOctave = oct;
			}
			//Since each octave halves frequency of one above, bounds can be halved each time
			upperBound = lowerBound;
			lowerBound = (int)Math.floor(upperBound/2);
		}
	  return maxOctave;
	}
	
	//Determine the sum of the amplitude of all of the frequency bands in the FFT
	public double getTotAmp(){
		double curTotalAmp = 0.0;
		for(int i = 0; i < spec.length; i++){
			curTotalAmp += spec[i];
		}
		return curTotalAmp;
	}
	
}
