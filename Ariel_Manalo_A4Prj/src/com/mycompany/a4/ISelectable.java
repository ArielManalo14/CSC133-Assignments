package com.mycompany.a4;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	
	public void setSelected(boolean b);
	public boolean isSelected();
	public boolean contains(double x1, double y1, double x2, double y2);
	public void draw(Graphics g, double x, double y);
}
