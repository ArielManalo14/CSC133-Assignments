/**
 * 
 */
package com.mycompany.a1;

/**
 * @author ariel
 *
 */
public class EnergyStation extends Fixed {
	//energy station variable
	private int capacity;
	
	public EnergyStation() {
		//energy station constructor to create new energy stations
		super.setSize(super.randSize());
		super.setColor(0,255,0);
		capacity = super.getSize();
		super.setxLocation(super.randX());
		super.setyLocation(super.randY());
	}

	//getters and setters for capacity
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void fadeColor() {
		//fades the color of the energy station
		super.setColor(179, 255, 179);
	}
	
	public String toString() {
		//toString method to print out the energy stations's variable values 
		return "Energy Station: loc= " + super.getxLocation() + ", " + super.getyLocation()
			+ " color= " + super.printColor()
			+ " size= " + super.getSize()
			+ " capacity= " + getCapacity();	
	}

}
