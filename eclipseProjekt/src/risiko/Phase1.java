package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Phase1 extends JPanel implements ActionListener {

		private static final Frame helpFrame = null;
		private Controller controller;	
		private JLabel controlfieldLabel;
		private ImageIcon controlfieldIcon;
		private Icon buttonIcon;
		private ImageIcon coatIcon;
		private ImageIcon menuIcon;
		
		private JMenuBar menuBar;
		private JMenu menu;
		private JMenuItem beenden;
		private JButton help;

		private JLabel playerInformationLabel;
		private JTextField guideDisplay;
		private JTextField selectedTerritory;
		private JButton putUnit;
		private JSpinner unitCounterManeuver;
		private SpinnerNumberModel unitCounterManeuverModel;
		
		private JScrollPane unitsDisplay;
		private String[][]unitsList;
		private JTable unitsTable;
		private TableColumn unitsTableColumn;
		private String[]unitsTitel = {"Einsetzbare Armeen"};
		
		private JScrollPane territoriesDisplay;
		private String [][]territoriesList;
		private JTable territoriesTable;
		private String [] territoriesTitel = {"Besetzte Gebiete", "Armeen"};
		private DefaultTableModel territoriesTableModel;
		private TableColumn territoriesColumn1;
		private TableColumn territoriesColumn2;
		private DefaultTableCellRenderer dtcr;
		
		private Color buttonColor;
		private controlerTry cntrl;
		private Dimension screenSize;
	
	
	public Phase1(Controller controller) {
		
		this.controller = controller;
		cntrl = new controlerTry();
		dtcr = new DefaultTableCellRenderer(); 
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		controlfieldIcon = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		Image controlfieldImage = controlfieldIcon.getImage();
		Image modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);	
		
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		coatIcon = new ImageIcon(cntrl.getPlayerCoat().get(controller.getPlayerAtTurn()));
		Image coatImage = coatIcon.getImage();
		Image modCoatImage = coatImage.getScaledInstance(303*1/13, 448*1/13, java.awt.Image.SCALE_SMOOTH);
		coatIcon = new ImageIcon(modCoatImage);
		
		menuIcon = new ImageIcon("assets\\Floris_Claesz._van_Dyck_001.jpg");
		Image menuImage = menuIcon.getImage();
		Image modMenuImage = menuImage.getScaledInstance(2048*1/30, 1255*1/30, java.awt.Image.SCALE_SMOOTH);
		menuIcon = new ImageIcon(modMenuImage);
		
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		this.setLayout(new BorderLayout());
		
		buttonColor = new Color(239, 228, 176);
		
		menuBar = new JMenuBar();
		menu = new JMenu();
		beenden = new JMenuItem("Beenden");
		beenden.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 14));
		beenden.setBackground(buttonColor);
		beenden.addActionListener(this);
		menuBar.setBounds(-10, -3, 70, 45);
		menu.setIcon(menuIcon);
		menu.add(beenden);
		menuBar.add(menu);	
		this.add(menuBar, BorderLayout.NORTH);
		this.setLayout(null);
		
		help = new JButton("?", buttonIcon);
		help.setBounds(60,(screenSize.height*0)/768, 45, 43);
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 24));
		help.addActionListener(this); 
		this.add(help);
		
		playerInformationLabel = new JLabel(controller.getPlayerObject().getName(), coatIcon, SwingConstants.CENTER);
		playerInformationLabel.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*85)/768, 170, 40);
		playerInformationLabel.setIconTextGap(12);	
		playerInformationLabel.setBackground(buttonColor);
		playerInformationLabel.setOpaque(true);
		playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		this.add(playerInformationLabel);
		
		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*140)/768, 240, 35);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setText("Verteilen Sie Ihre Armeen");
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		guideDisplay.setEditable(false);
		this.add(guideDisplay);
		
		selectedTerritory = new JTextField();
		selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTerritory.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*275)/768, 190, 35);
		selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		selectedTerritory.setBackground(buttonColor);
		selectedTerritory.setEditable(false);
		this.add(selectedTerritory);
		
		putUnit = new JButton("Armeen setzen", buttonIcon);
		putUnit.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*325)/768, 240, 35);
		putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
		putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		putUnit.addActionListener(this);
		this.add(putUnit);
		
		unitCounterManeuverModel = new SpinnerNumberModel(0, 0, 99, 1);
		unitCounterManeuver = new JSpinner(unitCounterManeuverModel);
		unitCounterManeuver.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*275)/768, 40, 35);
		unitCounterManeuver.setBackground(Color.blue);
		unitCounterManeuver.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		unitCounterManeuver.getComponent(0).setBackground(buttonColor);
		unitCounterManeuver.getComponent(1).setBackground(buttonColor);
		unitCounterManeuver.getEditor().getComponent(0).setBackground(buttonColor);
		this.add(unitCounterManeuver);
		
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
		unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);
		unitsTable.setShowGrid(true);
		unitsTable.setOpaque(false);
		unitsTable.setBackground(buttonColor);
		unitsDisplay = new JScrollPane(unitsTable);
		unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		unitsDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*390)/768, 240, 58 );
		unitsDisplay.getViewport().setBackground(buttonColor);
		this.add (unitsDisplay);
		
		territoriesList = new String[42][2];
		territoriesTableModel = new DefaultTableModel(territoriesList, territoriesTitel);
		territoriesTable = new JTable();
		territoriesTable.setModel(territoriesTableModel);
		territoriesTable.setRowHeight(20);
		territoriesTable.setShowGrid(true);
		territoriesTable.setOpaque(false);
		territoriesTable.setBackground(buttonColor);
		territoriesTable.getTableHeader().setBackground(buttonColor);
		territoriesTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 13));
		territoriesTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 13));
		territoriesTable.setSelectionBackground(buttonColor);
		
		for(int i = 0; i < controller.getPlayerObject().getOccupiedTerritories().size(); i++) {
			territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getName(),i,0);
			for(int j = 0; j < controller.getPlayerObject().getOccupiedTerritories().size(); j++) {
				territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getArmiesOnTerritory(),i,1);
			}
		}		
		
		territoriesColumn1 = territoriesTable.getColumnModel().getColumn(0);
		territoriesColumn2 = territoriesTable.getColumnModel().getColumn(1);
		dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        territoriesColumn1.setCellRenderer(dtcr);		
		territoriesColumn2.setCellRenderer(dtcr);
        territoriesColumn2.setPreferredWidth(65);
        territoriesColumn1.setPreferredWidth(165);
		territoriesDisplay = new JScrollPane(territoriesTable);
		territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		territoriesDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*570)/768, 240, 160);
		territoriesDisplay.getViewport().setBackground(buttonColor);
		territoriesDisplay.setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		territoriesDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);	
		this.add (territoriesDisplay);
	
		this.add(controlfieldLabel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == this.help) {
		
			new HelpPopUp(helpFrame, 1);
		
		}
		else if(e.getSource() == this.beenden) {
			
			System.exit(0);
		}
		else if(e.getSource() == this.putUnit) {
			if(controller.placeArmies((int) unitCounterManeuver.getValue())) {
				unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);
				updateTable();
				controller.updateMap();
				if(controller.getPlayerObject().getArmies() == 0) {
					controller.getGui().changePhase(2);
				}
			}
		}
	}
	
	public void updateSelectedTerritory() {
		if(controller.validTerritory()) {
			this.selectedTerritory.setText(controller.activeTerritory.getName());
			this.guideDisplay.setText("Verteilen Sie ihre Armeen");
		}else {
			this.guideDisplay.setText("Ungültige Auswahl");
			this.selectedTerritory.setText("");
		}
	}
	
	/*private void updatePlayerInfo() {
		coatIcon = new ImageIcon(cntrl.getPlayerCoat().get(controller.getPlayerAtTurn()));
		Image greenImage = coatIcon.getImage();
		Image modGreenImage = greenImage.getScaledInstance(303*1/13, 448*1/13, java.awt.Image.SCALE_SMOOTH);
		coatIcon = new ImageIcon(modGreenImage);
		
		playerInformationLabel.setText(controller.getPlayerObject().getName());
		playerInformationLabel.setIcon(coatIcon);
		updateTable();
		
		unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);
	}*/
	
	private void updateTable() {
		for(int i = 0; i < controller.getPlayerObject().getOccupiedTerritories().size(); i++) {
			territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getName(),i,0);
			for(int j = 0; j < controller.getPlayerObject().getOccupiedTerritories().size(); j++) {
				territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getArmiesOnTerritory(),i,1);
			}
		}	
	}
}
