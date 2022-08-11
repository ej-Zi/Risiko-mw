package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

public class Phase3 extends JPanel implements ActionListener{

	//private JFrame jframe = new JFrame();
		private JPanel panelMap;
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
		
		public Phase3() {
			
			cntrl = new controlerTry();
			
			//Bild einlesen
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
			
			
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
			

			
			controlfieldLabel = new JLabel (controlfieldIcon);
			controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
			
			this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
			this.setLayout(new BorderLayout());
			
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
			this.add(menuBar, BorderLayout.NORTH);
			this.setLayout(null);
			
			playerInformationBackground = new JLabel("Player Three", greenIcon, SwingConstants.CENTER);
			playerInformationBackground.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*85)/768, 170, 40);
			playerInformationBackground.setIconTextGap(12);	
			playerInformationBackground.setBackground(buttonColor);
			playerInformationBackground.setOpaque(true);
			playerInformationBackground.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			this.add(playerInformationBackground);
			
			guideDisplay = new JTextField();
			guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*140)/768, 240, 35);
			guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			guideDisplay.setBackground(buttonColor);
			guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			guideDisplay.setEditable(false);
			
			this.add(guideDisplay);
		
			help = new JButton("?", buttonIcon);
			help.setBounds((screenSize.width*2/10 - 152)/2,(screenSize.height*0)/768, 45, 43);
			help.setHorizontalTextPosition(SwingConstants.CENTER);
			help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 24));
		
			help.addActionListener(this); 
			this.add(help);
			
//			this.remove(startPositionAttack);
//			this.remove(unitCounterAttack);
//			this.remove(attackedPosition);
//			this.remove(attack);
//			this.remove(endPhaseAttack);
			
			startPositionMovement = new JTextField("The Kingdom of Song");
			startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
			startPositionMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 190, 35);
			startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			startPositionMovement.setBackground(buttonColor);
			startPositionMovement.setEditable(false);
			this.add(startPositionMovement);
			
			guideDisplay.setText("Bewegen Sie Ihre Armeen");
			
			unitCounterMovementModel = new SpinnerNumberModel(0, 0, 99, 1);
			unitCounterMovement = new JSpinner(unitCounterMovementModel);
			unitCounterMovement.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 40, 35);
			unitCounterMovement.setBackground(Color.blue);
			unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			unitCounterMovement.getComponent(0).setBackground(buttonColor);
			unitCounterMovement.getComponent(1).setBackground(buttonColor);
			unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
			this.add(unitCounterMovement);
			
			movedToPosition = new JTextField("South Valoran");
			movedToPosition.setHorizontalAlignment(SwingConstants.CENTER);
			movedToPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 190, 35);
			movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			movedToPosition.setBackground(buttonColor);
			movedToPosition.setEditable(false);
			this.add(movedToPosition);
			
			//attack.setText("Armeen bewegen");
			
			unitMovement = new JButton("Armeen bewegen", buttonIcon);
			unitMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 240, 35);
			unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			this.add(unitMovement);
		
			endPhaseMovement = new JButton("Phase beenden", buttonIcon);
			endPhaseMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 240, 35);
			endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			this.add(endPhaseMovement);
			
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
			this.add (territoriesDisplay);
			
			//Bild auf Panel
			this.add(controlfieldLabel);
			
		
			
			
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
						
				helpDisplay.setText(cntrl.getHelpText().get(phase-1)); //wegen for-Schleife
				helpDisplay.setCaretPosition(0);
						
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
			else if(e.getSource() == this.unitMovement) {
				
				unitCounterMovement.getValue();
			}
			
			else if(e.getSource() == this.endPhaseMovement) {
				
				phase = phase + 1;
			}
		}
}
