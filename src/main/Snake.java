package main;

import controller.MainController;
import model.BoardModel;
import model.GameModel;
import view.MainFrame;

public class Snake {
	public static void main(String[] args) {
		MainFrame ui=new MainFrame();
		BoardModel board=new BoardModel();
		GameModel game=new GameModel(board);
		MainController controller=new MainController(game, board);
		board.setGame(game);
		board.addObserver(ui.getBoard());
	}
}
