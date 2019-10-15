package uk.ac.manchester.cs.m84556jh.colour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColPal {
	
	private Col[] colours;
	
	public ColPal(File cols) throws FileNotFoundException {
		colours = new Col[12];
		List<Integer> vals = new ArrayList<>();
		//Get the given notes from the file
		Scanner scanner = new Scanner(cols);
		while (scanner.hasNext()) {
		    if (scanner.hasNextInt()) {
		        vals.add(scanner.nextInt());
		    } else {
		        scanner.next();
		    }
		}
		scanner.close();
		//Calculate the correct hue and saturation for each note from the vals given
		colours[0] = new Col(vals.get(0), vals.get(1), vals.get(2));
		colours[1] = new Col((vals.get(0) + vals.get(3))/2, (vals.get(1) + vals.get(4))/2, (vals.get(2) + vals.get(5))/2);
		colours[2] = new Col(vals.get(3), vals.get(4), vals.get(5));
		colours[3] = new Col((vals.get(3) + vals.get(6))/2, (vals.get(4) + vals.get(7))/2, (vals.get(5) + vals.get(8))/2);
		colours[4] = new Col(vals.get(6), vals.get(7), vals.get(8));
		colours[5] = new Col(vals.get(9), vals.get(10), vals.get(11));
		colours[6] = new Col((vals.get(9) + vals.get(12))/2, (vals.get(10) + vals.get(13))/2, (vals.get(11) + vals.get(14))/2);
		colours[7] = new Col(vals.get(12), vals.get(13), vals.get(14));
		colours[8] = new Col((vals.get(12) + vals.get(15))/2, (vals.get(13) + vals.get(16))/2, (vals.get(14) + vals.get(17))/2);
		colours[9] = new Col(vals.get(15), vals.get(16), vals.get(17));
		colours[10] = new Col((vals.get(15) + vals.get(18))/2, (vals.get(16) + vals.get(19))/2, (vals.get(17) + vals.get(20))/2);
		colours[11] = new Col(vals.get(18), vals.get(19), vals.get(20));
	}
	
	public Col getCol(int index) {
		return colours[index];
	}

}
