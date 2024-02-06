/**
 * 
 */
package com.mycompany.a1;

/**
 * @author ariel
 *
 */
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
	
	public String toString() {
		//toString method to later print out the bases variable values
		return "Base: loc= " + super.getxLocation() + ", " + super.getyLocation()
			+ " color= " + super.printColor()
			+ " size= " + super.getSize()
			+ " seq num= " + getSequenceNumber();
			
	}
}
