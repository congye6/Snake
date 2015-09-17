package controller;

import model.BoardModel;
import model.GameType;
import model.SnakeModel;
import view.BoardPanel;

public class GameTypeController {

	public void setDouble(BoardPanel boardPanel){
		BoardModel board=MainController.getBoardModel();
		board.setGameType(GameType.DOUBLE);
		SnakeModel snake2=board.newSnake();
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
