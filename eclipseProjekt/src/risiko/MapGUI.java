package risiko;
import java.awt.*;
import javax.swing.*;

public class MapGUI extends JFrame{
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();				
		ImageIcon mapIcon = new ImageIcon("assets\\risk-map.jpg");
		Image map = mapIcon.getImage();
		Image modmap = map.getScaledInstance(screenSize.width*8/10, screenSize.height*99/107, java.awt.Image.SCALE_SMOOTH);
		mapIcon = new ImageIcon(modmap);
		
		JFrame frame = new JFrame("Risiko");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel mapLabel = new JLabel(mapIcon);
		JButton testButton  = new JButton("Test");
		
		panel1.add(mapLabel);
		panel2.add(testButton);
		panel1.setPreferredSize(new Dimension(screenSize.width*8/10,screenSize.height*9/10));
		
		panel2.setPreferredSize(new Dimension(screenSize.width*2/10,screenSize.height*9/10 ));
		panel2.setBackground(Color.darkGray);
		
		frame.setLayout(new BorderLayout());
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2,BorderLayout.WEST);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}
}


