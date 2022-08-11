package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements MouseListener, MouseMotionListener{
	
	private int rgb;
	private int currentRGB;
	private Dimension screenSize;
	private Controller controller;
	private BufferedImage posBuffImage;
	private JLabel mapLabel;	
	private boolean chooseTerritory;
	private Territory activeTerritory;
	private ArrayList<Territory> territories;
	private ArrayList<ImageIcon> coa; 
	private HashMap<Integer, Territory> colorToTerritory;
	private HashMap <String, Entry<Integer, JLabel>> coaOnMap;
	private HashMap <String, Entry<Integer, JLabel>> armiesOnMap;


	public MapPanel(Controller controller) {
		
		
		this.controller = controller;
		territories = controller.game.getTerritories();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		
		//read images for background and positioning
		ImageIcon mapIcon = new ImageIcon("assets\\risk-map.jpg");
		mapIcon = scaleIcon(mapIcon, screenSize.width*8/10, screenSize.height);
		
		File input = new File("assets\\risk-pos-map.png");
		try {
			posBuffImage = ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mapLabel = new JLabel(mapIcon);
		mapLabel.setLayout(null);
		mapLabel.setBounds(0, 0, screenSize.width*8/10, screenSize.height);
		
		
		//Track coas on map
		coaOnMap = new HashMap<>();
		
		//Track armies on map
		armiesOnMap = new HashMap<>();
		
		//List of all coas
		coa = new ArrayList<>();
		for(int i = 1; i< 6; ++i) {
			ImageIcon coatIcon = new ImageIcon("assets\\coa" + i + ".png");
			coa.add(coatIcon);
		}
		this.setPreferredSize(new Dimension(screenSize.width*8/10,screenSize.height));
		this.setLayout(new BorderLayout());
		this.add(mapLabel);		
		
	}
	

	//Functions
		public Dimension getCoaCoordinates(String t) {
			int x = 0;
		    int y = 0;
		    boolean foundTerr = false;
			try (BufferedReader br = new BufferedReader(new FileReader("assets//coatPos.txt"))) {
			    String line;
			    String [] parts;
			    String territory;
			    while ((line = br.readLine()) != null && !foundTerr) {
			    	parts = line.split(",");
			    	territory = parts[0];
			    	if(territory.equals(t)) {
			    		foundTerr = true;
			    		x = (int) (Double.parseDouble(parts[1])*(screenSize.width*8/10));
				    	y = (int) (Double.parseDouble(parts[2])*screenSize.height);
			    	}
			    }
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return new Dimension(x,y);	
		}

		public ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
				Image image = icon.getImage();
				Image modImage = image.getScaledInstance
						(width, height, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(modImage);
				return icon;
			}
		
		public void placeCoa(String territory, int numCoat) {
			
			if(numCoat < coa.size()+1 && getCoaCoordinates(territory).width != 0) {
				if(!coaOnMap.containsKey(territory)){
					int x = getCoaCoordinates(territory).width;
					int y = getCoaCoordinates(territory).height;
					ImageIcon coaIcon = coa.get(numCoat-1);
					coaIcon = scaleIcon(coaIcon, 25, 40);
					JLabel coat = new JLabel(coaIcon);
					coat.setBounds(x-15,y-15, 30, 30);
					coat.setOpaque(false);
					mapLabel.add(coat);
					repaint();
					coaOnMap.put(territory, new SimpleEntry<>(numCoat, coat));	
				}
				else {
					removeCoa(territory);
					placeCoa(territory,numCoat);
				}	
			}
		}	
		
		public void removeCoa(String territory) {
			if(coaOnMap.containsKey(territory)){
				JLabel toBeRemoved = coaOnMap.get(territory).getValue();
				coaOnMap.remove(territory);
				mapLabel.remove(toBeRemoved);
			}
		}

		public void placeArmy(String territory, Integer army){
			
			if(!armiesOnMap.containsKey(territory)) {
				Dimension dim = getCoaCoordinates(territory);
				int x = dim.width;
				int y = dim.height;
				JLabel numArm = new JLabel(army.toString());
				numArm.setForeground(Color.RED);
				numArm.setBounds(x-3,y+4, 30, 30);
				numArm.setOpaque(false);
				mapLabel.add(numArm);
				armiesOnMap.put(territory, new SimpleEntry<>(army, numArm));
				repaint();
			}
			else {
				removeArmy(territory);
				placeArmy(territory, army);
			}	
		}
		
		public void removeArmy(String territory) {
			if(armiesOnMap.containsKey(territory)){
				JLabel toBeRemoved = armiesOnMap.get(territory).getValue();
				mapLabel.remove(toBeRemoved);
				armiesOnMap.remove(territory);
			}
		}
		
		// when enabled, mouseclick changes attribute active territory
		public void enableChooseTerritory() {
			chooseTerritory = true;
			this.addMouseListener(this);
			this.addMouseMotionListener(this);	
		}
		
		public void mouseClicked(MouseEvent e) {
			if(chooseTerritory) { 
				if(currentRGB != 0){
					rgb = currentRGB;
					this.removeMouseListener(this);
					this.removeMouseMotionListener(this);
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					int colorCode = new Color(rgb).getRed();
					activeTerritory = colorToTerritory.get(colorCode);
					System.out.println(activeTerritory.getName());
					}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			int posX = posBuffImage.getWidth()* e.getX()/(screenSize.width*8/10);
			int posY = posBuffImage.getHeight()*e.getY()/screenSize.height;
			currentRGB = posBuffImage.getRGB(posX, posY);
			if(currentRGB != 0) { 
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));}
			else{
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
	}

	


