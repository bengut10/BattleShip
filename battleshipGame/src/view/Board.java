package view;

import java.text.StringCharacterIterator;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.GameController;
import javafx.scene.input.MouseEvent;

public class Board extends Parent 
{	
	VBox rows = new VBox();
	GameController gameC;
	public GridPane gridPane = new GridPane();	

	Board(){
	}
	
	
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
	
}