package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestModel {


	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		ArrayList<String> namen = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
		Game game = Game.getInstance(2, namen);
		
		for(int i = 0; i < game.getPlayers().size(); i++) {
			for(Territory t : game.getPlayers().get(i).getOccupiedTerritories()){
			System.out.println(t.getName());
			}
			System.out.println();
		}
		int indexStart = input.nextInt();
		int indexZiel = input.nextInt();
		
		System.out.println(game.movePossible(game.getPlayers().get(0), game.getTerritories().get(indexStart)
				,game.getTerritories().get(indexZiel)));
		
		input.close();
		
	}

}
