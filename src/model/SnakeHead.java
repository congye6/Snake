package model;

public class SnakeHead {
	static final int MAX_X=34;
	static final int MAX_Y=19;
	static final int MIN_X=0;
	static final int MIN_Y=0;
	
	private Direction direction;
	private int x;
	private int y;
	
	
	public SnakeHead(int x,int y, Direction headDirection) {
		this.direction = headDirection;
		this.x = x;
		this.y = y;
	}
	
	public void right(){
		x++;
		if(x>MAX_X)
			x=MIN_X;
	}
	
	public void up(){
		y--;
		if(y<MIN_Y)
			y=MAX_Y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void down(){
		y++;
		if(y>MAX_Y)
			y=MIN_Y;
	}
	
	public void left(){
		x--;
		if(x<MIN_X)
			x=MAX_X;
	}
	
	
	
	public void setDirection(Direction headDirection) {
		this.direction = headDirection;
	}

	public Direction getDirection() {
		return direction;
	}
	
}
