package controller;

import view.GameScreen;
import model.SingleGame;
import view.ViewHandler;


public class GameController
{
	GameScreen gs;
	SingleGame sg;
	
	
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
		return true;
	}
}
