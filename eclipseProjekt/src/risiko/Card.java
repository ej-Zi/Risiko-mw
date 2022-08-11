package risiko;

public class Card {
	
	private Territory territory;
	private String symbol;
	
	Card(Territory territory, String symbol){
		this.territory = territory;
		this.symbol = symbol;
	}
	
	public Territory getTerritory() {
		return territory;
	}

	public String getSymbol() {
		return symbol;
	}	
}
