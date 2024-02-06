package com.mycompany.a2;

public class Robot extends Moveable implements ISteerable {
	//Robots variables declared 
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	private int steeringDirection;
	
	public Robot(double x, double y) {
		//robot constructor to create a new robot and initialize values
		super.setHeading(0);
		super.setxLocation(x);
		super.setyLocation(y);
		
		super.setColor(255,0,0);
		maximumSpeed = 10;
		damageLevel = 0;
		energyLevel = 100;
		energyConsumptionRate = 15;
		lastBaseReached = 0;

	}
	
	public Robot() {
		//this is for the nonplayer robot, needed to match the npr constructor 
	}

	//getter and setter for maximum speed
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	//getter and setter for energy level
	public int getEnergyLevel() {
		return energyLevel;
	}
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	//getter and setter for energy consumption rate
	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}
	public void setEnergyConsumptionRate(int energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}

	//getter and setter for damage level
	public int getDamageLevel() {
		return damageLevel;
	}
	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}

	//getter and setter for last base reached
	public int getLastBaseReached() {
		return lastBaseReached;
	}
	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}

	public void takeDamage() {
		//increases the damage by 10
		damageLevel = damageLevel + 10;
	}
	public void damagedMaxSpeed() {
		//changes the maximum speed depending on the damage level of the robot 
		int i = damageLevel / 10;
		maximumSpeed = maximumSpeed - i;
	}
	public void fadeColor() {
		//fades the color of the robot 
		super.setColor(255, 128, 128);
	}
	public void goFaster() {
		//increases the speed but doesn't let it exceed maximum speed 
		if(super.getSpeed() == maximumSpeed) {
			System.out.println("Can't accelerate, at max speed");
		}
		else {
			super.setSpeed(super.getSpeed() + 5);
			System.out.println("Accelerating");
		}
	}
	public void brake() {
		//slows down the speed and if it is already at 0, the speed will stay and 0
		if(super.getSpeed() >= 5) {
			super.setSpeed(super.getSpeed() - 5);
			System.out.println("Braking");
		}
		else {
			super.setSpeed(0);
			System.out.println("Braked to a stop");
		}
	}
	@Override
	//getter and setter for steering direction
	public int getSteeringDirection() {
		return steeringDirection;
	}
	public void setSteeringDirection(int steeringDirection){
		this.steeringDirection = steeringDirection;
	}

	@Override
	public void steerLeft() {
		//changes the steering direction by subtracting by 5
		if(steeringDirection >= -35) {
			steeringDirection = steeringDirection - 5;
		}
		
		System.out.println("Steering to the left");
	}

	@Override
	public void steerRight() {
		// changes the steering direction by adding 5
		if(steeringDirection  < 35) {
			steeringDirection = steeringDirection + 5;
		}
		
		System.out.println("Steering to the right");
	}
	public void loseEnergy() {
		// decreases energy level based on the energy consumption rate 
		energyLevel = energyLevel - energyConsumptionRate;
	}
	public String toString() {
		//toString method to print all of the robots values 
		return "Robot: loc= " + super.getxLocation() + ", " + super.getyLocation()
				+ " color= " + super.printColor()
				+ " heading= " + super.getHeading()
				+ " speed= " + super.getSpeed()
				+ " max speed= " + getMaximumSpeed()
				+ " steering direction= " + getSteeringDirection()
				+ " energy level= " + getEnergyLevel()
				+ " damage level= " + getDamageLevel()
				+ " last base reached= " + getLastBaseReached();
	}
	
}
