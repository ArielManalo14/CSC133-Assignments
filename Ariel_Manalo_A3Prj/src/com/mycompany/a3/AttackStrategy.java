package com.mycompany.a3;

import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy{
	//private fields for this strategy
	private NonPlayerRobot npr;
	private GameWorld gw;
	
	private double deltaX;
	private double deltaY;
	private double beta;
	private int newHeading;
	private int idealHeading;
	private String strat = "Attack Strategy";

	public AttackStrategy(NonPlayerRobot r, GameWorld g) {
		//initializes the strategy
		this.npr = r;
		gw = g;
		
	}
	@Override
	public void apply() {
		//this is taking the robots location from gameworld and making the nonplayer robot go towards it
		deltaX = gw.getRLocationX() - npr.getxLocation();
		deltaY = gw.getRLocationY() - npr.getyLocation();
		beta = Math.toDegrees(MathUtil.atan2(deltaY, deltaX));
		idealHeading = (int) (90 - beta);
		newHeading = idealHeading - npr.getHeading();
		
		if(newHeading < 0) {
			npr.steerLeft();
		}
		if(newHeading > 0) {
			npr.steerRight();
		}
		//changing the steering direction in order to achieve the heading needed to reach target
	}
	
	public String getStrat() {
		//returns the strategy name
		return strat;
	}

}
