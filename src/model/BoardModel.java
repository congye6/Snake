package model;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import view.DisplayState;
import view.SnakeVO;

public class BoardModel extends BaseModel{

	private GameModel game;
	
	private List<SnakePO> snake;
	private Wall wall=new Wall();
	private SnakePO food;

	private SnakeHead head;
	
	private Thread move;
	
	/**
	 * 通过方向调用方法
	 */
	private static Map<Direction,Method> directionMap=new HashMap<>();
	static{
		try {
			directionMap.put(Direction.UP,SnakeHead.class.getMethod("up"));
			directionMap.put(Direction.DOWN, SnakeHead.class.getMethod("down"));
			directionMap.put(Direction.LEFT, SnakeHead.class.getMethod("left"));
			directionMap.put(Direction.RIGHT, SnakeHead.class.getMethod("right"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	/**
	 * 初始化
	 * @author congye6
	 */
	 void initial(){
		wall.buildWall();
		initialSnake();
		createFood();
		move();
	}

	private void createFood(){
		int randomY;
		int randomX;
		do{
			randomX=(int)(Math.random()*35);
			randomY=(int)(Math.random()*20);
		}while(wall.isWall(randomX,randomY)||isSnake(randomX,randomY));
		food=new SnakePO(randomX,randomY);
		List<SnakeVO> snakeDisplay=displaySnake();
		this.updateChange(new UpdateMessage("snake", snakeDisplay));;
	}
	
	
	private void initialSnake() {
		int randomX=(int)(Math.random()*20)+10;
		int randomY=(int)(Math.random()*10)+10;
		snake=new ArrayList<>();
		head=new SnakeHead(randomX, randomY, Direction.DOWN);
		snake.add(new SnakePO(randomX, randomY-1));
		snake.add(new SnakePO(randomX, randomY));
		List<SnakeVO> snakeDisplay=displaySnake();
		this.updateChange(new UpdateMessage("snake", snakeDisplay));
	}

	private boolean isSnake(int x,int y){
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			if(body.getX()==x&&body.getY()==y){
				return true;
			}
				
		}
		return false;
	}
	

	
	/**
	 * 移动
	 * @author congye6
	 */
	private void move(){
		if(move!=null){
			move.stop();
		}
		move=new Thread(){
			@Override
			synchronized public void run(){
				while(true){
					Method method=directionMap.get(head.getDirection());
					try {
						
						method.invoke(head);
						snake.add(new SnakePO(head.getX(), head.getY()));
						//吃到食物
						if(head.getX()!=food.getX()||head.getY()!=food.getY())
							snake.remove(0);
						else
							createFood();
						//撞墙
						if(wall.isWall(head.getX(),head.getY())||isSnake(head.getX(),head.getY())){
							game.gameOver();
							break;
						}
						List<SnakeVO> snakeDisplay=displaySnake();
						BoardModel.this.updateChange(new UpdateMessage("snake", snakeDisplay));
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		};
		move.start();
	}
	
	/**
	 * 控制方向
	 * @author congye6
	 */
	public void up(){
		if(head.getDirection()==Direction.DOWN)
			return;
		head.setDirection(Direction.UP);
	}
	
	public void down(){
		if(head.getDirection()==Direction.UP)
			return;
		head.setDirection(Direction.DOWN);
	}
	
	public void left(){
		if(head.getDirection()==Direction.RIGHT)
			return;
		head.setDirection(Direction.LEFT);
	}
	
	public void right(){
		if(head.getDirection()==Direction.LEFT)
			return;
		head.setDirection(Direction.RIGHT);
	}

	
	public void setGame(GameModel game) {
		this.game = game;
	}
	
	private List<SnakeVO> displaySnake(){
		List<SnakeVO> displayList=new ArrayList<>();
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			displayList.add(new SnakeVO(DisplayState.BODY, body.getX(), body.getY()));
		}
		SnakePO head=snake.get(snake.size()-1);
		displayList.add(new SnakeVO(DisplayState.HEAD, head.getX(), head.getY()));
		if(food!=null){
			displayList.add(new SnakeVO(DisplayState.FOOD, food.getX(), food.getY()));
		}
		return displayList;
	}
	
	
	public Wall getWall() {
		return wall;
	}
	
	
	
	
	
}
