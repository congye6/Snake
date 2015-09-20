package controller;


import model.SnakeHead;
import model.SnakeModel;

public class DirectionController {
	
	
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
	
	public void upPlayer2(){
		SnakeModel snake2=MainController.getBoardModel().getSnake2();
		if(snake2==null)
			return;
		snake2.getHead().turnUp();
	}
	
	public void downPlayer2(){
		SnakeModel snake2=MainController.getBoardModel().getSnake2();
		if(snake2==null)
			return;
		snake2.getHead().turnDown();
	}
	
	public void leftPlayer2(){
		SnakeModel snake2=MainController.getBoardModel().getSnake2();
		if(snake2==null)
			return;
		snake2.getHead().turnLeft();
	}
	
	public void rightPlayer2(){
		SnakeModel snake2=MainController.getBoardModel().getSnake2();
		if(snake2==null)
			return;
		snake2.getHead().turnRight();
	}

}
