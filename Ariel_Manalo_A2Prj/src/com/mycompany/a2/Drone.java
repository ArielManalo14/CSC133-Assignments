
package com.mycompany.a2;
import java.util.Random;

public class Drone extends Moveable {
	
	//creating a new random object to get a random heading and speed
	Random rand = new Random();

	public Drone() {
		//drone constructor to create a new drone and initialize values to its variables
		super.setHeading(randHeading());
		super.setSpeed(randSpeed());
		super.setSize(super.randSize());
		super.setColor(255,0,255);
		super.setxLocation(super.randX());
		super.setyLocation(super.randY());
	}
	public int randHeading() {
		//randomly chooses a heading between 0 and 359
		int a = rand.nextInt(359);
		return a;
	}
	public int randSpeed() {
		//randomly chooses a speed between 5 and 10;
		int b = rand.nextInt(6)+5;
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
}
