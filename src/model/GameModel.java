package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public void gameOver(int result){
		File file=new File("src/GameRecord");
		try {
			RecordPO record;
			ObjectInputStream reader;
			if(file.length()!=0){
				reader=new ObjectInputStream(new FileInputStream(file));
				record=(RecordPO) reader.readObject();
			}
			else
				record=new RecordPO();
			record.addRecord(result);
			ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(file));
			writer.writeObject(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 通知更新方法，请在子类中需要通知观察者的地方调用此方法
	 * @param data
	 */
	private void updateChange(List<SnakePO> message){
		
		super.setChanged();
		super.notifyObservers(message);
		
	}

	public void gameOver(Player player) {
		
		
	}

}
