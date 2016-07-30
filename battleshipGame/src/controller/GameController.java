package controller;

import view.SinglePlayer;
import model.SingleGame;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import model.LeaderBoard;
import view.ViewHandler;


public class GameController
{
	private SinglePlayer sp;
	private SingleGame sg;
	private LeaderBoard lb;
	private ViewHandler vh;
	
	
	public GameController(){}
	
	public boolean performOperation(String operation) 
	{	
		if(operation == "Begin Single Game")
		{
			this.sg = new SingleGame(ViewHandler.userName);
			sg.StartGame();
			return true;	
		}
		
		else if(operation == "Begin MultiPlayer Game")
		{
			return true;
		}
		
		else if(operation == "compare cordinate")
		{
			sp = new SinglePlayer();
			String cordinate = sp.getUserCordinate();	
			return sg.checkUserGuess(cordinate);
		}
		
		else if(operation == "compare enemy cordinate")
		{
		
			SinglePlayer.x = sg.getXenemyCord();
			SinglePlayer.y = sg.getYenemyCord();	
			return sg.enemyMove(4);
		}
		
		else if(operation == "get me the leaderboard")
		{
			lb = new LeaderBoard();
			vh = new ViewHandler();	
			ArrayList <Object> leaderb = (ArrayList) lb.displayLeaderBoard();
			ViewHandler.leaderboard = FXCollections.observableList(leaderb);
		}	
		
		else if(operation == "Notify view of changes")
		{
			ViewHandler.displayWinning();
			return true;
		}
		return false;

	}
}
