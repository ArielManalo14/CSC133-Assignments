package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.Graphics;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;

public class GameWorld extends Observable {
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
	private NonPlayerRobot npr1;
	private NonPlayerRobot npr2;
	private NonPlayerRobot npr3;
	
	private int clockVal = 0;
	private int remLives = 3;
	private boolean sound = false;

	private double width;
	private double height;
	
	//creating an array list of all game objects 
	//ArrayList<GameObject> gamePieces = new ArrayList<>();
	private GameObjectCollection gamePieces;
	
	//creating an array list of npr's
	private ArrayList<NonPlayerRobot> nprList;
	
	Random rand = new Random();
	private BGSound loopSound;
	//private Sound harmonic;
	private Sound alarm;
	private Sound bunny;
	private Sound grunt;
	
	Vector<GameObject> collideVect = new Vector<>();
	//Vector<GameObject> obj2Vect = new Vector<>();
	
	private GameObject objA;
	private GameObject objB;
	
	boolean isPaused = false;
	
	
	public void init(double mapWidth, double mapHeight) {
		//code to create initial game objects and set up
		setGamePieces(new GameObjectCollection());
		
		r = new Robot(200.0, 200.0);
		d1 = new Drone();
		d2 = new Drone();
		b1 = new Base(250.0, 250.0, 1, this);
		b2 = new Base(1200.0, 300.0, 2, this);
		b3 = new Base(300.0, 900.0, 3, this);
		b4 = new Base(1300.0, 1000.0, 4, this);
		e1 = new EnergyStation(this);
		e2 = new EnergyStation(this);
		
		nprList = new ArrayList<NonPlayerRobot>();
		
		npr1 = new NonPlayerRobot(b1.nprInitialx(), b1.nprInitialy());
		npr1.setStrategy(new AttackStrategy(npr1, this));
		nprList.add(npr1);
		npr2 = new NonPlayerRobot(b2.nprInitialx(), b2.nprInitialy());
		npr2.setStrategy(new AttackStrategy(npr2, this));
		nprList.add(npr2);
		npr3 = new NonPlayerRobot(b3.nprInitialx(), b3.nprInitialy());
		npr3.setStrategy(new NextBaseStrategy(npr3, this));
		nprList.add(npr3);
		
		
		width = mapWidth;
		height = mapHeight;
		
		//adding each initial game object into the the gamePieces array list
//		getGamePieces().add(r);
//		getGamePieces().add(d1);
//		getGamePieces().add(d2);
//		getGamePieces().add(b1);
//		getGamePieces().add(b2);
//		getGamePieces().add(b3);
//		getGamePieces().add(b4);
//		getGamePieces().add(e1);
//		getGamePieces().add(e2);
//		getGamePieces().add(npr1);
//		getGamePieces().add(npr2);
//		getGamePieces().add(npr3);
		
		gamePieces.add(r);
		gamePieces.add(d1);
		gamePieces.add(d2);
		gamePieces.add(b1);
		gamePieces.add(b2);
		gamePieces.add(b3);
		gamePieces.add(b4);
		gamePieces.add(e1);
		gamePieces.add(e2);
		gamePieces.add(npr1);
		gamePieces.add(npr2);
		gamePieces.add(npr3);
		
		this.setChanged();
		
	}
	
	public void attack() {
		//this is going through the lst and invoking each of strategies for each npr
		for(NonPlayerRobot npr : nprList) {
			npr.invokeStrategy();
		}
	}
	
	public void switchStrategies() {
		//this is switching each npr's strategy to a different one
		for(NonPlayerRobot npr : nprList) {
			if(npr.getStrategy().getStrat().equals("Attack Strategy")) {
				npr.setStrategy(new NextBaseStrategy(npr, this));
			}
			else {
				npr.setStrategy(new AttackStrategy(npr, this));
			}
			//npr.setLastBaseReached(npr.getLastBaseReached() + 1);
		}
	}
	
