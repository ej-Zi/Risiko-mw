package risiko;

import java.util.ArrayList;

public class Territory {

	//konkrete Gebiete in Konstruktor der GameEngine initialisieren
	
	private String name;
	private ArrayList<Territory> borderingTerritories;
	private Player occupier;
	private Continent continent; //vielleicht unnoetig
	
	Territory(String name){
		this.name = name;
		this.borderingTerritories = new ArrayList<>();
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
	
}
