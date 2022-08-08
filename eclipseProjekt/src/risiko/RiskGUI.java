package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RiskGUI extends JFrame {
	
	//private JFrame jframe = new JFrame();
	private JPanel panelMap;
	private JPanel panelCf;
	private JLabel mapLabel;	
	
	private JLabel controlfieldLabel;
	private JLabel anzeige;
	private ImageIcon controlfieldIcon;
	
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem speichern;
	private JMenuItem beenden;
	
	private JTextArea textAreaPlayerRequest;
	private JTextArea textAreaPlayerInformation;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	
	
	
	//private JPanel panel1;
	//private JPanel panel2;
	
	
	//private GridLayout gridlayout = new GridLayout(2, 1);
	
	
	public RiskGUI() {
		
		
		//Bild einlesen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		ImageIcon mapIcon = new ImageIcon("assets\\risk-map.jpg");
		Image map = mapIcon.getImage();
		Image modmap = map.getScaledInstance(screenSize.width*8/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		mapIcon = new ImageIcon(modmap);
		

		controlfieldIcon = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		Image controlfieldImage = controlfieldIcon.getImage();
		Image modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);

		
		panelMap = new JPanel();
		panelCf = new JPanel();
		
		mapLabel = new JLabel(mapIcon);
		//mapLabel.setBounds(0, 0, screenSize.width*8/10, screenSize.height);
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		
		panelMap.setPreferredSize(new Dimension(screenSize.width*8/10,screenSize.height));
		panelCf.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));


		panelCf.setLayout(new BorderLayout());
		
		
		menuBar = new JMenuBar();
		menu = new JMenu("M");
		speichern = new JMenuItem("Speichern");
		beenden = new JMenuItem("Beenden");
		menuBar.setBackground(Color.lightGray);
		menuBar.setBounds(0,0,screenSize.width,30);
		menu.add(speichern);
		menu.add(beenden);
		menuBar.add(menu);
		
		panelCf.add(menuBar, BorderLayout.NORTH);
		
		panelCf.setLayout(null);
		
		textAreaPlayerRequest = new JTextArea();
		Color myColor = new Color(239,222,176);
		textAreaPlayerRequest.setBounds(40, 30, 200, 40);
		textAreaPlayerRequest.setBackground(myColor);
		panelCf.add(textAreaPlayerRequest);
		
		button1 = new JButton("Knopf One");
		button1.setBounds(90, 100, 100, 20);
		button1.setBackground(Color.cyan.darker());
		panelCf.add(button1);
		
		button2 = new JButton("Knopf Two");
		button2.setBounds(90, 160, 100, 20);
		button2.setBackground(Color.cyan.darker());
		panelCf.add(button2);
		
		button3 = new JButton("Knopf Three");
		button3.setBounds(90, 220, 100, 20);
		button3.setBackground(Color.cyan.darker());
		panelCf.add(button3);
		
		button4 = new JButton("Knopf Four");
		button4.setBounds(90, 280, 100, 20);
		//button4.setBackground(Color.cyan.darker());
		button4.setIcon(mapIcon);
		JLabel button4Text = new JLabel ("Hallo");
		button4.add(button4Text);
		panelCf.add(button4);
		
		textAreaPlayerInformation = new JTextArea();
		textAreaPlayerInformation.setBounds(30, 500, 220, 200);
		textAreaPlayerInformation.setBackground(Color.RED.darker());
		panelCf.add(textAreaPlayerInformation);
		
		anzeige = new JLabel("Anzeige");
		anzeige.setBounds(0, 0, 100, 15);
		panelCf.add(anzeige);
		

		//Bild auf Panel
		panelMap.add(mapLabel);
		panelCf.add(controlfieldLabel);
		
		//Panel auf JFrame
		this.setLayout(new BorderLayout());
		this.add(panelMap, BorderLayout.EAST);
		this.add(panelCf,BorderLayout.WEST);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	
}
