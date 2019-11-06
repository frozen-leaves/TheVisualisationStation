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
	  

	public Style() {
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Visualisation Style");
	    Container contents = getContentPane();
	    contents.setLayout(new GridLayout(0,2));
	    contents.add(rectButton);
	    contents.add(circleButton);
	    //Make this class the action listener for each of the buttons
	    rectButton.addActionListener(this);
	    circleButton.addActionListener(this);
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
	    	dispose();
	    }//end if
	    else {
	    	style = "circle";
	    	dispose();
	    }
	    pack();
	}
}
