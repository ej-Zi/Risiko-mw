package risiko;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class IntroGUI extends JFrame {

	private JPanel panelIntro;
	private JLabel labelImage;
	private JLabel gameTitel;
	private JLabel author;
	
	private ImageIcon origBackgroundImage;
	private Dimension screenSize;
	private Color displayColor;
	
	
	
	public IntroGUI() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		origBackgroundImage = new ImageIcon("assets\\La_Rendición_de_Granada_-_Pradilla.jpg");
		Image backgroundImage = origBackgroundImage.getImage();
		Image modBackgroundImage = backgroundImage.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH);
		origBackgroundImage = new ImageIcon(modBackgroundImage);
		
		displayColor = new Color(239, 228, 176);
		panelIntro = new JPanel();
		panelIntro.setBounds(0,0,screenSize.width,screenSize.height);
		panelIntro.setLayout(new BorderLayout());
		
		gameTitel = new JLabel("Risiko",SwingConstants.CENTER);
		gameTitel.setBounds((panelIntro.getWidth() - 300)/2 - 100,(panelIntro.getHeight()*100)/768,400,110);
		gameTitel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 100));
		//gameTitel.setForeground(labelColor);
		//gameTitel.setBackground(labelColor);
		gameTitel.setOpaque(false);	
		panelIntro.add(gameTitel);
		
		author = new JLabel("Zimmermann | Hübenthal | Mäder",SwingConstants.CENTER);
		author.setBounds(panelIntro.getWidth()-280, panelIntro.getHeight()-30, 280,30);
		author.setBackground(displayColor);
		author.setOpaque(true);
		author.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		panelIntro.add(author);
		
		labelImage = new JLabel(origBackgroundImage);
		
		panelIntro.add(labelImage);
		this.add(panelIntro);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}

}
