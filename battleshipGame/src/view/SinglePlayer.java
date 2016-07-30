package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SinglePlayer implements Window
{	
	private Stage window;
	private Board myself, enemy;
	private GameController gc; 
	private Boolean enemyTurn = false;
	static String userCordinate;
	public static int x, y;
	
	public SinglePlayer(){}
	@Override
	public void displayWindow(String title) 
	{
		window = new Stage();
		window.setTitle(title);
		BorderPane layout = new BorderPane();
		VBox vbox = new VBox();
		VBox vbox2 = new VBox();
		myself = new Board();
		enemy = new Board();
		gc = new GameController();
		gc.performOperation("Begin Single Game");
		vbox2 = myself.createBoard(event->{
			//do nothing.
		});
		vbox = enemy.createBoard(event->
		{
			Cell cell = (Cell) event.getSource();
			SinglePlayer.userCordinate = cell.getCellCordinate();
			if (gc.performOperation("compare cordinate"))
			{
				cell.changeStatus(true);	
				enemyTurn = false;
			}
			else
			{
				if(cell.getUsed())
				{
					enemyTurn = false;
				}
				else
				{
					cell.changeStatus(false);
					enemyTurn = true;
				}
			}		
			while(enemyTurn)
			{
				Cell attackCell = myself.getCell(x,y);
				if(gc.performOperation("compare enemy cordinate"))
				{	
					if(attackCell.getUsed())
					continue;
					attackCell.changeStatus(true);
				}
				else
				{	if(attackCell.getUsed())
					continue;
					attackCell.changeStatus(false);
					enemyTurn = false;
				}	
			}
		});
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(vbox,vbox2);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setCenter(hbox);
		Scene scene = new Scene(layout, 750, 600);
		window.setScene(scene);
		window.show();

	}
	public String getUserCordinate()
	{
		return userCordinate;
	}
}
