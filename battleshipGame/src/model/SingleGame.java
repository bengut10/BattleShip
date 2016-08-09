package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Implements the functionality and data storage needed for a single player game. The player's ships are contained in an ArrayList playerListOfShips. 
 * The enemy's ships are contained in an ArrayList enemyListOfShips. The purpose of this class is to compare enemy and player attacks to the Lists of ships
 * and return true or false back to the invoking object.
 * @author RR2
 * @version 1.0
 */
public class SingleGame extends Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList <Ship> playerListOfShips = new ArrayList<Ship>();   
	private ArrayList <Ship> enemyListOfShips = new ArrayList <Ship>();
	private ArrayList<String> sendThisCoords = new ArrayList<String>();
	private Grid grid = new Grid();
	private Random random = new Random();
	private String alpha = "abcdefghij";
	private Boolean enemyTurn = false;
	private int  xEnemyCord;
	private int yEnemyCord;
	
	/**
	 * 
	 * @return xEnemyCord - represents the row of the current coordinate on the enemy board.
	 */
	public int getXenemyCord()
	{
		return xEnemyCord;
	}
	/**
	 * 
	 * @return yEnemyCord - represents the column of the current coordinate on the enemy board.
	 */
	public int getYenemyCord()
	{
		return yEnemyCord;
	}
	/**
	 * Constructs a single game by calling the constructor of the super class Game
	 * @param name - the user's name
	 */
	public SingleGame(String name)
	{
		super(name);
	}
	
	/**
	 * Creates the player's and enemey's ships and adds them to their corresponding list of ships.
	 * The method then invokes the placeShipsOnGrid method to set the locations of each ship.
	 */
	@Override
	public void StartGame() 
	{
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
		
		enemyListOfShips.add(shipOne); 
		enemyListOfShips.add(shipTwo);
		enemyListOfShips.add(shipThree);
		enemyListOfShips.add(shipFour);
		enemyListOfShips.add(shipFive);
		playerListOfShips.add(shipSix); 
		playerListOfShips.add(shipSeven);
		playerListOfShips.add(shipEight);
		playerListOfShips.add(shipNine);
		playerListOfShips.add(shipTen);
		
		for (int i = 0; i<enemyListOfShips.size(); i++)
		{ 
			ArrayList<String> enemyLocation = grid.placeShipsOnGrid(enemyListOfShips.get(i).getShipSize()); 
			enemyListOfShips.get(i).setLocationCells(enemyLocation);  
			
			
		}
		for (int i = 0; i<playerListOfShips.size(); i++)
		{
			ArrayList<String> playerLocation = grid.placeShipsOnGrid(playerListOfShips.get(i).getShipSize()); 
			this.sendThisCoords.addAll((ArrayList<String>) playerLocation.clone());	
			playerListOfShips.get(i).setLocationCells(playerLocation);
			//System.out.println(playerListOfShips.get(i));
		}
		
	} 
	

	public ArrayList<String> getLocs()
	{
		return this.sendThisCoords;
	}
	
	/**
	 * Compares the players guess with all of the coordinates of the enemy's ships and determines if the shot is a miss, a hit, or a sink and removes any
	 * ships that have been sunk. Ends the game if all of the enemy's ships have been sunk.
	 * @param userGuess - the coordinates represented in the form "a9"(represents the location on the enemy's board) that are provided by the player
	 * @return - true if userGuess is found on one of the enemy's ships, false if userGuess is not found on one of the enemey's ships
	 *  
	 */

	public boolean checkUserGuess(String userGuess)
	{   
		String result = "miss";   
		for(Ship ship : enemyListOfShips)
		{
			result = ship.shipWasShot(userGuess);
			
			if(result.equalsIgnoreCase("hit"))
			{
				return true;
			}
			if(result.equalsIgnoreCase("sink"))  
			{
				enemyListOfShips.remove(ship);
				if(enemyListOfShips.isEmpty())
				{
					
					endGame();
				}
				return true;
			}
		}
		
		missedShot();
		System.out.println(getScore());
		return false;
	}
	
	/**
	 * Compares the enemy's guess with all of the coordinates of the player's ships and determines if the shot is a miss, a hit, or a sink and removes any
	 * ships that have been sunk. Ends the game if all of the player ships have been sunk.
	 * @param enemyGuess - the coordinates represented in the form "a9"(represents the location on the player's board) that are provided by the AI)
	 * @return - true if enemyGuess is found on one of the player's ships, false if enemyGuess is not found on one of the player's ships
	 */
	public boolean checkEnemyGuess(String enemyGuess)
	{   
		String result = "miss";   
		for(Ship ship : playerListOfShips)
		{
			result = ship.shipWasShot(enemyGuess);	
			if(result.equalsIgnoreCase("hit"))
			{
				return true;
			}
			if(result.equalsIgnoreCase("sink"))  
			{
				playerListOfShips.remove(ship);
				if(playerListOfShips.isEmpty())
				{
					endGame();
				}
				return true;
			}
		}
		missedShot();
		return false;
	}
	/**
	 * Generates an enemyGuess and compares it with the players coordinates.
	 * Simulates the way a user would guess and creates enemy guesses to attack the player
	 * @param difficulty - the lower the value of difficulty the smarter the stimulated enemy becomes. 
	 * The recommended range is between 4 and 12, 4 making the most challenging opponent. 
	 * @return - true if the enemy has hit one of the players ships, false if no player ship was hit
	 */
	public boolean enemyMove(int difficulty){
		String enemyGuess = "";
		String temp = "";
		int probToHit = random.nextInt(difficulty);
		int randToHit = random.nextInt(difficulty);
		
		if (probToHit == randToHit){
			Ship shipToHit = playerListOfShips.get(0);
			ArrayList<String> cordsToHit = shipToHit.getLocationCells();
			enemyGuess = cordsToHit.get(0);
			System.out.println(enemyGuess);
			char y = enemyGuess.charAt(1);
			char x = enemyGuess.charAt(0);
			xEnemyCord = alpha.indexOf(x);
			yEnemyCord =  Character.getNumericValue(y);
		}
		
		else{
			int x = random.nextInt(10);
			xEnemyCord = x;
			int y = random.nextInt(10);
			yEnemyCord = y;
			temp = String.valueOf(alpha.charAt(x));     //get numeric equivalent column value
			enemyGuess = (temp.concat(Integer.toString(y)));
		}
		
			
		enemyTurn = checkEnemyGuess(enemyGuess);
	
		return enemyTurn;
	
	}
}
