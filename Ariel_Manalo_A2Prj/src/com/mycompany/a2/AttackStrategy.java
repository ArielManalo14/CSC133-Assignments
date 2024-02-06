package com.mycompany.a2;

import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy{
	//private fields for this strategy
	private NonPlayerRobot npr;
	private GameWorld gw;
	
	private double deltaX;
	private double deltaY;
	private double beta;
	private int newSteering;
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
		newSteering = (int) (90 - beta);
		//sets steering direction for npr so it can go towards the robot
		npr.setSteeringDirection(newSteering);
	}
	
	public String getStrat() {
		//returns the strategy name
		return strat;
	}

}
