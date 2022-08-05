package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	private JLabel controlBar;
	private JLabel anzeige;
	private ImageIcon image;
	
	
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
	
	
	private GridLayout gridlayout = new GridLayout(2, 1);
	
	
	public RiskGUI() {
		
		//this.setLayout(null);
		//this.getContentPane().setBackground(Color.blue.brighter());
		
		this.setSize(300, 1000);
		this.setLocation(-10,-35);
		
		
		image = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		controlBar = new JLabel(image);
		this.add(controlBar);
		
		menuBar = new JMenuBar();
		menu = new JMenu("M");
		speichern = new JMenuItem("Speichern");
		beenden = new JMenuItem("Beenden");
		menuBar.setBackground(Color.lightGray);
		menuBar.setBounds(0,0,50,30);
		menu.add(speichern);
		menu.add(beenden);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		textAreaPlayerRequest = new JTextArea();
		Color myColor = new Color(239,222,176);
		textAreaPlayerRequest.setBounds(40, 30, 200, 40);
		textAreaPlayerRequest.setBackground(myColor);
		controlBar.add(textAreaPlayerRequest);
		
		button1 = new JButton("Knopf One");
		button1.setBounds(90, 100, 100, 20);
		button1.setBackground(Color.cyan.darker());
		controlBar.add(button1);
		
		button2 = new JButton("Knopf Two");
		button2.setBounds(90, 160, 100, 20);
		button2.setBackground(Color.cyan.darker());
		controlBar.add(button2);
		
		button3 = new JButton("Knopf Three");
		button3.setBounds(90, 220, 100, 20);
		button3.setBackground(Color.cyan.darker());
		controlBar.add(button3);
		
		button4 = new JButton("Knopf Four");
		button4.setBounds(90, 280, 100, 20);
		//button4.setBackground(Color.cyan.darker());
		button4.setIcon(image);
		JLabel button4Text = new JLabel ("Hallo");
		button4.add(button4Text);
		controlBar.add(button4);
		
		textAreaPlayerInformation = new JTextArea();
		textAreaPlayerInformation.setBounds(30, 500, 220, 200);
		textAreaPlayerInformation.setBackground(Color.RED.darker());
		controlBar.add(textAreaPlayerInformation);
		
		anzeige = new JLabel("Anzeige");
		anzeige.setBounds(0, 0, 100, 15);
		controlBar.add(anzeige);
		
		//this.setLayout(gridlayout);
		
		/*panel1 = new JPanel();
		panel2 = new JPanel();
				
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		//panel1.setPreferredSize(new Dimension(300,20));
				
				
		*/
		
		
		
		/*panel2.add(controlBar);
		this.add(panel1);
		this.add(panel2);*/
	
	}
	
	
}
