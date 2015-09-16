package model;


public class BoardModel extends BaseModel{

	private GameModel game;
	private GameType gameType=GameType.SINGLE;
	
	private SnakeModel snake=new SnakeModel(this,Player.PLAY1);
	private Wall wall=new Wall();
	private Food food=new Food(this);


	/**
	 * 初始化
	 * @author congye6
	 */
	 void initial(){
		wall.buildWall();
		snake.initialSnake();
		food.createFood();
		snake.move();
	}

	public void setGame(GameModel game) {
		this.game = game;
	}
	
	
	public void eatFood() {
		food.createFood();
	}
	
	public void over(Player player,int result) {
		if(gameType==GameType.DOUBLE)
			game.gameOver(player);
		else
			game.gameOver(result);
	}
	
	
	public Wall getWall() {
		return wall;
	}
	
	public SnakeModel getSnake(){
		return snake;
	}
	
	public Food getFood(){
		return food;
	}

}
