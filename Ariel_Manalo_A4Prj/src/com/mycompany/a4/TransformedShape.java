package com.mycompany.a4;

import java.util.Vector;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

public abstract class TransformedShape {

//	public TransformedShape() {
//		// TODO Auto-generated constructor stub
//	}

	public Transform localXform = Transform.makeIdentity();
	public Vector<TransformedShape> subShape = new Vector<TransformedShape>();
	public void selfDraw(Graphics g) {};
	public void draw(Graphics g) {
		Transform xForm = Transform.makeIdentity();
		Transform oldOne = Transform.makeIdentity();
		g.getTransform(oldOne);
		g.getTransform(xForm);
		xForm.concatenate(localXform);
		g.setTransform(xForm);
		for(TransformedShape shape: subShape) {
			shape.draw(g);
		}
		selfDraw(g);
		g.setTransform(oldOne);
	}
}
