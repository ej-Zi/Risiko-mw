package risiko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestModel {


	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		ArrayList<String> namen = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
		Game game = Game.getInstance(2, namen);
		
	
		
		for(int i = 0; i < 20; i++) {
			game.drawCard(game.getPlayers().get(0));
		}
		
		for(Card c : game.getPlayers().get(0).getCardsInHand()) {
			System.out.println(c.getSymbol());
		}
		
		while(input.nextInt() == 1) {
			int idex1 = input.nextInt();
			int idex2 = input.nextInt();
			int idex3 = input.nextInt();
		
			System.out.println(game.validCards(game.getPlayers().get(0).getCardsInHand().get(idex1),
				game.getPlayers().get(0).getCardsInHand().get(idex2), game.getPlayers().get(0).getCardsInHand().get(idex3)));
		}
		
		
		
		input.close();
		
	}

}
