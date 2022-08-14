package risiko;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class ResourcesGUI implements ActionListener {

	protected static final Frame helpFrame = null;
	private Map<Integer,String> helpText = new HashMap<>();
	private Map<Integer, String> playerCoat = new HashMap<>();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon controlfieldIcon;
	private ImageIcon coatIcon;
	private ImageIcon menuIcon;
	private Image controlfieldImage;
	private Image modControlfieldImage;
	private Image menuImage;
	private Image modMenuImage;
	private Image coatImage;
	private Image modCoatImage;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem beenden;
	private JButton help;
	
	Color buttonColor = new Color(239, 228, 176);
	
	public ResourcesGUI() {
		
		this.createHelpText();
		this.addPlayerCoat();
	}

	public Map<Integer, String> getPlayerCoat() {
		
		return playerCoat;
	}

	public void setPlayerCoat(Map<Integer, String> playerCoat) {
		
		this.playerCoat = playerCoat;
	}

	public Map<Integer, String> getHelpText() {
		
		return helpText;
	}

	public void setHelpText(Map<Integer, String> helpText) {
		
		this.helpText = helpText;
	}

	public void createHelpText() {
	
		helpText.put(0, "Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen möchten. Drücken Sie danach den Knopf 'Einheit setzen'.");
		
		helpText.put(1, "Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen möchten. Bestimmen Sie im Anschluss mit dem Zähler die Anzahl der Einheiten, "
						+ "die Sie auf das Gebiet setzen möchten und drücken Sie danach 'Armeen setzen'.");
		
		helpText.put(2, "Klicken Sie auf das Gebiet, "
						+ "Von dem aus Sie angreifen möchten und danach auf das Gebiet, das Sie angreifen möchten. "
						+ "Bestimmen Sie mit dem Zähler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen möchten und drücken Sie danach 'Gegner angreifen'. "
						+ "Wenn Sie nicht angreifen möchten, drücken Sie 'Phase beenden'.");
		
		helpText.put(3, "Klicken Sie auf das Gebiet, dessen Armeen "
						+ "Sie Verschieben möchten und danach auf das Gebiet, wohin Sie die Armeen senden möchten. "
						+ "Bestimmen Sie danach mit dem Zähler die Anzahl der Einheiten, "
						+ "die Sie bewegen möchten und drücken Sie im Anschluss 'Armeen bewegen'. "
						+ "Wenn Sie keine Armeen verschieben möchten, drücken Sie 'Phase beenden'.");
	
	}
	
	public void addPlayerCoat() {
	
		playerCoat.put(0, "assets\\coa1.png");
		playerCoat.put(1, "assets\\coa2.png");
		playerCoat.put(2, "assets\\coa3.png");
		playerCoat.put(3, "assets\\coa4.png");
		playerCoat.put(4, "assets\\coa5.png");
	
	}
	
	public ImageIcon getControlfieldIcon() {
		
		controlfieldIcon = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		controlfieldImage = controlfieldIcon.getImage();
		modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);	
		
		return controlfieldIcon;
	}
	
	public ImageIcon getMenuIcon() {
		
		menuIcon = new ImageIcon("assets\\Floris_Claesz._van_Dyck_001.jpg");
		menuImage = menuIcon.getImage();
		modMenuImage = menuImage.getScaledInstance(2048*1/(screenSize.height/25), 1255*1/(screenSize.height/25), java.awt.Image.SCALE_SMOOTH);
		menuIcon = new ImageIcon(modMenuImage);
		
		return menuIcon;
	}
	
	public ImageIcon getCoatIcon(Controller controller) {
		
		coatIcon = new ImageIcon(getPlayerCoat().get(controller.getPlayerAtTurn()));
		coatImage = coatIcon.getImage();
		modCoatImage = coatImage.getScaledInstance(303*1/(screenSize.height/59), 448*1/(screenSize.height/59), java.awt.Image.SCALE_SMOOTH);
		coatIcon = new ImageIcon(modCoatImage);
	
		return coatIcon;
	}
	
	public JMenuBar getMenu() {
		
		menuBar = new JMenuBar();
		menu = new JMenu();
		beenden = new JMenuItem("Beenden");
		beenden.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 14 / 768));
		beenden.setBackground(buttonColor);
		beenden.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);	
			}	
		});
		menuBar.setBounds(-10, -1, ((screenSize.width*2/10) * 70)/273, (screenSize.height*45)/768);
		menu.setIcon(getMenuIcon());
		menu.add(beenden);
		menuBar.add(menu);	
		
		return menuBar;
	}

	public JButton getHelpButton(int phase) {
		
		help = new JButton("?", new ImageIcon("assets\\OldPaper2.png"));
		help.setBounds(((screenSize.width*2/10) * 60)/273, -1, ((screenSize.width*2/10) * 45)/273, (screenSize.height*45)/768);
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 24 / 768));
		help.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				
				new HelpPopUp(helpFrame, phase);
			}	
		});
		return help;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
