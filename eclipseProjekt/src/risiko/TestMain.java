package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestMain {	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		System.out.println("Bitte Modus auswaehlen:\nTestmodus [1]\nSpiel [2]\n");
		if(input.nextInt() == 1) {	//Testmodus
			System.out.println("Testmodus wird gestartet");
			Controller ctrl = new Controller();
			ctrl.startGame(-1, new ArrayList<>(Arrays.asList("Player 1" , "Player 2")));
		}else { //Spielmodus
			System.out.println("Spiel wird gestartet");
			new IntroGUI(new Controller());
			
		}
		
		input.close();
	}
}
