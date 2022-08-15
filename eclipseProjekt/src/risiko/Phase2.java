package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
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
	
	private static final Frame attackFrame = null;
	private Controller controller;
	private JLabel controlfieldLabel;
	private ImageIcon controlfieldIcon;
	private Icon buttonIcon;
	private ImageIcon coatIcon;
	
	private JMenuBar menuBar;
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
	private ResourcesGUI resource;
	private Dimension screenSize;
	
	private ArrayList<Integer[]> dice;
		

	
	public ArrayList<Integer[]> getDice() {
		return dice;
	}

	public Phase2(Controller controller) {
		System.out.println("Phase 2 erstellt");
		this.controller = controller;
		resource = new ResourcesGUI();
		dtcr = new DefaultTableCellRenderer();
	
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		controlfieldIcon = resource.getControlfieldIcon();
		coatIcon = resource.getCoatIcon(controller, 1);
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
		this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		this.setLayout(new BorderLayout());
		
		buttonColor = new Color(239, 228, 176);
		 
		menuBar = resource.getMenu();
		this.add(menuBar, BorderLayout.NORTH);
		this.setLayout(null);
		
		help = resource.getHelpButton(2);
		this.add(help);
		
		playerInformationLabel = new JLabel(controller.getPlayerObject().getName(), coatIcon, SwingConstants.CENTER);
		playerInformationLabel.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 170)/273)/2,
				(screenSize.height*85)/768, ((screenSize.width*2/10) * 170)/273, (screenSize.height*40)/768);;
		playerInformationLabel.setIconTextGap(((screenSize.width* 2/10) * 12)/273);	
		playerInformationLabel.setBackground(buttonColor);
		playerInformationLabel.setOpaque(true);
		playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 18 / 768));
		this.add(playerInformationLabel);
		
		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*140)/768,  
				((screenSize.width*2/10) * 240)/273, (screenSize.height*35)/768);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setText("Wählen Sie das Startgebiet");
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 14 / 768));
		guideDisplay.setEditable(false);
		this.add(guideDisplay);		
		
		startPositionAttack = new JTextField();
		startPositionAttack.setHorizontalAlignment(SwingConstants.CENTER);
		startPositionAttack.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*305)/768, 
				 ((screenSize.width* 2/10) * 190)/273, (screenSize.height*35)/768);
		startPositionAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		startPositionAttack.setBackground(buttonColor);
		startPositionAttack.setEditable(false);
		this.add(startPositionAttack);
		
		unitCounterAttackModel = new SpinnerNumberModel(1, 1, 3, 1);
		unitCounterAttack = new JSpinner(unitCounterAttackModel);
		unitCounterAttack.setBounds((screenSize.width*2/10 + ((screenSize.width*2/10) * 160)/273)/2,(screenSize.height*305)/768, 
				 ((screenSize.width*2/10) *40)/273, (screenSize.height*35)/768);
		unitCounterAttack.setBackground(Color.blue);
		unitCounterAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		unitCounterAttack.getComponent(0).setBackground(buttonColor);
		unitCounterAttack.getComponent(1).setBackground(buttonColor);
		unitCounterAttack.getEditor().getComponent(0).setBackground(buttonColor);
		this.add(unitCounterAttack);
		
		attackedPosition = new JTextField();
		attackedPosition.setHorizontalAlignment(SwingConstants.CENTER);
		attackedPosition.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*255)/768, 
				 ((screenSize.width*2/10) *190)/273, (screenSize.height*35)/768);
		attackedPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		attackedPosition.setBackground(buttonColor);
		attackedPosition.setEditable(false);
		this.add(attackedPosition);
		
		attack = new JButton("Gegner angreifen", buttonIcon);
		attack.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*355)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
		attack.setHorizontalTextPosition(SwingConstants.CENTER);
		attack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		attack.addActionListener(this);
		this.add(attack);
	
		endPhaseAttack = new JButton("Phase beenden", buttonIcon);
		endPhaseAttack.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*445)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
		endPhaseAttack.setHorizontalTextPosition(SwingConstants.CENTER);
		endPhaseAttack.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		endPhaseAttack.addActionListener(this);
		this.add(endPhaseAttack);
		
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
		updateTable();
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
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*160)/768);;
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
	
		if(e.getSource() == this.attack) {
    
			

			Player tmp = controller.activeTerritory2.getOccupier();
			int armies = (int) unitCounterAttack.getValue();
		
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
			new AttackPopUp(attackFrame, controller, this);	
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
