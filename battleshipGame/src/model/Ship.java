package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ship implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private ArrayList<String> locationCells;  
	private String name;
	private int shipSize;
	
	Ship(){}	
	
	public int getShipSize() {
		return shipSize;
	}
	
	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}
	
	public Ship(int shipSize, String name)         
	{
		this.name = name;
		this.shipSize = shipSize;
	}
	
	public void setLocationCells(ArrayList<String> locations)
	{
		this.locationCells = locations;
	}
	public ArrayList<String> getLocationCells(){
		return this.locationCells;
	}

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
