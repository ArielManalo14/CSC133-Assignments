package com.mycompany.a1;

import java.util.ArrayList;

public class GameWorld {
	//Game World class variables
	private Robot r;
	private Drone d1;
	private Drone d2;
	private Base b1;
	private Base b2;
	private Base b3;
	private Base b4;
	private EnergyStation e1;
	private EnergyStation e2;

	int clockVal = 0;
	int remLives = 3;
	
	//creating an array list of all game objects 
	ArrayList<GameObject> gamePieces = new ArrayList<>();
	
	public void init() {
		//code to create initial game objects and set up
		r = new Robot(200.0, 200.0);
		d1 = new Drone();
		d2 = new Drone();
		b1 = new Base(200.0, 200.0, 1);
		b2 = new Base(200.0, 800.0, 2);
		b3 = new Base(700.0, 800.0, 3);
		b4 = new Base(900.0, 400.0, 4);
		e1 = new EnergyStation();
		e2 = new EnergyStation();
		
		//adding each initial game object into the the gamePieces array list
		gamePieces.add(r);
		gamePieces.add(d1);
		gamePieces.add(d2);
		gamePieces.add(b1);
		gamePieces.add(b2);
		gamePieces.add(b3);
		gamePieces.add(b4);
		gamePieces.add(e1);
		gamePieces.add(e2);
	}
	public void loseLife() {
		//this method keeps track of how many lives the player has left and if out of lives, the game ends
		if(remLives >= 2) {
			remLives = remLives - 1;
			System.out.println("Player lost a life");
			init();
		}
		else {
			System.out.println("Game over, you failed");
			exit();
		}			
	}
	public void damage() {
		//this method is called if the robot collides with a robot or drone and gets damage 
		if(r.getDamageLevel() == 0) {
			r.takeDamage();
		}
		else if(r.getDamageLevel() < 90) {
			r.takeDamage();
			r.damagedMaxSpeed();
		}
		else {
			loseLife();
		}
	}
	public void energy() {
		/*this method is for when the robot collides with an energy station,
		the robot takes the energy from the energy station and then the energy station is left with capacity 0
		*/
		r.setEnergyLevel(r.getEnergyLevel() + e1.getCapacity()); 
		e1.setCapacity(0);
	}
	public void changeRoColor() {
		//calls a method from the robot class to fade the robot color 
		r.fadeColor();
	}
	public void changeEnColor() {
		//calls a method from the energy station class to fade the energy station color 
		e1.fadeColor();
	}
	public void exit() {
		//exits the program entirely
		System.exit(0);
	}
	public void accelerate() {
		//calls a robot method to make the robot accelerate if possible
		r.goFaster();
	}
	public void brake() {
		//calls a robot method to make the robot brake 
		r.brake();
	}
	public void steerLeft() {
		//calls a robot method to steer left
		r.steerLeft();
	}
	public void steerRight() {
		//calls a robot method to steer right
		r.steerRight();
	}
	public void baseOne() {
		//sets the robots last base reached to 1
		r.setLastBaseReached(1);;
		System.out.println("Collided with Base 1");
	}
	public void baseTwo() {
		//checks if it already hit base 1, if so, it updates the last base reached to 2
		if((r.getLastBaseReached() + 1) == 2) {
			r.setLastBaseReached(2);
		}
		System.out.println("Collided with Base 2");
	}
	public void baseThree() {
		//checks if it already hit base 2, if so, it updates the last base reached to 3
		if((r.getLastBaseReached() + 1) == 3) {
			r.setLastBaseReached(3);
		}
		System.out.println("Collided with Base 3");
	}
	public void baseFour() {
		/*checks if it already hit base 3, if so, it updates the last base reached to 4
		 *since it reached the last base, the player wins and the game is ended
		 */
		if((r.getLastBaseReached() + 1) == 4) {
			r.setLastBaseReached(4);
			
			//displays that the player won and the total time
			System.out.println("Game over, you win!");
			System.out.println("Total time: " + clockVal);
			exit();
		}
		System.out.println("Collided with Base 4");
	}
	public void cKey() {
		/*this method is called when the robot collides with another robot,
		 *it then increases damage and fades the robot color 
		 */
		System.out.println("collided with other robot");
		damage();
		changeRoColor();
		
	}
	public void eKey() {
		/*This method is for when the robot collides with an energy station
		 *The energy method is called so the robot gains energy and the energy station capacity goes to 0
		 *Then the energy station's color gets faded 
		 *Then the a new energy station is created and added to the gamePieces array
		 */
		energy();
		changeEnColor();
		System.out.println("collided with an energy station");
		EnergyStation e3 = new EnergyStation();
		gamePieces.remove(e1);
		gamePieces.add(e3);
	}
	public void gKey() {
		/*This method is for when a drone collides with the robot
		 *It calls the method damage to increase damage to the robot
		 *It also fades the color of the robot 
		 */
		damage();
		changeRoColor();
		System.out.println("drone collided with robot");
	}
	public void tKey() {
		//1. updates the robots heading depending on the steering direction, that is, adds the steering direction to the heading
		r.setHeading(r.getHeading() + r.getSteeringDirection());
		//2. updates the heading of the drones
		d1.setHeading(d1.getHeading());
		d2.setHeading(d2.getHeading());
		/*3. this is a for loop going through each of the game objects and checking of they are moveable 
		 *Then, if they are moveable, it calls two methods in moveable and updates the objects location 
		 */
		for(int i = 0; i < gamePieces.size(); i++) {
			if(gamePieces.get(i) instanceof Moveable) {
				Moveable mObj = (Moveable)gamePieces.get(i);
				mObj.setxLocation(mObj.moveX());
				mObj.setyLocation(mObj.moveY());
			}
		}
		//4. While time is passing, the robot loses energy, once it loses all energy, it loses a life
		if(r.getEnergyLevel() > 0 + r.getEnergyConsumptionRate()) {
			r.loseEnergy();
		}
		else {
			loseLife();
		}
		//5. increments the the time every time the t key is pressed
		clockVal = clockVal + 1;
		System.out.println("game clock has ticked");
	}
	public void dKey() {
		//displays remaining lives, elapsed time, last base reached, robots energy level and robots damage level
		System.out.println("Remaining Lives: " + remLives);
		System.out.println("Elapsed Time: " + clockVal);
		System.out.println("Last Base Reached: " + r.getLastBaseReached());
		System.out.println("Robots Energy Level: " + r.getEnergyLevel());
		System.out.println("Robots Damage Level: " + r.getDamageLevel());
	}
	public void map() {
		//prints out all of the objects variable values including location, size and color 
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(r);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(e1);
		System.out.println(e2);
	}
	public void xKey() {
		//prompts the user if they want to confirm exit 
		System.out.println("want to exit? 'y' or 'n'");
	}
	public void nKey() {
		//lets the user know they did not confirm to exit
		System.out.println("did not confirm exit");
	}

}
