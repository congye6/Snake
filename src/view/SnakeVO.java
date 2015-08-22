package view;

public class SnakeVO {

	private DisplayState displayState;
	private int x;
	private int y;
	
	
	public SnakeVO(DisplayState displayState, int x, int y) {
		this.displayState = displayState;
		this.x = x;
		this.y = y;
	}


	public DisplayState getDisplayState() {
		return displayState;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	};
	
	
	
}
