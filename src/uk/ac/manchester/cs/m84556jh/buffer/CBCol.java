package uk.ac.manchester.cs.m84556jh.buffer;

import uk.ac.manchester.cs.m84556jh.colour.Col;

public class CBCol extends CB<Col> {
	
	private int prevSize;

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
	
	//Take the current colour and amplitude and return the new pixel buffer
	//with the correct number of elements for the current amplitude
	public Col[] calcPixelBuf(Col curCol, double size) {
		//Convert amplitude size to number of pixels
		int curSize = (int)(size/100*getBufSize());
		//If bigger than previous size, top up pixel buffer with pixels 
		//of new colour equal to difference
		//Otherwise, add 5 pixels of current colour
		if(curSize > prevSize) {
			for(int i = 0; i < curSize-prevSize; i++)
				add(curCol);
		} else {
			for(int i = 0; i < 5; i++)
				add(curCol);
		}
		//Produce array from pixel buffer of size required
		Col[] pixelBufArray = new Col[curSize];
		setReadPoint();
		for(int i = 0; i < curSize; i++) {
			pixelBufArray[i] = read();
		}
		//Keep track of previous size
		prevSize = curSize;
		return pixelBufArray;
	}
	
	

}
