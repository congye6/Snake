package model;

import java.util.List;

import view.DisplayState;
import view.SnakeVO;

public class Food extends BaseModel{

	private BoardModel board;
	private SnakePO food;
	
	
	
	public Food(BoardModel board) {
		this.board = board;
	}



	public void createFood(){
		int randomY;
		int randomX;
		do{
			randomX=(int)(Math.random()*35);
			randomY=(int)(Math.random()*20);
		}while(board.getWall().isWall(randomX,randomY)||board.getSnake().isSnake(randomX,randomY));
		food=new SnakePO(randomX,randomY);
		SnakeVO foodDisplay=this.displayFood();
		this.updateChange(new UpdateMessage("food", foodDisplay));;
	}

	

	private SnakeVO displayFood() {
		return new SnakeVO(DisplayState.FOOD, food.getX(), food.getY());
	}



	public boolean isFood(int x, int y) {
		if(x!=food.getX())
			return false;
		if(y!=food.getY())
			return false;
	
		return true;
	}
}
