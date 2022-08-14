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
		System.out.println("Controller erstellt");
		names = new ArrayList<>();
		names.add("Player1");
		names.add("Player2");
		names.add("Player3");
		names.add("Player4");
		names.add("Player5");
		this.game = Game.getInstance(-1, names);
		this.gui = new RiskGUI(this);		
		playerAtTurn = 0;
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
		}
		
		return false;
		
		
	}
	public boolean validTerritory2() {
		return activeTerritory.getBorderingTerritories().contains(activeTerritory2) && activeTerritory2.getOccupier() != getPlayerObject();
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
	
	
	public void updateMap() {
		gui.mapPanel.drawMap();
	}

	public RiskGUI getGui() {
		return gui;
	}
	
}
