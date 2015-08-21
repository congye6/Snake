package controller;

import model.BoardModel;

public class BoardController {
	
	
	public void up(){
		BoardModel board=MainController.getBoardModel();
		board.up();
	}
	
	public void down(){
		BoardModel board=MainController.getBoardModel();
		board.down();
	}
	
	public void left(){
		BoardModel board=MainController.getBoardModel();
		board.left();
	}
	
	public void right(){
		BoardModel board=MainController.getBoardModel();
		board.right();
	}
	

}
