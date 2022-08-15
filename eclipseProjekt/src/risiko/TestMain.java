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

