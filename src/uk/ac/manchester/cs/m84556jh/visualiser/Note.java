package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.colour.Col3;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class Note {
	public static final String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private double freq;
	private double amplitude;
	private int octave;
	private int index;
	
	public Note(double givenFreq, double givenAmp) {
		this.freq = givenFreq;
		this.amplitude = givenAmp;
		int noteIncrements = (int)Math.round((12*Math.log(givenFreq/16.35))/Math.log(2));
		this.octave = noteIncrements/12;
		this.index = noteIncrements%12;
	}
	
	public double getFreq() {
		return this.freq;
	}
	
	public double getAmp() {
		return this.amplitude;
	}
	
	public int getOctave() {
		return this.octave;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public Col3 getCol(ColPal pal) {
		int colHue = pal.getCol(getIndex()).getHue();
		int colSat = pal.getCol(getIndex()).getSat();
		int colBri = getOctave()*10 + (getIndex()+1)*10/12;
		return new Col3(colHue, colSat, colBri);
	}
	
	public String toString() {
		return notes[this.index]+this.octave;
	}
  
}
