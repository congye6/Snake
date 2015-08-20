package view;
import javax.swing.*;

public class MainFrame {
	
	/**
	 * frame大小
	 */
	static final int HEIGHT=520;
	static final int WIDTH=800;
	
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
	
	public static void main(String[] args) {
		MainFrame mainFrame=new MainFrame();
	}
	
	
}
