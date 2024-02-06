package com.mycompany.a4;

import com.codename1.util.MathUtil;

public class NextBaseStrategy implements IStrategy {

	//fields for nextbasestrategy
	private NonPlayerRobot npr;
	private GameWorld gw;
	
	private double deltaX;
	private double deltaY;
	private double beta;
	private int idealHeading;
	private int newHeading;
	private String strat = "Next Base Strategy";
	
	public NextBaseStrategy(NonPlayerRobot r, GameWorld g) {
		//initializing this strategy
		npr = r;
		gw = g;
	}

	@Override
	public void apply() {
		//this method that is called in other classes 
		if(npr.getLastBaseReached() == 0) {
			//checking if last base reached is zero, then makes it move to the next base
			deltaX = gw.getB1XLoc() - npr.getxLocation();
			deltaY = gw.getB1YLoc() - npr.getyLocation();
			
			beta = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
			idealHeading = (int) (90 - beta);
			newHeading = idealHeading - npr.getHeading();
			if(newHeading < 0) {
				npr.steerLeft();
			}
			if(newHeading > 0) {
				npr.steerRight();
			}
		}
				
		if(npr.getLastBaseReached()==1) {
			//checking if last base reaches is 1, then moves to base 2
			deltaX = gw.getB2XLoc() - npr.getxLocation();
			deltaY = gw.getB2YLoc() - npr.getyLocation();
			
			beta = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
			idealHeading = (int) (90 - beta);
			newHeading = idealHeading - npr.getHeading();
			if(newHeading < 0) {
				npr.steerLeft();
			}
			if(newHeading > 0) {
				npr.steerRight();
			}
		}
		else if(npr.getLastBaseReached()==2) {
			//checking if the last base reached is 2, then moves to base 3
			deltaX = gw.getB3XLoc() - npr.getxLocation();
			deltaY = gw.getB3YLoc() - npr.getyLocation();
			
			beta = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
			idealHeading = (int) (90 - beta);
			newHeading = idealHeading - npr.getHeading();
			if(newHeading < 0) {
				npr.steerLeft();
			}
			if(newHeading > 0) {
				npr.steerRight();
			}
		}
		else if(npr.getLastBaseReached()==3) {
			
			deltaX = gw.getB4XLoc() - npr.getxLocation();
			deltaY = gw.getB4YLoc() - npr.getyLocation();
			
			beta = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
			idealHeading = (int) (90 - beta);
			newHeading = idealHeading - npr.getHeading();
			if(newHeading < 0) {
				npr.steerLeft();
			}
			if(newHeading > 0) {
				npr.steerRight();
			}
		}
	}
	
	public String getStrat() {
		//returns the strategy name
		return strat;
	}

}
