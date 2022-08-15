package risiko;

import java.util.ArrayList;



public class Controller {
	private ArrayList<String> names;
	public Game game;
	private static int playerAtTurn; 
	public Territory activeTerritory;
	public Territory activeTerritory2;
	public static int phase;
	private RiskGUI gui;
	
	public Controller() {
		playerAtTurn = 0;
	}
	public void startGame(int numberOfPlayers, ArrayList<String> names) {
		System.out.println("game start");
		this.game = Game.getInstance(numberOfPlayers, names);
		this.gui = new RiskGUI(this);
	}
	
	
	public int getPlayerAtTurn() {
		return playerAtTurn;
	}
	public Player getPlayerObject() {
		return this.game.getPlayers().get(playerAtTurn);
	}
	public void nextPlayer() {
		playerAtTurn = (playerAtTurn + 1) % game.players.size();
	}
	public Player getLastPlayer() {
		return this.game.getPlayers().get(this.game.getPlayers().size() - 1);
	}
	
	public boolean validTerritory() {
		if(phase < 2) {
			return this.getPlayerObject().getOccupiedTerritories().contains(activeTerritory);
		}
		switch(phase) {
		case 2 : 
			if(!this.getPlayerObject().getOccupiedTerritories().contains(activeTerritory)) {
				return false;
			}
			for(Territory t : activeTerritory.getBorderingTerritories()) {
				if(t.getOccupier() != getPlayerObject()) {
					return true;
				}
			}
			return false;
		case 3:
			if(!this.getPlayerObject().getOccupiedTerritories().contains(activeTerritory)) {
				return false;
			}
			for(Territory t : activeTerritory.getBorderingTerritories()) {
				if(t.getOccupier() == getPlayerObject()) {
					return true;
				}
			}
			return false;
		}
		
		return false;
		
		
	}
	public boolean validTerritory2() {
		switch(phase) {
		case 2:
			return activeTerritory.getBorderingTerritories().contains(activeTerritory2) && activeTerritory2.getOccupier() != getPlayerObject();
		case 3: 
			boolean possible = game.movePossible(getPlayerObject(), activeTerritory, activeTerritory2);
			System.out.println(possible);
			game.getTmp().clear();
			game.valid = false;
			return possible;
		default:
			return false;
		}
		
	}

	//TODO
	public void updateActiveTerritory() {
		switch(phase) {
		case 0:
			gui.phase0.updateSelectedTerritory();
			break;		
		case 1:
			gui.phase1.updateSelectedTerritory();
			break;
		case 2:
			gui.phase2.updateSelectedTerritory();
			break;
		case 3: 
			gui.phase3.updateSelectedTerritory();
			break;
		}
	}
	
	public void updatePhase() {
		switch(phase) {
		case 1 : 
			recruitArmies();
			//TODO Komponenten aktualisieren
			break;
		}
	}
	
	
	public ArrayList<Integer[]> attack(int armies) {
		if(activeTerritory.getArmiesOnTerritory() - armies > 0) {
			return game.attack(getPlayerObject(), armies, activeTerritory, activeTerritory2);
		}else {
			return null;
		}
	}	
	
	public boolean moveArmies(int armies) {
		return game.moveArmies(getPlayerObject(), activeTerritory, activeTerritory2, armies);
	}
	
	public boolean placeArmyInitial() {
		if(game.placeArmies(getPlayerObject(), activeTerritory, 1)){
			return true;
		}else {
			return false;
		}
	}
	public boolean placeArmies(int armies) {
		if(game.placeArmies(getPlayerObject(), activeTerritory, armies)) {
			return true;
		}else {
			return false;
		}
	}
	
	public int recruitArmies() {
		return game.recruiting(getPlayerObject());
	}
	
	public void drawCard() {
		for(int i = 0; i < 4; i++) {
			game.drawCard(getPlayerObject());
		}
	}
	
	
	public void updateMap() {
		gui.mapPanel.drawMap();
	}
	public void updateCoa(int tmp, int terr) {
		if(terr == 2) {
			gui.mapPanel.placeCoa(activeTerritory2.getName(), playerAtTurn);
			gui.mapPanel.placeArmy(activeTerritory2.getName(), tmp);
		}else {
			gui.mapPanel.placeCoa(activeTerritory.getName(), playerAtTurn);
			gui.mapPanel.placeArmy(activeTerritory.getName(), tmp);
		}
		
	}
	public void resetTerritoryFlag() {
		gui.mapPanel.resetTerritoryFlag();
	}

	
	public RiskGUI getGui() {
		return gui;
	}
	
}
