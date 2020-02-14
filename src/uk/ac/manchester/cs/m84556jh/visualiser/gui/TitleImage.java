package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TitleImage extends JPanel {
	private BufferedImage titleImg;
	
	public TitleImage(String imgFileName) {
		try {
			titleImg = ImageIO.read(new File(imgFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		gr.drawImage(titleImg, 0, 0, this.getWidth(), (int)((titleImg.getHeight()/(double)titleImg.getWidth())*this.getWidth()), null);
	}
	
}
