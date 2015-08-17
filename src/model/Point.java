package model;

public class Point {

	private int x;
	private int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public boolean equals(Point point){
		if(point.getX()==x&&point.getY()==y)
			return true;
		return false;
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void addX(){
		x++;
	}
	public void addY(){
		y++;
	}
	
	
}
