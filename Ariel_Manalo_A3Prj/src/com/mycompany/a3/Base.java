
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Base extends Fixed {
	//base class variable
	private int sequenceNumber;
	GameWorld gw;
	
	public Base(double x, double y, int seqNum, GameWorld g) {
		//base constructor to create new base objects
		super.setSize(100);
		super.setColor(0,0,255);
		
		sequenceNumber = seqNum;
		super.setxLocation(x);
		super.setyLocation(y);
		
		gw = g;
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

	@Override
	public void draw(Graphics g, double x, double y) {
		
		if(this.isSelected() && (gw.getisPaused()==true)) {
			//System.out.println("test");
			g.setColor(ColorUtil.CYAN);
			int[] xPoints = {(int)(x-getSize()/2),(int)(x-getSize()/2)+getSize(),(int)(x-getSize()/2)+getSize()/2};
			int[] yPoints = {(int)(y-getSize()/2),(int)y-getSize()/2,(int)(y-getSize()/2)+getSize()};
			g.drawPolygon(xPoints,yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+getSequenceNumber()+"", (int)(x-getSize()/2)+getSize()/2, (int)(y-getSize()/2));
		}else {
			
			g.setColor(ColorUtil.CYAN);
			int[] xPoints = {(int)(x-getSize()/2),(int)(x-getSize()/2)+getSize(),(int)(x-getSize()/2)+getSize()/2};
			int[] yPoints = {(int)(y-getSize()/2),(int)y-getSize()/2,(int)(y-getSize()/2)+getSize()};
			g.fillPolygon(xPoints,yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(""+getSequenceNumber()+"", (int)(x-getSize()/2)+getSize()/2, (int)(y-getSize()/2));
			//g.drawString(""+getSequenceNumber(), (int)x, (int)y, ColorUtil.BLACK);
		}

	}
//
//	@Override
//	public void draw(Graphics g, Point pCmpRelPrnt) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
//		int px = pPtrRelPrnt.getX();
//		int py = pPtrRelPrnt.getY();
//		int xLoc = (int) (pCmpRelPrnt.getX()+super.getxLocation());
//		int yLoc = (int) (pCmpRelPrnt.getY()+super.getyLocation());
//		
//		if( (px >= xLoc) && (px <= xLoc+getSize())
//				&& (py>=yLoc) && (py <= yLoc+getSize())) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}

	@Override
	public boolean contains(double x1, double y1, double x2, double y2) {
		// TODO Auto-generated method stub
		
		double px = x1;
		double py = y1;
		double xLoc = x2-getSize()/2 + super.getxLocation();
		double yLoc = y2-getSize()/2 + super.getyLocation();
		
		if( (px >= xLoc) && (px <= xLoc+getSize())
				&& (py>=yLoc) && (py <= yLoc+getSize())) {
			return true;
		}
		else {
			return false;
		}
	}


		
}
