package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.imageio.ImageIO;



public class RiskGUI extends JFrame implements MouseListener, MouseMotionListener{
	
	private JPanel panelMap;
	private JPanel panelCf;
	
	private JLabel mapLabel;	
	private JLabel controlfieldLabel;
	private JLabel anzeige;	
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem speichern;
	private JMenuItem beenden;
	
	private JTextArea textAreaPlayerRequest;
	private JTextArea textAreaPlayerInformation;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	
	private boolean chooseTerritory;
	private Territory activeTerritory;
	private int rgb;
	private int currentRGB;
	private BufferedImage posBuffImage;
	private Dimension screenSize;
	private ArrayList<Territory> territories;
	private HashMap<Integer, Territory> colorToTerritory;
	
	public RiskGUI() {
		
	//TerritoryList
		this.territories = new ArrayList<>(42);
		//Schataria
		Territory shalathra = createTerritory("Shalathra", 0);
		Territory northernSchataria = createTerritory("Northern Schataria", 0);
		Territory azealon = createTerritory("Azealon", 0);
		Territory travaria = createTerritory("Travaria", 0);
		Territory northValoran = createTerritory("North Valoran", 0);
		Territory southValoran = createTerritory("South Valoran", 0);
		Territory wraithilles = createTerritory("Wraithilles", 0);
		Territory terrafen = createTerritory("Terrafen", 0);
		Territory theKingdomOfSun = createTerritory("The Kingdom of Sun", 0);
		
		//Arillia
		Territory drakeland = createTerritory("Drakeland", 1);
		Territory ssizara = createTerritory("Ssizara", 1);
		Territory azissa = createTerritory("Azissa", 1);
		Territory shajera = createTerritory("Shajera", 1);
		Territory myridia = createTerritory("Myridia", 1);
		Territory inghal = createTerritory("Inghal", 1);
		Territory westernArillia = createTerritory("Western Arillia", 1);
		Territory southernArillia = createTerritory("Southern Arillia", 1);
		Territory dreadmarch = createTerritory("Dreadmarch", 1);
		Territory druhm = createTerritory("Druhm", 1);
		Territory aroya = createTerritory("Aroya", 1);
		Territory azureKingdom = createTerritory("Azure Kingdom", 1);
		
		//Gilacia
		Territory gilacianIsles = createTerritory("Gilacian Isles", 2); 
		Territory whiteTundra = createTerritory("White Tundra", 2);
		Territory glice = createTerritory("Glice", 2);
		Territory fridigia = createTerritory("Fridigia", 2);
		
		//Urza
		Territory uria = createTerritory("Uria", 3);
		Territory lunador = createTerritory("Lunador", 3);
		Territory veylor = createTerritory("Veylor", 3);
		Territory solador = createTerritory("Solador", 3);
		Territory centralUrza = createTerritory("Central Urza", 3);
		Territory glendara = createTerritory("Glendara", 3);
		Territory easternUrza = createTerritory("Eastern Urza", 3);
		
		//Arlas
		Territory northernArlas = createTerritory("Northern Arlas", 4);
		Territory hagros = createTerritory("Hagros", 4);
		Territory arlasBarrens = createTerritory("Arlas Barrens", 4);
		Territory zeish = createTerritory("Zeish", 4);
		Territory agashar = createTerritory("Agashar", 4);
		Territory southernArlas = createTerritory("Southern Arlas", 4);
		
		//Algos
		Territory velis = createTerritory("Velis", 5);
		Territory westernAlgos = createTerritory("Western Algos", 5);
		Territory easternAlgos = createTerritory("Eastern Algos", 5);
		Territory boria = createTerritory("Boria", 5);
		
	
		colorToTerritory= new HashMap<>();
		for(int i = 0; i< 41; ++i) {
			colorToTerritory.put(i, territories.get(i));
		}		
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		//Bilder einlesen
		ImageIcon mapIcon = new ImageIcon("assets\\risk-map.jpg");
		Image map = mapIcon.getImage();
		Image modmap = map.getScaledInstance
				(screenSize.width*8/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		mapIcon = new ImageIcon(modmap);
				
		ImageIcon controlfieldIcon = new ImageIcon
				("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		Image controlfieldImage = controlfieldIcon.getImage();
		Image modControlfieldImage = controlfieldImage.getScaledInstance
				(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);

		File input = new File("assets\\risk-pos-map.png");
		try {
			posBuffImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		//Panel anpassen
		panelMap = new JPanel();
		panelMap.setPreferredSize(new Dimension(screenSize.width*8/10, screenSize.height));
		panelCf = new JPanel();
				
		mapLabel = new JLabel(mapIcon);
		mapLabel.setLayout(null);
		mapLabel.setBounds(20, 20, screenSize.width*8/10, screenSize.height);
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		panelMap.setPreferredSize(new Dimension(screenSize.width*8/10,screenSize.height));
		panelCf.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		panelCf.setLayout(new BorderLayout());
		
		menuBar = new JMenuBar();
		menu = new JMenu("M");
		speichern = new JMenuItem("Speichern");
		beenden = new JMenuItem("Beenden");
		menuBar.setBackground(Color.lightGray);
		menuBar.setBounds(0,0,screenSize.width,30);
		menu.add(speichern);
		menu.add(beenden);
		menuBar.add(menu);
		
		panelCf.add(menuBar, BorderLayout.NORTH);
		panelCf.setLayout(null);
		
		textAreaPlayerRequest = new JTextArea();
		Color myColor = new Color(239,222,176);
		textAreaPlayerRequest.setBounds(40, 30, 200, 40);
		textAreaPlayerRequest.setBackground(myColor);
		panelCf.add(textAreaPlayerRequest);
		
		button1 = new JButton("Knopf One");
		button1.setBounds(90, 100, 100, 20);
		button1.setBackground(Color.cyan.darker());
		panelCf.add(button1);
		
		button2 = new JButton("Knopf Two");
		button2.setBounds(90, 160, 100, 20);
		button2.setBackground(Color.cyan.darker());
		panelCf.add(button2);
		
		button3 = new JButton("Knopf Three");
		button3.setBounds(90, 220, 100, 20);
		button3.setBackground(Color.cyan.darker());
		panelCf.add(button3);
		
		button4 = new JButton("Knopf Four");
		button4.setBounds(90, 280, 100, 20);
		//button4.setBackground(Color.cyan.darker());
		button4.setIcon(mapIcon);
		JLabel button4Text = new JLabel ("Hallo");
		button4.add(button4Text);
		panelCf.add(button4);
		
		textAreaPlayerInformation = new JTextArea();
		textAreaPlayerInformation.setBounds(30, 500, 220, 200);
		textAreaPlayerInformation.setBackground(Color.RED.darker());
		panelCf.add(textAreaPlayerInformation);
		
		anzeige = new JLabel("Anzeige");
		anzeige.setBounds(0, 0, 100, 15);
		panelCf.add(anzeige);
		
		//Bild auf Panel
		panelMap.setLayout(new BorderLayout());
		panelMap.add(mapLabel);
		panelCf.add(controlfieldLabel);
		
		//Panel auf JFrame
		this.setLayout(new BorderLayout());
		this.add(panelMap, BorderLayout.EAST);
		this.add(panelCf,BorderLayout.WEST);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public void chooseTerritory(boolean b) {
		panelMap.addMouseListener(this);
		panelMap.addMouseMotionListener(this);
	}
	
	private Territory createTerritory(String name, int continentIndex) {
		Territory newTerritory = new Territory(name);
		//newTerritory.setContinent(this.continents.get(continentIndex));
		this.territories.add(newTerritory);
		//this.continents.get(continentIndex).addTerritory(newTerritory);
		return newTerritory;
	}
	
	public void addCoatToMap(int numCoat) {
		panelMap.remove(mapLabel);
		ImageIcon coatIcon = new ImageIcon("assets\\coa" + numCoat + ".png");
		JLabel coat = new JLabel(coatIcon);
		coat.setBounds(0,0,500,500);
		coat.setOpaque(false);
		panelMap.setLayout(new BorderLayout());
		mapLabel.add(coat);
		panelMap.add(mapLabel);
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if(chooseTerritory) { 
			if(currentRGB != 0){
				rgb = currentRGB;
				panelMap.removeMouseListener(this);
				panelMap.removeMouseMotionListener(this);
				panelMap.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				int colorCode = new Color(rgb).getRed();
				System.out.println(colorToTerritory.get(colorCode).getName());
				activeTerritory = colorToTerritory.get(colorCode);
				}
		}
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void enableChooseTerritory() {
		chooseTerritory = true;
		panelMap.addMouseListener(this);
		panelMap.addMouseMotionListener(this);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int posX = posBuffImage.getWidth()* e.getX()/(screenSize.width*8/10);
		int posY = posBuffImage.getHeight()*e.getY()/screenSize.height;
		currentRGB = posBuffImage.getRGB(posX, posY);
		if(currentRGB != 0) { 
			panelMap.setCursor(new Cursor(Cursor.HAND_CURSOR));}
		else{
			panelMap.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
