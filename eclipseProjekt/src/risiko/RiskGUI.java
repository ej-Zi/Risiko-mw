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
	
	private Phase0 phase0;
	private Phase2 phase2;
	private Phase3 phase3;
	
	
	private Controller controller;
	
	public RiskGUI() {
		
		controller = new Controller();
		
		//editing panels
		panelMap = new JPanel();
		panelCf = new JPanel();
		panelMap.setLayout(new BorderLayout());
		panelMap.add(new MapPanel(controller));
		panelCf.setLayout(new BorderLayout());
		
		phase0 = new Phase0(this);
		phase2 = new Phase2(this);
		//phase3 = new Phase3(this);
		
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
			break;
		case 1:
			
		case 2:
			panelCf.remove(phase0);
			panelCf.add(phase2);
			break;
		case 3:
			panelCf.remove(phase2);
			panelCf.add(phase3);
			break;
		}
	}
	
	
}