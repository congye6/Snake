package controller;

import model.BoardModel;

public class BoardController {
	
	
	public void up(){
		BoardModel board=GameController.getBoardModel();
		board.up();
	}
	
	public void down(){
		BoardModel board=GameController.getBoardModel();
		board.down();
	}
	
	public void left(){
		BoardModel board=GameController.getBoardModel();
		board.left();
	}
	
	public void right(){
		BoardModel board=GameController.getBoardModel();
		board.right();
	}
	

}
