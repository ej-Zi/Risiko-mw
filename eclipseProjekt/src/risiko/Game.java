package risiko;

import java.util.ArrayList;
import java.util.Random;

public class Game extends GameInitializer {

	private static Game instance = null;
	private static int cardSetCount;
	
	private Game(int anzahlSpieler, ArrayList<String> namen) {
		super(anzahlSpieler, namen);
		cardSetCount = 0;
	}

	public static Game getInstance(int anzahlSpieler, ArrayList<String> namen) {
		if(instance == null) {
			return new Game(anzahlSpieler, namen);
		}else {
			return null;
		}
	}
	
	//-----Spielmethoden-----
	//Karte ziehen:
	public void drawCard(Player player) {
		Random random = new Random();
		int randomNumber = random.nextInt(this.cards.size());;
		while(this.cards.get(randomNumber) == null) {
			randomNumber = random.nextInt(44);
		}
		player.getCardsInHand().add(this.cards.get(randomNumber));
		this.cards.remove(randomNumber);
	}
	
	public static void resetCardSetCount() {
		cardSetCount = 0;
	}
	
	//Karten einsetzen: returns true if successful; irgendwo sicherstellen, dass Karten dem Spieler gehören
	public boolean tradeCards(Player player, Card c1, Card c2, Card c3) {
		Card[] tmpCards = {c1, c2, c3};
		if(validCards(c1, c2, c3)) {
			int[] numberOfArmies = {4, 6, 8, 10, 12, 15};
			player.setArmies(player.getArmies() + numberOfArmies[cardSetCount]);
			if(c1.getTerritory().getOccupier() == player || c2.getTerritory().getOccupier() == player || c3.getTerritory().getOccupier() == player) {
				player.setArmies(player.getArmies() + 2);
			}
			cardSetCount += 1;
			for(Card c : tmpCards) {
				player.getCardsInHand().remove(c);
				this.cards.add(c);
			}
			return true;
		}else {
			return false;
		}	
	}
	
	private boolean validCards(Card c1, Card c2, Card c3) {
		String[] cardSymbols = {c1.getSymbol(), c2.getSymbol(), c3.getSymbol()};
		for(int i = 0; i < 2; i++) {
			if(cardSymbols[i].equals("Joker")) {
				return true;
			}
		}		
		int[] counter = new int[3];
		for(String s : cardSymbols) {
			if(s.equals("Kavallerie")) {
				counter[0] += 1;
			}else if(s.equals("Artillerie")) {
				counter[1] += 1;
			}else if(s.equals("Infanterie")) {
				counter[2] += 1;
			}
		}
		for(int c : counter) {
			if(c == 3) {
				return true;
			}
		}
		if(counter[0] == 1 && counter[1] == 1 && counter[2] == 1) {
			return true;
		}else {
			return false;
		}
	}
	
}
