package risiko;

import java.util.ArrayList;

public class Kontinent {

	//ist Klasse sinnvoll?
	
	private ArrayList<Gebiet> gebiete; //benutzen, um beim Einheiten erhalten festzustellen, ob Bonuseinheiten erhalten werden

	Kontinent(ArrayList<Gebiet> gebiete){
		this.gebiete = gebiete;
	}
	
	public ArrayList<Gebiet> getGebiete() {
		return gebiete;
	}
}
