package uk.ac.manchester.cs.m84556jh.colour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColPal {
	
	private Col2[] colours;
	
	public ColPal(File cols) throws FileNotFoundException {
		colours = new Col2[12];
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
		colours[0] = new Col2(vals.get(0), vals.get(1));
		colours[1] = new Col2((vals.get(0) + vals.get(2))/2, (vals.get(1) + vals.get(3))/2);
		colours[2] = new Col2(vals.get(2), vals.get(3));
		colours[3] = new Col2((vals.get(2) + vals.get(4))/2, (vals.get(3) + vals.get(5))/2);
		colours[4] = new Col2(vals.get(4), vals.get(5));
		colours[5] = new Col2(vals.get(6), vals.get(7));
		colours[6] = new Col2((vals.get(6) + vals.get(8))/2, (vals.get(7) + vals.get(9))/2);
		colours[7] = new Col2(vals.get(8), vals.get(9));
		colours[8] = new Col2((vals.get(8) + vals.get(10))/2, (vals.get(9) + vals.get(11))/2);
		colours[9] = new Col2(vals.get(10), vals.get(11));
		colours[10] = new Col2((vals.get(10) + vals.get(12))/2, (vals.get(11) + vals.get(13))/2);
		colours[11] = new Col2(vals.get(11), vals.get(13));
	}
	
	public Col2 getCol(int index) {
		return colours[index];
	}

}
