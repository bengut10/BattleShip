package view;


import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application{

	Stage window;
	Scene mainWindow, gameWindow;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		Label label1 = new Label("Main Window");
		Button button1 = new Button("Go to game window");
		BorderPane bp = new BorderPane();
		bp.setCenter(label1);
		bp.setBottom(button1);	
		
		Scene mainWindow =  new Scene(bp, 1000,1000);
		window.setScene(mainWindow);
		window.show();
		
		button1.setOnAction(e-> 
		{
			if (e.getSource() == button1)
			{
				GameController gc = new GameController();
				gc.performOperation("open sec window");
				window.close();
			}
		});
		
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
