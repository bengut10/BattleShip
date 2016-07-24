package model;

public abstract class Game 
{	
	private int score;
	private String playerName;
	private LeaderBoard lb;
	
	public Game(){
		
	}
	
	public Game(String playerName)
	{
		this.playerName = playerName;
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
		lb = new LeaderBoard();
		lb.storeScore(this);
	}
}
