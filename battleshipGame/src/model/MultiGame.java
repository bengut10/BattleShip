package model;

import java.util.ArrayList;

/**
 * Implements the data storage needed for the players ships during a multiplier game. This includes 
 * creating an placing the player's ships as well as creating a copy to send to the opponents game.
 * Each ship is an ArrayList containing the ships string coordinates. All of the ships are then stored
 * in an ArrayList to iterate through the ships. 
 * @author RR2
 * @version 1.0
 *
 */
public class MultiGame extends Game
{
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Ship> playerListOfShips = new ArrayList<Ship>();   
	private Grid grid = new Grid();
	private ArrayList<String> sendThisCoords = new ArrayList<String>();
	
	@Override
	public void StartGame() {
		Ship shipOne = new Ship(5,  "Carrier"); 
		Ship shipTwo = new Ship(4, "Battleship");
		Ship shipThree = new Ship(3, "Cruiser");
		Ship shipFour = new Ship(3, "submarine");
		Ship shipFive = new Ship(2, "Destroyer");
		
		Ship shipSix = new Ship(5,  "Carrier"); 
		Ship shipSeven = new Ship(4, "Battleship");
		Ship shipEight = new Ship(3, "Cruiser");
		Ship shipNine = new Ship(3, "submarine");
		Ship shipTen = new Ship(2, "Destroyer");
		

		playerListOfShips.add(shipSix); 
		playerListOfShips.add(shipSeven);
		playerListOfShips.add(shipEight);
		playerListOfShips.add(shipNine);
		playerListOfShips.add(shipTen);
		
		for (int i = 0; i<playerListOfShips.size(); i++)
		{
			ArrayList<String>playerLocation = grid.placeShipsOnGrid(playerListOfShips.get(i).getShipSize()); 
			this.sendThisCoords.addAll((ArrayList<String>) playerLocation.clone());	
			playerListOfShips.get(i).setLocationCells(playerLocation);
		}
		
		
	}
	/**
	 * 
	 * @return a copy of the player's ships with coordinates of their locations to the opponents game
	 */
	public ArrayList<String> getLocs()
	{
		return this.sendThisCoords;
	}
}
