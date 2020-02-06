package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Parameters extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//DEFAULT VALUES FOR PARAMETERS
	public int fps = 30;
	public int ampBufSecs = 10;
	public double ampPerBufSecs = 0.35;
	public int ampMinSize = 20;
	public int bpmBufSize = 30;
	public int keyBufSecs = 10;
	public boolean useDefaultColFile = true;
	
	private final JTextField fpsTextField = new JTextField("30");
	private final JTextField ampBufSecsTextField = new JTextField("10");
	private final JTextField ampPerBufSecsTextField = new JTextField("0.35");
	private final JTextField ampMinSizeTextField = new JTextField("20");
	private final JTextField bpmBufSizeTextField = new JTextField("30");
	private final JTextField keyBufSecsTextField = new JTextField("10");
	private final JButton setButton = new JButton("Set Parameters");
	  

	public Parameters() {
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("Application Parameters");
	    Container contents = getContentPane();
	    contents.setLayout(new GridLayout(0,2));
	    JLabel fpsLabel = new JLabel("Frames Per Second (10-120):");
	    fpsLabel.setToolTipText("<html><p width=\"200\">A larger value will make the program more accurate, but will cause it to run slower</p></html>");
	    JLabel ampBufSecsLabel = new JLabel("Amplitude Buffer Size (Seconds) (1-60):");
	    ampBufSecsLabel.setToolTipText("<html><p width=\"200\">The larger this value is, the longer amount of time considered when looking for the previous minimum and maximum amplitudes</p></html>");
	    JLabel ampPerBufSecsLabel = new JLabel("Amplitude Percentage Buffer Size (Seconds) (0.2 - 1):");
	    ampPerBufSecsLabel.setToolTipText("<html><p width=\"200\">The larger this value is, the smoother the visualisation changes will be</p></html>");
	    JLabel ampMinSizeLabel = new JLabel("Amplitude Minimum Size (10-90):");
	    ampMinSizeLabel.setToolTipText("<html><p width=\"200\">The smallest percentage of the screen that the visualisation can take up - a smaller value may give small visualisations for low amplitudes, but a larger value gives less size variation</p></html>");
	    JLabel bpmBufSizeLabel = new JLabel("BPM Buffer Size (20-100):");
	    bpmBufSizeLabel.setToolTipText("<html><p width=\"200\">A larger value will give a more accurate BPM, but will take a long time to adjust after changes in the BPM</p></html>");
	    JLabel keyBufSecsLabel = new JLabel("Key Buffer Size (Seconds) (5-30):");
	    keyBufSecsLabel.setToolTipText("<html><p width=\"200\">A larger value will give a more accurate key, but will take a long time to adjust after changes in the key</p></html>");
	    contents.add(fpsLabel);
	    contents.add(fpsTextField);
	    contents.add(ampBufSecsLabel);
	    contents.add(ampBufSecsTextField);
	    contents.add(ampPerBufSecsLabel);
	    contents.add(ampPerBufSecsTextField);
	    contents.add(ampMinSizeLabel);
	    contents.add(ampMinSizeTextField);
	    contents.add(bpmBufSizeLabel);
	    contents.add(bpmBufSizeTextField);
	    contents.add(keyBufSecsLabel);
	    contents.add(keyBufSecsTextField);
	    contents.add(setButton);
	    //Make this class the action listener for each of the buttons
	    setButton.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
	  
	//Performs actions for buttons
	public void actionPerformed(ActionEvent event) {
    	try {
    		fps = Integer.parseInt(fpsTextField.getText());
	    	ampBufSecs = Integer.parseInt(ampBufSecsTextField.getText());
	    	ampPerBufSecs = Double.parseDouble(ampPerBufSecsTextField.getText());
	    	ampMinSize = Integer.parseInt(ampMinSizeTextField.getText());
	    	bpmBufSize = Integer.parseInt(bpmBufSizeTextField.getText());
	    	keyBufSecs = Integer.parseInt(keyBufSecsTextField.getText());
	    	dispose();
    	} catch(NumberFormatException e){
    		JOptionPane.showMessageDialog(null, "Please ensure all the values entered are numbers in the range required", "Error", JOptionPane.ERROR_MESSAGE); 
    	}

	    pack();
	}
}
