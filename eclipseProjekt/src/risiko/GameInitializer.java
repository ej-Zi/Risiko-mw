package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class GameInitializer { //Bezeichnung vielleicht noch ändern?; Konstruktor in Main aufrufen -> erstellt Spieler, Gebiete etc.

	private ArrayList<Territory> territories;
	private ArrayList<Continent> continents;
	private ArrayList<Player> players;
	private ArrayList<String> namen;
	private ArrayList<Card> cards;
	
	protected GameInitializer(int anzahlSpieler, ArrayList<String> namen) {
		
		this.namen = namen;
		//-----Spieler erstellen-----
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
		
		//-----Kontinente erstellen-----
		this.continents = new ArrayList<>(6);
		String[] continentNames = {"Schataria", "Arillia", "Gilacia", "Urza", "Arlas", "Algos"};
		int[] bonusArmies = {5,7,2,5,3,2};
		for(int i = 0; i < 6; i++){
			this.continents.add(new Continent(continentNames[i], bonusArmies[i]));
		}
		continentNames = null;
		bonusArmies = null;
		
		//-----Territorien erstellen-----
		this.territories = new ArrayList<>(42);
		//Schataria
		Territory shalathra = createTerritory("Shalathra", 0);
		Territory northernSchataria = createTerritory("Northern Schataria", 0);
		Territory azealon = createTerritory("Azealon", 0);
		Territory travaria = createTerritory("Travaria", 0);
		Territory northValoran = createTerritory("North Valoran", 0);
		Territory southValoran = createTerritory("South Valoran", 0);
		Territory wraithilles = createTerritory("Wraithilles", 0);
		Territory terrafen = createTerritory("Terrafen", 0);
		Territory theKingdomOfSun = createTerritory("The Kingdom of Sun", 0);
		
		//Arillia
		Territory drakeland = createTerritory("Drakeland", 1);
		Territory ssizara = createTerritory("Ssizara", 1);
		Territory azissa = createTerritory("Azissa", 1);
		Territory shajera = createTerritory("Shajera", 1);
		Territory myridia = createTerritory("Myridia", 1);
		Territory inghal = createTerritory("Inghal", 1);
		Territory westernArillia = createTerritory("Western Arillia", 1);
		Territory southernArillia = createTerritory("Southern Arillia", 1);
		Territory dreadmarch = createTerritory("Dreadmarch", 1);
		Territory druhm = createTerritory("Druhm", 1);
		Territory aroya = createTerritory("Aroya", 1);
		Territory azureKingdom = createTerritory("Azure Kingdom", 1);
		
		//Gilacia
		Territory gilacianIsles = createTerritory("Gilacian Isles", 2); 
		Territory whiteTundra = createTerritory("White Tundra", 2);
		Territory glice = createTerritory("Glice", 2);
		Territory fridigia = createTerritory("Fridigia", 2);
		
		//Urza
		Territory uria = createTerritory("Uria", 3);
		Territory lunador = createTerritory("Lunador", 3);
		Territory veylor = createTerritory("Veylor", 3);
		Territory solador = createTerritory("Solador", 3);
		Territory centralUrza = createTerritory("Central Urza", 3);
		Territory glendara = createTerritory("Glendara", 3);
		Territory easternUrza = createTerritory("Eastern Urza", 3);
		
		//Arlas
		Territory northernArlas = createTerritory("Northern Arlas", 4);
		Territory hagros = createTerritory("Hagros", 4);
		Territory arlasBarrens = createTerritory("Arlas Barrens", 4);
		Territory zeish = createTerritory("Zeish", 4);
		Territory agashar = createTerritory("Agashar", 4);
		Territory southernArlas = createTerritory("Southern Arlas", 4);
		
		//Algos
		Territory velis = createTerritory("Velis", 5);
		Territory westernAlgos = createTerritory("Western Algos", 5);
		Territory easternAlgos = createTerritory("Eastern Algos", 5);
		Territory boria = createTerritory("Boria", 5);
		
		//set borderingTerritories
		shalathra.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(northernSchataria, azealon, lunador)));
		northernSchataria.setBorderingTerritories(new ArrayList<Territory>(Arrays.asList(shalathra, azealon, travaria, gilacianIsles, whiteTundra)));
		azealon.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(shalathra, northernSchataria, travaria, northValoran)));
		travaria.setBorderingTerritories(new ArrayList<Territory>(Arrays.asList(azealon, northValoran, northernSchataria, wraithilles)));
		northValoran.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(azealon, travaria, southValoran)));
		southValoran.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(wraithilles, northValoran, terrafen, theKingdomOfSun)));
		wraithilles.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(travaria, southValoran, terrafen, northernArlas, hagros)));
		terrafen.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(theKingdomOfSun, southValoran, wraithilles, northernArlas)));
		theKingdomOfSun.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(terrafen, southValoran, azureKingdom)));
		drakeland.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(fridigia, ssizara)));
		ssizara.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(azissa, drakeland, shajera, myridia)));
		azissa.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(ssizara, veylor, solador, shajera)));
		shajera.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(ssizara, azissa, solador, glendara, myridia)));
		myridia.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(ssizara, shajera, dreadmarch, inghal)));
		inghal.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(westernArillia, myridia, southernArillia)));
		westernArillia.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(southernArlas, inghal, southernArillia)));
		southernArillia.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(southernArlas, inghal, westernArillia, aroya, velis)));
		dreadmarch.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(myridia, druhm, aroya)));
		druhm.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(dreadmarch, glendara, azureKingdom, aroya)));
		aroya.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(dreadmarch, druhm, azureKingdom, boria, southernArillia)));
		azureKingdom.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(druhm, aroya, theKingdomOfSun)));
		gilacianIsles.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(northernSchataria, whiteTundra)));
		whiteTundra.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(northernSchataria, gilacianIsles, glice, fridigia)));
		glice.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(whiteTundra, fridigia)));
		fridigia.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(whiteTundra, glice, uria, drakeland)));
		uria.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(fridigia, veylor)));
		lunador.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(veylor, shalathra)));
		veylor.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(uria, lunador, centralUrza, solador, azissa)));
		solador.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(veylor, centralUrza, glendara, shajera, azissa)));
		centralUrza.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(veylor, easternUrza, glendara, solador)));
		glendara.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(centralUrza, easternUrza, druhm, shajera, solador)));
		easternUrza.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(centralUrza, glendara)));
		northernArlas.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(terrafen, wraithilles, hagros, arlasBarrens)));
		hagros.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(wraithilles, agashar, arlasBarrens, northernArlas)));
		arlasBarrens.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(zeish, agashar, hagros, northernArlas, southernArlas)));
		zeish.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(arlasBarrens, southernArlas)));
		agashar.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(hagros, arlasBarrens, southernArlas)));
		southernArlas.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(agashar, arlasBarrens, westernArillia, southernArillia, zeish)));
		velis.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(westernAlgos, southernArillia)));
		westernAlgos.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(easternAlgos, velis)));
		easternAlgos.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(westernAlgos, boria)));
		boria.setBorderingTerritories(new ArrayList<Territory>( Arrays.asList(easternAlgos, aroya)));
		
		
		//Karten erstellen:
		this.cards = new ArrayList<>(44);
		String[] symbols = {"Infanterie", "Artillerie", "Kavallerie"};
		for(int i = 0; i < 42; i++) {
			this.cards.add(new Card(this.territories.get(i).getName(), symbols[i % 3]));
		}
		symbols = null;
		for(int i = 0; i < 2; i++) {
			this.cards.add(new Card("Joker", "Joker"));
		}
		
		//Spielern Territorien zuordnen:
		this.setStartingTerritories();

		
		
		
	}
	
	

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	
	private Territory createTerritory(String name, int continentIndex) {
		Territory newTerritory = new Territory(name);
		newTerritory.setContinent(this.continents.get(continentIndex));
		this.territories.add(newTerritory);
		this.continents.get(continentIndex).addTerritory(newTerritory);
		return newTerritory;
	}
	
	public ArrayList<Territory> getTerritories(){
		return this.territories;
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	private void setStartingTerritories() {
		Random random = new Random();
		ArrayList<ArrayList<Territory>> assignedTerritories = new ArrayList<>();
		for(int i = 0; i < this.players.size(); i++) {
			assignedTerritories.add(new ArrayList<Territory>());
		}
		
		if(this.players.size() == 2 || this.players.size() == 3) {
			for(ArrayList<Territory> list : assignedTerritories) {
				while(list.size() < (42 / this.players.size())) {
					int randomNumber = random.nextInt(42);
					boolean available = true;
					for(ArrayList<Territory> list2 : assignedTerritories) {
						if(list2.contains(this.territories.get(randomNumber))) {
							available = false;
						}
					}
					if(available){
						list.add(this.territories.get(randomNumber));
					}
				}
			}
		}else if(this.players.size() == 4) {
			for(int i = 0; i < 2; i++) {
				while(assignedTerritories.get(i).size() < 11) {
					int randomNumber = random.nextInt(42);
					boolean available = true;
					for(ArrayList<Territory> list2 : assignedTerritories) {
						if(list2.contains(this.territories.get(randomNumber))) {
							available = false;
						}
					}
					if(available){
						assignedTerritories.get(i).add(this.territories.get(randomNumber));
					}
				}
			}
			
			for(int i = 2; i < 4; i++) {
				while(assignedTerritories.get(i).size() < 10) {
					int randomNumber = random.nextInt(42);
					boolean available = true;
					for(ArrayList<Territory> list2 : assignedTerritories) {
						if(list2.contains(this.territories.get(randomNumber))) {
							available = false;
						}
					}
					if(available){
						assignedTerritories.get(i).add(this.territories.get(randomNumber));
					}
				}
			}
		}else if(this.players.size() == 5) {
			for(int i = 0; i < 2; i++) {
				while(assignedTerritories.get(i).size() < 9) {
					int randomNumber = random.nextInt(42);
					boolean available = true;
					for(ArrayList<Territory> list2 : assignedTerritories) {
						if(list2.contains(this.territories.get(randomNumber))) {
							available = false;
						}
					}
					if(available){
						assignedTerritories.get(i).add(this.territories.get(randomNumber));
					}
				}
			}
			
			for(int i = 2; i < 5; i++) {
				while(assignedTerritories.get(i).size() < 8) {
					int randomNumber = random.nextInt(42);
					boolean available = true;
					for(ArrayList<Territory> list2 : assignedTerritories) {
						if(list2.contains(this.territories.get(randomNumber))) {
							available = false;
						}
					}
					if(available){
						assignedTerritories.get(i).add(this.territories.get(randomNumber));
					}
				}
			}
		}
		
		for(int i = 0; i < this.players.size(); i++) {
			this.players.get(i).setOccupiedTerritories(assignedTerritories.get(i));
		}
	}
	
	
	
	
	
}
