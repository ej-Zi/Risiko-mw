package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestMain {	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		if(input.nextInt() == 1) {	//Testmodus
			System.out.println("Testmodus wird gestartet");
			Controller ctrl = new Controller();
			ctrl.startGame(-1, new ArrayList<>(Arrays.asList("Player 1" , "Player 2", "Player 3", "Player 4", "Player 5")));
		}else {
			System.out.println("Spiel wird gestartet");
			new IntroGUI(new Controller());
			
		}
		
		input.close();
