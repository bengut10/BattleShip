package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiPlayer implements Window{

	MultiPlayer(){}

	@Override
	public void displayWindow(String title) {
		Stage window = new Stage();
		window.setTitle("Game Screen");
		
		BorderPane layout = new BorderPane();
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		Board playerB = new Board();
		Board opponentB = new Board();
		HBox hbox = new HBox(10);
		
		vbox2 = playerB.createBoard(event->{
		});
		
		vbox1 = opponentB.createBoard(event->{	
		});
		
		hbox.getChildren().addAll(vbox1, vbox2);
		
		VBox chat = new VBox();
		TextArea chatText = new TextArea("Chat Window");
		chatText.setPrefHeight(180);
		Button send = new Button("Send");
		
		chat.getChildren().addAll(chatText, send);
		chat.setAlignment(Pos.BOTTOM_CENTER);
		
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setCenter(hbox);
		layout.setBottom(chat);
		
		Scene scene = new Scene(layout, 750, 600);
		window.setScene(scene);
		window.show();
	}

}
