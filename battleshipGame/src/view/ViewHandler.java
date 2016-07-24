package view;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import controller.GameController;
import javafx.geometry.*;


public class ViewHandler 
{
	public static String userName;
	public static void playSingle(String title) 
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
			userName =  (String) nameInput.getText();
			GameController gc = new GameController();
			gc.performOperation("open sec window");
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
  
}