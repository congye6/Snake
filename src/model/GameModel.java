package model;

import java.util.List;
import java.util.Observable;

public class GameModel extends Observable{
	
	private BoardModel board;
	
	public GameModel(BoardModel board) {
		this.board = board;
	}

	public void gameStart(){
		board.initial();
	}
	
	public void gameOver(){
		
	}
	
	
	
	
	/**
	 * 通知更新方法，请在子类中需要通知观察者的地方调用此方法
	 * @param data
	 */
	private void updateChange(List<SnakePO> message){
		
		super.setChanged();
		super.notifyObservers(message);
		
	}

}
