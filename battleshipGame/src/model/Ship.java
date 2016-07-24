package model;

import java.util.ArrayList;

public class Ship {
	
	private ArrayList<String> locationCells;  
	private String name;
	private int shipSize;
	
	Ship(){}
	
	public Ship(int shipSize, String name)         
	{
		this.name = name;
		this.shipSize = shipSize;
	}
	
	public void setLocationCells(ArrayList<String> locations)
	{
		this.locationCells = locations;
	}
	
	public int getShipSize() {
		return shipSize;
	}
	
	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}

	public String wasShot(String stringGuess)    
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
			}
		}
		return result;       
	}
}
