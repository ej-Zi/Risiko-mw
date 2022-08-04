package risiko;

import java.util.ArrayList;

public class GameEngine { //Bezeichnung vielleicht noch ändern?; Konstruktor in Main aufrufen -> erstellt Spieler, Gebiete etc.

	private static GameEngine instance = null;
	private ArrayList<Territory> territories;
	private ArrayList<Continent> continents;
	private ArrayList<Player> players;
	private ArrayList<String> namen;
	
	//TODO Konstruktor
	private GameEngine(int anzahlSpieler, ArrayList<String> namen) {
		
		this.namen = namen;
		
		int startingArmies;
		switch(anzahlSpieler) {
		case 2:
			this.players = new ArrayList<>(2);
			startingArmies = 40;
			for(int i = 0; i < 2; i++) {
				this.players.add(new Player(this.namen.get(i), startingArmies)); //namen aus Liste holen
			}
			break;
		case 3:
			this.players = new ArrayList<>(3);
			startingArmies = 35;
			for(int i = 0; i < 3; i++) {
				this.players.add(new Player(this.namen.get(i), startingArmies));
			}
			break;		
		case 4:
			this.players = new ArrayList<>(4);
			startingArmies = 30;
			for(int i = 0; i < 4; i++) {
				this.players.add(new Player(this.namen.get(i), startingArmies));
			}
			break;
		case 5:
			this.players = new ArrayList<>(5);
			startingArmies = 25;
			System.out.println("case 5");
			for(int i = 0; i < 5; i++) {
				this.players.add(new Player(this.namen.get(i), startingArmies));
			}
			break;
		}
		
	}
	
	public static GameEngine getInstance(int anzahlSpieler, ArrayList<String> namen) {
		if(instance == null) {
			instance =  new GameEngine(anzahlSpieler, namen);
		}
		return instance;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
}
