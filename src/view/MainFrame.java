package view;
import javax.swing.*;

public class MainFrame {
	
	/**
	 * frame大小
	 */
	static final int HEIGHT=520;
	static final int WIDTH=800;
	
	private static JFrame frame;
	
	
	private BoardPanel board;
	
	public  MainFrame() {
		frame=new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(frame);
		
		board=new BoardPanel();
		
		frame.add(board);
		frame.addKeyListener(new MyKeyListener());
		frame.addKeyListener(new GameKeyListener());

		frame.setVisible(true);
	}
	
	public BoardPanel getBoard() {
		return board;
	}
	
	public static JFrame getFrame(){
		return frame;
	}
	
	
}
