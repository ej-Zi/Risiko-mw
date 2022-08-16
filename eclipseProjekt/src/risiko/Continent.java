package risiko;

import java.util.ArrayList;

public class Continent {
	
	private String name;
	private ArrayList<Territory> territoriesOnContinent;
	private int bonusArmies;
	
	Continent(String name, int bonusArmies){
		this.territoriesOnContinent = new ArrayList<>();
		this.name = name;
		this.bonusArmies = bonusArmies;
	}
	
	public ArrayList<Territory> getTerritoriesOnContinent() {
		return territoriesOnContinent;
	}
	
	public void addTerritory(Territory territory) {
		this.territoriesOnContinent.add(territory);
	}

	public String getName() {
		return name;
	}

	public int getBonusArmies() {
		return bonusArmies;
	}
}