	public void loseLife() {
		//this method keeps track of how many lives the player has left and if out of lives, the game ends
		if(remLives >= 2) {
			remLives = remLives - 1;
			
			
			if(sound == true) {
				grunt.play();
			}

			
			System.out.println("Player lost a life");
			init(width, height);
			setChanged();
			notifyObservers(remLives);
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
			
			setChanged();
			notifyObservers(r.getDamageLevel());
		}
		else if(r.getDamageLevel() < 90) {
			r.takeDamage();
			r.damagedMaxSpeed();
			
			setChanged();
			notifyObservers(r.getDamageLevel());
		}
		else {
			loseLife();
			setChanged();
			notifyObservers(remLives);
		}
	}
	
	public void nprDamage() {
		//this method is for when an npr takes damage, it will increase damage level
		if(npr1.getDamageLevel() == 0) {
			npr1.takeDamage();
		}
		else if(npr1.getDamageLevel() < 90) {
			npr1.takeDamage();
			npr1.damagedMaxSpeed();
		}
		else {
			//loseLife();
			npr1.setSpeed(0);
		}
	}
	
	public void energy() {
		/*this method is for when the robot collides with an energy station,
		the robot takes the energy from the energy station and then the energy station is left with capacity 0
		*/
		r.setEnergyLevel((int) (r.getEnergyLevel() + e1.getCapacity())); 
		e1.setCapacity(0);
		
		setChanged();
		notifyObservers(r.getEnergyLevel());
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
	

	public void checkRLastBase(int baseNum) {
		//this checks whats the last base the robot went to and they have to get to them in sequential order.
		if(r.getLastBaseReached()==(baseNum-1)) {
			r.setLastBaseReached(baseNum);
		}
		else {
			System.out.println("last base didn't change");
		}
		
		if(r.getLastBaseReached() == 4) {
			//if the robot reaches the last base, they win the game 
			System.out.println("Game over, you win!");
			exit();
		}
		setChanged();
		notifyObservers(r.getLastBaseReached());
	}
	
//	public void cKey() {
//		/*this method is called when the robot collides with another robot,
//		 *it then increases damage and fades the robot color 
//		 */
//		System.out.println("collided with other robot");
//		damage();
//		nprDamage();
//		changeRoColor();
//		
//	}
	
	public void eKey() {
		/*This method is for when the robot collides with an energy station
		 *The energy method is called so the robot gains energy and the energy station capacity goes to 0
		 *Then the energy station's color gets faded 
		 *Then the a new energy station is created and added to the gamePieces array
		 */
		energy();
		changeEnColor();
		System.out.println("collided with an energy station");
		EnergyStation e3 = new EnergyStation(this);
		//gamePieces.remove(e1);
		getGamePieces().add(e3);
	}
	
//	public void gKey() {
//		/*This method is for when a drone collides with the robot
//		 *It calls the method damage to increase damage to the robot
//		 *It also fades the color of the robot 
//		 */
//		damage();
//		changeRoColor();
//		System.out.println("drone collided with robot");
//	}
	
	public void tKey(int elapsedMillisecs) {
		//1. updates the robots heading depending on the steering direction, that is, adds the steering direction to the heading
		r.setHeading((int) ((r.getHeading() + r.getSteeringDirection())));
		r.setSteeringDirection(0);
		
		//2. updates the heading of the drones
		d1.setHeading((int) (d1.getHeading()));
		d2.setHeading((int) (d2.getHeading()));
		
		npr1.setHeading((int) ((npr1.getHeading() + npr1.getSteeringDirection())));
		npr3.setHeading((int) ((npr3.getHeading() + npr3.getSteeringDirection())));
		npr2.setHeading((int) ((npr2.getHeading() + npr2.getSteeringDirection())));
		
		npr1.setSteeringDirection(0);
		npr2.setSteeringDirection(0);
		npr3.setSteeringDirection(0);
		
		/*3. this is a for loop going through each of the game objects and checking of they are moveable 
		 *Then, if they are moveable, it calls two methods in moveable and updates the objects location 
		*/
		IIterator it = getGamePieces().getIterator();
		//creating an iterator for gamepieces to use in while loop
		while(it.hasNext()) {
			GameObject temp = it.getNext();
			if(temp instanceof Moveable) {
				//checking if the object is moveable 
				Moveable mObj = (Moveable) temp;
				mObj.setxLocation(mObj.moveX(elapsedMillisecs, width));
				mObj.setyLocation(mObj.moveY(elapsedMillisecs, height));
				//calling the move methods for both x and y locations 
			}
		}
	
		/*This is going through each of the objects and checking if there are any
		 * collisions going on between them, then it will call gameobject methods
		 * to handle the collision
		 */
		
		//System.out.println(obj1Vect.toString());
		
		/*
		 * collision array
		 * if collided add both a and b
		 * check if both are in array, if not, remove both 
		 * 
		 * check first
		 * then check if still colliding 
		 * handle collision 
		 * 
		 * for
		 * for
		 * if collide
		 * handle
		 * else(theyre not colliding)
		 * remove
		 */
		
		
		for(int i=0; i<it.getSize(); i++) {
			for(int j = 1+i; j<it.getSize(); j++) {
				objA = (it.get(i));
				objB = (it.get(j));
				if(objA.collidesWith(objB)) {
					//if(!(collideVect.contains(objA)) && !(collideVect.contains(objB))) {
						
					if(!(objA.getCollideVect().contains(objB)) && !(objB.getCollideVect().contains(objA))) {	
						objA.handleCollision(objB);
						
						
						//collideVect.add(objA);			
//						objA.getvector().addobjb
//						objb.getv.addobja
//						collideVect.add(objB);
						
						objA.getCollideVect().add(objB);
						objB.getCollideVect().add(objA);
						
						if(objA instanceof Robot && objB instanceof Robot) {
							if(sound == true) {
								alarm.play();
							}
						}
						if((objA instanceof Robot && objB instanceof EnergyStation) 
								|| (objA instanceof EnergyStation && objB instanceof Robot)) {
							if(sound == true) {
								bunny.play();
							}
						}
					}
				}
				else {
//					collideVect.remove(objA);
//					collideVect.remove(objB);
					
					objA.getCollideVect().remove(objB);
					objB.getCollideVect().remove(objA);
					
				}
			}
		}
		
//		for(int i=0; i<it.getSize(); i++) {
//			for(int j = 1+i; j<it.getSize(); j++) {
//				objA = (it.get(i));
//				objB = (it.get(j));
//				
//				if((objB.collidesWith(objA) || objA.collidesWith(objB)) && !(obj1Vect.contains(objB)) && !(obj2Vect.contains(objA))) {
//				
//					if(objA instanceof Robot || objB instanceof Robot) {
//						if(objA instanceof Robot && objB instanceof Robot) {
//							
//							objA.handleCollision(objB);
//							obj1Vect.add(objB);
//							obj2Vect.add(objA);
//							
//							if(sound == true) {
//								alarm.play();
//							}
//							
//						}
//						else if(objA instanceof Robot){
//							objA.handleCollision(objB);
//							obj1Vect.add(objB);
//							if(objB instanceof EnergyStation){
//								
//								if(sound == true) {
//									bunny.play();
//								}
//							}
//						}
//						else {
//							objB.handleCollision(objA);
//							obj2Vect.add(objA);
//							if(objA instanceof EnergyStation){
//								if(sound == true) {
//									bunny.play();
//								}
//							}
//						}
//					}
//					else {
//						continue;
//					}
//				}
//			}
//		}
				
		
		//4. While time is passing, the robot loses energy, once it loses all energy, it loses a life
		if(r.getEnergyLevel() > 0 + r.getEnergyConsumptionRate()) {
			r.loseEnergy(elapsedMillisecs);
			
			setChanged();
			notifyObservers(r.getEnergyLevel());
		}
		else {
			loseLife();
		}
		
		/* This is checking if the robot won
		 * 
		 */
		if(r.getLastBaseReached() == 4) {
			System.out.println("Game over, you win!");
			exit();
		}
		
		//notify last base reached to mapview
		notifyObservers(r.getLastBaseReached());
		
		// updating damagelevel in scoreview
		notifyObservers(r.getDamageLevel());
		if(r.getDamageLevel() >=100) {
			loseLife();
		}
		
		//check if a npr has reached last base
		if((npr1.getLastBaseReached() == 4 && r.getLastBaseReached() < 4) ||
			(npr2.getLastBaseReached() == 4 && r.getLastBaseReached() < 4) ||
			(npr3.getLastBaseReached() == 4 && r.getLastBaseReached() < 4)){
			System.out.println("Game over, a non-player robot wins");
			exit();
		}
		
		//System.out.println("game clock has ticked");
		
		attack();
		
		//5. increments the the time every time the t key is pressed
		clockVal = clockVal + 1;
		
		//notify observers
		setChanged();
		notifyObservers(clockVal);
		

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
		System.out.println(npr1);
		System.out.println(npr2);
		System.out.println(npr3);
	}
	
	public void xKey() {
		//prompts the user if they want to confirm exit 
		System.out.println("want to exit? 'y' or 'n'");
	}
	
	public void nKey() {
		//lets the user know they did not confirm to exit
		System.out.println("did not confirm exit");
	}
	
	public int getClockVal() {
		//returns clockval
		return clockVal;
	}
	
	public int getLivesLeft() {
		//returns remlives
		return remLives;
	}
	public int getRLastBaseReached() {
		//returns robors last base reached
		return r.getLastBaseReached();
	}
	
	public double getEnergyLevel() {
		//returns robots energy level
		return r.getEnergyLevel();
	}
	
	public int getDamageLevel() {
		//returns robots damage level
		return r.getDamageLevel();
	}
 
	public void setSoundOn() {
		//sets sound to true 
		sound = true;

		loopSound.play();
		//grunt.play();
		//alarm.play();
		
		setChanged();
		notifyObservers(sound);
	}
	
	public void setSoundOff() {
		//sets sound to false
		sound = false;
		
		loopSound.pause();
		
		setChanged();
		notifyObservers(sound);
	}
	
	
	
	public String displaySound() {
		//if statement to show what to display in scoreview to show whether the sound is on or off
		if(sound == true) {
			return "ON";
		}
		else {
			return "OFF";
		}
	}
	
	public double getRLocationX() {
		//returns robots xlocation
		return r.getxLocation();
	}
	
	public double getRLocationY() {
		//returns robots ylocation
		return r.getyLocation();
	}
	
	//returning xlocation of bases
	public double getB1XLoc() {
		return b1.getxLocation();
	}
	public double getB2XLoc() {
		return b2.getxLocation();
	}
	public double getB3XLoc() {
		return b3.getxLocation();
	}
	public double getB4XLoc() {
		return b4.getxLocation();
	}
	//returning ylocation of bases
	public double getB1YLoc() {
		return b1.getyLocation();
	}
	public double getB2YLoc() {
		return b2.getyLocation();
	}
	public double getB3YLoc() {
		return b3.getyLocation();
	}
	public double getB4YLoc() {
		return b4.getyLocation();
	}

	public GameObjectCollection getGamePieces() {
		return gamePieces;
	}

	public void setGamePieces(GameObjectCollection gamePieces) {
		this.gamePieces = gamePieces;
	}
	
	public void createSounds() {
		//System.out.println("1");
		//loopSound = alarm;
		loopSound = new BGSound("nocca3.wav");
		//System.out.println("2");
		//harmonic = new Sound("HARPINIC.wav");
		alarm = new Sound("alarm.wav");
		//System.out.println("3");
		//bunny = alarm;
		bunny = new Sound("bunny.wav");
		//System.out.println("4");
		//grunt = alarm;
		grunt = new Sound("DK_Grunt_2.wav");
		//System.out.println("5");
		
	}

	public void isPaused(boolean b) {
		isPaused = b;
	}
	
	public boolean getisPaused() {
		return isPaused;
	}

}
