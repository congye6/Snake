package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.GameController;

public class StartKeyListener extends KeyAdapter{

	private GameController gameController=new GameController();
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
			gameController.startGame();
	}
	
}
