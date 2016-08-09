package controller;

import model.MultiGame;
import view.MultiPlayer;

/**
 * Class GameMultiController maps the users inputs to the Model classes and selects the appropriate view for response.
 * The class creates an object of type MultiGame which creates the player's ships and maps their coordinates.
 * @author RR2
 * @version 1.0
 *
 */
public class GameMultiController {

	
	/**
	 * Constructor - creates a new MultiGame object and starts a multiplayer game, assigns
	 * the controller to a MultiPlayer user interface. The constructor then
	 * creates the user's ships and assigns them coordinates for game play. 
	 * @param multiPlayer - the user interface that is assigned to the controller 
	 */
	public GameMultiController(MultiPlayer multiPlayer)
	{
		MultiGame mp = new MultiGame();
		mp.StartGame();
		multiPlayer.setMyCoordList(mp.getLocs());	
		System.out.println(mp.getLocs().size());
		
	}
	
	
}
