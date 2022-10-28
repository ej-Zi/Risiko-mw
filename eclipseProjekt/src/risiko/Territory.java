package risiko;

import java.util.ArrayList;

public class Territory {
	
	private String name;
	private ArrayList<Territory> borderingTerritories;
	private Player occupier;
	private int armiesOnTerritory;
	private Continent continent;
	
	Territory(String name){
		this.name = name;
		this.borderingTerritories = new ArrayList<>();
		this.armiesOnTerritory = 0;
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

	public void setOccupier(Player occupier) {
		this.occupier = occupier;
	}
	
	public void setBorderingTerritories(ArrayList<Territory> territories) {
		this.borderingTerritories = territories;
	}

	public Continent getContinent() {
		return continent;
	}


	public void setContinent(Continent continent) {
		this.continent = continent;
	}


	public int getArmiesOnTerritory() {
		return armiesOnTerritory;
	}


	public void setArmiesOnTerritory(int armiesOnTerritory) {
		this.armiesOnTerritory = armiesOnTerritory;
	}
	
}