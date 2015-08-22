package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.GameController;

public class GameKeyListener extends KeyAdapter{

	private GameController gameController=new GameController();
	
	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("enter");
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
			gameController.startGame();
	}
	
}
