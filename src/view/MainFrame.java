package view;
import javax.swing.*;

public class MainFrame {
	
	/**
	 * frame大小
	 */
	private static final int HEIGHT=500;
	private static final int WIDTH=360;
	
	
	
	private JFrame frame;
	
	public BoardPanel getBoard() {
		return board;
	}

	private BoardPanel board;
	
	public  MainFrame() {
		frame=new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(frame);
		
		board=new BoardPanel();
		
		frame.add(board);

		frame.setVisible(true);
	}
	
	
	
	
}
