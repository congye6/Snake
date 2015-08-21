package view;

import java.awt.Graphics;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.SnakePO;
import model.UpdateMessage;

public class BoardPanel extends JPanel implements Observer{
	
	
	private static final  int HEIGHT_OF_TITLE=50; 
	private static final  int HEIGHT_OF_BOARD=400; 
	private static final  int WIDTH_OF_EDGE=50; 
	private static final  int WIDTH_OF_CHESS=20;

	private List<SnakePO> snake;
	
	private JButton startButton=new JButton(Images.START_BUTTON);
	
	public BoardPanel() {
		this.setLayout(null);
		startButton.setBounds(HEIGHT_OF_BOARD, HEIGHT_OF_BOARD+HEIGHT_OF_TITLE,
								WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		startButton.addActionListener(new StartButtonListener());
		this.add(startButton);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		/**
		 * 背景
		 */
		//顶部标题部分
		g.drawImage(Images.EDGE1, 0, 0, MainFrame.WIDTH, HEIGHT_OF_TITLE,  2, 40, 381, 0,  null);
		
		//左右边缘
		g.drawImage(Images.EDGE2, 0, HEIGHT_OF_TITLE, WIDTH_OF_EDGE, HEIGHT_OF_TITLE+HEIGHT_OF_BOARD, 
				0, 0, 35, 399, null);
		g.drawImage(Images.EDGE2, 750, HEIGHT_OF_TITLE, MainFrame.WIDTH, HEIGHT_OF_TITLE+HEIGHT_OF_BOARD, 
				 35, 0,0, 399, null);
		
		//中间背景
		for(int i=0;i<35;i++){
			for(int j=0;j<20;j++){
				g.drawImage(Images.BACKGROUND, WIDTH_OF_EDGE+WIDTH_OF_CHESS*i, 
						                       WIDTH_OF_EDGE+WIDTH_OF_CHESS*j, null);
			}
		}
		
		//底部
		g.drawImage(Images.EDGE1, 0, HEIGHT_OF_TITLE+HEIGHT_OF_BOARD, 
								MainFrame.WIDTH, MainFrame.HEIGHT, 
								2, 0, 381, 40,  null);
		
		/**
		 * 蛇
		 */
		if(snake==null)
			return;
		SnakePO head=snake.get(snake.size()-1);
		int x=head.getPoint().getX();
		int y=head.getPoint().getY();
		g.drawImage(Images.HEAD, WIDTH_OF_EDGE+WIDTH_OF_CHESS*x, WIDTH_OF_EDGE+WIDTH_OF_CHESS*y, null);
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			x=body.getPoint().getX();
			y=body.getPoint().getY();
			g.drawImage(Images.BODY, WIDTH_OF_EDGE+WIDTH_OF_CHESS*x, WIDTH_OF_EDGE+WIDTH_OF_CHESS*y, null);
		}
		
		MainFrame.getFrame().requestFocus();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		UpdateMessage message=(UpdateMessage)arg;
		snake=(List<SnakePO>)message.getValue();
		repaint();
	}
}
