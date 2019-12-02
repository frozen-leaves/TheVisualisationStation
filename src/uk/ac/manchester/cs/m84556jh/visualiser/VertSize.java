package uk.ac.manchester.cs.m84556jh.visualiser;

public class VertSize {
	
	private int state;
	private double curPerc;
	private double basePerc;
	private double increment;

	
	public VertSize(int numFramesEachWay, double basePerc) {
		this.state = 0;
		this.curPerc = basePerc;
		this.basePerc = basePerc;
		this.increment = (1.0 - basePerc)/(double)numFramesEachWay;
		
	}
	
	public double calcVertPerc(boolean isBeat) {
		//If it is a beat, start incrementing
		if(isBeat)
			state = 1;
		
		//STATE 0: No Change
		//STATE 1: Increase by increment 
		//If at max size, switch to state 2, set to 1.0
		//STATE 2: Decrease by increment
		//If at base size, switch to state 0, set to basePerc
		if(state == 1) {
			curPerc += increment;
			if(curPerc >= 1.0) {
				curPerc = 1.0;
				state = 2;
			}
		} else if(state == 2) {
			 curPerc -= increment;
			 if(curPerc <= basePerc) {
				 curPerc = basePerc;
				 state = 0;
			 }
		}
		return curPerc;
	}
}
