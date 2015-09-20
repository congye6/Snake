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
		food.createFood();
		snake.initialSnake();
		snake.move();
		if(gameType==GameType.DOUBLE){
			snake2.initialSnake();
			snake2.move();
		}
			
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
		if(gameType==GameType.DOUBLE){
			if(player==Player.PLAY1)
				snake2.stop();
			else
				snake.stop();
			game.gameOver(player);
		}
		
		else
			game.gameOver(result);
			
	}
	
	public void setGameType(GameType type){
		this.gameType=type;
		snake.setGameType(type);
		if(snake2!=null)
			snake2.setGameType(type);
		this.updateChange(new UpdateMessage("gameType", type));
	}
	
	
	public boolean isSnake(int x,int y,Player player){
		if(gameType==GameType.SINGLE)
			return snake.isSnakeBody(x, y);
		else{
			boolean result=snake.isSnakeBody(x, y)||snake2.isSnakeBody(x, y);
			if(player==Player.PLAY1)
				result=result||snake2.isSnakeHead(x, y);
			else
				result=result||snake.isSnakeHead(x, y);
			
			return result;
		}
			
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
