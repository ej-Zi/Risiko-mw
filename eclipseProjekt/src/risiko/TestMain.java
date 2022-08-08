//###########nur zum testen, später löschen###########

package risiko;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TestMain {

	public static void main(String[] args) {
		
		
		ArrayList<String> namen = new ArrayList<String>( Arrays.asList("Lord", "Ladada", "X2", "hallo"));
		Game engine = Game.getInstance(4, namen);
		
		for(int i = 0; i < 10; i++) {
			engine.drawCard(engine.getPlayers().get(0));
		}
		
		
		for(Card c : engine.getPlayers().get(0).getCardsInHand()) {
			System.out.println(c.getTerritory().getName() + " " + c.getSymbol());
		}
		System.out.println("-----------------------");
		
		engine.tradeCards(engine.getPlayers().get(0), engine.getPlayers().get(0).getCardsInHand().get(0), engine.getPlayers().get(0).getCardsInHand().get(1), engine.getPlayers().get(0).getCardsInHand().get(2));
		
		for(Card c : engine.getPlayers().get(0).getCardsInHand()) {
			System.out.println(c.getTerritory().getName() + " " + c.getSymbol());
		}
		
	
		
	}
		//Bitte nicht loeschen:
	    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
			Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
			return outputImage;
}
	
}

