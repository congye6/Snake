package model;

public class SnakePO {

	protected Point point;
	protected Direction direction;
	
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
	
	public Direction getDirection() {
		return direction;
	}
	public Point getPoint() {
		return point;
	}
	public void setDirection(Direction headDirection) {
		
	}
}
