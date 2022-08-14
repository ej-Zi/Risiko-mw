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
		
//		StartMenuGUI start = new StartMenuGUI();
//		start.setVisible(true);
		
		Controller controller = new Controller();
		
		controller.getGui().setVisible(true);
	

		
		//IntroGUI view2 = new IntroGUI();
		//view2.setVisible(true);
		
		//StartMenuGUI view3 = new StartMenuGUI();
		//view3.setVisible(true);
		//Karte als Bild anzeigen:
		/*File image = new File("assets\\Risiko-Karte.jpg");
		BufferedImage map = null;
		try {
			map = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//resize image: (muss im Verhältnis zur Bildschirmgröße angepasst werden)
		try {
		map = resizeImage(map, 1500, 1000);
		}catch(IOException e) {
			e.printStackTrace();
		}*/

//		TestMain testmain = new TestMain();
//		testmain.view.setVisible(true);
		
		/*JLabel mapLabel = new JLabel(new ImageIcon(map));
		JPanel jPanel = new JPanel();
		jPanel.add(mapLabel);
	
		
	}
		//Bitte nicht loeschen:
	    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
			Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
			return outputImage;*/

	 }
	    
}

