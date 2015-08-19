package controller;

import model.BoardModel;
import model.GameModel;

public class GameController {
	private static GameModel game;
	private static BoardModel board;
	
	public GameController(GameModel game2,BoardModel board2) {
		game=game2;
		board=board2;
	}
	
	public void startGame(){
		game.gameStart();
	}
	
	public static GameModel getGameModel(){
		return game;
	}
	
	public static BoardModel getBoardModel(){
		return board;
	}
	
}
