package com.mycompany.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerRobot extends Robot  {
	private IStrategy curStrategy;
	
	
	public NonPlayerRobot(double x, double y) {
		//initializing the non player robot
		super.setxLocation(x);
		super.setyLocation(y);
		super.setMaximumSpeed(300);
		super.setSpeed(150);
		super.setDamageLevel(0);
		super.setEnergyLevel(100);
		super.setEnergyConsumptionRate(0);
		super.setLastBaseReached(0);
		super.setSize(70);
		
		//super.translate((float)super.getxLocation(), (float)super.getyLocation());

	}
	@Override
	public void takeDamage() {
		//method to increase damage on the nonplayer robot
		super.setDamageLevel(super.getDamageLevel()+5);
	}
	
	@Override
	public String toString() {
		//toString method to print all of the non player robots values 
		return "Non Player Robot: loc= " + super.getxLocation() + ", " + super.getyLocation()
				+ " color= " + super.printColor()
				+ " heading= " + super.getHeading()
				+ " speed= " + super.getSpeed()
				+ " max speed= " + getMaximumSpeed()
				+ " steering direction= " + getSteeringDirection()
				+ " energy level= " + getEnergyLevel()
				+ " damage level= " + getDamageLevel()
				+ " last base reached= " + getLastBaseReached()
				+ " current strategy= " + getStrategy().getStrat();
	}
	
	@Override
	public void draw(Graphics g, Point p, Point p2) {
		//draw method for the npr's to show in mapview 
		
		double x = super.getTranslate().getTranslateX(); //+ p.getX();
		double y = super.getTranslate().getTranslateY(); //+ p.getY();
		g.setColor(ColorUtil.rgb(250,0,0));
		g.drawRect((int)(x-getSize()/2), (int)(y-getSize()/2), getSize(), getSize());
		
		
	}
	
	public void setStrategy(IStrategy s) {
		//sets the current strategy to the npr
		curStrategy = s;
		
	}
	public IStrategy getStrategy() {
		//returns the current strategy
		return curStrategy;
		
	}
	public void invokeStrategy() {
		//invokes the strategy
		curStrategy.apply();
	}

}
