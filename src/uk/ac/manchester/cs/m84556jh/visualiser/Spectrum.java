package uk.ac.manchester.cs.m84556jh.visualiser;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	//Get a sub-band of the frequency spectum, based on the
	//band required and the number of sub-bands to split into
	public double getFSubBand(int band, int numSBands) {
		int bandsPerSubBand = (int)Math.floor(bands/numSBands);
		int low = band * bandsPerSubBand;
		int high = (band + 1) * bandsPerSubBand;
		double subBandSum = 0.0;
		for(int b = low; b < high; b++)
			subBandSum += spec[b];
		return subBandSum;
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
	
	//Determine the notes with the highest amplitude in the FFT 
	//Initially find the amplitude of the maximum note and then
	//get notes where the amplitude is above a given percentage of
	//this amplitude
	public Note[] getMaxFreqs(int minPerc) {
		
		//Get amplitude of the biggest element in the spectrum
		double curMaxAmp = 0;
		for (int i = 0; i < spec.length; i++)
			if(spec[i] > curMaxAmp)
				curMaxAmp = spec[i];
		
		//Calculate minimum threshold for note
		double minNoteAmp = minPerc/100.0 * curMaxAmp;
		//Add each frequency above the threshold to an ArrayList
		ArrayList<Note> maxNotes = new ArrayList<Note>();
		for (int i = 0; i < spec.length; i++)
			if(spec[i] > minNoteAmp)
				maxNotes.add(new Note((double)samFreq/2/spec.length*i, spec[i]));
		
		Note[] notes = new Note[maxNotes.size()];
		notes = maxNotes.toArray(notes);
		return notes;
	}
	
	//Determine the notes with the highest amplitude in the FFT
	//Return the top n notes in the FFT
	public Note[] getNMaxFreqs(int n) {
		Note[] notes = new Note[n+1];
		//Populate array with notes of zero amplitude
		for(int k = 0; k < n+1; k++)
			notes[k] = new Note(0,0);
		//Insert highest notes up until this point into the array
		for(int i = 0; i < spec.length; i++) {
			int noteIndex = n - 1;
			while(noteIndex > -1 && spec[i] > notes[noteIndex].getAmp()) {
				notes[noteIndex + 1] = notes[noteIndex];
				noteIndex--;
			}
			notes[noteIndex + 1] = new Note((double)samFreq/2/spec.length*i, spec[i]);
		}
		//Cut off last element of the array - only used for shifting values
		notes = Arrays.copyOfRange(notes, 0, n);
		return notes;
	}
	
}
