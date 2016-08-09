package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import view.SinglePlayer;

public class GameControllerTest {
	
	
	private SinglePlayer sg = new SinglePlayer();
	GameController gameController = new GameController(sg);
	public boolean userCoord(){
		
		String coord = sg.getUserCordinate();
		return true;
	}
	

	@Test
	public void compareUserCoordTest() {
			
		boolean userCoord = gameController.compareUserCoord();	
		assertFalse("Should return false", userCoord);
	}
	
	@Test
	public void compareEnemyCoord(){
		
		boolean enemyCoord = gameController.CompareEnemyCoord();
		
		assertFalse("Enemy's initial compare coordinate should be false", enemyCoord);
		
	}

}
