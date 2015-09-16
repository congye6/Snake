package controller;


import model.SnakeHead;

public class BoardController {
	
	
	public void up(){
		SnakeHead head=MainController.getBoardModel().getSnake().getHead();
		head.turnUp();
	}
	
	public void down(){
		SnakeHead head=MainController.getBoardModel().getSnake().getHead();
		head.turnDown();
	}
	
	public void left(){
		SnakeHead head=MainController.getBoardModel().getSnake().getHead();
		head.turnLeft();
	}
	
	public void right(){
		SnakeHead head=MainController.getBoardModel().getSnake().getHead();
		head.turnRight();
	}
	

}
