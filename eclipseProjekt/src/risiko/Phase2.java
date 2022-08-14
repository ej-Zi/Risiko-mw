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
import java.util.ArrayList;

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

public class Phase2 extends JPanel implements ActionListener{
	
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
		private JTextField startPositionAttack;
		private JTextField attackedPosition;
		private JSpinner unitCounterAttack;
		private SpinnerNumberModel unitCounterAttackModel;
		private JButton attack;
		private JButton endPhaseAttack;
		
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
		
	
	public Phase2(Controller controller) {
		System.out.println("Phase 2 erstellt");
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
		help.setBounds((screenSize.width*2/10 - 152)/2,(screenSize.height*0)/768, 45, 43);
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
		guideDisplay.setText("Wählen Sie das Startgebiet");
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		guideDisplay.setEditable(false);
		this.add(guideDisplay);		
		
		startPositionAttack = new JTextField();
		startPositionAttack.setHorizontalAlignment(SwingConstants.CENTER);
		startPositionAttack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 190, 35);
		startPositionAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		startPositionAttack.setBackground(buttonColor);
		startPositionAttack.setEditable(false);
		this.add(startPositionAttack);
		
		unitCounterAttackModel = new SpinnerNumberModel(1, 1, 3, 1);
		unitCounterAttack = new JSpinner(unitCounterAttackModel);
		unitCounterAttack.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 40, 35);
		unitCounterAttack.setBackground(Color.blue);
		unitCounterAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		unitCounterAttack.getComponent(0).setBackground(buttonColor);
		unitCounterAttack.getComponent(1).setBackground(buttonColor);
		unitCounterAttack.getEditor().getComponent(0).setBackground(buttonColor);
		this.add(unitCounterAttack);
		
		attackedPosition = new JTextField();
		attackedPosition.setHorizontalAlignment(SwingConstants.CENTER);
		attackedPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 190, 35);
		attackedPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		attackedPosition.setBackground(buttonColor);
		attackedPosition.setEditable(false);
		this.add(attackedPosition);
		
		attack = new JButton("Gegner angreifen", buttonIcon);
		attack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 240, 35);
		attack.setHorizontalTextPosition(SwingConstants.CENTER);
		attack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		attack.addActionListener(this);
		this.add(attack);
	
		endPhaseAttack = new JButton("Phase beenden", buttonIcon);
		endPhaseAttack.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 240, 35);
		endPhaseAttack.setHorizontalTextPosition(SwingConstants.CENTER);
		endPhaseAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		endPhaseAttack.addActionListener(this);
		this.add(endPhaseAttack);
		
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
		updateTable();
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
		
			new HelpPopUp(helpFrame, 2);
		
		}
		else if(e.getSource() == this.beenden) {
			
			System.exit(0);
		}
		else if(e.getSource() == this.attack) {
	
			Player tmp = controller.activeTerritory2.getOccupier();
			int armies = (int) unitCounterAttack.getValue();
			ArrayList<Integer[]> dice;
			dice = controller.attack(armies);
			
			//zum Testen:
			if(dice != null) {
				for(int i = 0; i < dice.size(); i++) {
					for(int j = 0; j < dice.get(i).length; j++) {
						System.out.print(dice.get(i)[j] + " ");
					}
					System.out.println();
				}
			}
	
			//TODO gewuerfeltes anzeigen
			updateTable();
			controller.updateMap();
			
			if(controller.activeTerritory2.getOccupier() != tmp) {
				//TODO Eroberung (irgendwo anzeigen?)
			}
			
		}
		else if(e.getSource() == this.endPhaseAttack) {
			controller.getGui().changePhase(3);
		
		}
	}	
	
	public void updateSelectedTerritory() {
		if(controller.activeTerritory != null) {
			startPositionAttack.setText(controller.activeTerritory.getName());
			guideDisplay.setText("Wählen Sie das Zielgebiet");
		}else {
			startPositionAttack.setText("");
			attackedPosition.setText("");
			this.guideDisplay.setText("Wählen Sie das Startgebiet");
		}
		
		if(controller.activeTerritory2 != null && controller.activeTerritory != null) {
			if(controller.validTerritory2()) {
				attackedPosition.setText(controller.activeTerritory2.getName());
				guideDisplay.setText("Greifen Sie an");
			}else {
				attackedPosition.setText("");
				this.guideDisplay.setText("Wählen Sie das Zielgebiet");
			}
		}else {
			attackedPosition.setText("");
		}
		
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
