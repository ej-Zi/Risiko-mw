package risiko;

import java.util.ArrayList;

public class Player {

	private String name;
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
	public String getName() {
		return name;
	}
	
	
	
	
}
