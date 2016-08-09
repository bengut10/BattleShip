package controller;

import model.MultiGame;
import view.MultiPlayer;

public class GameMultiController {

	
	
	public GameMultiController(MultiPlayer multiPlayer)
	{
		MultiGame mp = new MultiGame();
		mp.StartGame();
		multiPlayer.setMyCoordList(mp.getLocs());	
		System.out.println(mp.getLocs().size());
	}
	
	
}
