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
	
	public BoardModel() {
		initial();
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
		Point p;
		do{
			int randomX=(int)(Math.random()*35);
			int randomY=(int)(Math.random()*20);
			p=new Point(randomX, randomY);
		}while(!isWall(p)&&!isSnake(p));
		food=new SnakePO(p);
	}
	
	
	private void initialSnake() {
		int randomX=(int)(Math.random()*20)+10;
		int randomY=(int)(Math.random()*10)+10;
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
						Point p=head.getPoint();
						//撞墙
						if(isWall(p)||isSnake(p)){
							game.gameOver();
							break;
						}
						snake.add(new SnakePO(new Point(p.getX(), p.getY())));
						//吃到食物
						if(!p.equals(food.getPoint()))
							snake.remove(snake.size()-1);
						else
							createFood();
						Thread.sleep(2000);
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
