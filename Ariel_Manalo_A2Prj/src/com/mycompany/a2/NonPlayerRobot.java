package com.mycompany.a2;

public class NonPlayerRobot extends Robot  {
	private IStrategy curStrategy;

	public NonPlayerRobot(double x, double y) {
		//initializing the non player robot
		super.setxLocation(x);
		super.setyLocation(y);
		super.setMaximumSpeed(10);
		super.setSpeed(10);
		super.setDamageLevel(0);
		super.setEnergyLevel(100);
		super.setEnergyConsumptionRate(0);
		super.setLastBaseReached(0);
			
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
