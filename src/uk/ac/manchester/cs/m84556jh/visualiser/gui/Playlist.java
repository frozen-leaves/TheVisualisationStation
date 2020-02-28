package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import processing.core.PApplet;

public class Playlist extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final PApplet launcher;
	private final JButton goButton = new JButton("Go");
	private final JButton addButton = new JButton("Add Song");
	private final JButton deleteButton = new JButton("Delete Last Song");
	
	public Playlist(PApplet app) {
		launcher = app;
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Application Parameters");
	    Container contents = getContentPane();
	    contents.setLayout(new GridLayout(0,1));
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(3,0));
	    buttonPanel.add(addButton);
	    buttonPanel.add(deleteButton);
	    buttonPanel.add(goButton);
	    contents.add(buttonPanel);
	    //Make this class the action listener for each of the buttons
	    addButton.addActionListener(this);
	    deleteButton.addActionListener(this);
	    goButton.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	  
	//Performs actions for buttons
	public void actionPerformed(ActionEvent event) {
    	if(event.getSource() == addButton) {
    		launcher.selectInput("Select an MP3 file to use:", "audioFileSelected");
    	} else if(event.getSource() == deleteButton) {
    		//STUB: IMPLEMENT
    	} else {
    		//CHECK AT LEAST ONE AUDIO FILE LOADED
    		dispose();
    	}

    	pack();
	}
}
