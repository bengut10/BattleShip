package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * The Grid class randomly places ships of a player or enemy on a board and ensures that no 2 ships occupy the same coordinates on a game board.
 * The ships that are placed are assigned grid coordinates in the form of "a9".
 * @author RR2
 * @version 1.0
 */
public class Grid implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String alphabet = "abcdefghij";
	private int gridLength = 10;
	private int gridSize = 100;
	private int [] grid = new int [gridSize];
	private int shipCount = 0;
/**
 * Randomly places ships on 10 x 10 grid with every other ship being placed horizontally and vertically. If any of the selected spaces for the 
 * ship are occupied, a new random starting location will be selected. The ships location will then be converted in to string coordinates in the 
 * form of "a9" . 
 * @param shipSize - the number of spaces that need to be chosen for the ship on the grid.
 * @return The string coordinates of the placed ships
 */
	public ArrayList<String> placeShipsOnGrid(int shipSize) 
	{
		ArrayList<String> alphaCells = new ArrayList<String>();     // holds alpha numeric coordinates such as c7
		@SuppressWarnings("unused")
		String[] alphacoords = new String[shipSize];            
		String temp = null;                                        //temp concat variable
		int [] coordinates  = new int [shipSize];                  //current possible coordinates 
		int attempts = 0;                                          //attempts to place ship - for testing
		boolean success = false;                                   //found a good starting location for ship placement?
		int location = 0;                                          //starting location 
		shipCount++;                                               //next ship
		int incr = 1;                                              //set horizontal increment
		if ((shipCount % 2) ==1 ){                                 //if odd entry of ship, place vertically
			incr = gridLength;                                     //set vertical increment 
		}
		           
		while(!success && attempts ++ < 200)                       //search for location to place current enemy ship - shouldn't take more than 200 attempts
		{
			location = (int) (Math.random() * gridSize);           //get random starting point
			int x = 0;                                             //nth position in the ship to place
			success = true;                                        //assume successful ship placement 
			while(success &&  x < shipSize)                        //look for adjacent unused spots to place the rest of the current ship
			{
				if(grid[location] == 0)                            //location is not used
				{
					coordinates[x++] = location;                   //set next portion of the ship
					location += incr;                              //try the next adjacent grid location
					if(location >= gridSize)                       //out of bounds - bottom
					{
						success = false;                           //failure to place ship
					}
					if (x>0 && (location % gridLength == 0))       //out of bounds - right edge
					{
						success = false;                           //failure to place the ship
					}	
				}
				else
				{
					System.out.print(" used " + location);        //found an already used location
					success = false;                              //failure to place ship
				}
			} 
		}
		
		int x = 0;                                               //turn ship location into alpha coordinates 
		int row = 0; 
		int column = 0;
		System.out.println("\n");
		while (x < shipSize)
		{
			grid[coordinates[x]] = 1;
			row = (int) (coordinates[x]/gridLength);            //mark grid points as used
			column = coordinates[x] % gridLength;               //get row value
			temp = String.valueOf(alphabet.charAt(column));     //get numeric equivalent column value
			alphaCells.add(temp.concat(Integer.toString(row))); //convert to alpha
			x++;
			System.out.print("  coord "+x+" = " + alphaCells.get(x-1)+ "\n"); //show ship placement 
		}
		return alphaCells;
   }
}