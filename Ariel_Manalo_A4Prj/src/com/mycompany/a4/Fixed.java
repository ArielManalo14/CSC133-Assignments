package com.mycompany.a4;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class Fixed extends GameObject implements ISelectable{

	public Fixed() {
		// TODO Auto-generated constructor stub
	}
	public void handleCollision(GameObject otherObject) {
		
	}
	
	private boolean isSelected;
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	public abstract void draw(Graphics g, Point p, Point p2);
	
	public abstract boolean contains(double x1, double y1, double x2, double y2);

}
