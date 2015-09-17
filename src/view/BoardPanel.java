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
	
	private int snakeLength=2;

	private List<SnakeVO> displayList;
	private List<SnakeVO> walls;
	private SnakeVO food;
	
	private JButton startButton=new JButton(Images.START_BUTTON);
	private JButton wall1Button=new JButton(Images.WALL1);
	private JButton wall2Button=new JButton(Images.WALL2);
	private JButton overButton=new JButton("over");
	private JButton singleTypeButton=new JButton("single");
	private JButton doubleTypeButton=new JButton("double");
	
	public BoardPanel() {
		
		//开始
		this.setLayout(null);
		startButton.setBounds(MainFrame.WIDTH-2*WIDTH_OF_EDGE, 0,
								WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		startButton.addActionListener(new StartButtonListener());
		startButton.setContentAreaFilled(false);
		this.add(startButton);
		
		//选择关卡
		wall1Button.setBounds(HEIGHT_OF_BOARD-2*WIDTH_OF_EDGE, HEIGHT_OF_BOARD+HEIGHT_OF_TITLE,
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
		
		//关闭
		overButton.setBounds(MainFrame.WIDTH-WIDTH_OF_EDGE, 0,
				WIDTH_OF_EDGE, WIDTH_OF_EDGE);
		overButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(overButton);
		
		//选择单双人
	
		
		
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
		
		//食物
		if(food!=null)
			displayList.add(food);
		
		for(SnakeVO vo:displayList){
			
			int x=vo.getX();
			int y=vo.getY();
			g.drawImage(Images.getDisplayImage(vo.getDisplayState()),
					WIDTH_OF_EDGE+WIDTH_OF_CHESS*x, WIDTH_OF_EDGE+WIDTH_OF_CHESS*y, null);
		}
		
		//计数
		g.drawImage(Images.NUMBER[snakeLength%10], MainFrame.WIDTH-4*WIDTH_OF_EDGE, 0, null);
		if(snakeLength>=10){
			g.drawImage(Images.NUMBER[snakeLength/10], MainFrame.WIDTH-5*WIDTH_OF_EDGE, 0, null);
		}
		
		MainFrame.getFrame().requestFocus();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		UpdateMessage message=(UpdateMessage)arg;
		if(message.getKey().equals("walls"))
			walls=(List<SnakeVO>)message.getValue();
		else if(message.getKey().equals("snake"))
			displayList=(List<SnakeVO>)message.getValue();
		else if(message.getKey().equals("snakeLength"))
			snakeLength=(int)message.getValue();
		else if(message.getKey().equals("food"))
			food=(SnakeVO)message.getValue();
		
		repaint();
	}
}
