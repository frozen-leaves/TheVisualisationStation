package uk.ac.manchester.cs.m84556jh.visualiser;

public class KeyPair implements Comparable<KeyPair>{
	
	private int index;
	private int val;
	
	public KeyPair(int key, int val) {
		this.index = key;
		this.val = val;
	}
	
	@Override
	public int compareTo(KeyPair arg0) {
		return ((Integer)this.val).compareTo(arg0.val);
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getVal() {
		return val;
	}

}
