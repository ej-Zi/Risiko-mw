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
		GameEngine engine = GameEngine.getInstance(3, namen);
		
		for(Player n : engine.getPlayers()) {
			System.out.println(n.getName() + " " + n.getArmies());
		}
		
		
		System.out.println(engine.getTerritories().get(0).getBorderingTerritories().get(1).getName());

		
		
		
		
	
		
	}
		//Bitte nicht loeschen:
	    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
			Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
			return outputImage;
}
	
}


