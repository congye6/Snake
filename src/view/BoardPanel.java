package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.WallController;
import model.UpdateMessage;

public class BoardPanel extends JPanel implements Observer{
	
	
	private static final  int HEIGHT_OF_TITLE=50; 
	private static final  int HEIGHT_OF_BOARD=400; 
	private static final  int WIDTH_OF_EDGE=50; 
	private static final  int WIDTH_OF_CHESS=20;

	private List<SnakeVO> displayList;
	private List<SnakeVO> walls;
	
	private JButton startButton=new JButton(Images.START_BUTTON);
	private JButton wall1Button=new JButton(Images.WALL1);
	private JButton wall2Button=new JButton(Images.WALL2);
	
	public BoardPanel() {
		this.setLayout(null);
		startButton.setBounds(HEIGHT_OF_BOARD, HEIGHT_OF_BOARD+HEIGHT_OF_TITLE,
								WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		startButton.addActionListener(new StartButtonListener());
		startButton.setContentAreaFilled(false);
		this.add(startButton);
		wall1Button.setBounds(HEIGHT_OF_BOARD+WIDTH_OF_EDGE, HEIGHT_OF_BOARD+HEIGHT_OF_TITLE,
				WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		wall1Button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WallController wall=new WallController();
				wall.changeWall(0);
			}
			
		});
		wall1Button.setContentAreaFilled(false);
		this.add(wall1Button);
		wall2Button.setBounds(HEIGHT_OF_BOARD+2*WIDTH_OF_EDGE, HEIGHT_OF_BOARD+HEIGHT_OF_TITLE,
				WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		wall2Button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WallController wall=new WallController();
				wall.changeWall(1);
			}
			
		});
		wall2Button.setContentAreaFilled(false);
		this.add(wall2Button);
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
		 * 墙
		 */
		if(walls!=null){
			for(SnakeVO wall:walls){
				int x=wall.getX();
				int y=wall.getY();
				g.drawImage(Images.getDisplayImage(wall.getDisplayState()),
						WIDTH_OF_EDGE+WIDTH_OF_CHESS*x, WIDTH_OF_EDGE+WIDTH_OF_CHESS*y, null);
			}
		}
		
		/**
		 * 蛇
		 */
		if(displayList==null)
			return;
		
		for(SnakeVO vo:displayList){
			
			int x=vo.getX();
			int y=vo.getY();
			g.drawImage(Images.getDisplayImage(vo.getDisplayState()),
					WIDTH_OF_EDGE+WIDTH_OF_CHESS*x, WIDTH_OF_EDGE+WIDTH_OF_CHESS*y, null);
		}
		MainFrame.getFrame().requestFocus();
	}

	@Override
	public void update(Observable o, Object arg) {
		UpdateMessage message=(UpdateMessage)arg;
		if(message.getKey().equals("walls"))
			walls=(List<SnakeVO>)message.getValue();
		else
			displayList=(List<SnakeVO>)message.getValue();
		
		repaint();
	}
}
