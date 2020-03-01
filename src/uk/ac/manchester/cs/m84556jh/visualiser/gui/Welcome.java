package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import processing.core.PApplet;
import uk.ac.manchester.cs.m84556jh.colour.ColPal;
import uk.ac.manchester.cs.m84556jh.visualiser.Launcher;

public class Welcome extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	//DEFAULT VALUES FOR PARAMETERS
	public String style = "Rectangle";
	public int fps = 30;
	public int ampBufSecs = 10;
	public double ampPerBufSecs = 0.35;
	public int ampMinSize = 20;
	public int bpmBufSize = 30;
	public int keyBufSecs = 10;
	public int numPType = 0;
	public int percMaxP = 90;
	public int numMaxP = 5;
	public boolean useDefaultColFile = true;
	public int shuffleNumSeconds = 20;
	public ColPal noteCols;
	private PApplet app;
	public Playlist p;
	
	private final JLabel selStyle = new JLabel("1. Select Visualisation Style:");
	private final JLabel blankLabel = new JLabel("");
	private final JRadioButton rectButton = new JRadioButton("Rectangular");
	private final JRadioButton circleButton = new JRadioButton("Circular");
	private final JRadioButton statsButton = new JRadioButton("Print Stats");
	private final JRadioButton spiralButton = new JRadioButton("Spiral");
	private final JRadioButton barsButton = new JRadioButton("Bars");
	private final JRadioButton pianoButton = new JRadioButton("Piano");
	private final JRadioButton ranParButton = new JRadioButton("Random Particles");
	private final JRadioButton ranTriButton = new JRadioButton("Random Triangles");
	private final JRadioButton rainParButton = new JRadioButton("Raining Particles");
	private final JRadioButton dyingStarsButton = new JRadioButton("Dying Stars");
	private final JRadioButton shuffleButton = new JRadioButton("SHUFFLE");
	private final JLabel shuffleLabel = new JLabel("Seconds per visualisation:");
	private final JTextField shuffleText = new JTextField("20");
	private final JButton selectColFileButton = new JButton("2. Choose Custom Colour File (Optional)");
	private final JButton selectParamButton = new JButton("3. Customise Parameters (Optional)");
	private final JButton selectNumParticlesButton = new JButton("4. Select Number of Particles Per Frame (Experimental!)");
	private final JButton selectMP3Button = new JButton("5. Select MP3");
	private final JButton exitButton = new JButton("Exit");
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
	    //Add GridLayout to keep buttons in two columns
	    JPanel rbPanel = new JPanel();
	    rbPanel.setLayout(new GridLayout(0,2));
	    rbPanel.add(selStyle);
	    rbPanel.add(blankLabel);
	    rbPanel.add(rectButton);
	    bg.add(rectButton);
	    rectButton.setSelected(true);
	    rbPanel.add(circleButton);
	    bg.add(circleButton);
	    rbPanel.add(spiralButton);
	    bg.add(spiralButton);
	    rbPanel.add(barsButton);
	    bg.add(barsButton);
	    rbPanel.add(pianoButton);
	    bg.add(pianoButton);
	    rbPanel.add(ranParButton);
	    bg.add(ranParButton);
	    rbPanel.add(ranTriButton);
	    bg.add(ranTriButton);
	    rbPanel.add(rainParButton);
	    bg.add(rainParButton);
	    rbPanel.add(dyingStarsButton);
	    bg.add(dyingStarsButton);
	    rbPanel.add(statsButton);
	    bg.add(statsButton);
	    JPanel shufflePanel = new JPanel();
	    shufflePanel.setLayout(new GridLayout(1,0));
	    shufflePanel.add(shuffleButton);
	    shufflePanel.add(shuffleLabel);
	    shufflePanel.add(shuffleText);
	    rbPanel.add(shufflePanel);
	    bg.add(shuffleButton);
	    contents.add(rbPanel);
	    JPanel bPanel = new JPanel();
	    bPanel.setLayout(new GridLayout(0,1));
	    bPanel.add(selectColFileButton);
	    bPanel.add(selectParamButton);
	    bPanel.add(selectNumParticlesButton);
	    bPanel.add(selectMP3Button);
	    bPanel.add(exitButton);
	    contents.add(bPanel);
	    //Make this class the action listener for each of the buttons
	    rectButton.addActionListener(this);
	    circleButton.addActionListener(this);
	    spiralButton.addActionListener(this);
	    barsButton.addActionListener(this);
	    pianoButton.addActionListener(this);
	    ranParButton.addActionListener(this);
	    ranTriButton.addActionListener(this);
	    rainParButton.addActionListener(this);
	    dyingStarsButton.addActionListener(this);
	    statsButton.addActionListener(this);
	    shuffleButton.addActionListener(this);
	    selectColFileButton.addActionListener(this);
	    selectParamButton.addActionListener(this);
	    selectNumParticlesButton.addActionListener(this);
	    selectMP3Button.addActionListener(this);
	    exitButton.addActionListener(this);
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	System.exit(0);
	        }
	    });
	    pack();
	    setLocationRelativeTo(null);
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
	    	style = "Rectangle";
	    } else if(event.getSource() == circleButton){
	    	style = "Circle";
	    } else if(event.getSource() == spiralButton){
	    	style = "Spiral";
	    } else if(event.getSource() == barsButton) {
	    	style = "Bars";
	    } else if(event.getSource() == pianoButton) {
	    	style = "Piano";
	    } else if(event.getSource() == ranParButton) {
	    	style = "RandomParticle";
	    } else if(event.getSource() == ranTriButton) {
	    	style = "RandomTriangle";
	    } else if(event.getSource() == rainParButton) {
	    	style = "RainingParticle";
	    } else if(event.getSource() == dyingStarsButton) {
	    	style = "DyingStars";
	    } else if(event.getSource() == statsButton){
	    	style = "Stats";
	    } else if(event.getSource() == shuffleButton) {
	    	style = "ran";
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
	    } else if(event.getSource() == selectNumParticlesButton) {
	    	NumParticles n = new NumParticles();
	    	numPType = n.numPType;
	    	percMaxP = n.percMaxP;
	    	numMaxP = n.numMaxP;
	    } else if(event.getSource() == selectMP3Button){
	    	try {
	    		shuffleNumSeconds = Integer.parseInt(shuffleText.getText());
	    	} catch(NumberFormatException e){
	    		JOptionPane.showMessageDialog(null, "Please ensure all the values entered are numbers in the range required", "Error", JOptionPane.ERROR_MESSAGE); 
	    	}
	    	p = new Playlist((Launcher)app);
	    	dispose();
	    } else {
	    	System.exit(0);
	    }
	    
	    pack();
	}
	
	
}
