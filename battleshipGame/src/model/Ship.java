package model;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.javafx.geom.RoundRectangle2D;
/**
 * Creates ship representation given by the classic game of Battle Ship. Each ship contains a name, a size, and its current locations on a game board. 
 * 
 * @author RR2
 * @version 1.0
 */
public class Ship implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private ArrayList<String> locationCells;  
	private String name;
	private int shipSize;
	/**
	 * default constructor contains no functionality
	 */
	Ship(){}	
	/**
	 * 
	 * @return - the ships size (how many coordinates does it cover)
	 */
	public int getShipSize() {
		return shipSize;
	}
	/**
	 * 
	 * @param shipSize - set the size of the ship (how many coordinates does it cover
	 */
	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}
	/**
	 * Overloaded constructor creates a ship and assigns it's size and name.
	 * @param shipSize - size of the ship
	 * @param name - name of the ship
	 */
	public Ship(int shipSize, String name)         
	{
		this.name = name;
		this.shipSize = shipSize;
	}
	/**
	 * locations cells is an ArrayList the holds the coordinates of the specified ship
	 * @param locations - sets the coordinates of the ship to locationCells
	 */
	public void setLocationCells(ArrayList<String> locations)
	{
		this.locationCells = locations;
	}
	/**
	 *  
	 * @return the locationCells (ship's coordinates)
	 */
	public ArrayList<String> getLocationCells(){
		return this.locationCells;
	}
	/** 
	 * Looks to see if a player or enemy's guess is a hit or a miss. If the ship becomes empty the ship will be removed  and sunk
	 * @param stringGuess - enemy or player guess in the form "a9"
	 * @return - "miss" if stringGuess is not found, "sink" if the ship no longer contains coordinates, or "hit" if the guess is one of the 
	 * ships coordinates. 
	 */
	
	public String shipWasShot(String stringGuess)    
	{        
		String result = "miss";    
		int index = locationCells.indexOf(stringGuess);
		if (index>= 0)
		{                              
			locationCells.remove(index);
			if(locationCells.isEmpty())
			{              
				result = "sink";
				System.out.println("You sunk the enemy's " + name + " ");
			}
			else
			{
				result = "hit";   
				System.out.println("hit: " );
			}
		}
		return result;       
	}
}