package controller;

import view.GameScreen;
import model.SingleGame;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import model.LeaderBoard;
import view.ViewHandler;


public class GameController
{
	GameScreen gs;
	SingleGame sg;
	LeaderBoard lb;
	ViewHandler vh;
	
	public GameController(){}
	
	public boolean performOperation(String operation) 
	{	
		if(operation == "open sec window")
		{
			gs = new GameScreen();
			gs.GameScreenDisplay();
			return true;
		}
		else if(operation == "Begin Single Game")
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
			gs = new GameScreen();
			String cordinate = gs.getUserCordinate();
			if(sg.checkUserGuess(cordinate))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		else if(operation == "get me the leaderboard")
		{
			lb = new LeaderBoard();
			vh = new ViewHandler();	
			
			ArrayList <Object> leaderb = (ArrayList) lb.displayLeaderBoard();
			ViewHandler.leaderboard = FXCollections.observableList(leaderb);

		}	
		return true;
	}
}
