package risiko;

public class Karte {
	
	private Gebiet gebiet;
	private String symbol;
	
	Karte(Gebiet gebiet, String symbol){
		this.gebiet = gebiet;
		this.symbol = symbol;
	}
	
	public Gebiet getGebiet() {
		return gebiet;
	}

	public String getSymbol() {
		return symbol;
	}

	
}
