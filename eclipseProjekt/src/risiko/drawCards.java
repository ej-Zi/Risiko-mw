package risiko;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout.Constraints;
import javax.swing.border.Border;


public class drawCards extends JPanel{
	Dimension screenSize;
	
	
	public drawCards() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Color backgroundColor = new Color(237,223,176);
		int width = screenSize.width*8/10;
		int height = screenSize.height;
	
		this.setLayout(new GridBagLayout());
		
		this.setBackground(backgroundColor);
		JPanel cardPanel = new JPanel();
		JLabel chooseYourCards = new JLabel("Wähle Karten aus: ");
		ImageIcon cardIcon = new ImageIcon("assets\\card.png");	
		ImageIcon cardSelIcon = new ImageIcon("assets\\cardSelected.png");	

		int numOfCards = 5;

		cardPanel.setSize(cardIcon.getIconWidth()*numOfCards, cardIcon.getIconHeight());
		cardPanel.setBackground(backgroundColor);
		cardPanel.setVisible(true);
	
		GridLayout gridLayout = new GridLayout();
		cardPanel.setLayout(gridLayout);
		gridLayout.setHgap(150/numOfCards);
		gridLayout.setVgap(150/numOfCards);
		
		for(int i = 0; i < numOfCards; ++i) {
			JToggleButton cardButton = new JToggleButton("Button" + i, cardIcon);
			cardButton.setFont(new Font("Algerian", Font.ROMAN_BASELINE, 30));
			//cardButton.setForeground(Color.green);
			cardButton.setBorder(BorderFactory.createEmptyBorder());
			cardButton.setContentAreaFilled(false);
			cardButton.setBackground(backgroundColor);
			cardButton.setSelectedIcon(cardSelIcon);
			cardButton.setHorizontalTextPosition(JButton.CENTER);
			cardButton.setVerticalTextPosition(JButton.CENTER);
			cardPanel.add(cardButton);
		}
		this.add(cardPanel);

		
//		//ArrayList<Card> cardsToBeDisplayed = p.getCardsInHand();
//		
//		for(Card c:cardsToBeDisplayed) {
//			
//		}
//		
		
		this.setVisible(true);
	}

}
