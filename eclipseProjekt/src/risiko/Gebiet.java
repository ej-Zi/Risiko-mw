package risiko;

import java.util.ArrayList;

public class Gebiet {

	//konkrete Gebiete in Konstruktor der GameEngine initialisieren
	
	private String name;
	private ArrayList<Gebiet> angrenzendeGebiete;
	private Spieler besetzer;
	
	Gebiet(String name, ArrayList<Gebiet> angrenzendeGebiete){
		this.name = name;
		this.angrenzendeGebiete = angrenzendeGebiete;
	}
	
	
	public String getName() {
		return name;
	}

	public ArrayList<Gebiet> getAngrenzendeGebiete() {
		return angrenzendeGebiete;
	}

	public Spieler getBesetzer() {
		return besetzer;
	}

	public void setBesetzer(Spieler besetzer) {
		this.besetzer = besetzer;
	}
	
}
