package uk.ac.manchester.cs.m84556jh.visualiser;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;

public class Welcome extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//DEFAULT VALUES FOR PARAMETERS
	public String style = "rect";
	public int fps = 30;
	public int ampBufSecs = 10;
	public double ampPerBufSecs = 0.35;
	public int ampMinSize = 20;
	public int bpmBufSize = 30;
	public int keyBufSecs = 10;
	public boolean useDefaultColFile = true;
	public ColPal noteCols;
	private PApplet app;
	
	private final JLabel selStyle = new JLabel("1. Select Visualisation Style");
	private final JRadioButton rectButton = new JRadioButton("Rectangular");
	private final JRadioButton circleButton = new JRadioButton("Circular");
	private final JRadioButton statsButton = new JRadioButton("Print Stats");
	private final JRadioButton spiralButton = new JRadioButton("Spiral");
	private final JRadioButton barsButton = new JRadioButton("Bars");
	private final JRadioButton pianoButton = new JRadioButton("Piano");
	private final JRadioButton ranParButton = new JRadioButton("Random Particles");
	private final JButton selectColFileButton = new JButton("2. Choose Custom Colour File (Optional)");
	private final JButton selectParamButton = new JButton("3. Customise Parameters (Optional)");
	private final JButton selectMP3Button = new JButton("4. Select MP3");
	private TitleImage tImg = new TitleImage("VSTitle.png");

	public Welcome(PApplet app) {
		this.app = app;
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("The Visualisation Station - Welcome");
	    
	    Container contents = getContentPane();
	    contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
	    setResizable(false);
	    tImg.setPreferredSize(new Dimension((int)(0.5*app.width), (int)(0.25*app.width)));
	    contents.add(tImg);
	    ButtonGroup bg = new ButtonGroup();
	    contents.add(selStyle);
	    contents.add(rectButton);
	    bg.add(rectButton);
	    rectButton.setSelected(true);
	    contents.add(circleButton);
	    bg.add(circleButton);
	    contents.add(spiralButton);
	    bg.add(spiralButton);
	    contents.add(barsButton);
	    bg.add(barsButton);
	    contents.add(pianoButton);
	    bg.add(pianoButton);
	    contents.add(ranParButton);
	    bg.add(ranParButton);
	    contents.add(statsButton);
	    bg.add(statsButton);
	    contents.add(selectColFileButton);
	    contents.add(selectParamButton);
	    contents.add(selectMP3Button);
	    //Make this class the action listener for each of the buttons
	    rectButton.addActionListener(this);
	    circleButton.addActionListener(this);
	    spiralButton.addActionListener(this);
	    barsButton.addActionListener(this);
	    pianoButton.addActionListener(this);
	    ranParButton.addActionListener(this);
	    statsButton.addActionListener(this);
	    selectColFileButton.addActionListener(this);
	    selectParamButton.addActionListener(this);
	    selectMP3Button.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    pack();
	    setVisible(true);
	    
	}
	
	public void colsSelected(File cols) {
    	try {
			noteCols = new ColPal(cols);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	  
	//Performs actions for buttons
	public void actionPerformed(ActionEvent event) {
	    //Check which button has been clicked
	    if (event.getSource() == rectButton) {
	    	style = "rect";
	    } else if(event.getSource() == circleButton){
	    	style = "circle";
	    } else if(event.getSource() == spiralButton){
	    	style = "spiral";
	    } else if(event.getSource() == barsButton) {
	    	style = "bars";
	    } else if(event.getSource() == pianoButton) {
	    	style = "piano";
	    } else if(event.getSource() == ranParButton) {
	    	style = "ranPar";
	    } else if(event.getSource() == statsButton){
	    	style = "stats";
	    } else if(event.getSource() == selectColFileButton) {
	    	useDefaultColFile = false;
	    	app.selectInput("Select a colour file to use:", "populateNoteCols");
	    } else if(event.getSource() == selectParamButton) {
	    	Parameters p = new Parameters();
	    	fps = p.fps;
	    	ampBufSecs = p.ampBufSecs;
	    	ampPerBufSecs = p.ampPerBufSecs;
	    	ampMinSize = p.ampMinSize;
	    	bpmBufSize = p.bpmBufSize;
	    	keyBufSecs = p.keyBufSecs;
	    } else {
	    	dispose();
	    }
	    
	    pack();
	}
	
	
}
