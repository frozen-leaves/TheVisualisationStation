package uk.ac.manchester.cs.m84556jh.buffer;

import java.util.ArrayList;
import java.util.List;

public abstract class CB<T> {
	
	List<T> buf;
	int readPoint;
	int writePoint;
	int size;
	
	public CB(int bufSize) {
		size = bufSize;
		readPoint = 0;
		writePoint = 0;
		buf = new ArrayList<T>();
	}
	
	public void add(T item) {
		if(buf.size() < size)
			buf.add(writePoint, item);
		else
			buf.set(writePoint, item);
		writePoint = (writePoint+1)%size;
	}
	
	public abstract T read();
	
	//Run before read operations after writing to determine 
	//where to read from
	public abstract void setReadPoint();
	
	public int getBufSize() {
		return this.size;
	}

}
