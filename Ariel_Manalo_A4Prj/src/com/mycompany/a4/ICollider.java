package com.mycompany.a4;

public interface ICollider {
	//collision interface
	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
	
}
