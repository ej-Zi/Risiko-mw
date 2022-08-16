package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VictoryPopUp{
	private Dimension screenSize;
	private JDialog popUp;
	private JButton closeBtn;

	public VictoryPopUp(Controller controller) {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		popUp = new JDialog(controller.getGui(), true);
		popUp.setSize(screenSize.width/3, screenSize.width/3);
		popUp.setLocation(screenSize.width/6, screenSize.height/8);
		popUp.setUndecorated(true);
		popUp.setLocationRelativeTo(controller.getGui());
		ImageIcon coa = scaleIcon(controller.getGui().mapPanel.coa
				.get(controller.getPlayerAtTurn()),popUp.getWidth()/3, popUp.getHeight()/2);
		ImageIcon bgIcon = new ImageIcon("assets\\VictoryBg.jpg");
		bgIcon = scaleIcon(bgIcon, screenSize.width/3, screenSize.width/3);
		JPanel upperPane = new JPanel();
		JPanel lowerPane = new JPanel();
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());		
		
		popUp.setLayout(new BorderLayout());
		upperPane.setLayout(new BorderLayout());
		 
		JLabel bgLabel = new JLabel(bgIcon);
		JLabel textLabel = new JLabel(controller.getPlayerObject().getName() 
				+ " hat gewonnen!", SwingConstants.CENTER);
		JLabel coaLabel = new JLabel(coa);
		
		bgLabel.setOpaque(false);
		
		closeBtn = new JButton("Spiel beenden");
		closeBtn.setFont(new Font("Algerian", Font.ROMAN_BASELINE, 16));
		ImageIcon btnBg = scaleIcon(new ImageIcon("assets\\OldPaper2.png"), 
				popUp.getWidth()/4, popUp.getHeight()/10);
		
		closeBtn.setHorizontalTextPosition(JButton.CENTER);
		closeBtn.setContentAreaFilled(false);
		closeBtn.setIcon(btnBg);
		closeBtn.setSize(popUp.getWidth()/4, popUp.getHeight()/10);
		closeBtn.setBorder(BorderFactory.createLineBorder(Color.black));
		closeBtn.addActionListener(e -> close());
		closeBtn.setVisible(true);
		closeBtn.setLocation((popUp.getWidth()-closeBtn.getWidth())/2, popUp.getWidth()/2);
		closeBtn.setOpaque(false);
		closeBtn.addMouseListener(new MouseAdapter() {
			 public void mouseEntered(MouseEvent me) {
					closeBtn.setBorder(BorderFactory.createLineBorder(Color.gray));
		         }
		         public void mouseExited(MouseEvent me) {
					closeBtn.setBorder(BorderFactory.createLineBorder(Color.black));
		         }
		});
		
		coaLabel.setOpaque(false);
		coaLabel.setBounds((popUp.getWidth()-coa.getIconWidth())/2, popUp.getHeight()/3, 
				coa.getIconWidth(), coa.getIconHeight());
		
		textLabel.setFont(new Font("Algerian", Font.ROMAN_BASELINE, 25));
		textLabel.setSize(new Dimension(popUp.getWidth(), popUp.getHeight()/5));
		textLabel.setOpaque(false);
		textLabel.setForeground(Color.white);
		
		upperPane.add(coaLabel);
		upperPane.add(textLabel);
		upperPane.add(bgLabel);
		
		lowerPane.add(closeBtn);
		lowerPane.setBackground(new Color(239, 228, 176));
		
		contentPane.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(upperPane, BorderLayout.CENTER);
		contentPane.add(lowerPane, BorderLayout.SOUTH);
		getMusic();
		popUp.add(contentPane);
		popUp.pack();
		popUp.setVisible(true);
	}
	
	public void close() {
		System.exit(0);
	}
	public ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image modImage = image.getScaledInstance
				(width, height, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(modImage);
		return icon;
	}
	
	public void getMusic(){
	    File fanfare = new File("assets\\victory.wav");

	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(fanfare));
	        clip.start();
	    } 
	    catch (Exception e){
	        e.printStackTrace();
	    }
	}
}
