
package com.mycompany.a2;


public class Base extends Fixed {
	//base class variable
	private int sequenceNumber;
	
	public Base(double x, double y, int seqNum) {
		//base constructor to create new base objects
		super.setSize(10);
		super.setColor(0,0,255);
		
		sequenceNumber = seqNum;
		super.setxLocation(x);
		super.setyLocation(y);
	}

	//getter and setter for sequence number
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public double nprInitialx() {
		//this is to get a random xlocation near the base so I can set my nprxlocation near the base
		double i = super.getxLocation() + rand.nextInt(30 + 30) + 30;
		return i;
	}
	
	public double nprInitialy() {
		//this is to get a random ylocation near the base so I can set my nprylocation near the base
		double j = super.getyLocation() + rand.nextInt(30 + 30) + 30;
		return j;
	}
	
	public String toString() {
		//toString method to later print out the bases variable values
		return "Base: loc= " + super.getxLocation() + ", " + super.getyLocation()
			+ " color= " + super.printColor()
			+ " size= " + super.getSize()
			+ " seq num= " + getSequenceNumber();
			
	}
}
