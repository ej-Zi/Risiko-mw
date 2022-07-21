package risiko;

import java.util.ArrayList;

public class Spieler {

	//optional:
	//String name; //beim Spielstart festlegen? alernativ Spieler durchnummerieren
	
	private int einheiten;
	public ArrayList<Gebiet> besetzteGebiete;
	public ArrayList<Karte> handkarten;
	
	//TODO Konstruktor
	
	public int getEinheiten() {
		return einheiten;
	}
	public void setEinheiten(int einheiten) {
		this.einheiten = einheiten;
	}
	
	
	
	
}
