package com.mycompany.a4;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class ActionCommand extends Command{
	private GameWorld gw;
	private CheckBox soundbox;
	private Game game;
	private boolean check;
	public ActionCommand(String command, GameWorld g) {
		super(command);
		gw = g;
		
	}
	public ActionCommand(String command, GameWorld g, CheckBox s) {
		super(command);
		gw = g;
		soundbox = s;
	
	}
	public ActionCommand(String command, GameWorld g, Game gam) {
		super(command);
		gw = g;
		game = gam;
	}
	
	public void actionPerformed(ActionEvent ev) {
		//this method is called whenever a button is pushed, will call a method in gameworld
		Command cOk = new Command("OK");
		Button randb1 = new Button("");
		
		switch(getCommandName()) {
		//switch statement to show which button was chosen and what methods to call 
		case "Accelerate" :
			gw.accelerate();
			break;
		case "Left":
			gw.steerLeft();
			break;
		case "Change Strategies":
			gw.switchStrategies();
			System.out.println("Change Strategies");
			break;
//		case "Collide with NPR":
//			gw.cKey();
//			break;
		case "Collide with Base":
			
			TextField tf = new TextField();
			Command d = Dialog.show("Enter what base collided with: ", tf , cOk);
			//this pops out a dialog box and prompts the player to enter the base they collided with
			if(d == cOk) {
				if((tf.getText().equals("1")) || (tf.getText().equals("2"))
						|| (tf.getText().equals("3")) || (tf.getText().equals("4"))) {
					System.out.println("Collided with base: " + tf.getText());
					gw.checkRLastBase(Integer.parseInt(tf.getText()));
					//this checks what they said and if they are going in order, it will update the robots last base reached
				}
				else {
					System.out.println("invalid input");
					Command f = Dialog.show("invalid input",randb1 , cOk);
					//this dialog box pops up if the player entered something that wasnt a base number(1-4)
					if(f == cOk) {
						System.out.println("chose ok");
					}
				}
			}

			break;
		case "Collide with Energy Station":
			gw.eKey();
			break;
//		case "Collide with Drone":
//			gw.gKey();
//			break;
		case "Tick":
			gw.tKey(20);
			break;
		case "Brake":
			gw.brake();
			break;
		case "Right":
			gw.steerRight();
			break;
		case "Sound On: ":
			//gw.soundOn();
			if(soundbox.isSelected()) {
				gw.setSoundOn();
			}
			else {
				gw.setSoundOff();
			}
			
			break;
		case "About":
			Command g = Dialog.show("Name: Ariel Manalo\n Course: CSC133", randb1, cOk);
			//pops up a dialog box of my name and course
			if(g == cOk) {
				System.out.println("ok");
			}
			
			break;
		case "Help":
			
			Command e = Dialog.show("List of keys that can be used: a, b, l, r, e, g, t", randb1, cOk);
			//pops up a dialog box showing what keys can be used
			if(e == cOk) {
				System.out.println("chose ok");
			}
			
			break;
		case "Exit":
			gw.xKey();
			
			Command cYes = new Command("Yes");
			Command cNo = new Command("No");
		
			Command[] exitCmds = new Command[] {cYes, cNo};
			Command c = Dialog.show("Want to exit?", randb1 , exitCmds);
			//this is asking the user whether or not they want to exit, if they click yes, game will exit, if no, game will not exit
			if(c == cYes) {
				System.out.println("chose yes");
				gw.exit();
			}
			else if(c == cNo){
				System.out.println("chose no");
				gw.nKey();
			}
			
			break;
		case "Play":
			
			check =!check;
			if(check) {
				game.pauseGame();
				gw.isPaused(true);
			}
			else {
				game.resumeGame();
				gw.isPaused(false);
			}
			
			break;
		}

	}

}
