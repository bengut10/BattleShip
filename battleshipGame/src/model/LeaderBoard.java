package model;

import java.io.Serializable;
import java.util.ArrayList;

public class LeaderBoard implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList <Game> scoreList = null;
	
	public LeaderBoard()
	{
		scoreList =  new ArrayList <Game>();
	}
	
	public ArrayList<Game> displayLeaderBoard()
	{
		return this.scoreList;
	}
	
	public void storeScore(Game game)
	{
		scoreList.add(game);
	}
	
	public void findScore()
	{
		
	}

}
