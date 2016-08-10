package src.model;

import java.util.ArrayList;

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
	
	public ArrayList<String> getLocs()
	{
		return this.sendThisCoords;
	}
}
