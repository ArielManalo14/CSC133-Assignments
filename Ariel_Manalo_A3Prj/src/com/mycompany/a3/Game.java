package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;

public class Game extends Form implements ActionListener, Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private Label label;
	private int t = 0;
	
	BGSound loopSound;
	Sound alarm;
	Sound bunny;
	Sound grunt;

	private UITimer timer;
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		add(new MapView(gw));
		
		//creating buttons
		Button b1 = new Button("Accelerate");
		Button b2 = new Button("Left");
		Button b3 = new Button("Change Stategies");
//		Button b4 = new Button("Collide with NPR");
//		Button b5 = new Button("Collide wiht Base");
//		Button b6 = new Button("Collide with Energy Station");
//		Button b7 = new Button("Collide with Drone");
		//Button b8 = new Button("Tick");
		Button b9 = new Button("Brake");
		Button b10 = new Button("Right");
		
		Button play = new Button("Play");
		
		//setting commands to each of the buttons
		b1.setCommand(new ActionCommand("Accelerate",gw));
		b2.setCommand(new ActionCommand("Left",gw));
		b3.setCommand(new ActionCommand("Change Strategies",gw));
//		b4.setCommand(new ActionCommand("Collide with NPR",gw));
//		b5.setCommand(new ActionCommand("Collide with Base",gw));
//		b6.setCommand(new ActionCommand("Collide with Energy Station",gw));
//		b7.setCommand(new ActionCommand("Collide with Drone",gw));
		//b8.setCommand(new ActionCommand("Tick",gw));
		b9.setCommand(new ActionCommand("Brake",gw));
		b10.setCommand(new ActionCommand("Right",gw));
		
		play.setCommand(new ActionCommand("Play", gw, this));
		
		//setting the layout in order to have multiple containers
		setLayout(new BorderLayout());
		
		//adding the title
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		Label myTF = new Label("Robo-track Game");
		myTF.getAllStyles().setAlignment(CENTER);
		myTF.getAllStyles().setFgColor(ColorUtil.BLACK);
		myToolbar.setTitleComponent(myTF);
		
		/* These are the side menu items
		 * they are being created, some are buttons, and one is a checkbox
		 * then i added an actioncommand to them
		 */
		Command sideMenuItem1 = new Command("Side Menu");
		myToolbar.addCommandToSideMenu(sideMenuItem1);
		
		Button accelerateButton = new Button("Accelerate");
		accelerateButton.setCommand(new ActionCommand("Accelerate",gw));
		myToolbar.addComponentToSideMenu(accelerateButton);
		
		CheckBox soundCheck = new CheckBox("Sound On: ");
		soundCheck.setCommand(new ActionCommand("Sound On: ",gw,soundCheck)); //calls a different constructor for the sound
		myToolbar.addComponentToSideMenu(soundCheck);
		
		Button aboutButton = new Button("About");
		aboutButton.setCommand(new ActionCommand("About",gw));
		myToolbar.addComponentToSideMenu(aboutButton);
			
		Button helpButton = new Button("Help");
		helpButton.setCommand(new ActionCommand("Help",gw));
		myToolbar.addComponentToSideMenu(helpButton);
		
		Button exitButton = new Button("Exit");
		exitButton.setCommand(new ActionCommand("Exit",gw));
		myToolbar.addComponentToSideMenu(exitButton);
		
		
		//adding score view to the form. will put it on the top
		add(BorderLayout.NORTH, sv);

		//left container components 
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.add(b1);
		leftContainer.add(b2);
		leftContainer.add(b3);
		//styles of button and containers
		b1.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		b1.getAllStyles().setBgTransparency(200);
		b1.getAllStyles().setFgColor(ColorUtil.WHITE);
		b1.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		b2.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		b2.getAllStyles().setBgTransparency(200);
		b2.getAllStyles().setFgColor(ColorUtil.WHITE);
		b2.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		b3.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		b3.getAllStyles().setBgTransparency(200);
		b3.getAllStyles().setFgColor(ColorUtil.WHITE);
		b3.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		add(BorderLayout.WEST, leftContainer);
		
		//right container components
		Container rightContainer = new Container(new GridLayout(4,1));
		rightContainer.add(b9);
		rightContainer.add(b10);
		//styles of buttons and container
		b9.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		b9.getAllStyles().setBgTransparency(200);
		b9.getAllStyles().setFgColor(ColorUtil.WHITE);
		b9.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		b10.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		b10.getAllStyles().setBgTransparency(200);
		b10.getAllStyles().setFgColor(ColorUtil.WHITE);
		b10.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		add(BorderLayout.EAST, rightContainer);
		
		//adding the map view to the form 
		add(BorderLayout.CENTER, mv);
		
		//bottom container components
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
//		bottomContainer.add(b4);
//		bottomContainer.add(b5);
//		bottomContainer.add(b6);
//		bottomContainer.add(b7);
		//bottomContainer.add(b8);
		
		bottomContainer.add(play);
		// this is just changing the styles of buttons and container
