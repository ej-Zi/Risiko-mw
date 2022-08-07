package risiko;

import java.util.ArrayList;

public class Game extends GameInitializer {

	private static Game instance = null;
	
	private Game(int anzahlSpieler, ArrayList<String> namen) {
		super(anzahlSpieler, namen);
	}

	public static Game getInstance(int anzahlSpieler, ArrayList<String> namen) {
		if(instance == null) {
			return new Game(anzahlSpieler, namen);
		}else {
			return null;
		}
	}
	
	
	
}
