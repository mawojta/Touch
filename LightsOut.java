import java.awt.BorderLayout;
import javax.swing.JFrame;

public class LightsOut extends JFrame {
	private ControlPanel controlPanel;
	private GameBoard gameBoard;
	
	public LightsOut() {		
		setTitle("LightsOut");
		setSize(800, 750);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
	}
	
	public void createContents() {
		controlPanel = new ControlPanel();		
		gameBoard = new GameBoard(controlPanel);
		controlPanel.setBoard(gameBoard);
		
		add(BorderLayout.EAST, controlPanel);		
		add(BorderLayout.CENTER, gameBoard);
	}
	
	public static void main(String[] args) {
		new LightsOut();
	}
}
