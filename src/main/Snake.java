package main;

import controller.GameController;
import model.BoardModel;
import model.GameModel;
import view.MainFrame;

public class Snake {
	public static void main(String[] args) {
		MainFrame ui=new MainFrame();
		BoardModel board=new BoardModel();
		GameModel game=new GameModel(board);
		GameController controller=new GameController(game, board);
		board.setGame(game);
		board.addObserver(ui.getBoard());
		game.gameStart();
	}
}
