package uk.ac.manchester.cs.m84556jh.visualiser;

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
	
	public String toString() {
		return notes[this.index]+this.octave;
	}
  
}
