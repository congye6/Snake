package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import view.DisplayState;
import view.SnakeVO;

public class Wall extends BaseModel{
	private List<SnakePO> walls;
	private int wallNumber=0;
	private static Method[] getWall=new Method[2];
	static{
		try {
			getWall[0]=Wall.class.getDeclaredMethod("buildWall0");
			getWall[1]=Wall.class.getDeclaredMethod("buildWall1");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	

	public boolean isWall(int x,int y){
		for(SnakePO wall:walls){
			if(wall.getX()==x&&wall.getY()==y)
				return true;
		}
		
		return false;
	}
	
	public void buildWall(){
		try {
			walls=(List<SnakePO>) getWall[wallNumber].invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		List<SnakeVO> wallsDisplay=displayWall();
		this.updateChange(new UpdateMessage("walls", wallsDisplay));
	}
	
	public List<SnakeVO> displayWall(){
		List<SnakeVO> displayList=new ArrayList<>();
		for(SnakePO po:walls){
			displayList.add(new SnakeVO(DisplayState.WALL, po.getX(), po.getY()));
		}
		return displayList;
	}
	
	private static List<SnakePO>  buildWall0(){
		List<SnakePO> walls=new ArrayList<>();
		for(int i=0;i<7;i++){
			walls.add(new SnakePO(0, i));
			walls.add(new SnakePO(34, i));
		}
		for(int i=7;i<13;i++){
			walls.add(new SnakePO(5, i));
			walls.add(new SnakePO(29, i));
		}
		for(int i=13;i<20;i++){
			walls.add(new SnakePO(0, i));
			walls.add(new SnakePO(34, i));
		}
		return walls;
	}
	
	private static List<SnakePO> buildWall1(){
		List<SnakePO> walls=new ArrayList<>();
		for(int i=SnakeHead.MIN_X;i<=SnakeHead.MAX_X;i++){
			walls.add(new SnakePO(i, SnakeHead.MAX_Y));
			walls.add(new SnakePO(i, SnakeHead.MIN_Y));
		}
		for(int i=SnakeHead.MIN_Y;i<=SnakeHead.MAX_Y;i++){
			walls.add(new SnakePO(SnakeHead.MAX_X, i));
			walls.add(new SnakePO(SnakeHead.MIN_X, i));
		}
		return walls;
	}
	
	public void setWallNumber(int wallNumber) {
		this.wallNumber = wallNumber;
	}

}
