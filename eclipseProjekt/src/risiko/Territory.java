package risiko;

import java.util.ArrayList;

public class Territory {

	//konkrete Gebiete in Konstruktor der GameEngine initialisieren
	
	private String name;
	private ArrayList<Territory> borderingTerritories;
	private Player occupier;
	
	Territory(String name, ArrayList<Territory> borderingTerritories){
		this.name = name;
		this.borderingTerritories = borderingTerritories;
	}
	
	
	public String getName() {
		return name;
	}

	public ArrayList<Territory> getBorderingTerritories() {
		return borderingTerritories;
	}

	public Player getOccupier() {
		return occupier;
	}

	public void setBesetzer(Player occupier) {
		this.occupier = occupier;
	}
	
}
