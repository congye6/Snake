package model;

public class SnakeHead extends SnakePO{
	
	public SnakeHead(Point head, Direction headDirection) {
		super();
		this.point = head;
		this.direction = headDirection;
	}
	
	
	public void setDirection(Direction headDirection) {
		this.direction = headDirection;
	}
	
	
	

}
