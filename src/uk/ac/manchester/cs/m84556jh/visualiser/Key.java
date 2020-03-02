package uk.ac.manchester.cs.m84556jh.visualiser;
//Key stores a previous number of notes found in the sample,
//giving an estimate of the key of the music based on this

import java.util.Arrays;

import uk.ac.manchester.cs.m84556jh.buffer.CBInteger;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.colour.Col;

public class Key {
	private int[] times = {0,0,0,0,0,0,0,0,0,0,0,0};
	private CBInteger timesCB;
	private int notesAdded;
	private KeyPair[] keyVals = new KeyPair[12];
	
	public Key(int bufSize) {
		this.timesCB = new CBInteger(bufSize);
		this.notesAdded = 0;
	}
	
	//Add notes in the latest sample to the notes present
	//Update KeyPair array with value for each key and sort so keyVals[11] is max
	public void calc(Note[] notes) {
		//Remove the first n notes from the circular buffer based on Notes size
		timesCB.setReadPoint();
		for(Note note: notes) {
			if(notesAdded >= timesCB.getBufSize())
				times[timesCB.read()]--;
			//Put new note in circular buffer
			timesCB.add(note.getIndex());
			//Increment maxNote in noteTime array
			times[note.getIndex()]++;
			notesAdded++;
		}
		//For each key, find the total number of samples of the notes in that key and store in keyVals
		for(int i=0; i<12; i++){
			int keyTot = times[i] + times[(i+2)%12] + times[(i+4)%12] + times[(i+5)%12] + times[(i+7)%12] + times[(i+9)%12] + times[(i+11)%12];
			keyVals[i] = new KeyPair(i, keyTot);
		}
		Arrays.sort(keyVals);
	}
	
	//Returns the root note of the key in its alphabetic form
	public String toString() {
		return Note.notes[keyVals[11].getIndex()];
	}
	
	
	public Col getCol(ColPal pal, int numKeys) {
		//Calculate colour based on values of top n keys, weighting by value
		int totVal = 0;
		int colHue = 0;
		int colSat = 0;
		int colBri = 0; 
		for(int i = 11; i > 11-numKeys; i--) {
			totVal += keyVals[i].getVal();
			colHue += pal.getCol(keyVals[i].getIndex()).getHue()*keyVals[i].getVal();
			colSat += pal.getCol(keyVals[i].getIndex()).getSat()*keyVals[i].getVal();
			colBri += pal.getCol(keyVals[i].getIndex()).getBri()*keyVals[i].getVal();
		}
		colHue /= totVal;
		colSat /= totVal;
		colBri /= totVal;
		return new Col(colHue, colSat, colBri);
		
	}
}
