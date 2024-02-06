package com.mycompany.a4;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public class Robot extends Moveable implements ISteerable {
	//Robots variables declared 
	private int maximumSpeed;
	private double energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached = 0;
	private int steeringDirection;
	
	//private Transform myRotation, myTranslation, myScale;
	
	private PlayerSquare player = new PlayerSquare();;
	
	private ArrayList<GameObject> collidedWith = new ArrayList<>(); 
	
	public Robot(double x, double y) {
		//robot constructor to create a new robot and initialize values
		super.setHeading(0);
		super.setxLocation(x);
		super.setyLocation(y);
		
		super.setColor(255,0,0);
		maximumSpeed = 400;
		damageLevel = 0;
		energyLevel = 100;
		energyConsumptionRate = 2;
		lastBaseReached = 0;

		super.setSize(70);
		
		player.translate((float)super.getxLocation(), (float)super.getyLocation());
			
		//super.translate((float)super.getxLocation(), (float)super.getyLocation());
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
	public double getEnergyLevel() {
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
		
		if(getLastBaseReached() == lastBaseReached-1) {
			this.lastBaseReached = lastBaseReached;
		}

	}

	public void takeDamage() {
		//increases the damage by 10
		damageLevel = damageLevel + 2;
		setDamageLevel(damageLevel);
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
			super.setSpeed(super.getSpeed() + 50);
			System.out.println("Accelerating");
		}
	}
	public void brake() {
		//slows down the speed and if it is already at 0, the speed will stay and 0
		if(super.getSpeed() >= 5) {
			super.setSpeed(super.getSpeed() - 50);
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
			steeringDirection = steeringDirection - 20;
		}
		
		//System.out.println("Steering to the left");
	}

	@Override
	public void steerRight() {
		// changes the steering direction by adding 5
		if(steeringDirection  < 35) {
			steeringDirection = steeringDirection + 20;
		}
		
		//System.out.println("Steering to the right");
	}
	public void loseEnergy(int elapsedMillisecs) {
		// decreases energy level based on the energy consumption rate 
		energyLevel = (energyLevel - energyConsumptionRate*(elapsedMillisecs/1000.0));
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

	public void draw(Graphics g, Point p, Point pCmpRelScrn) {
		//this creates the red square for the robot to appear in mapview
//		g.setColor(ColorUtil.rgb(250, 0, 0));
//		g.fillRect((int)(x-getSize()/2), (int)(y-getSize()/2), getSize(), getSize());
//		
//		float x = p.getX();
//		float y = p.getY();
		
		
//		Transform gXform = Transform.makeIdentity();
//		g.getTransform(gXform);
//		
//		Transform temp = gXform.copy();
//		
//		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
//		gXform.translate(myTranslation.getTranslateX(), myTranslation.getTranslateY());
//		gXform.concatenate(myRotation);
//		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
//		gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
//		
//		g.setTransform(gXform);
		
		//player.translate(x, y);
		player.draw(g, p, pCmpRelScrn);
		
		//g.setTransform(temp);
	}
	
	
	
	public void handleCollision(GameObject otherObject) {
		//this method is checking what this robot has collided with then calls other methods to handle the collision
		
		if(otherObject instanceof Robot) {
			handleRobotCollision((Robot) otherObject);
		}
		
		if(otherObject instanceof Drone) {
			handleDroneCollision(otherObject);
		}
		
		if(otherObject instanceof Base) {
			handleBaseCollision((Base) otherObject);
		}

		if(otherObject instanceof EnergyStation) {
			handleEnergyStationCollision((EnergyStation) otherObject);
		}

		
	}
	
	@Override
	public void handleRobotCollision(GameObject otherObject) {
		//System.out.println("collided w another robot");
		if(getDamageLevel() == 0) {
			takeDamage();

		}
		else if(getDamageLevel() < 90) {
			takeDamage();
			damagedMaxSpeed();
		}
		else {
			takeDamage();
		}
		fadeColor();
		
		//System.out.println("collided w robot");
		
	}
	
	@Override
	public void handleDroneCollision(GameObject otherObject) {
		if(getDamageLevel() == 0) {
			takeDamage();

		}
		else if(getDamageLevel() < 90) {
			takeDamage();
			damagedMaxSpeed();

		}
		else {
			takeDamage();
		}
		fadeColor();
		
	}
	
	public void handleBaseCollision(Base otherObject) {
		setLastBaseReached(otherObject.getSequenceNumber());
		//System.out.println("lbr: "+getLastBaseReached());
	}
	
	@Override
	public void handleEnergyStationCollision(EnergyStation otherObject) {
		setEnergyLevel((int) (getEnergyLevel() + otherObject.getCapacity()));
		//System.out.println("en lvl"+getEnergyLevel());
		
	}	
	
}
