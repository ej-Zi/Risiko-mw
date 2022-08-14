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
	
	private JButton help;
	public JDialog attackPopUp;
	private JTextArea helpDisplay;
	private JPanel attackPanelImage;
	private JPanel attackPanelButton;
	private JScrollPane scrollHelpDisplay;
	private JButton closeAttack;
	private Color buttonColor;
	private ResourcesGUI resource;
	private Controller controller;
	private ImageIcon battleIcon;
	private Image battleImage;
	private Image modBattleImage;
	private JLabel imageLabel;
	
	
	public AttackPopUp () {
		
		buttonColor = new Color(239, 228, 176);
		resource = new ResourcesGUI();
				
		battleIcon = new ImageIcon("assets\\Richard_Caton_Woodville's_The_Battle_of_Towton.jpg");
		//battleImage = battleIcon.getImage();
		//modBattleImage = battleImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		//battleIcon = new ImageIcon(modBattleImage);	
		imageLabel = new JLabel (battleIcon);
		imageLabel.setBounds(0, 0, 977, 640);
		
		
		attackPopUp = new JDialog();
		attackPopUp.setBounds(50,50,977,680);
		attackPopUp.setLayout(new BorderLayout());
		attackPanelImage = new JPanel();
		attackPanelButton = new JPanel();
		
		attackPanelImage.setBounds(0, 0, 977, 630);
		attackPanelImage.setBackground(buttonColor);
		attackPanelImage.setBorder(BorderFactory.createLineBorder(Color.black));
		attackPanelImage.setLayout(new BorderLayout());
		attackPanelImage.add(imageLabel);
		attackPanelButton.setBounds(0,630,977,50);
		attackPanelButton.setBackground(buttonColor);
		attackPanelButton.setBorder(BorderFactory.createLineBorder(Color.black));
		
		/*helpDisplay = new JTextArea();
		helpDisplay.setBounds(1,1,248,160);
		helpDisplay.setBackground(buttonColor);
		helpDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		helpDisplay.setEditable(false);*
				
		helpDisplay.setText(resource.getHelpText().get(phase)); //wegen for-Schleife

		helpDisplay.setCaretPosition(0);
		helpDisplay.setLineWrap(true);
		helpDisplay.setWrapStyleWord(true);
		scrollHelpDisplay = new JScrollPane(helpDisplay);
		
		scrollHelpDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollHelpDisplay.setBounds(1,1,248,160);
		scrollHelpDisplay.getViewport().setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);
		attackPopUp.add(scrollHelpDisplay);*/
		
		closeAttack = new JButton("Ok", new ImageIcon("assets\\OldPaper2.png"));
		closeAttack.setPreferredSize(new Dimension(60,30));
		closeAttack.setHorizontalTextPosition(SwingConstants.CENTER);
		closeAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		closeAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attackPopUp.dispose();
				
			}
		});
		
		new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
		      public void run() {
		        try {
		          Clip clip = AudioSystem.getClip();
		          AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("assets\\mixkit-war-horn-ambience-2785.wav").getAbsoluteFile());
		          clip.open(inputStream);
		          clip.start(); 
		        } catch (Exception e) {
		          System.err.println(e.getMessage());
		        }
		      }
		    }).start();
		
		
		
		
		
		/*String soundName = "yourSound.wav";    
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets\\mixkit-war-horn-ambience-2785.wav").getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();*/
		
		
		attackPanelButton.add(closeAttack);
		attackPopUp.add(attackPanelImage);
		attackPopUp.add(attackPanelButton);
		attackPopUp.add(attackPanelImage, BorderLayout.CENTER);
		attackPopUp.add(attackPanelButton, BorderLayout.SOUTH);
		attackPopUp.setUndecorated(true);
		attackPopUp.setVisible(true);
		
	}
}
