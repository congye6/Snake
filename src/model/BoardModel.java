package model;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class BoardModel extends Observable{

	private GameModel game;
	
	private List<SnakePO> snake;
	private List<SnakePO> walls;
	private SnakePO food;
	private SnakeHead head;
	
	
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
		buildWall();
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
		}while(!isWall(randomX,randomY)&&!isSnake(randomX,randomY));
		food=new SnakePO(randomX,randomY);
	}
	
	
	private void initialSnake() {
		int randomX=(int)(Math.random()*20)+10;
		int randomY=(int)(Math.random()*10)+10;
		snake=new ArrayList<>();
		head=new SnakeHead(randomX, randomY, Direction.DOWN);
		snake.add(new SnakePO(randomX, randomY-1));
		snake.add(new SnakePO(randomX, randomY));
		this.updateChange(new UpdateMessage("snake", snake));
		
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
	

	private boolean isWall(int x,int y){
		for(SnakePO wall:walls){
			if(wall.getX()==x&&wall.getY()==y)
				return true;
		}
		
		return false;
	}
	
	private void buildWall(){
		walls=new ArrayList<>();
		for(int i=0;i<7;i++){
			walls.add(new SnakePO(0, i));
			walls.add(new SnakePO(34, i));
		}
		for(int i=7;i<13;i++){
			walls.add(new SnakePO(5, i));
			walls.add(new SnakePO(29, i));
		}
		for(int i=13;i<20;i++){
			walls.add(new SnakePO(0, i));
			walls.add(new SnakePO(34, i));
		}
	}
	
	/**
	 * 移动
	 * @author congye6
	 */
	private void move(){
		Thread move=new Thread(){
			@Override
			public void run(){
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
						if(isWall(head.getX(),head.getY())||isSnake(head.getX(),head.getY())){
							game.gameOver();
							break;
						}
						BoardModel.this.updateChange(new UpdateMessage("snake", snake));
						Thread.sleep(500);
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
	/**
	 * 通知更新方法，请在子类中需要通知观察者的地方调用此方法
	 * @param data
	 */
	private void updateChange(UpdateMessage message){
		
		super.setChanged();
		super.notifyObservers(message);
		
	}
	
}
