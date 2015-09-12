package controller;

import model.GameModel;
import model.Wall;

public class WallController {

	public void changeWall(int num){
		Wall wall=MainController.getBoardModel().getWall();
		wall.setWallNumber(num);
		GameModel game=MainController.getGameModel();
		game.gameStart();
	}
	
	
	
	
}
