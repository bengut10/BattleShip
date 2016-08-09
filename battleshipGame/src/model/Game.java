package model;

import java.io.Serializable;
import controller.GameController;

/**
 * Class game sets up a game for a user by taking the user's name and assigning the max score of 1000 to 
 * the user. For every missed shot the user's score is decrease by 10. The user's name and score are
 * then added to the leader board.
 * @author RR2
 * @version 1.0
 *
 */
public abstract class Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int score;
	private String playerName;
	
	/**
	 * Initializes the game, the functionality is over written by sub classes SingleGame or MultiGame
	 */
	abstract public void StartGame();
	
	/**
	 * default constructor is left unimplemented
	 */
	public Game(){	
	}
	
	/**
	 * Overloaded constructor sets the player's name and sets their score to 1000.
	 * @param playerName - sets the players name to playerName
	 */
	public Game(String playerName)
	{
		this.setPlayerName(playerName);
		score = 1000;
	}
	/**
	 * Decreases the player score by 10 for each missed shot
	 */
	public void missedShot()
	{
		this.score = this.score - 10;
	}
	
	/**
	 * 
	 * @return the player's current score
	 */
	public int getScore()
	{
		return this.score;
	}
	/**
	 * Adds the player's score at the end of a game to the leaderBoard and notify's the user that
	 * the game has ended.
	 */
	public void endGame()
	{
		LeaderBoard lb = new LeaderBoard();
		lb = new LeaderBoard();
		lb.storeScore(this);
		GameController gc = new GameController();
		gc.notifyOfWin();
	}

	/**
	 * 
	 * @return the name of the player that was entered by the user
	 */
	public String getPlayerName() 
	{
		return playerName;
	}

	/**
	 *  
	 * @param playerName - sets the players name to playerName
	 */
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
}
