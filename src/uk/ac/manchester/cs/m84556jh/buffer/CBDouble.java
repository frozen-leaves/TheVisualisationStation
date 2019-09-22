package uk.ac.manchester.cs.m84556jh.buffer;

public class CBDouble extends CB<Double> {

	public CBDouble(int bufSize) {
		super(bufSize);
	}

	@Override
	public Double read() {
		if(buf.size() == 0)
			return 0.0;
		readPoint = (readPoint + 1)%buf.size();
		return buf.get(readPoint);
	}
	
	@Override
	public void setReadPoint() {
		readPoint = writePoint - 1;
		if(readPoint == -1)
			readPoint = buf.size()-1;
	}

	//Get minimum value in the buffer
	public double min() {
		double curMin = Double.MAX_VALUE;
		setReadPoint();
		for(int i = 0; i < buf.size(); i++) {
			double val = read();
			if(val < curMin)
				curMin = val;
		}
		return curMin;
	}
	
	//Get maximum value in the buffer
	public double max() {
		double curMax = 0;
		setReadPoint();
		for(int i = 0; i < buf.size(); i++) {
			double val = read();
			if(val > curMax)
				curMax = val;
		}	
		return curMax;
	}

	//Get the average of value in buffer
	public double avg() {
		double sum = 0.0;
		setReadPoint();
		for(int i = 0; i < buf.size(); i++)
			sum += read();
		return sum/buf.size();
	}
	
	//Get the variance of doubles in buffer
	public double var() {
		double varSum = 0.0;
		double avg = avg();
		setReadPoint();
		for(int i = 0; i < buf.size(); i++)
			varSum += Math.pow(read() - avg, 2);
		return varSum/buf.size();
	}
}
