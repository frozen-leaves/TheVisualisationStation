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
		boolean nonConFound = false;
		int conSets = 0;
		int conTotal = 0;
		int conSubtotal = 0;
		setReadPoint();
		double curVal = read();
		double nextVal;
		for(int i = 0; i < buf.size() - 1; i++){
			nextVal = read();
			//If values are consecutive and we have already found a non-consecutive value
			//(i.e we are not at the start of the buffer before there is a non-consecutive value),
			//increment the number of consecutive values found
			if(nextVal-curVal == 1){
				if(nonConFound)
					conSubtotal++;
			}
			else{
				//We have now found a non-consecutive value, so set the found boolean to true
				if(!nonConFound)
					nonConFound = true;
				//Only update the conTotal after we have found a non-consecutive value
				//The number of consecutive sets should be a count of the number of groups of 
				//consecutive values between non-consecutive values
				//By only duplicating conSubTotal into conTotal when a non-consecutive 
				//value is found, we ignore the consecutive values that appear after the last
				//non-consecutive value in the buffer
				else{
					conTotal = conSubtotal;
					conSets++;
				}  
				//Vals keeps track of the non-consecutive values found
				vals++;
				difTotal += nextVal - curVal;
			}
			curVal = nextVal;
		}  
		if(vals == 0)
			//If no non-consecutive values, cannot give a BPM
			return -1.0;
		else if(conSets == 0)
			//If no consecutive values, simply return the average difference between non-consecutive values
			return difTotal/(double)vals;
		else
			//Otherwise add to this the average number of consecutive values between each non-consecutive value
			return difTotal/(double)vals + conTotal/(double)conSets;
	}

}
