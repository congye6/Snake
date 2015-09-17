package model;


public class BoardModel extends BaseModel{

	private GameModel game;
	private GameType gameType=GameType.SINGLE;
	
	private SnakeModel snake=new SnakeModel(this,Player.PLAY1);
	private SnakeModel snake2;
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
		if(gameType==GameType.DOUBLE)
			snake2.move();
	}

	public void setGame(GameModel game) {
		this.game = game;
	}
	
	
	public void eatFood() {
		food.createFood();
	}
	
	public SnakeModel newSnake(){
		snake2=new SnakeModel(this, Player.PLAY2);
		return snake2;
	}
	
	public void over(Player player,int result) {
		if(gameType==GameType.DOUBLE)
			game.gameOver(player);
		else
			game.gameOver(result);
	}
	
	public void setGameType(GameType type){
		this.gameType=type;
		snake.setGameType(type);
		snake2.setGameType(type);
	}
	
	
	public boolean isSnake(int x,int y){
		if(gameType==GameType.SINGLE)
			return snake.isSnake(x, y);
		else
			return snake.isSnake(x, y)&&snake2.isSnake(x, y);
	}
	
	
	public Wall getWall() {
		return wall;
	}
	
	public SnakeModel getSnake(){
		return snake;
	}
	
	public SnakeModel getSnake2(){
		return snake2;
	}
	
	public Food getFood(){
		return food;
	}

	public void deleteSnake() {
		snake2=null;
	}

}
