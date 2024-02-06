package com.mycompany.a4;


import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public class PlayerSquare{

	private Square myBody;
	private Triangle [] legs;
	private Transform myTranslation, myRotation, myScale;
	
	public PlayerSquare() {
		
		myTranslation = Transform.makeIdentity();
		myRotation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		myBody = new Square(50, 50);
		//myBody.scale((float)2.5, (float)2.5);
		
		legs = new Triangle [4];
		
		//left
		Triangle f0 = new Triangle(50,50);
		f0.translate(50, 0); 
		f0.rotate(-90);
		//f0.scale((float)3, (float)3);
		legs[0]= f0;
		
		//right
		Triangle f1 = new Triangle(50,50);
		f1.translate(-50, 0); 
		f1.rotate(90);
		//f1.scale((float)3, (float)3);
		legs[1]= f1;
		
		//bottom
		Triangle f2 = new Triangle(50,50);
		f2.translate(0, -50); 
		f2.rotate(180);
		//f2.scale((float)3, (float)3);
		legs[2]= f2;
		
		//top
		Triangle f3 = new Triangle(50,50);
		f3.translate(0, 50); 
		//f3.rotate(180);
		//f3.scale((float)3, (float)3);
		legs[3]= f3;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform gOrigXform = gXform.copy();
		
		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getX());
		
		gXform.translate(myTranslation.getTranslateX(), myTranslation.getTranslateY());;
		gXform.concatenate(myRotation);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		
		gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
		g.setTransform(gXform);
		
		myBody.draw(g, pCmpRelPrnt, pCmpRelScrn);
		for(Triangle f: legs) {
			f.draw(g,  pCmpRelPrnt, pCmpRelScrn);
		}
		g.setTransform(gOrigXform);
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
	

}
