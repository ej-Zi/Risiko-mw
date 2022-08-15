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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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

public class RiskGUI extends JFrame{
	
	private JPanel panelMap;
	private JPanel panelCf;	
	
	public Phase0 phase0;
	public Phase1 phase1;
	public Phase2 phase2;
	public Phase3 phase3;
	public Phase4 phase4;
	public MapPanel mapPanel;
	public DrawCards drawCards;
	
	public Controller controller;
	
	public RiskGUI(Controller controller) {
		
		this.controller = controller;
		
		//editing panels
		panelMap = new JPanel();
		panelCf = new JPanel();
		panelMap.setLayout(new BorderLayout());
		panelCf.setLayout(new BorderLayout());
		
		this.phase0 = new Phase0(controller);
		this.phase1 = new Phase1(controller);
		this.phase2 = new Phase2(controller);
		this.phase3 = new Phase3(controller);
		this.phase4 = new Phase4(controller);
		this.mapPanel = new MapPanel(controller);
		this.drawCards = new DrawCards(controller);
		
		panelMap.add(mapPanel);
		
		changePhase(0);
	
		//place Panel on JFrame
		this.setLayout(new BorderLayout());
		this.add(panelMap, BorderLayout.EAST);
		this.add(panelCf,BorderLayout.WEST);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);		
	}
	
	
	public void changePhase(int phase) {
		switch(phase) {
		case 0:
			panelCf.add(phase0);
			Controller.phase = phase;
			break;
		case 1:
			panelCf.remove(phase0);
			panelCf.remove(phase4);
			panelCf.revalidate();
			//controller.updatePhase();
			panelCf.add(phase1);
			Controller.phase = phase;
			break;
		case 2:
			panelCf.remove(phase1);
			panelCf.revalidate();
			//controller.updatePhase();
			panelCf.add(phase2);
			Controller.phase = phase;
			break;
		case 3:
			panelCf.remove(phase2);
			panelCf.revalidate();
			//controller.updatePhase();
			panelCf.add(phase3);
			Controller.phase = phase;
			break;
		case 4:
			//TODO
			panelMap.remove(mapPanel);
			panelCf.remove(phase3);
			panelMap.revalidate();
			panelCf.revalidate();

			//controller.updatePhase();

			panelMap.add(drawCards);
			panelCf.add(phase4);
			Controller.phase = phase;
			break;
		}
	}
	
	
}