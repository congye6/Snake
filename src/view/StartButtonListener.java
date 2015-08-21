package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameController;
import controller.MainController;

public class StartButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		GameController controller=new GameController();
		controller.startGame();
	}

}
