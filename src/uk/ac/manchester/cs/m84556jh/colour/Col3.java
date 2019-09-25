package uk.ac.manchester.cs.m84556jh.colour;

public class Col3 {
	private int hue;
	private int sat;
	private int bri;
	
	public Col3(int givenHue, int givenSat, int givenBri) {
		this.hue = givenHue;
		this.sat = givenSat;
		if(givenBri > 100)
			this.bri = 100;
		else
			this.bri = givenBri;	
	}

	public int getHue() {
		return hue;
	}

	public int getSat() {
		return sat;
	}
	
	public int getBri() {
		return bri;
	}
	
	public boolean equals(Col3 other) {
		if((this.hue == other.hue) && (this.sat == other.sat) && (this.bri == other.bri))
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "(" + this.hue + "," + this.sat + "," + this.bri + ")";
	}
	
}
