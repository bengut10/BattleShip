package src.view;

import src.controller.GameController;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



public class ViewHandler 
{
	public static String userName;
	public static ObservableList<Object> leaderboard;

	public static void playMulti(String title) 
	{
		Window openW = new MultiPlayer();
		displayLogIn(title, openW);
	}
	
	
	public static void playSingle(String title) 
	{
		Window openW = new SinglePlayer();
		displayLogIn(title, openW);
	}
	
	public static void displayLogIn(String title, Window openW) 
	{
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		TextField nameInput = new TextField();
		Button button = new Button("Begin Game");
		button.setOnAction( e ->
		{
			ViewHandler.userName =  (String) nameInput.getText();
			openW.displayWindow(title);
			window.close();
		});
		
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		
		layout.getChildren().addAll(nameInput, button, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();	
	}
	
	public static void displayLeaderBoard() 
	{
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Leader Board");
		window.setMinWidth(250);
		GameController gc = new GameController();
		gc.getLeaderBoard();
		
		TableColumn <Object, String> nameColumn = new TableColumn<> ("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory <> ("playerName"));
		
		TableColumn <Object, Integer> scoreColumn = new TableColumn<> ("Score");
		scoreColumn.setMinWidth(200);
		scoreColumn.setCellValueFactory(new PropertyValueFactory <> ("score"));
		
		TableView <Object> table = new TableView<>();
		table.setItems(leaderboard);
		table.getColumns().add(nameColumn);
		table.getColumns().add(scoreColumn);
		VBox vbox = new VBox();
		vbox.getChildren().add(table);
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.show();
			
	}
	
	public static void displayWinning()
	{
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game over");
		window.setMinWidth(250);	
		Text tb = new Text("Game Over: All ships have been destroyed");
		VBox vbox = new VBox();
		vbox.getChildren().add(tb);
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.show();
	}

}
