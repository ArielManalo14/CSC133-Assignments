package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	
	public Game() {
		//creates a new game world and begins the game
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		// This method is allowing the user to type commands in order to do something in the game
		Label myLabel = new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField= new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt) {
				//This contains a switch statement for each key that could be pressed and calls other methods inside them
				
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				switch (sCommand.charAt(0)) {
				case 'a' :
					// tells robot to accelerate if possible
					gw.accelerate();
					break;
				case 'b' :
					// tells robot to brake
 					gw.brake();
					break;
				case 'l' :
					//chance steering direction to the left
					gw.steerLeft();
					break;
				case 'r' :
					//change steering direction to the right
					gw.steerRight();
					break;
				case 'c' :
					//collided robot with robot
					gw.cKey();
					break;
				case '1' : 
					//robot collided with base number 1
					gw.baseOne();
					break;
				case '2' : 
					//robot collided with base number 2
					gw.baseTwo();
					break;
				case '3' : 
					//robot collided with base number 3
					gw.baseThree();
					break;
				case '4' : 
					//robot collided with base number 4
					gw.baseFour();
					break;
				case '5' : 
					//robot collided with base number 5 but doesn't exist so nothing happens
					break;
				case '6' : 
					//robot collided with base number 6 but doesn't exist so nothing happens
					break;
				case '7' : 
					//robot collided with base number 7 but doesn't exist so nothing happens
					break;
				case '8' : 
					//robot collided with base number 8 but doesn't exist so nothing happens
					break;
				case '9' : 
					//robot collided with base number 9 but doesn't exist so nothing happens
					break;
				case 'e' :
					//intersected with energy station
					gw.eKey();
					break;
				case 'g' :
					//drone collided with robot
					gw.gKey();
					break;
				case 't' :
					//game clock has ticked
					gw.tKey();
					break;
				case 'd' :
					//display values
					gw.dKey();
					break;
				case 'm' :
					//map, this is the set of lines needed to display
					gw.map();

					break;
				case 'x' :
					//prompted y or n to exit
					gw.xKey();
					break;
				case 'y' :
					//user has confirmed by saying yes
					gw.exit();
					break;
				case 'n' :
					//user has not confirmed exit by saying no
					gw.nKey();
					break;
				}
			}
		}
		
		);
	}
}
