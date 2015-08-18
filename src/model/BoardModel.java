package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class BoardModel extends Observable{

	List<SnakePO> snake;
	SnakeHead head;
	List<SnakePO> walls;
	
	public BoardModel() {
		initial();
	}


	private void initial(){
		buildWall();
		initialSnake();
	}

	
	
	
	private void initialSnake() {
		int randomX=(int)(Math.random()*10)+10;
		int randomY=(int)(Math.random()*20)+10;
		snake=new ArrayList<>();
		head=new SnakeHead(new Point(randomX, randomY), Direction.DOWN);
		snake.add(new SnakePO(new Point(randomX, randomY-1)));
		snake.add(new SnakePO(new Point(randomX, randomY)));
		
	}

	private boolean isSnake(Point p){
		for(SnakePO body:snake){
			if(body.getPoint().equals(p))
				return true;
		}
		return false;
	}
	

	private boolean isWall(Point p){
		for(SnakePO wall:walls){
			if(wall.getPoint().equals(p))
				return true;
		}
		
		return false;
	}
	
	private void buildWall(){
		for(int i=0;i<7;i++){
			walls.add(new SnakePO(new Point(0, i)));
			walls.add(new SnakePO(new Point(34, i)));
		}
		for(int i=7;i<13;i++){
			walls.add(new SnakePO(new Point(5, i)));
			walls.add(new SnakePO(new Point(29, i)));
		}
		for(int i=13;i<20;i++){
			walls.add(new SnakePO(new Point(0, i)));
			walls.add(new SnakePO(new Point(34, i)));
		}
	}




	/**
	 * 通知更新方法，请在子类中需要通知观察者的地方调用此方法
	 * @param data
	 */
	private void updateChange(List<SnakePO> message){
		
		super.setChanged();
		super.notifyObservers(message);
		
	}
	
}
