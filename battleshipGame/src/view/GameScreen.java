package view;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScreen{
	
	Stage window;
	Board enemyBoard, playerBoard;
	GameController gc; 
	static String userCordinate;
	private Boolean enemyTurn = false;
	//private String enemyCordinate;
	
	
	
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
	
		layout.setPrefSize(600,  800);
		VBox enemyVbox = new VBox();
		enemyBoard = new Board();
		VBox playerVbox = new VBox();
		playerBoard = new Board();
		playerVbox = playerBoard.createPlayerBoard();
		
		String image = GameScreen.class.getResource("/explosion.jpg").toExternalForm();
		layout.setStyle("-fx-background-image: url('" + image + "'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-size: 1600, 800;" +
		           "-fx-background-repeat: stretch;");
		
		
		gc = new GameController();
		gc.performOperation("Begin Single Game");
		layout.setTop(new Text(gc.getMessage()));
		enemyVbox = enemyBoard.createBoard(event->{
			
			Cell cell = (Cell) event.getSource();
			GameScreen.userCordinate = cell.getCellCordinate();
			if (gc.performOperation("compare cordinate")){
				
				cell.changeStatus(true);	
				enemyTurn = false;
				}
			else{
				
				if(cell.getUsed()){
					enemyTurn = false;
				}
				else{
					
					cell.changeStatus(false);
					enemyTurn = true;
				}
				
				layout.setTop(new Text(gc.getMessage()));
				layout.setBottom(new Text("Your Score is " + (gc.getPlayerScore() - 10)));
				
			}
			while(enemyTurn){
		
				if(gc.performOperation("compare enemy cordinate" )){
					//cell.setCellCordinate(enemyCordinate);
					Cell attackCell = playerBoard.getCell(gc.getxCord(), gc.getyCord());
					if(attackCell.getUsed())
						continue;
					attackCell.changeStatus(true);
				}
				else{
					Cell attackCell = playerBoard.getCell(gc.getxCord(), gc.getyCord());
					if(attackCell.getUsed())
						continue;
					attackCell.changeStatus(false);
					enemyTurn = false;
				}

			}
				
			
				
		});
		
		VBox vbox = new VBox(30, enemyVbox, playerVbox);
		vbox.setAlignment(Pos.CENTER);
		layout.setCenter(vbox);
		layout.setTop(new Text(gc.getMessage()));
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.show();
	
	}
	
	
	
}
