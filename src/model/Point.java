package model;

public class Point {

	private static final int MAX_X=34;
	private static final int MAX_Y=19;
	private static final int MIN_X=0;
	private static final int MIN_Y=0;
	
	
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
		if(x>MAX_X)
			x=MIN_X;
	}
	public void addY(){
		y++;
		if(y>MAX_Y)
			y=MIN_Y;
	}
	public void minusX(){
		x--;
		if(x<MIN_X)
			x=MAX_X;
	}
	public void minusY(){
		y--;
		if(y<MIN_Y)
			y=MAX_Y;
	}
}
