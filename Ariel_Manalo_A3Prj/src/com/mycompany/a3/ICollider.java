package com.mycompany.a3;

public interface ICollider {
	//collision interface
	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
	
}
