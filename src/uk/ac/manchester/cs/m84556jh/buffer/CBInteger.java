package uk.ac.manchester.cs.m84556jh.buffer;

public class CBInteger extends CB<Integer> {

	public CBInteger(int bufSize) {
		super(bufSize);
	}

	@Override
	public Integer read() {
		if(buf.size() == 0)
			return 0;
		readPoint = (readPoint + 1)%buf.size();
		return buf.get(readPoint);
	}

	@Override
	public void setReadPoint() {
		readPoint = writePoint - 1;
		if(readPoint == -1)
			readPoint = buf.size()-1;
	}
	
	//Calculate average difference between consecutive non-zero values in array
	//Ignore values whose difference is only 1, i.e. consecutive samples
	//Add the average number of consecutive values between non-consecutive values
	//to the average non-consecutive value to get an accurate difference between values
	public double dif() {
		int difTotal = 0;
		int vals = 0;
		boolean nonConValFound = false;
		int numConSets = 0;
		int conValTotal = 0;
		int conValSubtotal = 0;
		setReadPoint();
		double curVal = read();
		double nextVal = 0;
		for(int i = 0; i < buf.size() - 1; i++){
			nextVal = read();
			if(nextVal-curVal == 1){
				if(nonConValFound)
					conValSubtotal++;
			}
			else{
				if(!nonConValFound)
					nonConValFound = true;
				else{
					conValTotal = conValSubtotal;
					numConSets++;
				}  
				vals++;
				difTotal += nextVal - curVal;
			}
			curVal = nextVal;
		}  
		if(vals == 0)
			return -1.0;
		else if(numConSets == 0)
			return difTotal/(double)vals;
		else
			return difTotal/(double)vals + conValTotal/(double)numConSets;
	}

}
