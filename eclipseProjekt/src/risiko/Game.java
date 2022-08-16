package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game extends GameInitializer {

	private static Game instance = null;
	private static int cardSetCount;
	private static ArrayList<Territory> tmp; //benutzt fuer Wegsuche beim verschieben
	public static boolean valid;
	
	private Game(int anzahlSpieler, ArrayList<String> namen) {
		super(anzahlSpieler, namen);
		cardSetCount = 0;
		tmp = new ArrayList<>();
		valid = false;
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
	
	public boolean validCards(Card c1, Card c2, Card c3) {
		String[] cardSymbols = {c1.getSymbol(), c2.getSymbol(), c3.getSymbol()};
		for(int i = 0; i < 3; i++) {
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
	
	//Armee setzen
	public boolean placeArmies(Player player, Territory territory, int amount) {
		if(player.getArmies() >= amount && territory.getOccupier() == player) {
			territory.setArmiesOnTerritory(territory.getArmiesOnTerritory() + amount);
			player.setArmies(player.getArmies() - amount);
			return true;
		}else {
			return false;
		}
	}
	
	//Armeen verschieben
	public boolean moveArmies(Player player, Territory start, Territory destination, int amount) {
		if(start.getArmiesOnTerritory() - amount > 0 && movePossible(player, start, destination)) {
			start.setArmiesOnTerritory(start.getArmiesOnTerritory() - amount);
			destination.setArmiesOnTerritory(destination.getArmiesOnTerritory() + amount);
			return true;
		}else {
			return false;
		}
	}
	public boolean movePossible(Player player, Territory start, Territory destination) {
		tmp.add(destination);
		if(start.getOccupier() == player && destination.getOccupier() == player && start != destination) {
			for(Territory t : destination.getBorderingTerritories()) {
				if(t.getOccupier() == player) {
					if(t == start) {
						valid = true;
					}else if(!tmp.contains(t)){
						movePossible(player, start, t);
					}
				}
			}
			return valid;
		}else {
			return false;
		}
	}
	
	//Armeen erhalten in Nachschubphase/Verstaerkungsphase:
	public int recruiting(Player player) {
		int numberOfArmies;
		numberOfArmies = player.getOccupiedTerritories().size()/3;
		if(numberOfArmies < 3) {
			numberOfArmies = 3;
		}
		for(Continent c : this.continents) {
			if(occupiesContinent(player, c)) {
				numberOfArmies = numberOfArmies + c.getBonusArmies();
			}
		}
		player.setArmies(player.getArmies() + numberOfArmies);
		return numberOfArmies;
	}
	//besetzt gesamten Kontinent
	private boolean occupiesContinent(Player player, Continent continent) {
		for(Territory t : continent.getTerritoriesOnContinent()) {
			if(!player.getOccupiedTerritories().contains(t)) {
				return false;
			}
		}
		return true;
	}
	
	//Angriff: returns Liste mit gewuerfelten Zahlen wenn erfolgreich (attacker index 0, defender index 1), sonst returns null
	public ArrayList<Integer[]> attack(Player attacker, int armies, Territory start, Territory target){
		if(start.getOccupier() == attacker && target.getOccupier() != attacker && start.getBorderingTerritories().contains(target) && armies > 0) {
			Random random = new Random();
			ArrayList<Integer[]> dice = new ArrayList<>(2);
			start.setArmiesOnTerritory(start.getArmiesOnTerritory() - armies);
			
			Integer[] diceDefender = new Integer[Math.min(2, target.getArmiesOnTerritory())];
			Integer[] diceAttacker = new Integer[Math.min(3, armies)];
			
			//Wuerfeln
			for(int i = 0; i < target.getArmiesOnTerritory() && i < 2; i++) {
				diceDefender[i] = random.nextInt(5) + 1;
			}
			for(int i = 0; i < armies && i < 3; i++) {
				diceAttacker[i] = random.nextInt(5) + 1;
			}
			
			//Wuerfel absteigend sortieren:
			Arrays.sort(diceDefender, Collections.reverseOrder());
			Arrays.sort(diceAttacker, Collections.reverseOrder());
			
			dice.add(diceAttacker);
			dice.add(diceDefender);
			
			int defenderCasualties = 0;
			int attackerCasualties = 0;
			
			//Armeen abziehen:
			for(int i = 0; i < Math.min(diceDefender.length, diceAttacker.length); i++) {
				if(diceDefender[i] >= diceAttacker[i]) {
					armies -= 1;
					attackerCasualties += 1;
				}else {
					target.setArmiesOnTerritory(target.getArmiesOnTerritory() -1 );
					defenderCasualties += 1;
				}
			}
			Integer[] casualties = {attackerCasualties, defenderCasualties};
			dice.add(casualties);			
			
			if(territoryConquered(attacker, target, armies)) {
				target.getOccupier().getOccupiedTerritories().remove(target);
				target.setOccupier(attacker);
				attacker.getOccupiedTerritories().add(target);
				target.setArmiesOnTerritory(armies);				
			}else {
				start.setArmiesOnTerritory(start.getArmiesOnTerritory() + armies);
			}
			return dice;
		}else {
			return null;
		}
	}
	
	//Land erobert: (nach jedem Angriff aufrufen)
	private boolean territoryConquered(Player attacker, Territory target, int armies) {
		if(target.getArmiesOnTerritory() <= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//Spieler besiegt: (nach jeder erfolgreichen Eroberung aufrufen)
	public boolean playerDefeated(Player attacker, Player defender) {
		if(defender.getOccupiedTerritories().isEmpty()) {
			for(int i = 0; i < defender.getCardsInHand().size(); i++) {
				attacker.getCardsInHand().add(defender.getCardsInHand().get(i));
			}
			defender.getCardsInHand().clear();
			return true;
		}else {
			return false;
		}
	}
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	//Sieg: (nach jedem Besiegen eines Spielers aufrufen)
	public boolean victory(Player player) {
		for(Territory t : this.territories) {
			if(t.getOccupier() != player) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Territory> getTmp(){
		return tmp;
	}
	
}