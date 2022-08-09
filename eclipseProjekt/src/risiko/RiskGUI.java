package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class RiskGUI extends JFrame implements ActionListener{
	
	//private JFrame jframe = new JFrame();
	private JPanel panelMap;
	private JPanel panelCf;
	private JLabel mapLabel;	
	
	private JLabel controlfieldLabel;
	private ImageIcon controlfieldIcon;
	private Icon buttonIcon;
	private ImageIcon greenIcon;
	private ImageIcon menuIcon;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem speichern;
	private JMenuItem beenden;
	
	private JLabel playerInformationBackground;
	
	//Spieleranweisung
	private JTextField guideDisplay;
	private JScrollPane scrollGuideDisplay;
	
	private JTextField selectedTerritory;
	private JTextField startPositionAttack;
	private JTextField attackedPosition;
	private JTextField startPositionMovement;
	private JTextField movedToPosition;
	
	//GebietsAnzeige
	private JScrollPane territoriesDisplay;
	private String [][]territoriesList;
	private JTable territoriesTable;
	private String [] territoriesTitel = {"Besetzte Gebiete", "Einheiten"};
	private DefaultTableModel territoriesTableModel;
	private TableColumn territoriesColumn1;
	private TableColumn territoriesColumn2;
	private DefaultTableCellRenderer dtcr;
	
	//EinheitenAnzeige
	private JScrollPane unitsDisplay;
	private String[][]unitsList;
	private JTable unitsTable;
	private String[]unitsTitel = {"Einheiten Zur Verteilung"};
	
	private JSpinner unitCounterManeuver;
	private SpinnerNumberModel unitCounterManeuverModel;
	private JSpinner unitCounterAttack;
	private SpinnerNumberModel unitCounterAttackModel;
	private JSpinner unitCounterMovement;
	private SpinnerNumberModel unitCounterMovementModel;
	
	private JButton putUnit;
	private JButton attack;
	private JButton endPhaseAttack;
	private JButton unitMovement;
	private JButton endPhaseMovement;
	private JButton hilfe;
	
	private Color buttonColor;
	private Integer phase = 0;
	
	
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
		
		playerInformationBackground = new JLabel("Player Three", greenIcon, SwingConstants.CENTER);
		playerInformationBackground.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*80)/768, 170, 40);
		playerInformationBackground.setIconTextGap(12);	
		playerInformationBackground.setBackground(buttonColor);
		playerInformationBackground.setOpaque(true);
		playerInformationBackground.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(playerInformationBackground);
		
		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*135)/768, 240, 25);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		guideDisplay.setEditable(false);
		
		panelCf.add(guideDisplay);
		
		/*guideDisplay.setLineWrap(true);
		guideDisplay.setWrapStyleWord(true);
		scrollGuideDisplay = new JScrollPane(guideDisplay);
		scrollGuideDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollGuideDisplay.setBounds((screenSize.width*2/10 - 180)/2, (screenSize.height*140)/768, 180, 70);
		scrollGuideDisplay.getViewport().setBackground(buttonColor);
		scrollGuideDisplay.getVerticalScrollBar().setBackground(buttonColor);
		scrollGuideDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		scrollGuideDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);
		panelCf.add(scrollGuideDisplay);*/
	
		hilfe = new JButton("?", buttonIcon);
		hilfe.setBounds((screenSize.width*2/10 + 161)/2,(screenSize.height*160)/768, 40, 35);
		hilfe.setHorizontalTextPosition(SwingConstants.CENTER);
		hilfe.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
	
		hilfe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedTerritory.setText("Northern Sun");
				JFrame jFrame = new JFrame();
				jFrame.setBounds(300,200,200,100);
				JPanel helpPanel = new JPanel();
				helpPanel.setBackground(buttonColor);
				jFrame.add(helpPanel);
				//jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				//jFrame.setUndecorated(true);
				jFrame.setVisible(true);
				
			
			}
		});
		panelCf.add(hilfe);
		
		
		
		for(int i = 0; i < 1; i++) {
		 
			if(phase == 0) {
		
				selectedTerritory = new JTextField("Southern Sun");
				selectedTerritory.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*320)/768, 170, 30);
				selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
				selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				selectedTerritory.setBackground(buttonColor);
				selectedTerritory.setEditable(false);
				panelCf.add(selectedTerritory);
		
				putUnit = new JButton("Einheit setzen", buttonIcon);
				putUnit.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*365)/768, 170, 30);
				putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
				putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				putUnit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						selectedTerritory.setText("Eastern Sun");
					}	
					});
				panelCf.add(putUnit);
			
				guideDisplay.setText("Verteilung der Einheiten." );
			
				//Klicken Sie auf das Gebiet, auf dass "
				//		+ "Sie ihre Einheit setzen möchten. Drücken Sie danach den Knopf 'Einheit setzen'."
				unitsList = new String[1][1];
				unitsTable = new JTable(unitsList, unitsTitel);
				unitsTable.getTableHeader().setBackground(buttonColor);
				unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitsTable.setSelectionBackground(buttonColor);
				unitsTable.setRowHeight(29);
				unitsTable.setValueAt("13",0,0);	
				unitsTable.setShowGrid(true);
				unitsTable.setOpaque(false);
				unitsTable.setBackground(buttonColor);
				unitsDisplay = new JScrollPane(unitsTable);
				unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				unitsDisplay.setBounds((screenSize.width*2/10 - 220)/2, (screenSize.height*520)/768, 220, 55 );
				unitsDisplay.getViewport().setBackground(buttonColor);
				panelCf.add (unitsDisplay);
			}
			
			if(phase == 1) {
			
				panelCf.remove(selectedTerritory);
				panelCf.remove(putUnit);
				panelCf.remove(unitsDisplay);
			
				selectedTerritory = new JTextField("The Kingdom of Sun");
				selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
				selectedTerritory.setBounds((screenSize.width*2/10 - 215)/2,(screenSize.height*320)/768, 160, 30);
				selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				selectedTerritory.setBackground(buttonColor);
				selectedTerritory.setEditable(false);
				panelCf.add(selectedTerritory);
				
				putUnit = new JButton("Einheit setzen", buttonIcon);
				putUnit.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*365)/768, 170, 30);
				putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
				putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(putUnit);
		
				guideDisplay.setText("Verteilung der Einheiten. Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen möchten. Bestimmen Sie mit dem Zähler die Anzahl der Einheiten,"
						+ "Sie auf das Gebiet setzen möchten und drücken Sie danach den Knopf 'Einheit setzen'.");
			
				unitCounterManeuverModel = new SpinnerNumberModel(0, 0, 200, 1);
				unitCounterManeuver = new JSpinner(unitCounterManeuverModel);
				unitCounterManeuver.setBounds((screenSize.width*2/10 + 125)/2,(screenSize.height*320)/768, 45, 30);
				unitCounterManeuver.setBackground(Color.blue);
				unitCounterManeuver.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				unitCounterManeuver.getComponent(0).setBackground(buttonColor);
				unitCounterManeuver.getComponent(1).setBackground(buttonColor);
				unitCounterManeuver.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterManeuver);
				
				unitsList = new String[1][1];
				unitsTable = new JTable(unitsList, unitsTitel);
				unitsTable.getTableHeader().setBackground(buttonColor);
				unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitsTable.setSelectionBackground(buttonColor);
				unitsTable.setRowHeight(29);
				unitsTable.setValueAt(" X V V V IIII",0,0);	
				unitsTable.setShowGrid(true);
				unitsTable.setOpaque(false);
				unitsTable.setBackground(buttonColor);
				unitsDisplay = new JScrollPane(unitsTable);
				unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				unitsDisplay.setBounds((screenSize.width*2/10 - 220)/2, (screenSize.height*520)/768, 220, 55 );
				unitsDisplay.getViewport().setBackground(buttonColor);
				panelCf.add (unitsDisplay);
			}
		
			if(phase == 2) {
			
				panelCf.remove(selectedTerritory);
				panelCf.remove(putUnit);
				panelCf.remove(unitCounterManeuver);
				panelCf.remove(unitsDisplay);
				
				startPositionAttack = new JTextField("The Kingdom of Sun");
				startPositionAttack.setHorizontalAlignment(SwingConstants.CENTER);
				startPositionAttack.setBounds((screenSize.width*2/10 - 215)/2,(screenSize.height*275)/768, 160, 30);
				startPositionAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				startPositionAttack.setBackground(buttonColor);
				startPositionAttack.setEditable(false);
				panelCf.add(startPositionAttack);
				
				guideDisplay.setText("Greifen Sie den Gegner an. Klicken Sie auf das Gebiet, dass "
						+ "Sie Angreifen möchten und danach auf das Gebiet von dem aus Sie angreifen möchten."
						+ "Bestimmen Sie mit dem Zähler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen möchten und drücken Sie danach den Knopf 'Angreifen'."
						+ "Wenn Sie nicht angreifen möchten, drücken Sie 'Phase beenden'.");
				
				unitCounterAttackModel = new SpinnerNumberModel(0, 0, 3, 1);
				unitCounterAttack = new JSpinner(unitCounterAttackModel);
				unitCounterAttack.setBounds((screenSize.width*2/10 + 125)/2,(screenSize.height*275)/768, 45, 30);
				unitCounterAttack.setBackground(Color.blue);
				unitCounterAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				unitCounterAttack.getComponent(0).setBackground(buttonColor);
				unitCounterAttack.getComponent(1).setBackground(buttonColor);
				unitCounterAttack.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterAttack);
				
				attackedPosition = new JTextField("South Valoran");
				attackedPosition.setHorizontalAlignment(selectedTerritory.CENTER);
				attackedPosition.setBounds((screenSize.width*2/10 - 215)/2,(screenSize.height*320)/768, 160, 30);
				attackedPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				attackedPosition.setBackground(buttonColor);
				attackedPosition.setEditable(false);
				panelCf.add(attackedPosition);
				
				attack = new JButton("Angreifen", buttonIcon);
				attack.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*365)/768, 170, 30);
				attack.setHorizontalTextPosition(SwingConstants.CENTER);
				attack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(attack);
			
				endPhaseAttack = new JButton("Phase beenden", buttonIcon);
				endPhaseAttack.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*530)/768, 170, 30);
				endPhaseAttack.setHorizontalTextPosition(SwingConstants.CENTER);
				endPhaseAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(endPhaseAttack);
				
			}
		
			if(phase == 3) {
				
				panelCf.remove(startPositionAttack);
				panelCf.remove(unitCounterAttack);
				panelCf.remove(attackedPosition);
				panelCf.remove(attack);
				
				startPositionMovement = new JTextField("The Kingdom of Sun");
				startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
				startPositionMovement.setBounds((screenSize.width*2/10 - 215)/2,(screenSize.height*275)/768, 160, 30);
				startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				startPositionMovement.setBackground(buttonColor);
				startPositionMovement.setEditable(false);
				panelCf.add(startPositionMovement);
				
				guideDisplay.setText("Greifen Sie den Gegner an. Klicken Sie auf das Gebiet, dass "
						+ "Sie Angreifen möchten und danach auf das Gebiet von dem aus Sie angreifen möchten."
						+ "Bestimmen Sie mit dem Zähler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen möchten und drücken Sie danach den Knopf 'Angreifen'."
						+ "Wenn Sie nicht angreifen möchten, drücken Sie 'Phase beenden'.");
				
				unitCounterMovementModel = new SpinnerNumberModel(0, 0, 200, 1);
				unitCounterMovement = new JSpinner(unitCounterMovementModel);
				unitCounterMovement.setBounds((screenSize.width*2/10 + 125)/2,(screenSize.height*275)/768, 45, 30);
				unitCounterMovement.setBackground(Color.blue);
				unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				unitCounterMovement.getComponent(0).setBackground(buttonColor);
				unitCounterMovement.getComponent(1).setBackground(buttonColor);
				unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterMovement);
				
				movedToPosition = new JTextField("South Valoran");
				movedToPosition.setHorizontalAlignment(selectedTerritory.CENTER);
				movedToPosition.setBounds((screenSize.width*2/10 - 215)/2,(screenSize.height*320)/768, 160, 30);
				movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
				movedToPosition.setBackground(buttonColor);
				movedToPosition.setEditable(false);
				panelCf.add(movedToPosition);
				
				unitMovement = new JButton("Einheit bewegen", buttonIcon);
				unitMovement.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*365)/768, 170, 30);
				unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
				unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 15));
				panelCf.add(unitMovement);
			
				endPhaseMovement = new JButton("Phase beenden", buttonIcon);
				endPhaseMovement.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*530)/768, 170, 30);
				endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
				endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(endPhaseMovement);
				
			}
			
			phase = phase+1;
		}
		territoriesList = new String[7][2];
		territoriesTableModel = new DefaultTableModel(territoriesList, territoriesTitel);
		territoriesTable = new JTable();
		territoriesTable.setModel(territoriesTableModel);
		/*Dynamische Tabellenanpassung
		territoriesTableModel.addRow(new Object[]{""}); */
		territoriesTable.setRowHeight(15);
		territoriesTable.getTableHeader().setBackground(buttonColor);
		territoriesTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 13));
		territoriesTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 13));
		territoriesTable.setSelectionBackground(buttonColor);
		territoriesTable.setValueAt("Northern Schataria",0,0);
		territoriesTable.setValueAt("2", 0, 1);
		territoriesColumn1 = territoriesTable.getColumnModel().getColumn(0);
		territoriesColumn2 = territoriesTable.getColumnModel().getColumn(1);
		dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        territoriesColumn1.setCellRenderer(dtcr);		
		territoriesColumn2.setCellRenderer(dtcr);
        territoriesColumn2.setPreferredWidth(75);
        territoriesColumn1.setPreferredWidth(160);
        territoriesTable.setRowHeight(20);
		territoriesTable.setShowGrid(true);
		territoriesTable.setOpaque(false);
		territoriesTable.setBackground(buttonColor);
		territoriesDisplay = new JScrollPane(territoriesTable);
		territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		territoriesDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*590)/768, 245, 120);
		territoriesDisplay.getViewport().setBackground(buttonColor);
		territoriesDisplay.setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);	
		panelCf.add (territoriesDisplay);
		
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	

	
	
	
}
