package model;

import java.io.Serializable;
import controller.GameController;

public abstract class Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int score;
	private String playerName;
	
	public Game(){
		
	}
	
	public Game(String playerName)
	{
		this.setPlayerName(playerName);
		score = 1000;
	}

	abstract public void StartGame();
	
	public void missedShot()
	{
		this.score = this.score - 10;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void endGame()
	{
		
		LeaderBoard lb = new LeaderBoard();
		lb = new LeaderBoard();
		lb.storeScore(this);
		GameController gc = new GameController();
		gc.performOperation("Notify view of changes");
		
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
