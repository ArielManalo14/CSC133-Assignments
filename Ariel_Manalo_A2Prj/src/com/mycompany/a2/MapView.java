package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	public MapView() {
		// TODO Auto-generated constructor stub
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(250, 0, 0)));

		
	}
	

	@Override
	public void update(Observable observable, Object data) {
		//this method is calling a method in gameworld that prints all of the tostrings of each object to the console
		GameWorld gw = (GameWorld) observable;
		gw.map();
		
	}

}
