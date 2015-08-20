package view;

import java.awt.Graphics;

import javax.swing.*;

public class BoardPanel extends JPanel{
	
	
	private static final  int HEIGHT_OF_TITLE=50; 
	private static final  int HEIGHT_OF_BOARD=400; 
	private static final  int WIDTH_OF_EDGE=50; 
	private static final  int WIDTH_OF_CHESS=20;

	public BoardPanel() {
		this.setLayout(null);
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
	}
}
