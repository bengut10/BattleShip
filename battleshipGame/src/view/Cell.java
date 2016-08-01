package view;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    @SuppressWarnings("unused")
	private int x, y;
    private String cordinate;
    @SuppressWarnings("unused")
	private Board board;
    private Boolean used = false;

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
	
	public String getCellCordinate()
	{
		return cordinate;
	}
	
	public void setCellCordinate(String cord){
		this.cordinate = cord;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}
		
}

