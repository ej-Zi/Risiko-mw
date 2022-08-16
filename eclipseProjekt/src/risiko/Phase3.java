package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class Phase3 extends JPanel implements ActionListener{

		private Controller controller;		
		private JLabel controlfieldLabel;
		private ImageIcon controlfieldIcon;
		private Icon buttonIcon;
		private ImageIcon coatIcon;
		
		private JMenuBar menuBar;
		private JButton help;
		
		private JLabel playerInformationLabel;
		private JTextField guideDisplay;
		private JTextField startPositionMovement;
		private JTextField movedToPosition;
		private JSpinner unitCounterMovement;
		private SpinnerNumberModel unitCounterMovementModel;
		private JButton unitMovement;
		private JButton endPhaseMovement;
		
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
	
		
		public Phase3(Controller controller) {
			
			this.controller = controller;
			resource = new ResourcesGUI();
			dtcr = new DefaultTableCellRenderer(); 
			
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
			
			controlfieldIcon = resource.getControlfieldIcon();
			coatIcon = resource.getCoatIcon(controller, 1, controller.game.getPlayers().indexOf(controller.getPlayerObject()));
			buttonIcon = new ImageIcon("assets\\OldPaper2.png");
			
			controlfieldLabel = new JLabel (controlfieldIcon);
			controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
			
			this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
			this.setLayout(new BorderLayout());
			
			buttonColor = new Color(239, 228, 176);
			
			menuBar = resource.getMenu();
			this.add(menuBar, BorderLayout.NORTH);
			this.setLayout(null);
			
			help = resource.getHelpButton(3);
			this.add(help);
			
			playerInformationLabel = new JLabel(controller.getPlayerObject().getName(), coatIcon, SwingConstants.CENTER);
			playerInformationLabel.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 170)/273)/2,
					(screenSize.height*85)/768, ((screenSize.width*2/10) * 170)/273, (screenSize.height*40)/768);
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
			guideDisplay.setText("Bewegen Sie Ihre Armeen");
			guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 14 / 768));
			guideDisplay.setEditable(false);
			this.add(guideDisplay);
			
			startPositionMovement = new JTextField();
			startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
			startPositionMovement.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*305)/768, 
					 ((screenSize.width* 2/10) * 190)/273, (screenSize.height*35)/768);
			startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			startPositionMovement.setBackground(buttonColor);
			startPositionMovement.setEditable(false);
			this.add(startPositionMovement);
			
			unitCounterMovementModel = new SpinnerNumberModel(0, 0, 99, 1);
			unitCounterMovement = new JSpinner(unitCounterMovementModel);
			unitCounterMovement.setBounds((screenSize.width*2/10 + ((screenSize.width*2/10) * 160)/273)/2,(screenSize.height*305)/768, 
					 ((screenSize.width*2/10) *40)/273, (screenSize.height*35)/768);
			unitCounterMovement.setBackground(Color.blue);
			unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			unitCounterMovement.getComponent(0).setBackground(buttonColor);
			unitCounterMovement.getComponent(1).setBackground(buttonColor);
			unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
			this.add(unitCounterMovement);
			
			movedToPosition = new JTextField();
			movedToPosition.setHorizontalAlignment(SwingConstants.CENTER);
			movedToPosition.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*255)/768, 
					 ((screenSize.width*2/10) *190)/273, (screenSize.height*35)/768);
			movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			movedToPosition.setBackground(buttonColor);
			movedToPosition.setEditable(false);
			this.add(movedToPosition);
			
			unitMovement = new JButton("Armeen bewegen", buttonIcon);
			unitMovement.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*355)/768, 
					 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
			unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			unitMovement.addActionListener(this);
			this.add(unitMovement);
		
			endPhaseMovement = new JButton("Phase beenden", buttonIcon);
			endPhaseMovement.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*445)/768, 
					 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
			endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			endPhaseMovement.addActionListener(this);
			this.add(endPhaseMovement);
			
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
	
		
			if(e.getSource() == this.unitMovement) {
				controller.moveArmies((int) unitCounterMovement.getValue());
				updateTable();
				controller.updateMap();
			}
			else if(e.getSource() == this.endPhaseMovement) {
				controller.getGui().changePhase(4);
				
			}
		}
		
		public void updateSelectedTerritory() {
			if(controller.activeTerritory != null) {
				startPositionMovement.setText(controller.activeTerritory.getName());
				guideDisplay.setText("Wählen Sie das Zielgebiet");
				movedToPosition.setText("");
			}else {
				startPositionMovement.setText("");
				movedToPosition.setText("");
				this.guideDisplay.setText("Wählen Sie das Startgebiet");
			}
			
			if(controller.activeTerritory2 != null && controller.activeTerritory != null) {
				if(controller.validTerritory2()) {
					movedToPosition.setText(controller.activeTerritory2.getName());
					guideDisplay.setText("Bestätigen Sie");
				}else {
					movedToPosition.setText("");
					this.guideDisplay.setText("Wählen Sie das Zielgebiet");
				}
			}else {
				movedToPosition.setText("");
			}
			
		}
		
		private void updatePlayerInfo() {
			coatIcon = resource.getCoatIcon(controller, 1, controller.game.getPlayers().indexOf(controller.getPlayerObject()));
			
			playerInformationLabel.setText(controller.getPlayerObject().getName());
			playerInformationLabel.setIcon(coatIcon);
			updateTable();
		}
		
		private void updateTable() {
			for(int i = 0; i < controller.getPlayerObject().getOccupiedTerritories().size(); i++) {
				territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getName(),i,0);
				for(int j = 0; j < controller.getPlayerObject().getOccupiedTerritories().size(); j++) {
					territoriesTable.setValueAt(controller.getPlayerObject().getOccupiedTerritories().get(i).getArmiesOnTerritory(),i,1);
				}
			}	
		}
		
		public void updatePanel() {
			updatePlayerInfo();
			updateTable();
			unitCounterMovementModel.setValue(0);
			startPositionMovement.setText("");
			movedToPosition.setText("");
			guideDisplay.setText("Bewegen Sie Ihre Armeen");
		}
}
