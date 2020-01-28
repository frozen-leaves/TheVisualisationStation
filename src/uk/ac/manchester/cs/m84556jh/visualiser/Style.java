package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Style extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//DEFAULT VALUES FOR PARAMETERS
	public String style = "rect";
	
	private final JButton rectButton = new JButton("Rectangular");
	private final JButton circleButton = new JButton("Circular");
	private final JButton statsButton = new JButton("Print Stats");
	private final JButton spiralButton = new JButton("Spiral");
	private final JButton barsButton = new JButton("Bars");
	private final JButton pianoButton = new JButton("Piano");

	public Style() {
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Visualisation Style");
	    Container contents = getContentPane();
	    contents.setLayout(new GridLayout(0,2));
	    contents.add(rectButton);
	    contents.add(circleButton);
	    contents.add(spiralButton);
	    contents.add(barsButton);
	    contents.add(pianoButton);
	    contents.add(statsButton);
	    //Make this class the action listener for each of the buttons
	    rectButton.addActionListener(this);
	    circleButton.addActionListener(this);
	    spiralButton.addActionListener(this);
	    barsButton.addActionListener(this);
	    pianoButton.addActionListener(this);
	    statsButton.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
	  
	//Performs actions for buttons
	public void actionPerformed(ActionEvent event) {
	    //Check which button has been clicked
	    if (event.getSource() == rectButton) {
	    	style = "rect";
	    	dispose();
	    } else if(event.getSource() == circleButton){
	    	style = "circle";
	    	dispose();
	    } else if(event.getSource() == spiralButton){
	    	style = "spiral";
	    	dispose();
	    } else if(event.getSource() == barsButton) {
	    	style = "bars";
	    	dispose();
	    } else if(event.getSource() == pianoButton) {
	    	style = "piano";
	    	dispose();
	    } else {
	    	style = "stats";
	    	dispose();
	    }
	    pack();
	}
}
