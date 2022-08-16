package risiko;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Phase4 extends JPanel{
	
	private JLabel controlfieldLabel;
	private ImageIcon controlfieldIcon;
	private Image controlfieldImage;
	private Image modControlfieldImage;
	private ImageIcon coatIcon;
	
	private JMenuBar menuBar;
	private JButton help;
	
	private JLabel playerInformationLabel;
	private JTextField guideDisplay;
	
	private JScrollPane territoriesDisplay;
	private String [][]territoriesList;
	private JTable territoriesTable;
	private String [] territoriesTitel = {"Besetzte Gebiete", "Armeen"};
	private DefaultTableModel territoriesTableModel;
	private JButton finishRound;
	private TableColumn territoriesColumn1;
	private TableColumn territoriesColumn2;
	private DefaultTableCellRenderer dtcr;
	
	private Color buttonColor;
	private ResourcesGUI resource;
	private Dimension screenSize;
	private Controller controller;
	private ImageIcon buttonIcon;
	
	
	public Phase4(Controller controller){
		
		this.controller = controller;
		resource = new ResourcesGUI();
		dtcr = new DefaultTableCellRenderer(); 
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		
		coatIcon = resource.getCoatIcon(controller, 1, controller.game.getPlayers().indexOf(controller.getPlayerObject()));
		buttonIcon = new ImageIcon("assets\\OldPaper2.png");
		
		controlfieldIcon = new ImageIcon("assets\\GambleTable2.jpg");
		controlfieldImage = controlfieldIcon.getImage();
		modControlfieldImage = controlfieldImage.getScaledInstance(screenSize.width*8/10, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		controlfieldIcon = new ImageIcon(modControlfieldImage);	
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
		playerInformationLabel.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 18 / 768));
		this.add(playerInformationLabel);

		guideDisplay = new JTextField();
		guideDisplay.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*140)/768,  
				((screenSize.width*2/10) * 240)/273, (screenSize.height*35)/768);
		guideDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		guideDisplay.setBackground(buttonColor);
		guideDisplay.setText("Verteilen Sie ihre Armeen" );
		guideDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 14 / 768));
		guideDisplay.setEditable(false);	
		this.add(guideDisplay);
		
		
		finishRound = new JButton("Phase beenden", buttonIcon);
		finishRound.setBounds((screenSize.width*2/10 - ((screenSize.width*2/10) * 240)/273)/2,(screenSize.height*445)/768, 
				 ((screenSize.width*2/10) *240)/273, (screenSize.height*35)/768);
		finishRound.setHorizontalTextPosition(SwingConstants.CENTER);
		finishRound.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, screenSize.height * 16 / 768));
		finishRound.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.nextPlayer();
				controller.getGui().changePhase(1);
				
			}}));
		if(controller.getPlayerObject().getCardsInHand().size() > 5) {
			finishRound.setEnabled(false);
		}
		
		this.add(finishRound);

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
	}
}
