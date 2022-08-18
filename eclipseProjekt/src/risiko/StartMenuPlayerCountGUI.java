package risiko;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartMenuPlayerCountGUI extends JPanel implements ActionListener{

	private IntroGUI introGUI;
	private JPanel panelStartMenu;
	private JLabel labelImage;
	private JButton playerTwo;
	private JButton playerThree;
	private JButton playerFour;
	private JButton playerFive;
	private JButton starten;
	private JButton verlassen;
	
	private ImageIcon origBackgroundImage;
	private Dimension screenSize;
	private Icon buttonIcon;
	private int playerCount;
	
	public StartMenuPlayerCountGUI(IntroGUI introGUI){
				
			this.introGUI = introGUI;
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
			
			origBackgroundImage = new ImageIcon("assets\\La_Rendicion_de_Granada_-_Pradilla.jpg");
			Image backgroundImage = origBackgroundImage.getImage();
			Image modBackgroundImage = backgroundImage.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH);
			origBackgroundImage = new ImageIcon(modBackgroundImage);
			
			buttonIcon = new ImageIcon("assets\\OldPaper2.png");
			
			panelStartMenu = new JPanel(new BorderLayout());
			panelStartMenu.setBounds(0,0,screenSize.width,screenSize.height);
			panelStartMenu.setLayout(new BorderLayout());
			
			playerTwo = new JButton("2 Spieler", buttonIcon);
			playerTwo.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*90)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			playerTwo.setHorizontalTextPosition(SwingConstants.CENTER);
			playerTwo.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			playerTwo.addActionListener(this);
			panelStartMenu.add(playerTwo);
			
			playerThree = new JButton("3 Spieler", buttonIcon);
			playerThree.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*160)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			playerThree.setHorizontalTextPosition(SwingConstants.CENTER);
			playerThree.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			playerThree.addActionListener(this);
			panelStartMenu.add(playerThree);
				
			playerFour = new JButton("4 Spieler", buttonIcon);
			playerFour.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*230)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			playerFour.setHorizontalTextPosition(SwingConstants.CENTER);
			playerFour.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			playerFour.addActionListener(this);
			panelStartMenu.add(playerFour);
			
			playerFive = new JButton("5 Spieler", buttonIcon);
			playerFive.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*300)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			playerFive.setHorizontalTextPosition(SwingConstants.CENTER);
			playerFive.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			playerFive.addActionListener(this);
			panelStartMenu.add(playerFive);
			
			starten = new JButton("Spiel starten", buttonIcon);
			starten.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*400)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			starten.setHorizontalTextPosition(SwingConstants.CENTER);
			starten.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			starten.setEnabled(false);
			starten.addActionListener(this);
			panelStartMenu.add(starten);
			
			verlassen = new JButton("Spiel verlassen", buttonIcon);
			verlassen.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*610)/768, 
					(screenSize.width * 320)/1366, (screenSize.height*40)/768);
			verlassen.setHorizontalTextPosition(SwingConstants.CENTER);
			verlassen.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
			verlassen.addActionListener(this);
			panelStartMenu.add(verlassen);
			
			labelImage = new JLabel(origBackgroundImage);
			
			panelStartMenu.add(labelImage);
			this.setPreferredSize(new Dimension(screenSize.width,screenSize.height));
			this.setLayout(new BorderLayout());
			this.add(panelStartMenu);
			this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if(e.getSource() == this.verlassen) {
			
			System.exit(0);
		}
		else if(e.getSource() == this.playerTwo) {
			
			starten.setEnabled(true);
			playerCount = 2;
		}
		else if(e.getSource() == this.playerThree) {
			
			starten.setEnabled(true);
			playerCount = 3;
		}
		else if(e.getSource() == this.playerFour) {
			
			starten.setEnabled(true);
			playerCount = 4;
		}
		else if(e.getSource() == this.playerFive) {
			
			starten.setEnabled(true);
			playerCount = 5;
		}
		else if(e.getSource() == this.starten) {
			
			introGUI.setPlayerCount(playerCount);
			introGUI.changeStartMenuPanel(1);	
		}
	}
}
