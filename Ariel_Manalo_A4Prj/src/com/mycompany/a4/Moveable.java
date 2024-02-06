package com.mycompany.a4;

import java.util.Vector;

public abstract class Moveable extends GameObject{
	//moveable variables
	private int heading;
	private int speed;
	private double deltaX;
	private double newLocX;
	private double deltaY;
	private double newLocY;
	
	public Moveable() {
		// TODO Auto-generated constructor stub
	}
	
	public double moveX(int elapsedMillisecs, double width){
		//this method calculates the new xLocation by adding the new location to the old location using the heading and speed
		double distance = speed*(elapsedMillisecs/1000.0);
		deltaX = (double) Math.cos(Math.toRadians(90 - heading))*distance;
		newLocX = super.getxLocation() + deltaX;
		super.translate((float)newLocX, 0);//(float)super.getyLocation());
		return newLocX;//returns the new location 
		
	}
	public double moveY(int elapsedMillisecs, double height) {
		//this method calculates the new yLocation by adding the new location to the old location using the heading and speed
		deltaY = (double) Math.sin(Math.toRadians(90 - heading))*(speed*(elapsedMillisecs/1000.0));
		newLocY = super.getyLocation() + deltaY;
		super.translate(0, (float)newLocY);//(float)super.getxLocation(), (float)newLocY);
		return newLocY;//returns the new lovation 
	}
	
	//getter and setter for heading 
	public int getHeading() {
		return heading;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	//getter and setter for speed 
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

	
//	public boolean collidesWith(GameObject otherObject) {
//		
//		return false;
//	}
//
//	public void handleCollision(GameObject otherObject) {
//		// TODO Auto-generated method stub
//		
//	}
}
