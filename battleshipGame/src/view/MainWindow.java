package view;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

public class MainWindow extends Application{
	
	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		window = primaryStage;
		window.setOnCloseRequest(e ->
		{
			e.consume();
		});
		
		/*-------------------------------------------------- */
		Button button1 = new Button("Single Game");
		Button button2 = new Button("Multiplayer Game");
		Button button3 = new Button("Leaderboard");
		Button button4 = new Button("Exit");
		
		/*-------------------------------------------------- */
		HBox hbox = new HBox();
		hbox.getChildren().addAll(button1, button2, button3, button4);	
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		/*-------------------------------------------------- */
		BorderPane bp = new BorderPane();
		bp.setId("MainWindow");
		bp.setBottom(hbox);	
		
		/*-------------------------------------------------- */
		Scene scene1 =  new Scene(bp, 900,373);
		scene1.getStylesheets().add("style.css");
		
		
		/*-------------------------------------------------- */
		window.setScene(scene1);
		window.show();
		
		/*-------------------------------------------------- */
		
		button1.setOnAction(e-> 
		{
			if (e.getSource() == button1)
			{
				ViewHandler.playSingle("Enter Name");
			}
		});
		/*-------------------------------------------------- */
		button2.setOnAction(e-> 
		{
			if(e.getSource() == button2)
			{
				ViewHandler.playMulti("Enter Name");
			}
		
		});
		/*-------------------------------------------------- */
		button3.setOnAction(e-> 
		{
			if (e.getSource() == button3)
			{
				ViewHandler.displayLeaderBoard();
			}
			
		});
		
		/*-------------------------------------------------- */
		button4.setOnAction(e-> 
		{
			if (e.getSource() == button4)
			{
				window.close();
				e.consume();
			}
		});
		/*-------------------------------------------------- */
		
	}

	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