//		b4.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
//		b4.getAllStyles().setBgTransparency(200);
//		b4.getAllStyles().setFgColor(ColorUtil.WHITE);
//		b4.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		play.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		play.getAllStyles().setBgTransparency(200);
		play.getAllStyles().setFgColor(ColorUtil.WHITE);
		play.getAllStyles().setBgColor(ColorUtil.BLUE);
		
//		b5.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
//		b5.getAllStyles().setBgTransparency(200);
//		b5.getAllStyles().setFgColor(ColorUtil.WHITE);
//		b5.getAllStyles().setBgColor(ColorUtil.BLUE);
//		
//		b6.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
//		b6.getAllStyles().setBgTransparency(200);
//		b6.getAllStyles().setFgColor(ColorUtil.WHITE);
//		b6.getAllStyles().setBgColor(ColorUtil.BLUE);
//		
//		b7.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
//		b7.getAllStyles().setBgTransparency(200);
//		b7.getAllStyles().setFgColor(ColorUtil.WHITE);
//		b7.getAllStyles().setBgColor(ColorUtil.BLUE);
		
//		b8.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
//		b8.getAllStyles().setBgTransparency(200);
//		b8.getAllStyles().setFgColor(ColorUtil.WHITE);
//		b8.getAllStyles().setBgColor(ColorUtil.BLUE);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		add(BorderLayout.SOUTH, bottomContainer);
	
		//this is for the keybinding requirement
		//adding a key listener to the key 
		this.addKeyListener('a', this);
		this.addKeyListener('l', this);
		this.addKeyListener('e', this);
		this.addKeyListener('g', this);
		this.addKeyListener('t', this);
		this.addKeyListener('b', this);
		this.addKeyListener('r', this);
		
		label = new Label("0"); 
		bottomContainer.add(label);
		
		//shows this on the form
		this.show();
		
		gw.init(mv.getWidth(), mv.getHeight());

		
		gw.createSounds();
		//Sound alarm = new Sound("alarm.wav");
		//alarm.play();
		//loopSound.run();
		revalidate();
		
		timer = new UITimer(this);
		timer.schedule(100, true, this);

	}
	
	
	public void actionPerformed(ActionEvent evt) {
		//switch statement for key binding and calls 
		switch(evt.getKeyEvent()) {
		case 'a': 
			gw.accelerate();
			break;
		case 'l':
			gw.steerRight();
			break;
		case 'e':
			gw.eKey();
			break;
//		case 'g':
//			gw.gKey();
//			break;
		case 't':
			gw.tKey(20);
			break;
		case 'b':
			gw.brake();
			break;
		case 'r':
			gw.steerRight();
			break;
		}
	}
	
	public void run() {
		//label.setText(Integer.toString(t++)+"sec");
		mv.tick();
		//alarm.play();
	}
	
	public void pauseGame() {
		timer.cancel();
	}
	
	public void resumeGame() {
		timer.schedule(100, true, this);
	}

}
