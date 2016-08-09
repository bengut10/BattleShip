package controller;

import javafx.collections.FXCollections;
import java.util.ArrayList;
import view.SinglePlayer;
import model.SingleGame;
import model.LeaderBoard;
import view.ViewHandler;
/**
 * The GameController class maps the users inputs to the Model classes and selects the appropriate view for response. Functionality of the class 
 * includes comparing user and enemy coordinates, setting the difficulty, getting the leaderboard, and determining when the game is complete.
 * @author RR2
 * @version 1.0
 */
public class GameController
{	
	private SingleGame sg = new SingleGame(ViewHandler.userName);
	private SinglePlayer sp;
	private int difficulty = 8;
	
	/**
	 * default constructor is unimplemented
	 */
	public GameController()
	{
	}
	/**
	 * Overloaded constructor that starts a single player game
	 * @param sp sets the GameController to a single player game
	 */
	public GameController(SinglePlayer sp)
	{
		this.sp = sp;
		sg.StartGame();	
	}
	
	/**
	 * Gets the users Coordinate from the current single player game and has the single player game check the user's guess for a hit or miss
	 * @return true if the guess is a hit, or false if it was a miss.
	 */
	public boolean compareUserCoord()
	{
		String cordinate = sp.getUserCordinate();	
		return sg.checkUserGuess(cordinate);
	}
	
	/**
	 * Calls the current single game's artificial intelligence for an enemy move and passes in the current difficulty. Then selects the 
	 * appropriate view by calling a view that shows a miss, or one that shows a hit. 
	 * @return true if the enemy's move is a hit, or false if the enemys move is a miss
	 */
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
	
	/**
	 * selects the LeaderBoard view
	 */
	public void getLeaderBoard()
	{
		LeaderBoard leaderB = new LeaderBoard();
		ArrayList <Object> leaderb = (ArrayList) leaderB.displayLeaderBoard();
		ViewHandler.leaderboard = FXCollections.observableList(leaderb);
	}
	
	/**
	 * selects the notify of Win view and ends the current game.
	 */
	public void notifyOfWin()
	{
		ViewHandler.displayWinning();
		SinglePlayer.gameNotOver = true;
	}
	/**
	 * 
	 * @param difficulty - sets the selected difficulty of the enemy AI
	 */
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
}