package controller;

import model.BoardModel;
import model.GameType;
import model.SnakeModel;
import view.BoardPanel;

public class GameTypeController {

	public void setDouble(BoardPanel boardPanel){
		BoardModel board=MainController.getBoardModel();
		SnakeModel snake2=board.getSnake2();
		if(snake2==null)
			snake2=board.newSnake();
		board.setGameType(GameType.DOUBLE);
		snake2.addObserver(boardPanel);
		MainController.getGameModel().gameStart();
	}
	
	public void setSingle(){
		BoardModel board=MainController.getBoardModel();
		board.setGameType(GameType.SINGLE);
		board.deleteSnake();
		MainController.getGameModel().gameStart();
	}
	
}
