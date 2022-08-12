package risiko;

import java.util.ArrayList;



public class Controller {
	private ArrayList<String> names;
	public Game game;
	private static int playerAtTurn; 
	public Territory activeTerritory;
	public static int phase;
	private RiskGUI gui;
	
	public Controller() {
		names = new ArrayList<>();
		names.add("Player1");
		names.add("Player2");
		names.add("Player3");
		names.add("Player4");
		names.add("Player5");
		this.game = Game.getInstance(5, names);
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
		return this.getPlayerObject().getOccupiedTerritories().contains(activeTerritory);
	}

	//TODO
	public void updateActiveTerritory() {
		switch(phase) {
		case 0:
			gui.phase0.updateSelectedTerritory();
			break;		
		
		}
	}
	
	public boolean placeArmyInitial() {
		if(game.placeArmies(game.getPlayers().get(playerAtTurn), activeTerritory, 1)){
			return true;
		}else {
			return false;
		}
	}
	
	public void updateMap() {
		gui.mapPanel.drawMap();
	}

	public RiskGUI getGui() {
		return gui;
	}
	
}
