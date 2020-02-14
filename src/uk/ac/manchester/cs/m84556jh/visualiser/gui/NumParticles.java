package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class NumParticles extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//DEFAULT VALUES FOR PARAMETERS
	public int numPType = 0;
	public int percMaxP = 90;
	public int numMaxP = 5;
	
	private final JLabel selNP = new JLabel("Select Number of Particles:");
	private final JRadioButton singleButton = new JRadioButton("Single");
	private final JRadioButton numButton = new JRadioButton("Given Number");
	private final JLabel numLabel = new JLabel("Number of particles to output (1-10):");
	private final JTextField numTextField = new JTextField("5");
	private final JRadioButton percButton = new JRadioButton("Given Percentage Threshold");
	private final JLabel percLabel = new JLabel("Threshold as percentage of max value (75 - 100):");
	private final JTextField percTextField = new JTextField("90");
	private final JButton setButton = new JButton("Set");

	public NumParticles() {
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Set Number of Particles Per Frame");
	    
	    Container contents = getContentPane();
	    contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
	    setResizable(false);
	    ButtonGroup bg = new ButtonGroup();
	    contents.add(selNP);
	    contents.add(singleButton);
	    bg.add(singleButton);
	    singleButton.setSelected(true);
	    contents.add(numButton);
	    bg.add(numButton);
	    contents.add(numLabel);
	    contents.add(numTextField);
	    numTextField.setEnabled(false);
	    contents.add(percButton);
	    bg.add(percButton);
	    contents.add(percLabel);
	    contents.add(percTextField);
	    percTextField.setEnabled(false);
	    contents.add(setButton);
	    //Make this class the action listener for each of the buttons
	    singleButton.addActionListener(this);
	    numButton.addActionListener(this);
	    percButton.addActionListener(this);
	    setButton.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	  
	//Performs actions for buttons
	public void actionPerformed(ActionEvent event) {
	    //Check which button has been clicked
	    if (event.getSource() == singleButton) {
	    	numPType = 0;
	    	numTextField.setEnabled(false);
	    	percTextField.setEnabled(false);
	    } else if(event.getSource() == numButton){
	    	numPType = 1;
	    	numTextField.setEnabled(true);
	    	percTextField.setEnabled(false);
	    } else if(event.getSource() == percButton){
	    	numPType = 2;
	    	numTextField.setEnabled(false);
	    	percTextField.setEnabled(true);
	    } else if(event.getSource() == setButton) {
	    	try {
	    		percMaxP = Integer.parseInt(percTextField.getText());
		    	numMaxP = Integer.parseInt(numTextField.getText());
		    	dispose();
	    	} catch(NumberFormatException e){
	    		JOptionPane.showMessageDialog(null, "Please ensure all the values entered are numbers in the range required", "Error", JOptionPane.ERROR_MESSAGE); 
	    	}
	    } 
	    pack();
	}
	
	
}
