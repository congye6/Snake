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
							SnakeModel.this.updateChange(new UpdateMessage("snakeLength",snake.size()));
						}
							
						//撞墙
						if(board.getWall().isWall(head.getX(),head.getY())||isSnake(head.getX(),head.getY())){
							board.over(player,snake.size());
							break;
						}
						List<SnakeVO> snakeDisplay=displaySnake();
						SnakeModel.this.updateChange(new UpdateMessage("snake", snakeDisplay));
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		};
		move.start();
	}
	
	private void randomSnake(){
		int randomX=(int)(Math.random()*20)+10;
		int randomY=(int)(Math.random()*7)+7;
		snake=new ArrayList<>();
		head=new SnakeHead(randomX, randomY, Direction.DOWN);
		snake.add(new SnakePO(randomX, randomY-1));
		snake.add(new SnakePO(randomX, randomY));
		List<SnakeVO> snakeDisplay=displaySnake();
		this.updateChange(new UpdateMessage("snake", snakeDisplay));
		this.updateChange(new UpdateMessage("snakeLength",snake.size()));
	}
	
	
	
	public void initialSnake() {
		this.randomSnake();
	}
	
	
	
	private void  doubleSnake(){
		
	}
	
	
	public boolean isSnake(int x,int y){
		for(int i=0;i<snake.size()-1;i++){
			SnakePO body=snake.get(i);
			if(body.getX()==x&&body.getY()==y){
				return true;
			}
				
		}
		return false;
	}
	
	public SnakeHead getHead(){
		return head;
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
}
