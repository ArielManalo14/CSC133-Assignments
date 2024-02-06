package com.mycompany.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public class Square {

	private Point topLeft, topRight, bottomLeft, bottomRight;
	private int myColor;
	private Transform myRotation, myTranslation, myScale;
	
	public Square(int base, int height) {
		
		topLeft = new Point(-base/2, height/2);
		topRight = new Point(base/2,height/2);
		bottomLeft = new Point(-base/2, -height/2);
		bottomRight = new Point(base/2, -height/2);
		
		myColor = ColorUtil.BLACK;
		
		myRotation = Transform.makeIdentity();
		myTranslation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
	}

	public void rotate(float degrees) {
		myRotation.rotate((float)Math.toRadians(degrees), 0, 0);
	}
	
	public void translate (float tx, float ty) {
		myTranslation.translate(tx, ty);
	}
	
	public void scale(float sx, float sy) {
		myScale.scale(sx, sy);
	}

	public void resetTransform() {
		myRotation.setIdentity();
		myTranslation.setIdentity();
		myScale.setIdentity();
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
		g.setColor(myColor);
		
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		
		Transform temp = gXform.copy();
		
		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
		gXform.translate(myTranslation.getTranslateX(), myTranslation.getTranslateY());
		gXform.concatenate(myRotation);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
		
		g.setTransform(gXform);
		
		g.drawLine(pCmpRelPrnt.getX()+topRight.getX(), pCmpRelPrnt.getY()+topRight.getY(),
				pCmpRelPrnt.getX()+topLeft.getX(), pCmpRelPrnt.getY()+topLeft.getY());
		g.drawLine(pCmpRelPrnt.getX()+topRight.getX(), pCmpRelPrnt.getY()+topRight.getY(),
				pCmpRelPrnt.getX()+bottomRight.getX(), pCmpRelPrnt.getY()+bottomRight.getY());
		g.drawLine(pCmpRelPrnt.getX()+topLeft.getX(), pCmpRelPrnt.getY()+topLeft.getY(),
				pCmpRelPrnt.getX()+bottomLeft.getX(), pCmpRelPrnt.getY()+bottomLeft.getY());
		g.drawLine(pCmpRelPrnt.getX()+bottomLeft.getX(), pCmpRelPrnt.getY()+bottomLeft.getY(),
				pCmpRelPrnt.getX()+bottomRight.getX(), pCmpRelPrnt.getY()+bottomRight.getY());
		
		g.setTransform(temp);
		
	}

	
	
}
