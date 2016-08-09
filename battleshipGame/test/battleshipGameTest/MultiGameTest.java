package battleshipGameTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.*;
import org.junit.Before;
import org.junit.Test;


public class MultiGameTest 
{
	ArrayList <String> algCoords;
	Set <String> setOfCoords;
	MultiGame multiGame;
	
	@Before
	public void setUp()
	{
		algCoords = null;
		setOfCoords = null;
	}
	
	@Test
	public void testForDuplicates() 
	{
		for(int i = 0; i < 100; i++)
		{
			multiGame = new MultiGame();
			multiGame.StartGame();
			algCoords = multiGame.getLocs();
			setOfCoords = new HashSet<String>(algCoords);
			assertTrue(algCoords.size() == setOfCoords.size());
		}
	}
}
