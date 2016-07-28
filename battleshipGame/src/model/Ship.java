package model;

import java.util.ArrayList;

public class Ship {
	
	private ArrayList<String> locationCells;  
	private String name;
	private int shipSize;
	private String shipMessage;
	
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
	public ArrayList<String> getLocationCells(){
		return this.locationCells;
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
		String temp = "";
		int index = locationCells.indexOf(stringGuess);
		if (index>= 0)
		{                              
			locationCells.remove(index);
			if(locationCells.isEmpty())
			{              
				result = "sink";
				shipMessage = "You sunk the enemy's " + name;
				System.out.println("You sunk the enemy's " + name + " ");
				
				setShipMessage("You sunk the enemy's "+ name);
			}
			else
			{
				result = "hit";   
				System.out.println("hit: " );
			}
		}
		return result;       
	}

	public String getShipMessage() {
		return shipMessage;
	}

	public void setShipMessage(String shipMessage) {
		this.shipMessage = shipMessage;
	}
	public String getSipMessage(){
		return this.shipMessage;
	}
	public String getName(){
		return this.name;
	}
}
