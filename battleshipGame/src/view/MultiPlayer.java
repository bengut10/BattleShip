package view;


import java.util.ArrayList;
import controller.GameMultiController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiPlayer implements Window
{	
	private boolean isServer = true;
	private TextArea messages = new TextArea();
	private ArrayList <String> myCoordList = null;
	private ArrayList <String> enemyCoordList = null;
	private Connection connection = isServer ? createServer() : createClient();
	static Boolean ready = false;
	private Button button = new Button("StartGame");
	
	MultiPlayer() 
	{
		try 
		{
			connection.startConnection();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private Server createServer()
	{
		return new Server(55555,data -> 
		{
			Platform.runLater(() -> {
			messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	private Client createClient(){
		return new Client("127.0.0.1",55555, data->
		{
			Platform.runLater(()-> 
			{
				messages.appendText(data.toString() + "\n");
			});
		
		});
	}

	public void setMyCoordList(ArrayList <String> myCoordList)
	{
		this.myCoordList = myCoordList;
	}
	
	
	
	@Override
	public void displayWindow(String title) 
	{
		Stage window = new Stage();
		window.setTitle("Game Screen");
		
		BorderPane layout = new BorderPane();
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		Board playerB = new Board();
		Board opponentB = new Board();
		HBox hbox = new HBox(10);
		
		button.setOnAction(event->
		{
			try 
			{
				connection.send(myCoordList);
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			ready = true;
		});

		GameMultiController multiController = new GameMultiController(this);
		
		vbox2 = playerB.createMultiBoard(this.myCoordList);		
		
		vbox1 = opponentB.createBoard(event->
		{
			if(ready == true)
			{
				Boolean wasAHit = false;
				Cell cell = (Cell) event.getSource();
				if(!cell.getUsed())
				{
					Object userCordinate = (Object) cell.getCellCordinate();
					for(Object S1 : Connection.info)
					{
						if(userCordinate.equals(S1))
						{
							wasAHit = true;
						}
					}
					if(wasAHit == true)
					{
						cell.changeStatus(true);
					}
					else
					{
						cell.changeStatus(false);
					}
				}
			}
		});
		
		hbox.getChildren().addAll(vbox2, vbox1);
		VBox chat = new VBox();
		messages.setPrefHeight(180);
		TextField input = new TextField();

		input.setOnAction(event-> 
		{
			if(ready == true)
			{
				String message = isServer ? "Player 1: " : "Player 2: ";
				message += input.getText();
				input.clear();
				messages.appendText(message + "\n"); 
				try
				{
					connection.send(message);
				}
				catch(Exception e)
				{
					messages.appendText("Failed to send \n");
				}
			}
			});
	
		chat.getChildren().addAll(input,messages,button);
		chat.setAlignment(Pos.BOTTOM_CENTER);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setCenter(hbox);
		layout.setBottom(chat);
		Scene scene = new Scene(layout, 750, 600);
		window.setScene(scene);
		window.show();
	}

}
