package com.mycompany.a3;

import java.util.ArrayList;


public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gamePieces;
	
	public GameObjectCollection() {
		//creates the arrayLIst
		gamePieces = new ArrayList<>();
		
	}

	public void add(GameObject newObject) {
		//adds an object to arraylist
		gamePieces.add(newObject);

	}
	
	public void remove(GameObject currentObject) {
		gamePieces.remove(currentObject);
	}

	public IIterator getIterator() {
		//returns new iterator
		return new ArrayListIterator();
		
	}
	
	private class ArrayListIterator implements IIterator{
		//new class that implements IIterator interface
		private int currElementIndex;
		
		public ArrayListIterator() {
			//initializes the the current index to -1
			currElementIndex = -1;
		}

		@Override
		public boolean hasNext() {
			//checks if arraylist has a next element
			if(gamePieces.size() <= 0) {
				return false;
			}
			if(currElementIndex == gamePieces.size()-1) {
				return false;
			}
			return true;
		}

		@Override
		public GameObject getNext() {
			//returns the next element at that index
			currElementIndex ++;
			return gamePieces.get(currElementIndex);
		}

		@Override
		public int getSize() {
			return gamePieces.size();
		}

		@Override
		public GameObject get(int element) {
			return gamePieces.get(element);
		}
		
	}

}
