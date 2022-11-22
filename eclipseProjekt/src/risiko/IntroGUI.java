package risiko;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class IntroGUI  extends Thread {

	private JFrame jFrame;
	private StartMenuPlayerCountGUI startMenu;
	private StartMenuPlayerNameGUI playerNameMenu;
	private JPanel basePanel;
	private JPanel introPanel;
	private JLabel labelImage;
	private JLabel gameTitel;
	private JLabel author;
	
	private ImageIcon origBackgroundImage;
	private Dimension screenSize;
	private Color displayColor;
	private int playerCount;
	
	public Controller controller;
	
	public IntroGUI(Controller controller) {
		
		this.controller = controller;
		this.playerCount = -1;
		this.startMenu = new StartMenuPlayerCountGUI(this);
		this.playerNameMenu = new StartMenuPlayerNameGUI(this);
		
		jFrame = new JFrame();
		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		startMenu = new StartMenuPlayerCountGUI(this);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		origBackgroundImage = new ImageIcon("assets\\La_Rendicion_de_Granada_-_Pradilla.jpg");
		Image backgroundImage = origBackgroundImage.getImage();
		Image modBackgroundImage = backgroundImage.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH);
		origBackgroundImage = new ImageIcon(modBackgroundImage);
		
		displayColor = new Color(239, 228, 176);
		introPanel = new JPanel();
		introPanel.setBounds(0,0,screenSize.width,screenSize.height);
		introPanel.setLayout(new BorderLayout());
		
		gameTitel = new JLabel("Risiko",SwingConstants.CENTER);
		gameTitel.setBounds((screenSize.width - (screenSize.width * 500)/1366)/2,(screenSize.height*100)/768,
				(screenSize.width * 400)/1366,(screenSize.height*110)/768);
		gameTitel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 100 / 768));
		gameTitel.setOpaque(false);	
		introPanel.add(gameTitel);
		
		author = new JLabel("Zimmermann | H�benthal | M�der",SwingConstants.CENTER);
		author.setBounds(screenSize.width-(screenSize.width * 280)/1366, screenSize.height - (screenSize.height*30)/768,
				(screenSize.width * 280)/1366,(screenSize.height*30)/768);
		author.setBackground(displayColor);
		author.setOpaque(true);
		author.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 14 / 768));
		introPanel.add(author);
		
		labelImage = new JLabel(origBackgroundImage);
		
		introPanel.add(labelImage);
		basePanel.add(introPanel);
		jFrame.add(basePanel);
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.setUndecorated(true);
		jFrame.setVisible(true);
		
		run();	
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			changeStartMenuPanel(0);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}	
	}

	public void changeStartMenuPanel(int startMenuPhase) {
		switch(startMenuPhase) {
		case 0:
			basePanel.remove(introPanel);
			basePanel.revalidate();
			basePanel.add(startMenu);
			break;
		case 1:
			basePanel.remove(startMenu);
			basePanel.revalidate();
			playerNameMenu.setPlayerCount(playerCount);
			basePanel.add(playerNameMenu);
			break;
	
		}
	}
	
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public int getPlayerCount() {
		return this.playerCount;
	}
	
	public JFrame getjframe() {
		return this.jFrame;
	}
	
}
