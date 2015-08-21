package view;

import java.awt.Image;

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
	
	
	private static Image createImage(String name){
		Image image=new ImageIcon("image/"+name+".png").getImage();
		return image;
	}

}
