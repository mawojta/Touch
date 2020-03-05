import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
	private GameBoard gameBoard;
	private JLabel yCount;
	private JLabel bCount;
	private JLabel winnerwinner;
	
	public ControlPanel() {			
		fillPanel();
	}
	
	public void setBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}	
	
	public void fillPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel yText = new JLabel("Yellow Count");
		JLabel space1 = new JLabel(" ");
		yCount = new JLabel("");
		JLabel bText = new JLabel("Black Count\n");
		bCount = new JLabel("");
		winnerwinner = new JLabel("");
		JLabel space2 = new JLabel(" ");
		JButton reset = new JButton("Reset");
		reset.addActionListener(new resetClick());
		
		this.add(yText);
		this.add(yCount);
		this.add(space1);
		this.add(bText);
		this.add(bCount);
		this.add(winnerwinner);		
		this.add(space2);
		this.add(reset);
	}
	
	public void setBcount(String count) {
		this.bCount.setText(count);
	}
	
	public void setYcount(String count) {
		this.yCount.setText(count);
	}
	
	public void setWinner(String text) {
		this.winnerwinner.setText(text);
	}
	
	public class resetClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gameBoard.resetGame();
		}
	}	
}
