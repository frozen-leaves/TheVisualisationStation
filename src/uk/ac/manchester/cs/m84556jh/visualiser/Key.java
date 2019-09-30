package uk.ac.manchester.cs.m84556jh.visualiser;
//A NoteTime records the notes that have been present for a number of previous
//seconds in the music sample.

import uk.ac.manchester.cs.m84556jh.buffer.CBInteger;

public class Key {
	private int[] times = {0,0,0,0,0,0,0,0,0,0,0,0};
	private CBInteger timesCB;
	private int framesPassed;
	private int index;
	
	public Key(int bufSize) {
		this.timesCB = new CBInteger(bufSize);
		this.framesPassed = 0;
		this.index = 0;
	}
	
	//Add note in the latest sample to the notes present
	//Based on the notes present in the noteTimes array, determine which
	//key they closest map to, i.e. look at the notes in each key and 
	//determine which set of these notes has the most instances
	public String calc(int noteIndex) {
		//Remove the note that is first in the circular buffer
		timesCB.setReadPoint();
		if(framesPassed >= timesCB.getBufSize())
			times[timesCB.read()]--;
		//Put new note in circular buffer
		timesCB.add(noteIndex);
		//Increment maxNote in noteTime array
		times[noteIndex]++;
		framesPassed++;
		int curMaxVal = 0;
		index = 0;
		for(int i=0; i<12; i++){
			int keyTot = times[i] + times[(i+2)%12] + times[(i+4)%12] + times[(i+5)%12] + times[(i+7)%12] + times[(i+9)%12] + times[(i+11)%12];
			if(keyTot > curMaxVal){
				curMaxVal = keyTot;
				index = i;
			}  
		}
		return this.toString();
	}
	
	public String toString() {
		return Note.notes[index];
	}
}
