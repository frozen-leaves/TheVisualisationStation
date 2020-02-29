package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.ac.manchester.cs.m84556jh.visualiser.Launcher;

public class Playlist extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final Launcher launcher;
	private final JButton goButton = new JButton("Go");
	private final JButton addButton = new JButton("Add Song");
	private final JButton deleteButton = new JButton("Delete Last Song");
	//public volatile JPanel playlistPanel = new JPanel();
	
	public Playlist(Launcher app) {
		launcher = app;
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Application Parameters");
	    Container contents = getContentPane();
	    contents.setLayout(new GridLayout(0,1));
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(0,3));
	    buttonPanel.add(addButton);
	    buttonPanel.add(deleteButton);
	    buttonPanel.add(goButton);
	    contents.add(buttonPanel);
	    //playlistPanel.setLayout(new GridLayout(0,1));
	    //contents.add(playlistPanel);
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
    		if(launcher.files.size() > 0)
    		    launcher.files.removeLast();
    	} else {
    		if(launcher.files.size() < 1) {
    			JOptionPane.showMessageDialog(this, "Must load at least one file!");
    		} else {
    			dispose();
    		}
    	}
    	pack();
	}
}
