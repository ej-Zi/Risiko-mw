package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RiskGUI extends JFrame {
	
	//private JFrame jframe = new JFrame();
	private JPanel panelMap;
	private JPanel panelCf;
	private JLabel mapLabel;	
	
	private JLabel controlfieldLabel;
	//private JLabel anzeige;
	private ImageIcon controlfieldIcon;
	private Icon buttonIcon;
	private ImageIcon greenIcon;
	private ImageIcon menuIcon;
	//private ImageIcon buttonImage;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem speichern;
	private JMenuItem beenden;
	
	private JLabel playerInformationBackground;
	//private JPanel playerInformationPanel;
	//private JLabel playerInformationLabel;
	
	
	
	//GebieteAnzeige
	private JScrollPane territoriesDisplay;
	private String [][]territoriesList;
	private JTable territoriesTable;
	private String [] territoriesTitel = {"Besetzte Gebiete"};
	private DefaultTableModel territoriesTableModel;
	private TableColumn territorriesColumn;
	private DefaultTableCellRenderer dtcr;
	
	//EinheitenAnzeige
	private JScrollPane unitsDisplay;
	private String[][]unitsList;
	private JTable unitsTable;
	private String[]unitsTitel = {"Einheiten Zur Verteilung"};
	
	private JLabel playerInformation;
	private JButton button4;
	
	private Color buttonColor;
	
	
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
		
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		greenIcon = new ImageIcon("assets\\coa4.png");
		Image greenImage = greenIcon.getImage();
		Image modGreenImage = greenImage.getScaledInstance(303*1/13, 448*1/13, java.awt.Image.SCALE_SMOOTH);
		greenIcon = new ImageIcon(modGreenImage);
		
		menuIcon = new ImageIcon("assets\\Floris_Claesz._van_Dyck_001.jpg");
		Image menuImage = menuIcon.getImage();
		Image modMenuImage = menuImage.getScaledInstance(2048*1/30, 1255*1/30, java.awt.Image.SCALE_SMOOTH);
		menuIcon = new ImageIcon(modMenuImage);
		
		panelMap = new JPanel();
		panelCf = new JPanel();
		
		mapLabel = new JLabel(mapIcon);
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		panelMap.setPreferredSize(new Dimension(screenSize.width*8/10,screenSize.height));
		panelCf.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		panelCf.setLayout(new BorderLayout());
		
		buttonColor = new Color(239, 228, 176);
		
		menuBar = new JMenuBar();
		menu = new JMenu();
		speichern = new JMenuItem("Speichern");
		beenden = new JMenuItem("Beenden");
		speichern.setHorizontalTextPosition(SwingConstants.CENTER);
		speichern.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		beenden.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		speichern.setBackground(buttonColor);
		beenden.setBackground(buttonColor);
		menuBar.setBounds(-10, -3, 70, 45);
		menu.setIcon(menuIcon);
		menu.add(speichern);
		menu.add(beenden);
		menuBar.add(menu);	
		panelCf.add(menuBar, BorderLayout.NORTH);
		panelCf.setLayout(null);
		
		
		
		/*playerInformationPanel = new JPanel();
		playerInformationPanel.setLocation(a + 40,b + 60);
		playerInformationPanel.setSize(200, 40);
		playerInformationLabel = new JLabel(buttonImage);
		playerInformationLabel.setBounds(a + 40, b + 60,200,40);
		playerInformationPanel.add(playerInformationLabel);

		panelCf.add(playerInformationPanel);*/
		
		
		
		playerInformationBackground = new JLabel("Player Three", greenIcon, SwingConstants.CENTER);
		playerInformationBackground.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*70)/768, 170, 40);
		playerInformationBackground.setIconTextGap(12);	
		playerInformationBackground.setBackground(buttonColor);
		playerInformationBackground.setOpaque(true);
		playerInformationBackground.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(playerInformationBackground);
		System.out.print(screenSize.width*2/10);
	
		
		//playerInformation = new JLabel("Player One");
		//playerInformation.setBounds(40,65,100,10);
		//playerInformationBackground.add(playerInformation);
		
		//panelCf.add(playerInformation);
		
		
		
		button4 = new JButton("Einheit setzen", buttonIcon);
		button4.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*220)/768, 170, 27);
		button4.setHorizontalTextPosition(SwingConstants.CENTER);
		button4.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(button4);
		
		//button4.setBackground(Color.cyan.darker());
		//button4.setIcon(buttonIcon);
		//button4.setText("Soldat setzen");
		/*JLabel button4Titel = new JLabel ("Soldat setzen");
		button4Titel.setBounds(1,1,50,10);
		button4.add(button4Titel);*/
		
		unitsList = new String[1][1];
		unitsTable = new JTable(unitsList, unitsTitel);
		unitsTable.getTableHeader().setBackground(buttonColor);
		unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 20));
		unitsTable.setSelectionBackground(buttonColor);
		unitsTable.setRowHeight(30);
		unitsTable.setValueAt(" X V V V IIII",0,0);	
		unitsTable.setShowGrid(true);
		unitsTable.setOpaque(false);
		unitsTable.setBackground(buttonColor);
		unitsDisplay = new JScrollPane(unitsTable);
		unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		unitsDisplay.setBounds((screenSize.width*2/10 - 220)/2, (screenSize.height*500)/768, 220, 56 );
		unitsDisplay.getViewport().setBackground(buttonColor);
		
		panelCf.add (unitsDisplay);
		
		territoriesList = new String[8][1];
		territoriesTableModel = new DefaultTableModel(territoriesList, territoriesTitel);
		territoriesTable = new JTable();
		territoriesTable.setModel(territoriesTableModel);
		/*Dynamische Tabellenanpassung
		territoriesTableModel.addRow(new Object[]{""}); */
		territoriesTable.getTableHeader().setBackground(buttonColor);
		territoriesTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		territoriesTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 12));
		territoriesTable.setSelectionBackground(buttonColor);
		territoriesTable.setValueAt("Southern Sun 2",0,0);
		territorriesColumn = territoriesTable.getColumnModel().getColumn(0);
		dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        territorriesColumn.setCellRenderer(dtcr);		
		territoriesTable.setShowGrid(true);
		territoriesTable.setOpaque(false);
		territoriesTable.setBackground(buttonColor);
		territoriesDisplay = new JScrollPane(territoriesTable);
		territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		territoriesDisplay.setBounds((screenSize.width*2/10 - 220)/2, (screenSize.height*600)/768, 220, 120);
		territoriesDisplay.getViewport().setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);	
		panelCf.add (territoriesDisplay);
		
		/*anzeige = new JLabel("Anzeige");
		anzeige.setBounds(0, 0, 100, 15);
		panelCf.add(anzeige);*/
		

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
