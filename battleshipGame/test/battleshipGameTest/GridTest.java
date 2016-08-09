package battleshipGameTest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import model.*;

public class GridTest {

	Grid grid;
	ArrayList <String> algCoords; 
	Set <String> setOfCoords;
	
	@Before
	public void setUp()
	{
		algCoords = null;
		setOfCoords = null;
	}
	
	@Test
	public void checkForDuplicatesShip5()
	{
		for(int i = 0; i < 100; i++)
		{
			grid = new Grid();
			algCoords = grid.placeShipsOnGrid(5);
			setOfCoords = new HashSet<String>(algCoords);
			assertTrue(algCoords.size() == setOfCoords.size());
		}
	}
	
	@Test
	public void checkForDuplicatesShip4()
	{
		for(int i = 0; i < 100; i++)
		{
			grid = new Grid();
			algCoords = grid.placeShipsOnGrid(4);
			setOfCoords = new HashSet<String>(algCoords);
			assertTrue(algCoords.size() == setOfCoords.size());
		}
	}
	
	@Test
	public void checkForDuplicatesShip3()
	{
		for(int i = 0; i < 100; i++)
		{
			grid = new Grid();
			algCoords = grid.placeShipsOnGrid(3);
			setOfCoords = new HashSet<String>(algCoords);
			assertTrue(algCoords.size() == setOfCoords.size());
		}
	}
	
	@Test
	public void checkForDuplicatesShip2()
	{
		for(int i = 0; i < 100; i++)
		{
			grid = new Grid();
			algCoords = grid.placeShipsOnGrid(2);
			setOfCoords = new HashSet<String>(algCoords);
			assertTrue(algCoords.size() == setOfCoords.size());
		}
	}
}
