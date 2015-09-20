package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.DisplayState;
import view.SnakeVO;

public class SnakeModel extends BaseModel{

	private List<SnakePO> snake;
	private SnakeHead head;
	private Thread move;
	private BoardModel board;
	private Player player;
	private GameType gameType=GameType.SINGLE;
	

	public SnakeModel(BoardModel boardModel,Player player) {
		super();
		this.board = boardModel;
		this.player=player;
	}


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
	 * 移动
	 * @author congye6
	 */
	public void move(){
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
						if(!board.getFood().isFood(head.getX(),head.getY()))
							snake.remove(0);
						else{
							board.eatFood();
							SnakeModel.this.updateChange(new UpdateMessage(player.getLengthKey(),snake.size()));
						}
							
						//撞墙
						if(board.getWall().isWall(head.getX(),head.getY())||board.isSnake(head.getX(),head.getY(),player)){
							board.over(player,snake.size());
							break;
						}
						List<SnakeVO> snakeDisplay=displaySnake();
						SnakeModel.this.updateChange(new UpdateMessage(player.getKey(), snakeDisplay));
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		};
		move.start();
	}
	
	public void stop(){
		
		if(move!=null){
			move.stop();
		}
	}
	
	public void initialSnake() {
		if(gameType==GameType.SINGLE){
			System.out.println("single"+player.name());
			this.randomSnake();
		}
			
		else
			this.staticSnake();
	}
	
	//单人随机生成位置
	private void randomSnake(){
		int randomX=(int)(Math.random()*20)+10;
		int randomY=(int)(Math.random()*7)+7;
		generateSnake(randomX, randomY);
	}
	
	//双人固定位置
	private void staticSnake(){
		generateSnake(player.getX(), player.getY());
	}
	
	private void generateSnake(int x,int y){
		snake=new ArrayList<>();
		head=new SnakeHead(x, y, Direction.DOWN);
		snake.add(new SnakePO(x, y-1));
		snake.add(new SnakePO(x, y));
		List<SnakeVO> snakeDisplay=displaySnake();
		this.updateChange(new UpdateMessage(player.getKey(), snakeDisplay));
		this.updateChange(new UpdateMessage(player.getLengthKey(),snake.size()));
	}
	
	public boolean isSnakeBody(int x,int y){
		
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			if(body.getX()==x&&body.getY()==y){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isSnakeHead(int x,int y){
		if(head.getX()==x&&head.getY()==y){
			return true;
		}else{
			return false;
		}
	}
	
	
	public SnakeHead getHead(){
		return head;
	}
	
	public int size(){
		return snake.size();
	}
	
	private List<SnakeVO> displaySnake(){
		List<SnakeVO> displayList=new ArrayList<>();
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			displayList.add(new SnakeVO(DisplayState.BODY, body.getX(), body.getY()));
		}
		SnakePO head=snake.get(snake.size()-1);
		displayList.add(new SnakeVO(DisplayState.HEAD, head.getX(), head.getY()));
		return displayList;
	}
	
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
}
