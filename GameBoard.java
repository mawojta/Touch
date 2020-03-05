import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel {
	private final int size = 8;
	private final int gridSize = (int) Math.pow(size, 2);
	private ControlPanel controlPanel;
	private int yCount;
	private boolean running;
	
	public GameBoard(ControlPanel controlPanel) {			
		this.controlPanel = controlPanel;
		yCount = 0;
		running = true;
	
		fillBoard();
	}
	
	public void fillBoard() {		
		this.setLayout(new GridLayout(gridSize/size, gridSize/size));
		
		for(int r = 0; r < gridSize; ++r) {
			int whole = r/8;
			int fraction = r%8;
			LightsOutCircle x = new LightsOutCircle(whole, fraction);
			x.addActionListener(new circleClkd());

			this.add(x);
			++yCount;
		}
		controlPanel.setBcount(Integer.toString(gridSize - yCount));
		controlPanel.setYcount(Integer.toString(yCount));
	}	

	public void resetGame()	{
		this.removeAll();
		yCount = 0;
		for(int r = 0; r < gridSize; ++r) {
			int whole = r/8;
			int fraction = r%8;
			LightsOutCircle x = new LightsOutCircle(whole, fraction);
			x.addActionListener(new circleClkd());
			
			this.add(x);
			++yCount;
		}
		running = true;
		controlPanel.setBcount(Integer.toString(gridSize - yCount));
		controlPanel.setYcount(Integer.toString(yCount));
		controlPanel.setWinner("");
	}

	public class circleClkd implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(running) {
				LightsOutCircle sourceButton = (LightsOutCircle) e.getSource();
				int rowIndex = sourceButton.row;
				int colIndex = sourceButton.col;

				if(rowIndex > 0) {
					int above = ((rowIndex-1) * size) + colIndex;
					if(above < gridSize && rowIndex < size && colIndex < size) {
						LightsOutCircle cp = (LightsOutCircle) sourceButton.getParent().getComponent(above);
						if(cp.isYellow()){
							cp.setBlack();
							--yCount;
						}else {
							cp.setYellow();
							++yCount;
						}
					}	
				}
				
				int below = ((rowIndex+1) * size) + colIndex;
				if(below < gridSize && rowIndex < size && colIndex < size) {
					LightsOutCircle cp = (LightsOutCircle) sourceButton.getParent().getComponent(below);
					if(cp.isYellow()) {
						cp.setBlack();
						--yCount;
					}else {
						cp.setYellow();
						++yCount;
					}
				}
				
				if(colIndex > 0) {
					int left = (rowIndex * size) + (colIndex-1);
					if(left < gridSize && rowIndex < size && colIndex < size) {
						LightsOutCircle cp = (LightsOutCircle) sourceButton.getParent().getComponent(left);
						if(cp.isYellow()) {
							cp.setBlack();
							--yCount;
						}else {
							cp.setYellow();
							++yCount;
						}
					}
				}
				
				if(colIndex < 7) {
					int right = (rowIndex * size) + (colIndex+1);
					if(right < gridSize && rowIndex < size && colIndex < size) {
						LightsOutCircle cp = (LightsOutCircle) sourceButton.getParent().getComponent(right);
						if(cp.isYellow()) {
							cp.setBlack();
							--yCount;
						}else {
							cp.setYellow();
							++yCount;
						}
					}
				}
				controlPanel.setYcount(Integer.toString(yCount));
				controlPanel.setBcount(Integer.toString(gridSize - yCount));
				
				if(yCount == 0) {
					running = false;
					controlPanel.setWinner("You Win");
				}
			}
		}
	}
}
