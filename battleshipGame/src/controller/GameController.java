package controller;

import view.GameScreen;
import model.SingleGame;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import model.Game;
import model.LeaderBoard;
import view.ViewHandler;


public class GameController
{
	GameScreen gs;
	SingleGame sg;
	LeaderBoard lb;
	ViewHandler vh;
	private int difficulty = 4;
	private int xCord;
	private int yCord;
	private int playerScore;
	private String message = "Sink The Enemy's Ships";
	
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
			//playerScore = sg.getPlayerScore();
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
			playerScore = sg.getScore();
			this.setMessage(sg.getMessage());
			if(sg.checkUserGuess(cordinate))
			{
				
				return true;
				
				
			}
			else
			{
				return false;
				
			}
			
			
		}
		else if(operation == "compare enemy cordinate"){
			gs = new GameScreen();
			if (sg.enemyMove(difficulty)){
				setxCord(sg.getXenemyCord());
				setyCord(sg.getYenemyCord());
				return true;
			}
			else{
				setxCord(sg.getXenemyCord());
				setyCord(sg.getYenemyCord());
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

	public int getxCord() {
		return xCord;
	}

	public void setxCord(int xCord) {
		this.xCord = xCord;
	}

	public int getyCord() {
		return yCord;
	}

	public void setyCord(int yCord) {
		this.yCord = yCord;
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
