package controller;

import model.GameModel;

public class GameController {
	public void startGame(){
		GameModel game=MainController.getGameModel();
		game.gameStart();
	}
}
