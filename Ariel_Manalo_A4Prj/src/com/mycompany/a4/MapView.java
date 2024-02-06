package com.mycompany.a4;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	
	private Triangle myTriangle;
	private Square mySquare;
	
	private PlayerSquare player;
	
	//vtm stuff
	Transform worldToND, ndToDisplay, theVTM;
	private float winLeft, winBottom, winRight, winTop, winWidth, winHeight;
	
	public MapView(GameWorld gw) {
		
		//vtm stuff
		winLeft = 0;
		winBottom = 0;
		winRight = this.getWidth()/2;
		winTop = this.getHeight()/2;
		winWidth = winRight - winLeft;
		winHeight = winTop = winBottom;
		
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(250, 0, 0)));
		setWidth(getX());
		setHeight(getY());
	
//		myTriangle = new Triangle(200,200);
//		myTriangle.translate(300, 300);
//		myTriangle.rotate(90);
//		myTriangle.scale(2, 1);
//		mySquare = new Square(50, 50);
//		mySquare.translate(200, 200);
//		mySquare.rotate(45);
		
//		player = new PlayerSquare();
//		player.translate(300, 300);
		
		this.gw=gw;

	}
	
	
	@Override
	public void paint(Graphics g) {
		//this method shows all the objects at the start of the game
		super.paint(g);
		
		//vtm stuff
//		worldToND = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
//		ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
//		theVTM = ndToDisplay.copy();
//		theVTM.concatenate(worldToND);
//		
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		gXform.translate(getAbsoluteX(), getAbsoluteY());
		//gXform.concatenate(theVTM);
		gXform.translate(-getAbsoluteX(), -getAbsoluteY());
		g.setTransform(gXform);
//		
//		//tell each shape to draw itself
//		Point pCmpRelPrnt = new Point(getX(), getY());
//		Point pCmpRelScreen = new Point(getAbsoluteX(), getAbsoluteY());
//		
//		for(Shape s : shapeCollection) {
//			s.draw(g, pCmpRelPrnt, pCmpRelScrn);
//		}
//		g.resetAffine();
		
		
		
		Point pCmpRelPrnt = new Point(getX(), getY());
		Point pCmpRelScreen = new Point(getAbsoluteX(), getAbsoluteY());
//		myTriangle.draw(g,  pCmpRelPrnt,  pCmpRelScreen);
		//mySquare.draw(g, pCmpRelPrnt, pCmpRelScreen);
		
		//player.draw(g, pCmpRelPrnt, pCmpRelScreen);
		
		//assign 3 stuff
		IIterator it = gw.getGamePieces().getIterator();
		//creating an iterator for game pieces to use in the while loop
		while(it.hasNext()) {
			//loop goes through game pieces and calls draw method for each
			GameObject temp = it.getNext();
			temp.draw(g, pCmpRelPrnt, pCmpRelScreen);
		}
		
		g.resetAffine();
	
	}
	
	//vtm stuff
	private Transform buildWorldToNDXform(float winWidth, float winHeight, float winLeft, float winBottom) {
		Transform tmpXform = Transform.makeIdentity();
		tmpXform.scale((1/winWidth), (1/winHeight));
		tmpXform.translate(-winLeft, -winBottom);
		return tmpXform;
	}
	private Transform buildNDToDisplayXform(float displayWidth, float displayHeight) {
		Transform tmpXform = Transform.makeIdentity();
		tmpXform.translate(0, displayHeight);
		tmpXform.scale(displayWidth, -displayHeight);
		return tmpXform;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		//this method is calling a method in gameworld that prints all of the tostrings of each object to the console
		this.gw = (GameWorld) observable;
		//gw.map();
		repaint(); //changes the shapes when clock ticks
	}

	public void tick() {
		//this method calls the tick method in gameworld while passing in 20 and a value for elapsed milliseconds
		gw.tKey(20);
		
		//repaint();
	}
	
	public void pointerPressed(int x, int y) {
		x = x-getParent().getAbsoluteX();
		y = y-getParent().getAbsoluteY();
		
//		Point pPtrRelPrnt = new Point(x,y);
//		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator it = gw.getGamePieces().getIterator();
		
		for(int i = 0; i<it.getSize(); i++) {
			GameObject temp = it.get(i);
			if(temp instanceof Fixed) {
				Fixed obj = (Fixed) temp;
				//System.out.println("Click" + x + ',' + y + ',' + getX() + ',' +  getY() + "@" + obj);
				if(obj.contains(x, y, getX(), getY())) {
					obj.setSelected(true);
				}
				else {
					obj.setSelected(false);
				}
			}
		}
		repaint();
	}
}
