package uk.ac.manchester.cs.m84556jh.visualiser;

import java.io.File;

public class PlaylistLoader implements Runnable {
	
	private Launcher app;
	private File file;
	
	public PlaylistLoader(Launcher launcher, File file) {
		app = launcher;
		this.file = file;
	}
	
	public void run() {
			app.nextSpectrum = new Spectrum(app, file.getAbsolutePath(), 4096);
	}

}
