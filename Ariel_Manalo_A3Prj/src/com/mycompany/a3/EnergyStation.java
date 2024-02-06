
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class EnergyStation extends Fixed {
	//energy station variable
	private int capacity;
	private GameWorld gw;
	
	public EnergyStation(GameWorld g) {
		//energy station constructor to create new energy stations
		super.setSize(super.randSize());
		super.setColor(0,255,0);
		capacity = super.randSize();
		super.setxLocation(super.randX());
		super.setyLocation(super.randY());
		gw = g;
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

	@Override
	public void draw(Graphics g, double x, double y) {
		//draw method for energy station to show up in mapview
		
		if(this.isSelected() && (gw.getisPaused()==true)) {
			//System.out.println("test2");
			g.setColor(ColorUtil.GREEN);
			g.drawArc((int)(x-getSize()/2), (int)(y-getSize()/2), getSize(), getSize(),0, 360);
		}else {
			g.setColor(ColorUtil.GREEN);
			g.fillArc((int)(x-getSize()/2), (int)(y-getSize()/2), getSize(), getSize(),0, 360);
		}
		
		
		

	}
	
	@Override
	public void handleRobotCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean contains(double x1, double y1, double x2, double y2) {
		double px = x1;
		double py = y1;
		double xLoc = x2-getSize()/2 + super.getxLocation();
		double yLoc = y2-getSize()/2 + super.getyLocation();
		//System.out.println("Station: " + px +','+ py +',' + xLoc + ',' + yLoc + ';' + getSize());
		//System.out.println(((px >= xLoc)?"T":"F") + "," +  ((px <= xLoc+getSize())?"T":"F") + "," + 
				// ((py>=yLoc) ?"T":"F") + "," +  ((py <= yLoc+getSize())?"T":"F"));
		if( (px >= xLoc) && (px <= xLoc+getSize())
				&& (py>=yLoc) && (py <= yLoc+getSize())) {
			//System.out.println("staion hit");
			return true;
		}
		else {
			return false;
		}
	}


}
