package controller;

import model.BoardModel;
import model.GameModel;

public class MainController {
	private static GameModel game;
	private static BoardModel board;
	
	public MainController(GameModel game2,BoardModel board2) {
		game=game2;
		board=board2;
	}
	
	
	public static GameModel getGameModel(){
		return game;
	}
	
	public static BoardModel getBoardModel(){
		return board;
	}
	
}
