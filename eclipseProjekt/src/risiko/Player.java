package risiko;

import java.util.ArrayList;

public class Player {

	private String name;
	private int armies;
	private ArrayList<Territory> occupiedTerritories;
	private ArrayList<Card> cardsInHand;
	
	Player(String name, int armies){
		this.name = name;
		this.armies = armies;
	}
	
	public int getArmies() {
		return armies;
	}
	public void setArmies(int armies) {
		this.armies = armies;
	}
	public String getName() {
		return name;
	}

	public ArrayList<Territory> getOccupiedTerritories() {
		return occupiedTerritories;
	}

	public void setOccupiedTerritories(ArrayList<Territory> occupiedTerritories) {
		this.occupiedTerritories = occupiedTerritories;
	}
	

	
	
	
}
