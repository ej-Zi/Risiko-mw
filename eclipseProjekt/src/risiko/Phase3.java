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

public class Phase3 extends JPanel implements ActionListener{

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
		private controlerTry cntrl;
		private Dimension screenSize;
	
		
		public Phase3(Controller controller) {
			
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
			
			playerInformationLabel = new JLabel(controller.getPlayerObject().getName(), coatIcon, SwingConstants.CENTER);
			playerInformationLabel.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 170)/273)/2,
					(screenSize.height*85)/768, ((screenSize.width*2/10) * 170)/273, (screenSize.height*40)/768);
			playerInformationLabel.setIconTextGap(12);	
			playerInformationLabel.setBackground(buttonColor);
			playerInformationLabel.setOpaque(true);
			playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
			this.add(playerInformationLabel);
			
			guideDisplay = new JTextField();
			guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*140)/768,  
					((screenSize.width*2/10) * 240)/273, (screenSize.height*35)/768);
			guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			guideDisplay.setBackground(buttonColor);
			guideDisplay.setText("Bewegen Sie Ihre Armeen");
			guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			guideDisplay.setEditable(false);
			this.add(guideDisplay);
		
			help = new JButton("?", buttonIcon);
			help.setBounds((screenSize.width*2/10 - 152)/2,(screenSize.height*0)/768, 45, 43);
			help.setHorizontalTextPosition(SwingConstants.CENTER);
			help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 24));
			help.addActionListener(this); 
			this.add(help);
			
			startPositionMovement = new JTextField("The Kingdom of Song");
			startPositionMovement.setHorizontalAlignment(SwingConstants.CENTER);
			startPositionMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*305)/768, 
					 ((screenSize.width* 2/10) * 190)/273, (screenSize.height*35)/768);
			startPositionMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			startPositionMovement.setBackground(buttonColor);
			startPositionMovement.setEditable(false);
			this.add(startPositionMovement);
			
			unitCounterMovementModel = new SpinnerNumberModel(0, 0, 99, 1);
			unitCounterMovement = new JSpinner(unitCounterMovementModel);
			unitCounterMovement.setBounds((screenSize.width*2/10 + 160)/2,(screenSize.height*305)/768, 
					 ((screenSize.width*2/10) *40)/273, (screenSize.height*35)/768);
			unitCounterMovement.setBackground(Color.blue);
			unitCounterMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			unitCounterMovement.getComponent(0).setBackground(buttonColor);
			unitCounterMovement.getComponent(1).setBackground(buttonColor);
			unitCounterMovement.getEditor().getComponent(0).setBackground(buttonColor);
			this.add(unitCounterMovement);
			
			movedToPosition = new JTextField("South Valoran");
			movedToPosition.setHorizontalAlignment(SwingConstants.CENTER);
			movedToPosition.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*255)/768, 
					 ((screenSize.width*2/10) *190)/273, (screenSize.height*35)/768);
			movedToPosition.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			movedToPosition.setBackground(buttonColor);
			movedToPosition.setEditable(false);
			this.add(movedToPosition);
			
			unitMovement = new JButton("Armeen bewegen", buttonIcon);
			unitMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*355)/768, 
					 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
			unitMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			unitMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			unitMovement.addActionListener(this);
			this.add(unitMovement);
		
			endPhaseMovement = new JButton("Phase beenden", buttonIcon);
			endPhaseMovement.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*445)/768, 
					 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
			endPhaseMovement.setHorizontalTextPosition(SwingConstants.CENTER);
			endPhaseMovement.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
			endPhaseMovement.addActionListener(this);
			this.add(endPhaseMovement);
			
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
			territoriesDisplay = new JScrollPane(territoriesTable);
			territoriesDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			territoriesDisplay.setBounds((screenSize.width*2/10 - 240)/2, (screenSize.height*570)/768, 
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
			// TODO Auto-generated method stub
		
			if(e.getSource() == this.help) {
			
				new HelpPopUp(helpFrame, 3);
			
			}
			else if(e.getSource() == this.beenden) {
				
				System.exit(0);
			}
			else if(e.getSource() == this.unitMovement) {
				
				unitCounterMovement.getValue();
			}
			else if(e.getSource() == this.endPhaseMovement) {
				
				
			}
		}
}
