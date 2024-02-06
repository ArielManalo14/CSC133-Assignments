package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		// TODO Auto-generated constructor stub
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(250, 0, 0)));
		setWidth(getX());
		setHeight(getY());
		this.gw=gw;
	}
	
	@Override
	public void paint(Graphics g) {
		//this method shows all the objects at the start of the game
		super.paint(g);
		IIterator it = gw.getGamePieces().getIterator();
		//creating an iterator for game pieces to use in the while loop
		while(it.hasNext()) {
			//loop goes through game pieces and calls draw method for each
			GameObject temp = it.getNext();
			temp.draw(g, getX()+temp.getxLocation(), getY()+temp.getyLocation());
		}
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
