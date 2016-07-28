package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SingleGame extends Game implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Ship> playerListOfShips = new ArrayList<Ship>();   
	private ArrayList <Ship> enemyListOfShips = new ArrayList <Ship>();
	private GameHelper helper = new GameHelper();  
	private Random random = new Random();
	private String alpha = "abcdefghij";
	private Boolean enemyTurn = false;
	private int  xEnemyCord;
	private int yEnemyCord;
	private int playerScore;
	private String message;
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
			ArrayList<String> enemyLocation = helper.placeShip(enemyListOfShips.get(i).getShipSize()); 
			enemyListOfShips.get(i).setLocationCells(enemyLocation);  
			
			
		}
		for (int i = 0; i<playerListOfShips.size(); i++)
		{
			ArrayList<String> playerLocation = helper.placeShip(playerListOfShips.get(i).getShipSize()); 
			playerListOfShips.get(i).setLocationCells(playerLocation);
			//System.out.println(playerListOfShips.get(i));
		}
		
	} 
	
	
	public boolean checkUserGuess(String userGuess)
	{   
		
		String result = "miss";   
		for(Ship ship : enemyListOfShips)
		{
			result = ship.wasShot(userGuess);
			
			
			if(result.equalsIgnoreCase("hit"))
			{
				
				return true;
			}
			if(result.equalsIgnoreCase("sink"))  
			{
				message = ship.getShipMessage();
				enemyListOfShips.remove(ship);
				if(enemyListOfShips.isEmpty())
				{
					System.out.println("43343");
					endGame();
				}
				return true;
			}
		}
		
		missedShot();
		System.out.println(getScore());
		playerScore =getScore();
		return false;
	}
	
	public boolean checkEnemyGuess(String enemyGuess)
	{   
		
		String result = "miss";   
		for(Ship ship : playerListOfShips)
		{
			result = ship.wasShot(enemyGuess);	
			
			if(result.equalsIgnoreCase("hit"))
			{
				return true;
			}
			if(result.equalsIgnoreCase("sink"))  
			{
				playerListOfShips.remove(ship);
				
				if(playerListOfShips.isEmpty())
				{
					System.out.println("43343");
					endGame();
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean enemyMove(int difficulty){
	
		String enemyGuess = "";
		String temp = "";
	
		int x = random.nextInt(10);
		xEnemyCord = x;
		int y = random.nextInt(10);
		yEnemyCord = y;
		temp = String.valueOf(alpha.charAt(x));     //get numeric equivalent column value
		enemyGuess = (temp.concat(Integer.toString(y)));
			
		enemyTurn = checkEnemyGuess(enemyGuess);
	
		return enemyTurn;
	
	}
	
	public int getXenemyCord()
	{
		return xEnemyCord;
	}
	public int getYenemyCord()
	{
		return yEnemyCord;
	}


	public int getPlayerScore() {
		return playerScore;
	}


	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}



}