package src.view;


import java.util.ArrayList;
import src.controller.GameMultiController;

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

/**
 * This class handles the multiplayer game mode
 * @author thanvas
 *
 */
 class MultiPlayer implements Window
{	
	private boolean isServer = true;
	private TextArea messages = new TextArea();
	private ArrayList <String> myCoordList = null;
	private ArrayList <String> enemyCoordList = null;
	private Connection connection = isServer ? createServer() : createClient();
	static Boolean ready = false;
	private Button button = new Button("StartGame");
	
	/**
	 * The constructor starts a new network connection that is either client or server
	 *
	 */
	public
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
	
	/**
	 * This function creates a new server with a specific port number
	 * 
	 * @return Server create a server object with a port number 55555 
	 *
	 */
	private Server createServer()
	{
		return new Server(55555,data -> 
		{
			Platform.runLater(() -> {
			messages.appendText(data.toString() + "\n");
			});
		});
	}
	
	/**
	 * This function creates a new client with a specific port number and ip address
	 * 
	 * @return Client returns a new client with the specific port number and ip address
	 *
	 */
	private Client createClient(){
		return new Client("localhost",55555, data->
		{
			Platform.runLater(()-> 
			{
				messages.appendText(data.toString() + "\n");
			});
		
		});
	}

	/**
	 * This function set my coordinates 
	 * 
	 * @param myCoordList the myCoordList are passed in
	 *
	 */
	public void setMyCoordList(ArrayList <String> myCoordList)
	{
		this.myCoordList = myCoordList;
	}
	
	
	/**
	 *This function displays the multiplayer menu
	 *
	 *@param title is the name of the window
	 *
	 */
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
