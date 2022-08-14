package risiko;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Phase0 extends JPanel implements ActionListener{

	private Controller controller;	
	private JLabel controlfieldLabel;
	private ImageIcon controlfieldIcon;
	private Icon buttonIcon;
	private ImageIcon coatIcon;
	
	private JMenuBar menuBar;
	private JButton help;
	
	private JLabel playerInformationLabel;
	private JTextField guideDisplay;
	private JTextField selectedTerritory;
	private JButton putUnit;

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
	private ResourcesGUI resource;
	private Dimension screenSize;
	
	
	public Phase0 (Controller controller){
		System.out.println("Phase 0 erstellt");
		
		this.controller = controller;
		resource = new ResourcesGUI();
		dtcr = new DefaultTableCellRenderer(); 
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		controlfieldIcon = resource.getControlfieldIcon();
		coatIcon = resource.getCoatIcon(controller);
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		this.setLayout(new BorderLayout());
		
		buttonColor = new Color(239, 228, 176);
		
		menuBar = resource.getMenu();
		this.add(menuBar, BorderLayout.NORTH);
		this.setLayout(null);
		
		help = resource.getHelpButton(0);
		this.add(help);
		
		playerInformationLabel = new JLabel(controller.getPlayerObject().getName(), coatIcon, SwingConstants.CENTER);
		playerInformationLabel.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 170)/273)/2,
				(screenSize.height*85)/768, ((screenSize.width*2/10) * 170)/273, (screenSize.height*40)/768);
		playerInformationLabel.setIconTextGap(((screenSize.width* 2/10) * 12)/273);
		playerInformationLabel.setBackground(buttonColor);
		playerInformationLabel.setOpaque(true);
		playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		this.add(playerInformationLabel);

		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*140)/768,  
				((screenSize.width*2/10) * 240)/273, (screenSize.height*35)/768);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setText("Verteilen Sie ihre Armeen" );
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		guideDisplay.setEditable(false);	
		this.add(guideDisplay);
		
		selectedTerritory = new JTextField();
		selectedTerritory.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2, (screenSize.height*275)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
		selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		selectedTerritory.setBackground(buttonColor);
		selectedTerritory.setEditable(false);
		this.add(selectedTerritory);

		putUnit = new JButton("Armee setzen", buttonIcon);
		putUnit.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2, (screenSize.height*325)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
		putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
		putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		putUnit.addActionListener(this);
		this.add(putUnit);
		
		unitsList = new String[1][1];
		unitsTable = new JTable(unitsList, unitsTitel);
		unitsTable.getTableHeader().setBackground(buttonColor);
		unitsTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		unitsTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768)); 
		unitsTableColumn =unitsTable.getColumnModel().getColumn(0);
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		unitsTableColumn.setCellRenderer(dtcr);
		unitsTableColumn.setPreferredWidth(((screenSize.width* 2/10) * 220)/273);
		unitsTable.setSelectionBackground(buttonColor);
		unitsTable.setRowHeight((screenSize.height*29)/768);	
		unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);	
		unitsTable.setShowGrid(true);
		unitsTable.setOpaque(false);
		unitsTable.setBackground(buttonColor);
		unitsDisplay = new JScrollPane(unitsTable);
		unitsDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		unitsDisplay.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2, (screenSize.height*390)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*58)/768);
		unitsDisplay.getViewport().setBackground(buttonColor);
		this.add (unitsDisplay);

		territoriesList = new String[42][2];
		territoriesTableModel = new DefaultTableModel(territoriesList, territoriesTitel);
		territoriesTable = new JTable();
		territoriesTable.setModel(territoriesTableModel);
		territoriesTable.setRowHeight((screenSize.height*20)/768);
		territoriesTable.setShowGrid(true);
		territoriesTable.setOpaque(false);
		territoriesTable.setBackground(buttonColor);
		territoriesTable.getTableHeader().setBackground(buttonColor);
		territoriesTable.getTableHeader().setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 13 / 768));
		territoriesTable.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 13 / 768));
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
		territoriesColumn2.setPreferredWidth(((screenSize.width* 2/10) * 65)/273);
        territoriesColumn1.setPreferredWidth(((screenSize.width* 2/10) * 165)/273);
		territoriesDisplay = new JScrollPane(territoriesTable);
		territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		territoriesDisplay.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2, (screenSize.height*570)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*160)/768);
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
	
		if(e.getSource() == this.putUnit) {
			
			if(controller.placeArmyInitial()) {
				unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);
				updateTable();
				controller.updateMap();
				boolean tmp = true;
				for(Player p : controller.game.getPlayers()) {
					if(p.getArmies() != 0) {
						tmp = false;
					}
				}
				if(tmp) {
					controller.getGui().changePhase(1);	
					controller.nextPlayer();
				}else {
					controller.nextPlayer();
					while(controller.getPlayerObject().getArmies() == 0) {
						controller.nextPlayer();
					}
					updatePlayerInfo();
					this.selectedTerritory.setText("");
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
	
	private void updatePlayerInfo() {
		coatIcon = new ImageIcon(resource.getPlayerCoat().get(controller.getPlayerAtTurn()));
		Image greenImage = coatIcon.getImage();
		Image modGreenImage = greenImage.getScaledInstance(303*1/13, 448*1/13, java.awt.Image.SCALE_SMOOTH);
		coatIcon = new ImageIcon(modGreenImage);
		
		playerInformationLabel.setText(controller.getPlayerObject().getName());
		playerInformationLabel.setIcon(coatIcon);
		updateTable();
		
		unitsTable.setValueAt(Integer.toString(controller.getPlayerObject().getArmies()) ,0,0);
	}
	
	private void updateTable() {
		for(int i = 0; i < controller.getPlayerObject().getOccupiedTerritories().size(); i++) {
			territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getName(),i,0);
			for(int j = 0; j < controller.getPlayerObject().getOccupiedTerritories().size(); j++) {
				territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getArmiesOnTerritory(),i,1);
			}
		}	
	}
	
}
