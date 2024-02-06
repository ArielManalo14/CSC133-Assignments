package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;
import java.util.Vector;

public abstract class GameObject{
	//game object constructor
	private int size;
	private double xLocation;
	private double yLocation;
	private int color;
	private int r;
	private int g;
	private int b;
		
	
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
		int k = rand.nextInt(6)+10;
		return k;
	}
	
	//getter and setter for xLocation
	public double getxLocation() {
		return xLocation;
	}
	public void setxLocation(double xLocation){
		this.xLocation = xLocation;
	}
	
	//randomly choosing an xLocation
	public double randX() {
		int i = rand.nextInt(1024);
		return i;
	}
	
	//getter and setter for yLocation
	public double getyLocation() {
		return yLocation;
	}
	public void setyLocation(double yLocation){
		this.yLocation = yLocation;
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
	
}
