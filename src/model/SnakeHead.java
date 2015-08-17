package model;

public class SnakeHead {
	private Point point;
	private Direction direction;
	
	
	public SnakeHead(Point head, Direction headDirection) {
		this.point = head;
		this.direction = headDirection;
	}
	
	public void right(){
		point.addX();
	}
	
	public void up(){
		point.addY();
	}
	
	public void down(){
		point.minusY();
	}
	
	public void left(){
		point.minusX();
	}
	
	
	
	public void setDirection(Direction headDirection) {
		this.direction = headDirection;
	}

	public Direction getDirection() {
		return direction;
	}
	
	
	

}
