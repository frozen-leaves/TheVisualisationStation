package uk.ac.manchester.cs.m84556jh.visualiser.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	public int shuffleNumSeconds = 10;
	public ColPal noteCols;
	private PApplet app;
	public Playlist p;
	
	String[] visualisations = {"Rectangle", "Circle", "Stats", "Spiral", "Bars", "Piano", "Random Particle", "Random Triangle", "Raining Particle", "Dying Stars", "SHUFFLE"};
	
	private final JComboBox<String> visList = new JComboBox<String>(visualisations);
	private final JLabel selStyle = new JLabel("Select Visualisation Style:");
	private JPanel shufflePanel = new JPanel();
	private final JLabel shuffleLabel = new JLabel("Seconds Per Visualisation:");
	private final JTextField shuffleText = new JTextField("10");
	private final JButton selectColFileButton = new JButton("Select Custom Colour File");
	private final JButton selectParamButton = new JButton("Customise Parameters");
	private final JButton selectNumParticlesButton = new JButton("Select Number of Particles Per Frame");
	private final JButton selectMP3Button = new JButton("Load Playlist (GO)");
	private final JButton exitButton = new JButton("Exit");
	private TitleImage tImg = new TitleImage("VSTitle.png");

	public Welcome(PApplet app) {
		this.app = app;
		setModalityType(DEFAULT_MODALITY_TYPE);
	    setTitle("The Visualisation Station - Welcome");
	    
	    Container contents = getContentPane();
	    contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
	    setResizable(false);
	    tImg.setPreferredSize(new Dimension((int)(0.5*app.width), (int)(0.25*app.width)));
	    contents.add(tImg);
	    
	    //Add GridLayout to keep buttons in two columns
	    JPanel topPanel = new JPanel();
	    topPanel.setLayout(new GridLayout(0,2));
	    topPanel.add(selStyle);
	    topPanel.add(visList);
	    visList.setSelectedIndex(0);
	    visList.addActionListener(this);
	    shufflePanel.setVisible(false);
	    shufflePanel.setLayout(new GridLayout(1,0));
	    shufflePanel.add(shuffleLabel);
	    shufflePanel.add(shuffleText);
	    topPanel.add(shufflePanel);
	    contents.add(topPanel);
	    JPanel bPanel = new JPanel();
	    bPanel.setLayout(new GridLayout(0,1));
	    bPanel.add(selectMP3Button);
	    bPanel.add(new JLabel("Optional/Experimental Features:"));
	    JPanel opPanel = new JPanel();
	    opPanel.setLayout(new GridLayout(1,0));
	    opPanel.add(selectColFileButton);
	    opPanel.add(selectParamButton);
	    opPanel.add(selectNumParticlesButton);
	    bPanel.add(opPanel);
	    bPanel.add(exitButton);
	    contents.add(bPanel);
	    
	    //Make this class the action listener for each of the buttons
	    selectColFileButton.addActionListener(this);
	    selectParamButton.addActionListener(this);
	    selectNumParticlesButton.addActionListener(this);
	    selectMP3Button.addActionListener(this);
	    exitButton.addActionListener(this);
	    
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
		if (event.getSource() == visList) {
			String visString = (String)visList.getSelectedItem();
			if(visString == "SHUFFLE") {
				//Display selection for seconds per shuffle
				shufflePanel.setVisible(true);
				visString = "ran";
			}else {
				//Hide selection for seconds per shuffle
				shufflePanel.setVisible(false);
			}
			//Set style as visString
			visString.replace(" " , "");
			style = visString;
			this.validate();
			this.repaint();
	    } else if(event.getSource() == selectColFileButton) {
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
