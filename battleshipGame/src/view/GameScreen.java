package view;

import controller.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScreen{
	
	Stage window;
	Board grid;
	GameController gc; 
	static String userCordinate;
	
	
	public String getUserCordinate()
	{
		return userCordinate;
	}

	public GameScreen(){}

	public void GameScreenDisplay() 
	{
		window = new Stage();
		window.setTitle("Game Screen");
		
		BorderPane layout = new BorderPane();
		VBox vbox = new VBox();
		grid = new Board();
		
		gc = new GameController();
		gc.performOperation("Begin Single Game");
		
		vbox = grid.createBoard(event->{
			Cell cell = (Cell) event.getSource();
			GameScreen.userCordinate = cell.getCellCordinate();
			if (gc.performOperation("compare cordinate")){
					cell.changeStatus(true);
				}
		});
		layout.setCenter(vbox);
		Scene scene = new Scene(layout, 1000, 1000);
		window.setScene(scene);
		window.show();
	
	}
	
	
	
}
