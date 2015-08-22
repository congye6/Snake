package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import controller.BoardController;
import controller.GameController;

public class MyKeyListener extends KeyAdapter{

	private BoardController directionController=new BoardController();
	
	private static Map<Integer,Method> keyMap=new HashMap<Integer,Method>();
	static{
		try {
			keyMap.put(KeyEvent.VK_UP, BoardController.class.getMethod("up"));
			keyMap.put(KeyEvent.VK_DOWN, BoardController.class.getMethod("down"));
			keyMap.put(KeyEvent.VK_LEFT,BoardController.class.getMethod("left"));
			keyMap.put(KeyEvent.VK_RIGHT, BoardController.class.getMethod("right"));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("实现监听");
		if(!keyMap.containsKey(e.getKeyCode()))
			return;
		try {
			keyMap.get(e.getKeyCode()).invoke(directionController);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
