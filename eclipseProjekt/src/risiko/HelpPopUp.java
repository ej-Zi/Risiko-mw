package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class HelpPopUp {

	private JDialog helpPopUp;
	private JTextArea helpDisplay;
	private JPanel helpPanelText;
	private JPanel helpPanelButton;
	private JScrollPane scrollHelpDisplay;
	private JButton closeHelp;
	private Color buttonColor;
	private ResourcesGUI resource;
	
	
	public HelpPopUp (Frame phase0, int phase) {
		
		buttonColor = new Color(239, 228, 176);
		resource = new ResourcesGUI();
				
		helpPopUp = new JDialog(phase0, true);
		helpPopUp.setBounds(300, 50, 250, 200);
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
		helpDisplay.setBounds( 1, 1, 248, 160);
		helpDisplay.setBackground(buttonColor);
		helpDisplay.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		helpDisplay.setEditable(false);
		helpDisplay.setText(resource.getHelpText().get(phase)); 
		helpDisplay.setCaretPosition(0);
		helpDisplay.setLineWrap(true);
		helpDisplay.setWrapStyleWord(true);
		scrollHelpDisplay = new JScrollPane(helpDisplay);
		
		scrollHelpDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollHelpDisplay.setBounds(1, 1, 248, 160);
		scrollHelpDisplay.getViewport().setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().getComponent(0).setBackground(buttonColor);
		scrollHelpDisplay.getVerticalScrollBar().getComponent(1).setBackground(buttonColor);
		helpPopUp.add(scrollHelpDisplay);
		
		closeHelp = new JButton("Ok", new ImageIcon("assets\\OldPaper2.png"));
		closeHelp.setPreferredSize(new Dimension(60, 30));
		closeHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		closeHelp.setFont(new java.awt.Font("Algerian", Font.ROMAN_BASELINE, 16));
		closeHelp.addActionListener(new ActionListener() {
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
		helpPopUp.setVisible(true);
		
	}
	
}
