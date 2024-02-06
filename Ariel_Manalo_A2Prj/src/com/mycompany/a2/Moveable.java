package com.mycompany.a2;


public abstract class Moveable extends GameObject{
	//moveable variables
	private int heading;
	private int speed;
	
	public Moveable() {
		// TODO Auto-generated constructor stub
	}
	
	public double moveX(){
		//this method calculates the new xLocation by adding the new location to the old location using the heading and speed
		double deltaX = Math.cos(Math.toRadians(90 - heading))*speed;
		double newLocX = super.getxLocation() + deltaX;
		return newLocX;
	}
	public double moveY() {
		//this method calculates the new yLocation by adding the new location to the old location using the heading and speed
		double deltaY = Math.sin(Math.toRadians(90 - heading))*speed;
		double newLocY = super.getyLocation() + deltaY;
		return newLocY;
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
}
