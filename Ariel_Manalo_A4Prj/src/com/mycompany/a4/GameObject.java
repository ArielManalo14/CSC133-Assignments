package com.mycompany.a4;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

import java.util.Random;
import java.util.Vector;

public abstract class GameObject implements IDrawable, ICollider{
	//game object constructor
	private int size;
	private double xLocation;
	private double yLocation;
	private int color;
	private int r;
	private int g;
	private int b;
	private Transform myRotation, myTranslation, myScale;
	
		
	private Vector<GameObject> collideVect = new Vector<>();;

	public GameObject() {
		myRotation = Transform.makeIdentity();
		myTranslation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
		
		//translate((float)xLocation - getTranslate().getTranslateX(), (float)yLocation - getTranslate().getTranslateY());
		
		translate((float)xLocation, (float)yLocation);
	}
	
	//creating a random object to use in other methods
	Random rand =  new Random();
	
	//getter and setter for size
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	//randomly selects size 
	public int randSize() {
		int k = rand.nextInt(61)+50;
		return k;
	}
	
	//getter and setter for xLocation
	public double getxLocation() {
		//return xLocation;
		return myTranslation.getTranslateX();
	}
	public void setxLocation(double xLocation){
		
		//myTranslation.setTranslation((float)xLocation, myTranslation.getTranslateY());
		
		translate((float)xLocation - myTranslation.getTranslateX(), 0);
		//this.xLocation = xLocation;
	}
	
	//randomly choosing an xLocation
	public double randX() {
		int i = rand.nextInt(1024);
		return i;
	}
	
	//getter and setter for yLocation
	public double getyLocation() {
		//return yLocation;
		return myTranslation.getTranslateY();
	}
	public void setyLocation(double yLocation){
		//this.yLocation = yLocation;
		
		//myTranslation.setTranslation(myTranslation.getTranslateX(), (float)yLocation );
		
		translate(0, (float)yLocation - myTranslation.getTranslateY());
	}
	
	//randomly choosing a yLocation
	public double randY() {
		int j = rand.nextInt(768);
		return j;
	}
	
	//getter and setter for color
	public int getColor() {
		return color;
	}
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r,g,b);
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String  printColor() {
		return "[" + r +"," + b + "," + g + "]" ;
	}
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		int objX = (int) getxLocation();
		int objY = (int) getyLocation();
		
		//System.out.println("location"+objX);
		
		int otherX = (int) otherObject.getxLocation();
		int otherY = (int) otherObject.getyLocation();
		
		//System.out.println("other lcoation"+otherX);
		
		int R1 = objX + (getSize()/2);
		int L1 = objX - (getSize()/2);
		int T1 = objY - (getSize()/2);
		int B1 = objY + (getSize()/2);
		
		int R2 = otherX + (otherObject.getSize()/2);
		int L2 = otherX - (otherObject.getSize()/2);
		int T2 = otherY - (otherObject.getSize()/2);
		int B2 = otherY + (otherObject.getSize()/2);
		
		if((R1<L2) || (R2<L1) || (T2>B1) || (T1>B2)) {
			return false;
		}
		else {
			//System.out.println("true");
			return true;
			
		}
	}

//	public void handleCollision(GameObject otherObject) {
//		
////		if(otherObject instanceof Drone) {
////			System.out.println("drone");
////			handleDroneCollision(otherObject);
////		}
//		
//		if(otherObject instanceof Robot) {
//			
////			if(!(otherObject instanceof NonPlayerRobot)) {
////				
////			}
//			
//			handleRobotCollision(otherObject);
//		}
////
////		if(otherObject instanceof EnergyStation) {
////			handleEnergyStationCollision(otherObject);
////		}
////		if(otherObject instanceof Base) {
////			handleBaseCollision(otherObject);
////		}
//		
//	}
	public void handleBaseCollision(Base otherObject) {
		// TODO Auto-generated method stub
		System.out.println("base");
		
	}
	public void handleEnergyStationCollision(EnergyStation otherObject) {
		// TODO Auto-generated method stub
		
	}
	public void handleDroneCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		System.out.println("drone ------");
		
	}
	public void handleRobotCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		System.out.println("collided w r");
	}

	public Vector<GameObject> getCollideVect() {
		return collideVect;
	}
	
	public void setCollideVect(Vector<GameObject> vect) {
		vect = collideVect;
	}
//	public abstract void draw(Graphics g2, Point pCmpRelPrnt, Point pCmpRelScreen);
//	
//	public void draw(Graphics g, Point p, Point p2) {
//		
//	}
	
	public void rotate(float degrees) {
		myRotation.rotate((float)Math.toRadians(degrees), 0, 0);
	}
	
	public void translate (float tx, float ty) {
		myTranslation.translate(tx, ty);
	}
	
	public void scale(float sx, float sy) {
		myScale.scale(sx, sy);
	}

	public void resetTransform() {
		myRotation.setIdentity();
		myTranslation.setIdentity();
		myScale.setIdentity();
	}
	
	
	public Transform getTranslate() {
		return myTranslation;
		
	}
	
}
