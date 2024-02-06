package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer {
	//fields for scoreview
	private Label timeL;
	private Label livesLeftL;
	private Label playerLBRL;
	private Label energyL;
	private Label damageL;
	private Label soundL;

	public ScoreView() {
	//this method is just creating all of the labels for the scoreview so they show up when it first runs
		timeL = new Label("Time: " + 0);
		add(timeL);
		timeL.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeL.getAllStyles().setPadding(2, 2, 2, 2);
		//creating lives left label
		livesLeftL = new Label("Lives Left: " + 3);
		add(livesLeftL);
		livesLeftL.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftL.getAllStyles().setPadding(2, 2, 2, 2);
		//creating player last base reached label
		playerLBRL = new Label("Player Last Base Reached: " + 0);
		add(playerLBRL);
		playerLBRL.getAllStyles().setFgColor(ColorUtil.BLUE);
		playerLBRL.getAllStyles().setPadding(2, 2, 2, 2);
		//creating player energy level label
		energyL = new Label("Player Energy Level: " + 100);
		add(energyL);
		energyL.getAllStyles().setFgColor(ColorUtil.BLUE);
		energyL.getAllStyles().setPadding(2, 2, 2, 2);
		//creating damage level label
		damageL = new Label("Player Damage Level: " + "000");
		add(damageL);
		damageL.getAllStyles().setFgColor(ColorUtil.BLUE);
		damageL.getAllStyles().setPadding(2, 2, 2, 2);
		//creating sound label
		soundL = new Label("Sound: " + "OFF");
		add(soundL);
		soundL.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundL.getAllStyles().setPadding(2, 2, 2, 2);

	}
	
	@Override
	public void update(Observable observable, Object data) {
		//this method is to update each of the labels everytime something changes in gameworld
		GameWorld gw = (GameWorld) observable;
		
		timeL.setText("Time: "+Integer.toString(gw.getClockVal()));
		livesLeftL.setText("Lives Left: "+Integer.toString(gw.getLivesLeft()));
		playerLBRL.setText("Player Last Base Reached: "+Integer.toString(gw.getRLastBaseReached()));
		energyL.setText("Player Energy Level: "+Integer.toString((int) gw.getEnergyLevel()));
		damageL.setText("Player Damage Level: "+Integer.toString(gw.getDamageLevel()));
		soundL.setText("Sound: "+gw.displaySound());

	}
	


}
