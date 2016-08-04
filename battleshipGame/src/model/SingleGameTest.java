package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleGameTest {

	@Before
	public void setUp() throws Exception {
		SingleGame sg = new SingleGame("game");
		sg.StartGame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartGame() {
	
		Ship shipOne = new Ship(5,  "Carrier"); 
		
		int actualSize = shipOne.getShipSize();
		int expectedSize = 5;
		
		assertEquals(expectedSize, actualSize);
		
		
		
		
	}

	@Test
	public void testGetXenemyCord() {
		
	}

	@Test
	public void testGetYenemyCord() {
		fail("Not yet implemented");
	}

	@Test
	public void testSingleGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckUserGuess() {
		
	}

	@Test
	public void testCheckEnemyGuess() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnemyMove() {
		fail("Not yet implemented");
	}

}
