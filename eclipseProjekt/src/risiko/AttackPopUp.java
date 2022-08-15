package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class AttackPopUp {
	
	public JDialog attackPopUp;
	private JPanel attackPanelImage;
	private JPanel attackPanelButton;
	private JButton closeAttack;
	private Color buttonColor;
	private ResourcesGUI resource;
	private Controller controller;
	private ImageIcon battleIcon;
	private JLabel imageLabel;
	private  Clip clip;
	private File warDrums;
	
	private JLabel attackerLabel;
	private JLabel defenderLabel;
	private JLabel attackerDice1;
	private JLabel attackerDice2;
	private JLabel attackerDice3;
	private JLabel defenderDice1;
	private JLabel defenderDice2;
	private JLabel attackerCasualties;
	private JLabel defenderCasualties;
	private JLabel attackerCasualtiesCount;
	private JLabel defenderCasualtiesCount;
	
	private ImageIcon diceIcon2;
	private ImageIcon coatIcon;
	private ImageIcon diceIcon;
	
	private ArrayList<JLabel> diceList1;
	private ArrayList<JLabel> diceList2;
	
	public AttackPopUp (Frame attackFrame, Controller controller, ArrayList<Integer[]> dice) {
		
		buttonColor = new Color(239, 228, 176);
		resource = new ResourcesGUI();
		
		this.diceList1 = new ArrayList<>();
		this.diceList2 = new ArrayList<>();	
		
		//coatIcon = resource.getCoatIcon(controller, 2, 0);
		diceIcon2 = new ImageIcon("assets\\dice1.png");
		
		battleIcon = new ImageIcon("assets\\Richard_Caton_Woodville's_The_Battle_of_Towton.jpg");	
		imageLabel = new JLabel (battleIcon);
		imageLabel.setBounds(0, 0, 977, 640);
		
		attackPopUp = new JDialog(attackFrame, true);
		attackPopUp.setBounds(50,50,977,680);
		attackPopUp.setLayout(new BorderLayout());
		attackPanelImage = new JPanel();
		attackPanelButton = new JPanel();
		
		attackPanelImage.setBounds(0, 0, 977, 630);
		attackPanelImage.setBackground(buttonColor);
		attackPanelImage.setBorder(BorderFactory.createLineBorder(Color.black));
		attackPanelImage.setLayout(new BorderLayout());
		
		attackPanelButton.setBounds(0, 630, 977, 50);
		attackPanelButton.setBackground(buttonColor);
		attackPanelButton.setBorder(BorderFactory.createLineBorder(Color.black));
		
		attackerLabel = new JLabel(resource.getCoatIcon(controller, 2, controller.game.getPlayers().indexOf(controller.getPlayerObject())));
		attackerLabel.setBounds(360, 95, 80, 80);	
		attackerLabel.setBackground(buttonColor);
		attackerLabel.setOpaque(false);
		attackPanelImage.add(attackerLabel);
		
		attackerDice1 = new JLabel();
		attackerDice1.setBounds(360, 220, 80, 80);	
		attackerDice1.setBackground(buttonColor);
		attackerDice1.setOpaque(false);
		attackPanelImage.add(attackerDice1);
		diceList1.add(attackerDice1);
		
		attackerDice2 = new JLabel();
		attackerDice2.setBounds(360, 325, 80, 80);	
		attackerDice2.setBackground(buttonColor);
		attackerDice2.setOpaque(false);
		attackPanelImage.add(attackerDice2);
		diceList1.add(attackerDice2);
		
		attackerDice3 = new JLabel();
		attackerDice3.setBounds(360, 430, 80, 80);	
		attackerDice3.setBackground(buttonColor);
		attackerDice3.setOpaque(false);
		attackPanelImage.add(attackerDice3);
		diceList1.add(attackerDice3);
		
		attackerCasualties = new JLabel("Angreifer Verluste", SwingConstants.CENTER);
		attackerCasualties.setBounds(80, 220, 200, 30);	
		attackerCasualties.setBackground(buttonColor);
		attackerCasualties.setOpaque(true);
		attackerCasualties.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		attackPanelImage.add(attackerCasualties);
		
		attackerCasualtiesCount = new JLabel(Integer.toString(dice.get(2)[0]) , SwingConstants.CENTER);
		attackerCasualtiesCount.setBounds(150, 280, 60, 50);	
		attackerCasualtiesCount.setBackground(buttonColor);
		attackerCasualtiesCount.setOpaque(true);
		attackerCasualtiesCount.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 35));
		attackPanelImage.add(attackerCasualtiesCount);
		
		defenderLabel = new JLabel(resource.getCoatIcon(controller, 2, controller.game.getPlayers().indexOf(controller.activeTerritory2.getOccupier())));
		defenderLabel.setBounds(540, 95, 80, 80);	
		defenderLabel.setBackground(buttonColor);
		defenderLabel.setOpaque(false);
		attackPanelImage.add(defenderLabel);
		
		defenderDice1 = new JLabel();
		defenderDice1.setBounds(540, 220, 80, 80);	
		defenderDice1.setBackground(buttonColor);
		defenderDice1.setOpaque(true);
		attackPanelImage.add(defenderDice1);
		diceList2.add(defenderDice1);
		
		defenderDice2 = new JLabel();
		defenderDice2.setBounds(540, 325, 80, 80);	
		defenderDice2.setBackground(buttonColor);
		defenderDice2.setOpaque(false);
		attackPanelImage.add(defenderDice2);
		diceList2.add(defenderDice2);
		
		defenderCasualties = new JLabel("Verteidiger Verluste", SwingConstants.CENTER);
		defenderCasualties.setBounds(700, 220, 200, 30);	
		defenderCasualties.setBackground(buttonColor);
		defenderCasualties.setOpaque(true);
		defenderCasualties.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		attackPanelImage.add(defenderCasualties);
		
		defenderCasualtiesCount = new JLabel(Integer.toString(dice.get(2)[1]), SwingConstants.CENTER);
		defenderCasualtiesCount.setBounds(770, 280, 60, 50);	
		defenderCasualtiesCount.setBackground(buttonColor);
		defenderCasualtiesCount.setOpaque(true);
		defenderCasualtiesCount.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 35));
		attackPanelImage.add(defenderCasualtiesCount);
		
		closeAttack = new JButton("Ok", new ImageIcon("assets\\OldPaper2.png"));
		closeAttack.setPreferredSize(new Dimension(60,30));
		closeAttack.setHorizontalTextPosition(SwingConstants.CENTER);
		closeAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		closeAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				attackPopUp.dispose();	
			}
		});
		getMusic();
		
		displayDice(dice, resource.getDiceImages());
       
		attackPanelImage.add(imageLabel);
		attackPanelButton.add(closeAttack);
		attackPopUp.add(attackPanelImage);
		attackPopUp.add(attackPanelButton);
		attackPopUp.add(attackPanelImage, BorderLayout.CENTER);
		attackPopUp.add(attackPanelButton, BorderLayout.SOUTH);
		attackPopUp.setUndecorated(true);
		attackPopUp.setVisible(true);		
	}
	
	public void displayDice(ArrayList<Integer[]> dice, Map<Integer, ImageIcon> diceImages) {
		
		
		    for(int j = 0; j < dice.get(0).length ; j++) {
		    	diceList1.get(j).setIcon(diceImages.get(dice.get(0)[j]));
		    }
		    for(int j = 0; j < dice.get(1).length; j++) {
		    	diceList2.get(j).setIcon(diceImages.get(dice.get(1)[j]));
		    }
		
	}
	
	public void getMusic(){
	    warDrums = new File("assets\\mixkit-drums-of-war-call-2780.wav");

	    try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(warDrums));
	        clip.start();
	    } 
	    catch (Exception e){
	        e.printStackTrace();
	    }
	}

	
}