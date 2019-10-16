package uk.ac.manchester.cs.m84556jh.buffer;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class CBCol extends CB<Col> {

	public CBCol(int bufSize) {
		super(bufSize);
	}

	//Reads backwards i.e. reads newly added colours first
	@Override
	public Col read() {
		readPoint--;
		if(readPoint == -1)
			readPoint = buf.size()-1;
		return buf.get(readPoint);
	}

	@Override
	public void setReadPoint() {
		readPoint = writePoint;
	}
	
	

}
