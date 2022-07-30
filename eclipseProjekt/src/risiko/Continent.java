package risiko;

import java.util.ArrayList;

public class Continent {

	//ist Klasse sinnvoll?
	
	private ArrayList<Territory> territoriesOnContinent; //benutzen, um beim Einheiten erhalten festzustellen, ob Bonuseinheiten erhalten werden

	Continent(ArrayList<Territory> territories){
		this.territoriesOnContinent = territories;
	}
	
	public ArrayList<Territory> getTerritoriesOnContinent() {
		return territoriesOnContinent;
	}
}
