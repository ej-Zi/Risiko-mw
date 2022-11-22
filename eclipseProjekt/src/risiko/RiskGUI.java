package risiko;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class RiskGUI extends JFrame{
	
	public JPanel panelMap;
	private JPanel panelCf;	
	
	public Phase0 phase0;
	public Phase1 phase1;
	public Phase2 phase2;
	public Phase3 phase3;
	public Phase4 phase4;
	public MapPanel mapPanel;
	public DrawCards drawCards;
	
	public Controller controller;
	
	public RiskGUI(Controller controller) {
		
		this.controller = controller;
		
		//editing panels
		panelMap = new JPanel();
		panelCf = new JPanel();
		panelMap.setLayout(new BorderLayout());
		panelCf.setLayout(new BorderLayout());
		
		this.phase0 = new Phase0(controller);
		this.phase1 = new Phase1(controller);
		this.phase2 = new Phase2(controller);
		this.phase3 = new Phase3(controller);
		this.phase4 = new Phase4(controller);
		this.mapPanel = new MapPanel(controller);
		this.drawCards = new DrawCards(controller);
		
		panelMap.add(mapPanel);
		
		changePhase(0);
	
		//place Panel on JFrame
		this.setLayout(new BorderLayout());
		this.add(panelMap, BorderLayout.EAST);
		this.add(panelCf,BorderLayout.WEST);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);		
	}
	
	
	public void changePhase(int phase) {
		controller.resetActiveTerritories();
		panelCf.removeAll();
		panelCf.revalidate();
		panelCf.repaint();
		switch(phase) {
		case 0:
			panelCf.add(phase0);
			Controller.phase = phase;
			break;
		case 1:
			Controller.phase = phase;
			panelMap.removeAll();
			panelMap.revalidate();
			panelMap.repaint();
			controller.updatePhase();
			panelCf.add(phase1);
			panelMap.add(mapPanel);
			break;
		case 2:
			Controller.phase = phase;
			controller.updatePhase();
			panelCf.add(phase2);
			break;
		case 3:
			Controller.phase = phase;
			controller.updatePhase();
			panelCf.add(phase3);
			break;
		case 4:
			Controller.phase = phase;
			panelMap.removeAll();
			panelMap.revalidate();
			panelMap.repaint();
			controller.updatePhase();
			panelMap.add(new DrawCards(controller));
			panelCf.add(phase4);
			break;
		}
	}
}