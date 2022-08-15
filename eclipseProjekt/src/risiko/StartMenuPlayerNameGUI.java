package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StartMenuPlayerNameGUI extends JPanel implements ActionListener {
	
	private JPanel panelStartMenu;
	private JLabel labelImage;
	
	private JButton verlassen;
	private JLabel playerNameRequest;
	private JTextField playerNameInput;
	private JButton confirmPlayerName;
	
	private ImageIcon origBackgroundImage;
	private Dimension screenSize;
	private Icon buttonIcon;
	private Color buttonColor;
	private int playerCount;
	private int playerNumber = 1;
	
	private ArrayList<String> playerNames;
	private IntroGUI introGUI;

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount - 1;
	}
	
	public StartMenuPlayerNameGUI(IntroGUI introGUI){
		
		this.introGUI = introGUI;
		this.playerNames = new ArrayList<String>();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		
		origBackgroundImage = new ImageIcon("assets\\La_Rendición_de_Granada_-_Pradilla.jpg");
		Image backgroundImage = origBackgroundImage.getImage();
		Image modBackgroundImage = backgroundImage.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH);
		origBackgroundImage = new ImageIcon(modBackgroundImage);
		
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		buttonColor = new Color(239, 228, 176);
		
		panelStartMenu = new JPanel(new BorderLayout());
		panelStartMenu.setBounds(0,0,screenSize.width,screenSize.height);
		panelStartMenu.setLayout(new BorderLayout());
		
		verlassen = new JButton("Spiel verlassen", buttonIcon);
		verlassen.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*610)/768, 
				(screenSize.width * 320)/1366, (screenSize.height*40)/768);
		verlassen.setHorizontalTextPosition(SwingConstants.CENTER);
		verlassen.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
		verlassen.addActionListener(this);
		panelStartMenu.add(verlassen);
		
		playerNameRequest = new JLabel("Name: Spieler " + playerNumber,SwingConstants.CENTER);
		playerNameRequest.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*215)/768, 
				(screenSize.width * 320)/1366, (screenSize.height*40)/768);
		playerNameRequest.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
		playerNameRequest.setBackground(buttonColor);
		playerNameRequest.setOpaque(true);
		panelStartMenu.add(playerNameRequest);
		
		playerNameInput = new JTextField();
		playerNameInput.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*270)/768, 
				(screenSize.width * 320)/1366, (screenSize.height*40)/768);
		playerNameInput.setHorizontalAlignment(SwingConstants.LEFT);
		playerNameInput.setBackground(buttonColor);
		playerNameInput.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
		playerNameInput.setCaretPosition(0);
		playerNameInput.setEditable(true);
		panelStartMenu.add(playerNameInput);
		
		confirmPlayerName = new JButton("Name bestätigen", buttonIcon);
		confirmPlayerName.setBounds((screenSize.width - (screenSize.width * 620)/1366)/2,(screenSize.height*350)/768, 
				(screenSize.width * 320)/1366, (screenSize.height*40)/768);
		confirmPlayerName.setPreferredSize(new Dimension((screenSize.width * 320)/1366, (screenSize.height*40)/768));
		confirmPlayerName.setHorizontalTextPosition(SwingConstants.CENTER);
		confirmPlayerName.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
		confirmPlayerName.addActionListener(this);
		panelStartMenu.add(confirmPlayerName);
		
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
		else if(e.getSource() == this.confirmPlayerName)
			System.out.println(playerCount);
			if(!playerNameInput.getText().isEmpty()) {
				
				playerNumber += 1;
				playerCount -= 1; 
				playerNameRequest.setText("Name: Spieler " + playerNumber);
				this.playerNames.add(playerNameInput.getText());
				playerNameInput.setText("");
				playerNameInput.requestFocusInWindow();
			}
			else if(playerNameInput.getText().isEmpty()) {
				
				playerNameRequest.setText("Name benötigt");
				playerNameInput.requestFocusInWindow();
			}
			if(playerCount < 0) {
				introGUI.controller.startGame(introGUI.getPlayerCount(), playerNames);
			}	
	}
	
	public void getCusor() {
		
		playerNameInput.requestFocusInWindow();
	}
	public ArrayList<String> getPlayerNames(){
		return this.playerNames;
	}
	                                         
}
