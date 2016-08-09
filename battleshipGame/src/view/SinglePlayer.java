package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class implements the user interface for a single player game. The user interface includes two side by side 10x10 grids. The player makes selections
 * for attack on the left grid (enemy board) and see where the enemy has attacked on the right grid(player board)
 * @author RR2
 * @version 1.0
 *
 */
public class SinglePlayer implements Window
{	
	private Stage window;
	private Board myself, enemy;
	private GameController gc; 
	private int x, y;
	private Boolean enemyTurn = false;
	static String userCordinate;
	public static boolean gameNotOver = false;
	
	/**
	 * the default constructor contains no functionality
	 */
	public SinglePlayer()
	{
	}
	
	/**
	 * 
	 * @return the selected user coordinate from a mouse click event
	 */
	public String getUserCordinate()
	{
		return userCordinate;
	}
	/**
	 * 
	 * @param x value is set - represents the row in which the selected cell is located
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	/**
	 * 
	 * @param y value is set - represents the column in which the selected cell is located
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * Creates the user interface for the player to select enemy cells and also see enemy attacks.
	 * An instance of the game controller is called to map user gestures to the model
	 * @param title - the name of the title that is displayed on the window
	 */
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
		
		gc = new GameController(this);
		
		
		vbox2 = myself.createBoard(event->{
			//do nothing.
		});
		
		vbox = enemy.createBoard(event->
		{
			Cell cell = (Cell) event.getSource();
			if(!cell.getUsed() && !gameNotOver)
			{
				SinglePlayer.userCordinate = cell.getCellCordinate();
				if (gc.compareUserCoord())
				{
					cell.changeStatus(true);	
					enemyTurn = true;
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
			}		
			while(enemyTurn)
			{
				
				if(gc.CompareEnemyCoord())
				{	
					Cell attackCell = myself.getCell(x,y);
					if(attackCell.getUsed())
						continue;
					attackCell.changeStatus(true);
					enemyTurn = false;
				}
				else
				{	Cell attackCell = myself.getCell(x,y);
					if(attackCell.getUsed())
						continue;
					attackCell.changeStatus(false);
					enemyTurn = false;
				}	
			}
			
		});
		
		Button button1 = new Button("Restart");
		button1.setPrefSize(150, 10);
		Button button2 = new Button("Return");
		button2.setPrefSize(150, 10);
		
		
		/*-------------------------------------------------- */
		/*-------------------------------------------------- */
		VBox hbox2 = new VBox();
		hbox2.getChildren().addAll(button1, button2);	
		hbox2.setAlignment(Pos.BOTTOM_LEFT);
		
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(vbox,vbox2);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setCenter(hbox);
		layout.setBottom(hbox2);
		
		Scene scene = new Scene(layout, 750, 600);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		window.show();

	}
}