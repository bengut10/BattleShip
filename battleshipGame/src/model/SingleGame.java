package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SingleGame extends Game implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Ship> listOfShips = new ArrayList<Ship>();   
	private GameHelper helper = new GameHelper();  	
	
	public SingleGame(String name)
	{
		super(name);
	}
	

	@Override
	public void StartGame() 
	{
		Ship shipOne = new Ship(5,  "Carrier"); 
		Ship shipTwo = new Ship(4, "Battleship");
		Ship shipThree = new Ship(3, "Cruiser");
		Ship shipFour = new Ship(3, "submarine");
		Ship shipFive = new Ship(2, "Destroyer");
		listOfShips.add(shipOne); 
		listOfShips.add(shipTwo);
		listOfShips.add(shipThree);
		listOfShips.add(shipFour);
		listOfShips.add(shipFive);
		
		for (int i = 0; i<listOfShips.size(); i++)
		{ 
			ArrayList<String> location = helper.placeShip(listOfShips.get(i).getShipSize()); 
			listOfShips.get(i).setLocationCells(location);                                  
		}
	} 
	
	public boolean checkUserGuess(String userGuess)
	{         
		String result = "miss";   
		for(Ship ship : listOfShips)
		{
			result = ship.wasShot(userGuess);		
			if(result.equalsIgnoreCase("hit"))
			{
				return true;
			}
			if(result.equalsIgnoreCase("sink"))  
			{
				listOfShips.remove(ship);
				if(listOfShips.isEmpty())
				{
					System.out.println("43343");
					endGame();
				}
				return true;
			}
		}
		missedShot();
		System.out.println(getScore());
		return false;
	}
}