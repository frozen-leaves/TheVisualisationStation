package uk.ac.manchester.cs.m84556jh.visualiser;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;

public class PlaylistLoader implements Runnable {
	
	private PApplet app;
	private ArrayList<File> files;
	private ArrayList<Spectrum> audioSpectrums;
	
	public PlaylistLoader(PApplet launcher, ArrayList<File> fileArray, ArrayList<Spectrum> audioArray) {
		app = launcher;
		files = fileArray;
		audioSpectrums = audioArray;
	}
	
	public void run() {
		for(int i = 0; i < files.size(); i++) {
			audioSpectrums.add(new Spectrum(app, files.get(i).getAbsolutePath(), 4096));
		}
	}

}
