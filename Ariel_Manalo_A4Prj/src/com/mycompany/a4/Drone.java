
package com.mycompany.a4;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public class Drone extends Moveable {
	
	//creating a new random object to get a random heading and speed
	Random rand = new Random();

	private double deltaX;

	private double newLocX;

	private double deltaY;

	private double newLocY;
	
	

	public Drone() {
		//drone constructor to create a new drone and initialize values to its variables
		super.setHeading(randHeading());
		super.setSpeed(randSpeed());
		super.setSize(super.randSize());
		super.setColor(255,0,255);
		super.setxLocation(super.randX());
		super.setyLocation(super.randY());
		
		//super.translate((float)super.getxLocation(), (float)super.getyLocation());
		
		//translate(super.getxLocation(), super.getyLocation())l
		
	}
	public int randHeading() {
		//randomly chooses a heading between 0 and 359
		int a = rand.nextInt(359);
		return a;
	}
	public int randSpeed() {
		//randomly chooses a speed between 5 and 10;
		int b = rand.nextInt(201)+150;
		return b;
	}
	public String toString() {
		//toString method to print out the drones values 
		return "Drone: loc= " + super.getxLocation() + ", " + super.getyLocation()
			+ " color= " + super.printColor()
			+ " heading= " + super.getHeading()
			+ " speed= " + super.getSpeed()
			+ " size= " + super.getSize();
	}
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScreen) {
		//draws the shape for the drone
		
		double x = super.getTranslate().getTranslateX(); //+ p.getX();
		double y = super.getTranslate().getTranslateY(); //+ p.getY();
		
		g.setColor(ColorUtil.BLACK);
		int[] xPoints = {(int)(x-getSize()/2),(int)(x-getSize()/2)+getSize(),(int)(x-getSize()/2)+getSize()/2};
		int[] yPoints = {(int)(y-getSize()/2),(int)y-getSize()/2,(int)(y-getSize()/2)+getSize()};
		g.drawPolygon(xPoints,yPoints, 3);
	}

	@Override
	//this method overrides the moveX method in moveable since drones cannot go outside of the screen 
	public double moveX(int elapsedMillisecs, double width){
		//this method calculates the new xLocation by adding the new location to the old location using the heading and speed
		double distance = super.getSpeed()*(elapsedMillisecs/1000.0);
		double xHeading = 90 - super.getHeading();
		deltaX = (double) Math.cos(Math.toRadians(xHeading))*distance;
		
		newLocX = super.getxLocation() + deltaX;
		//calculates the new x location
		if(newLocX < 0) {
			super.setHeading((int) (xHeading*(-1)));
			return 1;	
		}
		if(newLocX > width) {
			super.setHeading((int) (xHeading*(-1)));
			return width;
		}
		else {
			return newLocX;
		}
		//checks the new x location to see if it is out of the screen, if it is, it will not allow the drone to go that way further
	}
	
	@Override
	public double moveY(int elapsedMillisecs, double height) {
		//this method overrides the moveY method in moveable since drones cannot go outside of the screen
		
		double dist = super.getSpeed()*(elapsedMillisecs/1000.0);
		double yHeading = 90 - super.getHeading();
		deltaY = (double) Math.sin(Math.toRadians(yHeading))*dist;
		
		newLocY = super.getyLocation() + deltaY;
		//calculates the new y location 
		if(newLocY < 0) {
			super.setHeading((int) (yHeading*(-1)));
			return 1;
		}
		else if(newLocY > height) {
			super.setHeading((int) (yHeading*(-1)));
			return height;
		}
		else {
			return newLocY;
		}
		//checks the new y location to see if it out of the screen, if it is, it will not allow the drone to go that way further

	}
	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}



}
