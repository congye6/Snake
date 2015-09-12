package view;

import java.awt.Image;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;

public class Images {
	static final Image BACKGROUND=createImage("background");
	static final Image BODY=createImage("body");
	static final Image EDGE1=createImage("edge1");
	static final Image EDGE2=createImage("edge2");
	static final Image FOOD=createImage("food");
	static final Image HEAD=createImage("head");
	static final Image TITLE=createImage("title");
	static final Image WALL=createImage("wall");
	static final ImageIcon START_BUTTON=new ImageIcon("image/start.png");
	static final ImageIcon WALL1=new ImageIcon("image/wall1.png");
	static final ImageIcon WALL2=new ImageIcon("image/wall2.png");
	
	
	private static Image createImage(String name){
		Image image=new ImageIcon("image/"+name+".png").getImage();
		return image;
	}
	
	public static Image getDisplayImage(DisplayState displayState){
		Image image=null; 
		try {
			Field field=Images.class.getDeclaredField(displayState.name());
			image=(Image) field.get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return image;
	}

}
