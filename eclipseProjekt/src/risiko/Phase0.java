package risiko;

import java.awt.Color;
import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Phase0 extends JPanel implements ActionListener{

<<<<<<< HEAD
private Controller controller;	
//private JFrame jframe = new JFrame();
private JPanel panelMap;
=======
>>>>>>> 896a375 (Lost)
private JPanel panelCf;
private JLabel controlfieldLabel;
private ImageIcon controlfieldIcon;
private Icon buttonIcon;
private ImageIcon coatIcon;
private ImageIcon menuIcon;

private JMenuBar menuBar;
private JMenu menu;
private JMenuItem speichern;
private JMenuItem beenden;

private JLabel playerInformationLabel;
private JTextField guideDisplay;
private JTextField selectedTerritory;
private JButton putUnit;

//Hilfs-PopUp
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

private Color buttonColor;
private Integer phase = 0;
private controlerTry cntrl;
private Dimension screenSize;

	
	
	public Phase0 (Controller controller){
		this.controller = controller;
		
		cntrl = new controlerTry();
		
		//Bild einlesen
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		controlfieldIcon = new ImageIcon("assets\\Velazquez-The_Surrender_of_Breda.jpg");
		Image controlfieldImage = controlfieldIcon.getImage();
		Image modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*9/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);	
		
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
<<<<<<< HEAD
		greenIcon = new ImageIcon(cntrl.getPlayerCoat().get(controller.getPlayerAtTurn()));
		Image greenImage = greenIcon.getImage();
=======
		coatIcon = new ImageIcon(cntrl.getPlayerCoat().get(playerNumber));
		Image greenImage = coatIcon.getImage();
>>>>>>> 896a375 (Lost)
		Image modGreenImage = greenImage.getScaledInstance(303*1/13, 448*1/13, java.awt.Image.SCALE_SMOOTH);
		coatIcon = new ImageIcon(modGreenImage);
		
		menuIcon = new ImageIcon("assets\\Floris_Claesz._van_Dyck_001.jpg");
		Image menuImage = menuIcon.getImage();
		Image modMenuImage = menuImage.getScaledInstance(2048*1/30, 1255*1/30, java.awt.Image.SCALE_SMOOTH);
		menuIcon = new ImageIcon(modMenuImage);
		
<<<<<<< HEAD
		
		
=======
		panelCf = new JPanel();
		panelCf.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		panelCf.setLayout(new BorderLayout());
>>>>>>> 896a375 (Lost)
		
		controlfieldLabel = new JLabel (controlfieldIcon);
		controlfieldLabel.setBounds(0,0,screenSize.width*2/10,screenSize.height);
		
<<<<<<< HEAD

		this.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height));
		this.setLayout(new BorderLayout());
		
=======
>>>>>>> 896a375 (Lost)
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
		
<<<<<<< HEAD
		playerInformationBackground = new JLabel(controller.getPlayerObject().getName(), greenIcon, SwingConstants.CENTER);
		playerInformationBackground.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*85)/768, 170, 40);
		playerInformationBackground.setIconTextGap(12);	
		playerInformationBackground.setBackground(buttonColor);
		playerInformationBackground.setOpaque(true);
		playerInformationBackground.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		this.add(playerInformationBackground);
=======
		playerInformationLabel = new JLabel("Player Three", coatIcon, SwingConstants.CENTER);
		playerInformationLabel.setBounds((screenSize.width*2/10 - 170)/2,(screenSize.height*85)/768, 170, 40);
		playerInformationLabel.setIconTextGap(12);	
		playerInformationLabel.setBackground(buttonColor);
		playerInformationLabel.setOpaque(true);
		playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		panelCf.add(playerInformationLabel);
>>>>>>> 896a375 (Lost)
		
		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*140)/768, 240, 35);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		guideDisplay.setText("Verteilen Sie ihre Armeen" );
		guideDisplay.setEditable(false);
		
		this.add(guideDisplay);
	
		help = new JButton("?", buttonIcon);
		help.setBounds((screenSize.width*2/10 - 152)/2,(screenSize.height*0)/768, 45, 43);
		help.setHorizontalTextPosition(SwingConstants.CENTER);
		help.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 24));
	
		help.addActionListener(this); 
		this.add(help);
		
		selectedTerritory = new JTextField();
		selectedTerritory.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*275)/768, 240, 35);
		selectedTerritory.setHorizontalAlignment(SwingConstants.CENTER);
		selectedTerritory.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		selectedTerritory.setBackground(buttonColor);
		selectedTerritory.setEditable(false);
		this.add(selectedTerritory);

		putUnit = new JButton("Armee setzen", buttonIcon);
		putUnit.setBounds((screenSize.width*2/10 - 240)/2,(screenSize.height*325)/768, 240, 35);
		putUnit.setHorizontalTextPosition(SwingConstants.CENTER);
		putUnit.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));

		this.add(putUnit);
	
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
		this.add (unitsDisplay);
		
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
<<<<<<< HEAD
					
			helpDisplay.setText(cntrl.getHelpText().get(0)); //wegen for-Schleife
=======
			helpDisplay.setText(cntrl.getHelpText().get(phase-1)); //wegen for-Schleife
>>>>>>> 896a375 (Lost)
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
		else if(e.getSource() == this.putUnit) {
			setArmies();
			//TODO
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
	
	private void setArmies() {
		controller.placeArmyInitial(controller.activeTerritory);
		
	}
	
}
