package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

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
	
	public void draw(Graphics g, double x, double y) {
		//draw method for the npr's to show in mapview 
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
