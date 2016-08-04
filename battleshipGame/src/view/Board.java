package view;

import java.text.StringCharacterIterator;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
/**
 * A board is a visual representation of a player or enemy coordinate grid. The board is made up of cells that react to Mouse Click events 
 * and change colors to represent different scenarios. This class utilizes the JavaFx  VBox to hold rows of JavaFx HBoxes to create a symmetrical grid.
 * @author RR2
 * @version 1.0
 *
 */
public class Board extends Parent 
{	
	private int x, y;
	private VBox rows = new VBox();
	public Board(){	
	}
	/**
	 * Creates a 10 by 10 grid of cells and assigns a mouse clicked handler to each cell. Each row of the grid is made up of a JavaFx HBox and each 
	 * HBox is added to a VBox to create a game board
	 * @param handler - a mouse event handle is assigned to each cell
	 * @return A VBox to add to the User Interface
	 */
	public VBox createBoard(EventHandler<? super MouseEvent> handler)
	{
		String alpha = "abcdefghij";
		StringCharacterIterator itr =  new StringCharacterIterator(alpha);
		char current;
		current = itr.current();
		
		int d = 0;
		for(int z = 0; z < 10; z++)
		{
			HBox row = new HBox();
			for(int i = 0; i < 10; i++)
			{
				if(d == 10)
				{
					current = itr.next();
					d = 0;
				}
				String cordinate = Character.toString(current) + d;
				Cell cell = new Cell(cordinate, z, i, this);
				cell.setOnMouseClicked(handler);
                row.getChildren().add(cell);	
                d++;
			}
			rows.getChildren().add(row);
		}
		
		getChildren().add(rows);
		return rows;
	}
	/**
	 * 
	 * @param x - row of the desired cell on the board
	 * @param y - column of the desire cell on the board
	 * @return The intersection of a row and column on a board, a desired cell
	 */
	public Cell getCell(int x, int y){  
		return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
	}
	/**
	 * 
	 * @return the desired row on the board
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x - sets x as the current row on the board
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return the desired column on the board
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y - sets y as the current column on the board
	 */
	public void setY(int y) {
		this.y = y;
	}
}