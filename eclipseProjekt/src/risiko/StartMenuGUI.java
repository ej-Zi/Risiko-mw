package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartMenuGUI extends JFrame{

	private JPanel panelStartMenu;
	//private JPanel panelIntro;
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
	
	
	
	public StartMenuGUI(){
				
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
			
			origBackgroundImage = new ImageIcon("assets\\La_Rendición_de_Granada_-_Pradilla.jpg");
			Image backgroundImage = origBackgroundImage.getImage();
			Image modBackgroundImage = backgroundImage.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH);
			origBackgroundImage = new ImageIcon(modBackgroundImage);
			
			buttonIcon = new ImageIcon("assets\\OldPaper2.png");
			
			panelStartMenu = new JPanel();
			panelStartMenu.setBounds(0,0,screenSize.width,screenSize.height);
			panelStartMenu.setLayout(new BorderLayout());
			
			playerTwo = new JButton("2 Spieler", buttonIcon);
			playerTwo.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*90)/768, 320, 40);
			playerTwo.setHorizontalTextPosition(SwingConstants.CENTER);
			playerTwo.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(playerTwo);
			
			playerThree = new JButton("3 Spieler", buttonIcon);
			playerThree.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*160)/768, 320, 40);
			playerThree.setHorizontalTextPosition(SwingConstants.CENTER);
			playerThree.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(playerThree);
			
			playerFour = new JButton("4 Spieler", buttonIcon);
			playerFour.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*230)/768, 320, 40);
			playerFour.setHorizontalTextPosition(SwingConstants.CENTER);
			playerFour.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(playerFour);
			
			playerFive = new JButton("5 Spieler", buttonIcon);
			playerFive.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*300)/768, 320, 40);
			playerFive.setHorizontalTextPosition(SwingConstants.CENTER);
			playerFive.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(playerFive);
			
			starten = new JButton("Spiel starten", buttonIcon);
			starten.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*390)/768, 320, 40);
			starten.setHorizontalTextPosition(SwingConstants.CENTER);
			starten.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(starten);
			
			verlassen = new JButton("Spiel verlassen", buttonIcon);
			verlassen.setBounds((panelStartMenu.getWidth()-320)/2 - 150,(panelStartMenu.getHeight()*610)/768, 320, 40);
			verlassen.setHorizontalTextPosition(SwingConstants.CENTER);
			verlassen.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 30));
			panelStartMenu.add(verlassen);
			
			/*gameTitel = new JLabel("Risiko",SwingConstants.CENTER);
			gameTitel.setBounds((panelIntro.getWidth() - 300)/2,100,400,100);
			gameTitel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 100));
			//gameTitel.setForeground(labelColor);
			//gameTitel.setBackground(labelColor);
			gameTitel.setOpaque(false);	
			panelIntro.add(gameTitel);*/
			
			/*author = new JLabel("Zimmermann / Hübenthal / Mäder",SwingConstants.CENTER);
			author.setBounds(panelIntro.getWidth()-280, panelIntro.getHeight()-30, 280,30);
			author.setBackground(displayColor);
			author.setOpaque(true);
			author.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
			panelIntro.add(author);*/
			
			labelImage = new JLabel(origBackgroundImage);
			
			panelStartMenu.add(labelImage);
			this.add(panelStartMenu);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setUndecorated(true);
			this.setVisible(true);
		

	}
}
