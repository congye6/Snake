package model;

public enum Player {
	
	PLAY1(10,5,"snake","snakeLength"){

		@Override
		Player opposite() {
			return PLAY2;
		}
		
	},PLAY2(25,5,"snake2","snakeLength2"){

		@Override
		Player opposite() {
			return PLAY1;
		}
		
	};
	
	//玩家的贪吃蛇的初始位置
	private int positionX;
	private int positionY;
	private String key;
	private String lengthKey;
	
	private Player(int positionX, int positionY,String key,String lengthKey) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.key=key;
		this.lengthKey=lengthKey;
	}

	abstract Player opposite();
	
	public int getX(){
		return positionX;
	}
	
	public int getY(){
		return positionY;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getLengthKey(){
		return lengthKey;
	}
	
}
