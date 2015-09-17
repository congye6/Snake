package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import controller.DirectionController;

public class MyKeyListener extends KeyAdapter{

	private DirectionController directionController=new DirectionController();
	
	private static Map<Integer,Method> keyMap=new HashMap<Integer,Method>();
	static{
		try {
			keyMap.put(KeyEvent.VK_UP, DirectionController.class.getMethod("up"));
			keyMap.put(KeyEvent.VK_DOWN, DirectionController.class.getMethod("down"));
			keyMap.put(KeyEvent.VK_LEFT,DirectionController.class.getMethod("left"));
			keyMap.put(KeyEvent.VK_RIGHT, DirectionController.class.getMethod("right"));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e){
		if(!keyMap.containsKey(e.getKeyCode()))
			return;
		try {
			keyMap.get(e.getKeyCode()).invoke(directionController);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
