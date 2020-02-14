package uk.ac.manchester.cs.m84556jh.visualiser;

import uk.ac.manchester.cs.m84556jh.colour.Col;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class Note implements Comparable<Note> {
	public static final String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private double freq;
	private double amplitude;
	private int octave;
	private int index;
	
	public Note(double givenFreq, double givenAmp) {
		this.freq = givenFreq;
		this.amplitude = givenAmp;
		if(givenFreq < 16.35) {
			this.octave = 0;
			this.index = 0;
		}else {
			int noteIncrements = (int)Math.round((12*Math.log(givenFreq/16.35))/Math.log(2));
			this.octave = noteIncrements/12;
			this.index = noteIncrements%12;
		}
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
	
	//Determine the colour of a note based on the colour pallette and
	//the octave of the note
	public Col getCol(ColPal pal) {
		int colHue = pal.getCol(getIndex()).getHue();
		int colSat = pal.getCol(getIndex()).getSat();
		//Adjust the brightness of the base note, using the octave value,
		int colBri = pal.getCol(getIndex()).getBri() + (getOctave()*5 - 25);
		if(colBri < 0)
			colBri = 0;
		else if(colBri > 100)
			colBri = 100;
		return new Col(colHue, colSat, colBri);
	}
	
	//Returns the note and its octave in its alphanumeric form
	public String toString() {
		return notes[this.index]+this.octave;
	}

	@Override
	public int compareTo(Note arg0) {
		if(arg0.amplitude == this.amplitude) {
			return 0;
		}else if(arg0.amplitude < this.amplitude) {
			return 1;
		}else {
			return -1;
		}
	}
  
}
