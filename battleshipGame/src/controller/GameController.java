package controller;

import javafx.collections.FXCollections;
import java.util.ArrayList;
import view.SinglePlayer;
import model.SingleGame;
import model.LeaderBoard;
import view.ViewHandler;

public class GameController
{	
	private SingleGame sg = new SingleGame(ViewHandler.userName);
	private SinglePlayer sp;
	private int difficulty = 8;
	
	public GameController()
	{
	}
	public GameController(SinglePlayer sp)
	{
		this.sp = sp;
		sg.StartGame();	
	}
		
	public boolean compareUserCoord()
	{
		String cordinate = sp.getUserCordinate();	
		return sg.checkUserGuess(cordinate);
	}
	
	public boolean CompareEnemyCoord()
	{
		if(sg.enemyMove(difficulty)){
			sp.setX(sg.getXenemyCord());
			sp.setY(sg.getYenemyCord());
			return true;
		}
		else{
			sp.setX(sg.getXenemyCord());
			sp.setY(sg.getYenemyCord());
			return false;
		}
	}
	
	
	public void getLeaderBoard()
	{
		LeaderBoard leaderB = new LeaderBoard();
		ArrayList <Object> leaderb = (ArrayList) leaderB.displayLeaderBoard();
		ViewHandler.leaderboard = FXCollections.observableList(leaderb);
	}
	
	public void notifyOfWin()
	{
		ViewHandler.displayWinning();
		SinglePlayer.gameNotOver = true;
	}
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
}