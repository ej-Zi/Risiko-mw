package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ConquestPopUp {
	
	private JDialog occupationPopUp;
	private JPanel occupationPanelImage;
	private JPanel occupationPanelButton;
	private JButton closeConquest;
	private JLabel conqueredTerritoryLabel;
	private ImageIcon conquestIcon;
	
	private JLabel text;
	private JLabel conquererLabel;
	private JLabel imageLabel;
	
	private Resources resource;
	private Color buttonColor;
	private Dimension screenSize;
	
	private Clip conquestHorn;
	private AudioInputStream conquestStream;
	
	
	
	public ConquestPopUp (Frame conquestFrame, Controller controller) {
		
		getMusic();
		
		buttonColor = new Color(239, 228, 176);
		resource = new Resources();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		conquestIcon = new ImageIcon("assets\\OccupationTerritory.jpg");	
		imageLabel = new JLabel (conquestIcon);
		imageLabel.setBounds(0, -3, 1000, 693);
		
		occupationPopUp = new JDialog(conquestFrame, true);
		occupationPopUp.setBounds((screenSize.width* 183)/1366 + ((screenSize.width * 1000)/1366 - 1000)/2, 
				(screenSize.height * 19)/768 + ((screenSize.height * 730)/768 - 730)/2, 1000, 730);
		occupationPopUp.setLayout(new BorderLayout());
		occupationPanelImage = new JPanel();
		occupationPanelButton = new JPanel();
		
		occupationPanelImage.setBounds(0, 0, 1000, 680);
		occupationPanelImage.setBackground(buttonColor);
		occupationPanelImage.setBorder(BorderFactory.createLineBorder(Color.black));
		occupationPanelImage.setLayout(new BorderLayout());
		
		occupationPanelButton.setBounds(0, 680, 1000, 58);
		occupationPanelButton.setBackground(buttonColor);
		occupationPanelButton.setBorder(BorderFactory.createLineBorder(Color.black));
		
		conqueredTerritoryLabel = new JLabel(controller.activeTerritory2.getName(), SwingConstants.CENTER);
		conqueredTerritoryLabel.setBounds(200, 100, 600, 80);	
		conqueredTerritoryLabel.setBackground(buttonColor);
		conqueredTerritoryLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 60));
		conqueredTerritoryLabel.setOpaque(false);
		occupationPanelImage.add(conqueredTerritoryLabel);
		
		text = new JLabel("erobert durch", SwingConstants.CENTER);
		text.setBounds(300, 200, 400, 80);	
		text.setBackground(buttonColor);
		text.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 35));
		text.setOpaque(false);
		occupationPanelImage.add(text);
		
		conquererLabel = new JLabel(resource.getCoatIcon(controller, 3, controller.game.getPlayers().indexOf(controller.getPlayerObject())));
		conquererLabel.setBounds(424, 320, 152, 224 );	
		conquererLabel.setBackground(buttonColor);
		conquererLabel.setOpaque(false);
		occupationPanelImage.add(conquererLabel);
		
		closeConquest = new JButton("Ok", new ImageIcon("assets\\OldPaper2.png"));
		closeConquest.setPreferredSize(new Dimension(60,30));
		closeConquest.setHorizontalTextPosition(SwingConstants.CENTER);
		closeConquest.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		closeConquest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				stopMusic();
				occupationPopUp.dispose();	
			}
		});
       
		occupationPanelImage.add(imageLabel);
		occupationPanelButton.add(closeConquest);
		occupationPopUp.add(occupationPanelImage);
		occupationPopUp.add(occupationPanelButton);
		occupationPopUp.add(occupationPanelImage, BorderLayout.CENTER);
		occupationPopUp.add(occupationPanelButton, BorderLayout.SOUTH);
		occupationPopUp.setUndecorated(true);
		occupationPopUp.setVisible(true);		
	}
	
	public void getMusic(){
	    try {
	           	conquestStream = AudioSystem.getAudioInputStream(new File("assets\\mixkit-war-horn-ambience-2785.wav"));
	            conquestHorn = AudioSystem.getClip( );
	            conquestHorn.open(conquestStream);
	            conquestHorn.start( );    
	            }
	
	        catch(Exception e)  {
	            e.printStackTrace( );
	        }
	}
	
	public void stopMusic(){
	    conquestHorn.stop();
	 }
}