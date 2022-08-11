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
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	//private JScrollPane scrollGuideDisplay;
	
	private JTextField selectedTerritory;
	private JTextField startPositionAttack;
	private JTextField attackedPosition;
	private JTextField startPositionMovement;
	private JTextField movedToPosition;
	
	private JButton help;
	private JDialog helpPopUp;
	private JTextArea helpDisplay;
	private JPanel helpPanelText;
	private JPanel helpPanelButton;
	private JScrollPane scrollHelpDisplay;
	private JButton closeHelp;
	
	//GebietsAnzeige
	private JScrollPane territoriesDisplay;
	private String [][]territoriesList;
	private JTable territoriesTable;
	private String [] territoriesTitel = {"Besetzte Gebiete", "Armeen"};
	private DefaultTableModel territoriesTableModel;
	private TableColumn territoriesColumn1;
	private TableColumn territoriesColumn2;
	private DefaultTableCellRenderer dtcr;
	
	//EinheitenAnzeige
	private JScrollPane unitsDisplay;
	private String[][]unitsList;
	private JTable unitsTable;
	private TableColumn unitsTableColumn;
	private String[]unitsTitel = {"Einsetzbare Armeen"};
	
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
	
	
	private Color buttonColor;
	private Integer phase = 0;
	private controlerTry cntrl;
	private Dimension screenSize;
	private Integer playerNumber = 1;
	
	
	int a = 3;
	
	public RiskGUI() {
		
		cntrl = new controlerTry();
		
		//Bild einlesen
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		ImageIcon mapIcon = new ImageIcon("assets\\risk-map.jpg");
		Image map = mapIcon.getImage();
		Image modmap = map.getScaledInstance(screenSize.width*8/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		mapIcon = new ImageIcon(modmap);
		
		controlfieldIcon = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		Image controlfieldImage = controlfieldIcon.getImage();
		Image modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);	
		
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		greenIcon = new ImageIcon(cntrl.getPlayerCoat().get(playerNumber));
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
		dtcr = new DefaultTableCellRenderer(); 
		
		
		menuBar = new JMenuBar();
		menu = new JMenu();
		speichern = new JMenuItem("Speichern");
		beenden = new JMenuItem("Beenden");
		speichern.setHorizontalTextPosition(SwingConstants.CENTER);
		speichern.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		beenden.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		speichern.setBackground(buttonColor);
		beenden.setBackground(buttonColor);
		beenden.addActionListener(this);
		menuBar.setBounds(-10, -3, 70, 45);
		menu.setIcon(menuIcon);
		menu.add(speichern);
		menu.add(beenden);
		menuBar.add(menu);	
		panelCf.add(menuBar, BorderLayout.NORTH);
		panelCf.setLayout(null);
		
		playerInformationBackground = new JLabel("Player Three", greenIcon, SwingConstants.CENTER);
		playerInformationBackground.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*85)/768, 170, 40);
		playerInformationBackground.setIconTextGap(12);	
		playerInformationBackground.setBackground(buttonColor);
		playerInformationBackground.setOpaque(true);
		playerInformationBackground.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(playerInformationBackground);
		
		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*140)/768, 240, 35);
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
	
		help = new JButton("?", buttonIcon);
		help.setBounds((screenSize.width*2/10 - 152)/2,(screenSize.height*0)/768, 45, 43);
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 24));
	
		help.addActionListener(this); /*{
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				helpPopUp = new JDialog();
				helpPopUp.setBounds(300,50,250,200);
				helpPopUp.setLayout(new BorderLayout());
				helpPanelText = new JPanel();
				helpPanelButton = new JPanel();
				
				helpPanelText.setBounds(1, 1, 248, 160);
				helpPanelText.setBackground(buttonColor);
				helpPanelText.setBorder(BorderFactory.createLineBorder(Color.black));
				helpPanelButton.setBounds(0,160,200,40);
				helpPanelButton.setBackground(buttonColor);
				helpPanelButton.setBorder(BorderFactory.createLineBorder(Color.black));
				
				helpDisplay = new JTextArea();
				helpDisplay.setBounds(1,1,248,160);
				helpDisplay.setBackground(buttonColor);
				helpDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				helpDisplay.setEditable(false);
				
				helpDisplay.setText("Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen m�chten. Dr�cken Sie danach den Knopf 'Einheit setzen'."
						+ "Sie Angreifen m�chten und danach auf das Gebiet von dem aus Sie angreifen m�chten."
						+ "Bestimmen Sie mit dem Z�hler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen m�chten und dr�cken Sie danach den Knopf 'Angreifen'."
						+ "Wenn Sie nicht angreifen m�chten, dr�cken Sie 'Phase beenden'.");
						
				helpDisplay.setLineWrap(true);
				helpDisplay.setWrapStyleWord(true);
				scrollHelpDisplay = new JScrollPane(helpDisplay);
				
				scrollHelpDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollHelpDisplay.setBounds(1,1,248,160);
				scrollHelpDisplay.getViewport().setBackground(buttonColor);
				scrollHelpDisplay.getVerticalScrollBar().setBackground(buttonColor);
				scrollHelpDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
				scrollHelpDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);
				helpPopUp.add(scrollHelpDisplay);
				
				closeHelp = new JButton("Ok", buttonIcon);
				closeHelp.setPreferredSize(new Dimension(60,30));
				closeHelp.setHorizontalTextPosition(SwingConstants.CENTER);
				closeHelp.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				closeHelp.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						helpPopUp.dispose();
					}	
					});
				helpPanelButton.add(closeHelp);
				
				helpPopUp.add(helpPanelText);
				helpPopUp.add(helpPanelButton);
				helpPopUp.add(helpPanelText, BorderLayout.CENTER);
				helpPopUp.add(helpPanelButton, BorderLayout.SOUTH);
				helpPopUp.setUndecorated(true);
				helpPopUp.setVisible(true);*/
				
				/*JFrame jFrame = new JFrame();
				jFrame.setBounds(300,200,200,100);
				JPanel helpPanel = new JPanel();
				helpPanel.setBackground(buttonColor);
				jFrame.add(helpPanel);
				//jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				//jFrame.setUndecorated(true);
				jFrame.setVisible(true);*/
				
			
		/*	}
		});*/
		panelCf.add(help);
		
		
		
		for(int i = 0; i < a; i++) {
		 
			if(phase == 0) {
		
				selectedTerritory = new JTextField("Northern Schataria");
				selectedTerritory.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*275)/768, 240, 35);
				selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
				selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				selectedTerritory.setBackground(buttonColor);
				selectedTerritory.setEditable(false);
				panelCf.add(selectedTerritory);
		
				putUnit = new JButton("Armee setzen", buttonIcon);
				putUnit.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*325)/768, 240, 35);
				putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
				putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));

				panelCf.add(putUnit);
			
				guideDisplay.setText("Verteilen Sie ihre Armeen" );
				
				unitsList = new String[1][1];
				unitsTable = new JTable(unitsList, unitsTitel);
				unitsTable.getTableHeader().setBackground(buttonColor);
				unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16)); 
				unitsTableColumn =unitsTable.getColumnModel().getColumn(0);
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
				unitsTableColumn.setCellRenderer(dtcr);
		        unitsTable.setSelectionBackground(buttonColor);
				unitsTable.setRowHeight(29);
				unitsTable.setValueAt("13",0,0);	
				unitsTable.setShowGrid(true);
				unitsTable.setOpaque(false);
				unitsTable.setBackground(buttonColor);
				unitsDisplay = new JScrollPane(unitsTable);
				unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				unitsDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*390)/768, 240, 58 );
				unitsDisplay.getViewport().setBackground(buttonColor);
				panelCf.add (unitsDisplay);
			}
			
			if(phase == 1) {
			
				panelCf.remove(selectedTerritory);
				panelCf.remove(putUnit);
				panelCf.remove(unitsDisplay);
			
				selectedTerritory = new JTextField("The Kingdom of Sun");
				selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
				selectedTerritory.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*275)/768, 190, 35);
				selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				selectedTerritory.setBackground(buttonColor);
				selectedTerritory.setEditable(false);
				panelCf.add(selectedTerritory);
				
				putUnit = new JButton("Armeen setzen", buttonIcon);
				putUnit.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*325)/768, 240, 35);
				putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
				putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(putUnit);
		
				guideDisplay.setText("Verteilen Sie Ihre Armeen");
			
				unitCounterManeuverModel = new SpinnerNumberModel(0, 0, 99, 1);
				unitCounterManeuver = new JSpinner(unitCounterManeuverModel);
				unitCounterManeuver.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*275)/768, 40, 35);
				unitCounterManeuver.setBackground(Color.blue);
				unitCounterManeuver.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitCounterManeuver.getComponent(0).setBackground(buttonColor);
				unitCounterManeuver.getComponent(1).setBackground(buttonColor);
				unitCounterManeuver.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterManeuver);
				
				unitsList = new String[1][1];
				unitsTable = new JTable(unitsList, unitsTitel);
				unitsTable.getTableHeader().setBackground(buttonColor);
				unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitsTableColumn =unitsTable.getColumnModel().getColumn(0);
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
				unitsTableColumn.setCellRenderer(dtcr);
				unitsTableColumn.setPreferredWidth(220);
				unitsTable.setSelectionBackground(buttonColor);
				unitsTable.setRowHeight(29);
				unitsTable.setValueAt("37",0,0);	
				unitsTable.setShowGrid(true);
				unitsTable.setOpaque(false);
				unitsTable.setBackground(buttonColor);
				unitsDisplay = new JScrollPane(unitsTable);
				unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				unitsDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*390)/768, 240, 58 );
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
				startPositionAttack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 190, 35);
				startPositionAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				startPositionAttack.setBackground(buttonColor);
				startPositionAttack.setEditable(false);
				panelCf.add(startPositionAttack);
				
				guideDisplay.setText("Greifen Sie den Gegner an");
				
				unitCounterAttackModel = new SpinnerNumberModel(0, 0, 3, 1);
				unitCounterAttack = new JSpinner(unitCounterAttackModel);
				unitCounterAttack.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 40, 35);
				unitCounterAttack.setBackground(Color.blue);
				unitCounterAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitCounterAttack.getComponent(0).setBackground(buttonColor);
				unitCounterAttack.getComponent(1).setBackground(buttonColor);
				unitCounterAttack.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterAttack);
				
				attackedPosition = new JTextField("South Valoran");
				attackedPosition.setHorizontalAlignment(SwingConstants.CENTER);
				attackedPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 190, 35);
				attackedPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				attackedPosition.setBackground(buttonColor);
				attackedPosition.setEditable(false);
				panelCf.add(attackedPosition);
				
				attack = new JButton("Gegner angreifen", buttonIcon);
				attack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 240, 35);
				attack.setHorizontalTextPosition(SwingConstants.CENTER);
				attack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(attack);
			
				endPhaseAttack = new JButton("Phase beenden", buttonIcon);
				endPhaseAttack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 240, 35);
				endPhaseAttack.setHorizontalTextPosition(SwingConstants.CENTER);
				endPhaseAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				endPhaseAttack.addActionListener(this);
				panelCf.add(endPhaseAttack);
				
			}
		
			/*if(phase == 3) {
				
				panelCf.remove(startPositionAttack);
				panelCf.remove(unitCounterAttack);
				panelCf.remove(attackedPosition);
				panelCf.remove(attack);
				panelCf.remove(endPhaseAttack);
				
				startPositionMovement = new JTextField("The Kingdom of Sun");
				startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
				startPositionMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 190, 35);
				startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				startPositionMovement.setBackground(buttonColor);
				startPositionMovement.setEditable(false);
				panelCf.add(startPositionMovement);
				
				guideDisplay.setText("Bewegen Sie Ihre Armeen");
				
				unitCounterMovementModel = new SpinnerNumberModel(0, 0, 99, 1);
				unitCounterMovement = new JSpinner(unitCounterMovementModel);
				unitCounterMovement.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 40, 35);
				unitCounterMovement.setBackground(Color.blue);
				unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				unitCounterMovement.getComponent(0).setBackground(buttonColor);
				unitCounterMovement.getComponent(1).setBackground(buttonColor);
				unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
				panelCf.add(unitCounterMovement);
				
				movedToPosition = new JTextField("South Valoran");
				movedToPosition.setHorizontalAlignment(SwingConstants.CENTER);
				movedToPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 190, 35);
				movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				movedToPosition.setBackground(buttonColor);
				movedToPosition.setEditable(false);
				panelCf.add(movedToPosition);
				
				unitMovement = new JButton("Armeen bewegen", buttonIcon);
				unitMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 240, 35);
				unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
				unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(unitMovement);
			
				endPhaseMovement = new JButton("Phase beenden", buttonIcon);
				endPhaseMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 240, 35);
				endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
				endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
				panelCf.add(endPhaseMovement);
				
			}*/
			
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
        territoriesColumn2.setPreferredWidth(65);
        territoriesColumn1.setPreferredWidth(165);
        territoriesTable.setRowHeight(20);
		territoriesTable.setShowGrid(true);
		territoriesTable.setOpaque(false);
		territoriesTable.setBackground(buttonColor);
		territoriesDisplay = new JScrollPane(territoriesTable);
		territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		territoriesDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*570)/768, 240, 160);
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
	
		if(e.getSource() == this.help) {
		
			helpPopUp = new JDialog();
			helpPopUp.setBounds(300,50,250,200);
			helpPopUp.setLayout(new BorderLayout());
			helpPanelText = new JPanel();
			helpPanelButton = new JPanel();
			
			helpPanelText.setBounds(1, 1, 248, 160);
			helpPanelText.setBackground(buttonColor);
			helpPanelText.setBorder(BorderFactory.createLineBorder(Color.black));
			helpPanelButton.setBounds(0,160,200,40);
			helpPanelButton.setBackground(buttonColor);
			helpPanelButton.setBorder(BorderFactory.createLineBorder(Color.black));
			
			helpDisplay = new JTextArea();
			helpDisplay.setBounds(1,1,248,160);
			helpDisplay.setBackground(buttonColor);
			helpDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			helpDisplay.setEditable(false);
					
			//cntrl.createHelpText();
			helpDisplay.setText(cntrl.getHelpText().get(phase-1)); //wegen for-Schleife
			helpDisplay.setCaretPosition(0);
			
			/*helpDisplay.setText("Klicken Sie auf das Gebiet, auf dass "
						+ "Sie ihre Einheit setzen m�chten. Dr�cken Sie danach den Knopf 'Einheit setzen'."
						+ "Sie Angreifen m�chten und danach auf das Gebiet von dem aus Sie angreifen m�chten."
						+ "Bestimmen Sie mit dem Z�hler die Anzahl der Einheiten,"
						+ "mit denen Sie angreifen m�chten und dr�cken Sie danach den Knopf 'Angreifen'."
						+ "Wenn Sie nicht angreifen m�chten, dr�cken Sie 'Phase beenden'.");*/
					
			helpDisplay.setLineWrap(true);
			helpDisplay.setWrapStyleWord(true);
			scrollHelpDisplay = new JScrollPane(helpDisplay);
			
			scrollHelpDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollHelpDisplay.setBounds(1,1,248,160);
			scrollHelpDisplay.getViewport().setBackground(buttonColor);
			scrollHelpDisplay.getVerticalScrollBar().setBackground(buttonColor);
			scrollHelpDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
			scrollHelpDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);
			helpPopUp.add(scrollHelpDisplay);
			
			closeHelp = new JButton("Ok", buttonIcon);
			closeHelp.setPreferredSize(new Dimension(60,30));
			closeHelp.setHorizontalTextPosition(SwingConstants.CENTER);
			closeHelp.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			closeHelp.addActionListener(this);
			helpPanelButton.add(closeHelp);
			
			helpPopUp.add(helpPanelText);
			helpPopUp.add(helpPanelButton);
			helpPopUp.add(helpPanelText, BorderLayout.CENTER);
			helpPopUp.add(helpPanelButton, BorderLayout.SOUTH);
			helpPopUp.setUndecorated(true);
			helpPopUp.setVisible(true);
		
		}
		else if(e.getSource() == this.closeHelp){
			
			helpPopUp.dispose();
		
		}
		else if(e.getSource() == this.beenden) {
			
			System.exit(0);
		}
		
		else if(e.getSource() == this.putUnit) {
			
			if(phase == 0) {
				
				
			}
			else if(phase == 1) {
				
				unitCounterManeuver.getValue();
			
			}
		}
		else if(e.getSource() == this.attack) {
			
			unitCounterAttack.getValue();
		}
		else if(e.getSource() == this.endPhaseAttack) {
			
			phaseMovementGUI();
		
		}
		else if(e.getSource() == this.unitMovement) {
			
			unitCounterMovement.getValue();
		}
		
		else if(e.getSource() == this.endPhaseMovement) {
			
			phase = phase + 1;
		}
		
	}


	public void phaseMovementGUI() {
		
		phase = phase + 1;
		
		panelCf.remove(startPositionAttack);
		panelCf.remove(unitCounterAttack);
		panelCf.remove(attackedPosition);
		//attack.setVisible(false);
		panelCf.remove(attack);
		//panelCf.revalidate();
		//panelCf.repaint();
		panelCf.remove(endPhaseAttack);
		
		startPositionMovement = new JTextField("The Kingdom of Song");
		startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
		startPositionMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 190, 35);
		startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		startPositionMovement.setBackground(buttonColor);
		startPositionMovement.setEditable(false);
		panelCf.add(startPositionMovement);
		
		guideDisplay.setText("Bewegen Sie Ihre Armeen");
		
		unitCounterMovementModel = new SpinnerNumberModel(0, 0, 99, 1);
		unitCounterMovement = new JSpinner(unitCounterMovementModel);
		unitCounterMovement.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 40, 35);
		unitCounterMovement.setBackground(Color.blue);
		unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		unitCounterMovement.getComponent(0).setBackground(buttonColor);
		unitCounterMovement.getComponent(1).setBackground(buttonColor);
		unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
		panelCf.add(unitCounterMovement);
		
		movedToPosition = new JTextField("South Valoran");
		movedToPosition.setHorizontalAlignment(SwingConstants.CENTER);
		movedToPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 190, 35);
		movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		movedToPosition.setBackground(buttonColor);
		movedToPosition.setEditable(false);
		panelCf.add(movedToPosition);
		
		attack.setText("Armeen bewegen");
		
		unitMovement = new JButton("Armeen bewegen", buttonIcon);
		unitMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 240, 35);
		unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
		unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(unitMovement);
	
		endPhaseMovement = new JButton("Phase beenden", buttonIcon);
		endPhaseMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 240, 35);
		endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
		endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		//panelCf.revalidate();
		//panelCf.repaint();
		panelCf.add(endPhaseMovement);
		
	}

	
	
	
}
