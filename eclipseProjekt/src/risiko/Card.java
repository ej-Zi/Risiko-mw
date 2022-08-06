package risiko;

public class Card {
	
	private String territory;
	private String symbol;
	
	Card(String territory, String symbol){
		this.territory = territory;
		this.symbol = symbol;
	}
	
	public String getTerritory() {
		return territory;
	}

	public String getSymbol() {
		return symbol;
	}

	
}
