//###########nur zum testen, später löschen; kann beliebig verändert werden###########

package risiko;

public class TestMain {

	public static void main(String[] args) {
		
		Spieler s1 = new Spieler();
		
		s1.setEinheiten(10);
		System.out.println(s1.getEinheiten());
		
		s1.setEinheiten(s1.getEinheiten() + 3);
		System.out.println(s1.getEinheiten());
		
		
	}

}
