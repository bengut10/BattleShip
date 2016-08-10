package src.view;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Cell objects are visual representations of grid coordinates for a 10 x10 player or enemy game board.Cell is a subclass
 * of Rectangle with added functionality. The cells are designed to react and change colors based on user and game interactions. 
 * The cells will turn white when a fired shot is a miss and red when a fired shot is a hit.
 * If a cell was previously used in game play it is also marked as used so it is not used again(toggle color).
 * @author RR2
 * @version 1.0
 *
 */
public class Cell extends Rectangle {
    @SuppressWarnings("unused")
	private int x, y;
    private String cordinate;
    @SuppressWarnings("unused")
	private Board board;
    private Boolean used = false;

    /** 
     * Constructs a Cell object by first creating a javafx rectangle, then sets its coordinates and assigns it to a board. The color is set to blue with black 
     * strokes. 
     * @param cordinate - representation of a ship's coordinate in the form "a9"
     * @param x - represents the row of a cell
     * @param y - represents the column of a cell
     * @param board - represents whether it is the player or opponents board.
     */
    public Cell(String cordinate,int x, int y, Board board) 
    {
        super(34, 34);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.BLUE);
        setOpacity(.6);
        setStroke(Color.BLACK);
        this.cordinate = cordinate;
    }

    /**
     * Changes the color of a cell to show if a ship was hit or missed. A hit will turn the cell red and a miss will turn the cell white.
     * The cell will also be marked as used so that is not guessed or hit again.
     * @param wasHit - If wasHit is true it will turn the cell red. If wasHit is false it will turn the cell white.
     */
	public void changeStatus(boolean wasHit) {
		if(wasHit){
			setFill(Color.RED);
			this.setUsed(true);
			
		}
		else{
			setFill(Color.WHITE);
			this.setUsed(true);
			
		}
	}
	/**
	 * 
	 * @return the coordinates of a cell in the form "a9"
	 */
	public String getCellCordinate()
	{
		return cordinate;
	}
	
	/**
	 * 
	 * @param cord - set the coordinates of a cell in the form "a9" 
	 */
	public void setCellCordinate(String cord){
		this.cordinate = cord;
	}

	/** 
	 * 
	 * @return true if the cell has been used
	 */
	public Boolean getUsed() {
		return used;
	}

	/**
	 * 
	 * @param used - set the cell as used if used
	 */
	public void setUsed(Boolean used) {
		this.used = used;
	}
	public void markShip(boolean isShip)
	{
		if(isShip)
		{	
			setFill(Color.YELLOW);
			this.setUsed(true);	
		}
		else
		{
			setFill(Color.WHITE);
			this.setUsed(true);	
		}
	}
}