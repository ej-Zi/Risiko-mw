package risiko;

import java.util.ArrayList;

public class Player {

	//optional:
	//String name; //beim Spielstart festlegen? alernativ vorher festlegen
	
	private int armies;
	public ArrayList<Territory> occupiedTerritories;
	public ArrayList<Card> cardsInHand;
	
	//TODO Konstruktor
	
	public int getArmies() {
		return armies;
	}
	public void setArmies(int armies) {
		this.armies = armies;
	}
	
	
	
	
}
