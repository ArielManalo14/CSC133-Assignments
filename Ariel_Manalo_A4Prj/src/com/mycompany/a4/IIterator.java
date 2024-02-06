package com.mycompany.a4;

public interface IIterator {
	//hasNext and getNext methods for this interface
	public boolean hasNext();
	public GameObject getNext();
	public int getSize();
	public GameObject get(int element);
}
