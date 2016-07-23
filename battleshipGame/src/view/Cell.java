package view;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private int x, y;
    private String cordinate;
    private Board board;

    public Cell(String cordinate,int x, int y, Board board) 
    {
        super(30, 30);
        this.x = x;
        this.y = y;
        this.board = board;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
        this.cordinate = cordinate;
    }

	public void changeStatus(boolean wasHit) {
		if(wasHit){
			setFill(Color.RED);
		}
		else
			setFill(Color.BLACK);
	}
	
	public String getCellCordinate()
	{
		return cordinate;
	}
}

