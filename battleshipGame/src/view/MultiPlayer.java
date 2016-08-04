package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	
	//**************************************************************  
	  private static final int PORT = 1234;     
	  private static final String HOST = "localhost";
	
	  private static final int MAX_PLAYERS = 2;
	  private final static int PLAYER1    = 1;
	  private final static int PLAYER2    = 2; 
	  
	  private Socket sock;
	  private PrintWriter out; 
	  
	  private int playerID;
	  private String status;         
	  private int numPlayers;
	  private int currPlayer;       
	  private boolean isDisabled;   
	
	  // chat variables
	  private String chatMSG;
	  
		TextArea chatText = new TextArea("Waiting for second player to connect..........");
	  
	
   //****************************************************************************
	MultiPlayer(){
	
		///******************************************************
	    playerID = 0;     // no id as yet
	    status = null;    // no status to report 
	    numPlayers = 0;
	    currPlayer = 1;    // player 1 starts first
	    isDisabled = false;
	    ///*********************************************************

	}

	@Override
	synchronized public void displayWindow(String title) {
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
		//TextArea chatText = new TextArea("Waiting for second player to connect..........");
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
		
		// contact the server and start the gameHandler thread
	    contactServer();
	    
		send.setOnAction(e-> 
		{
			if (e.getSource() == send)
			{
				chatMSG = chatText.getText().trim();
				sendMessage(chatMSG);

			}
		});
	    

	}
	
	//***********************************************************


	synchronized private void contactServer(){
	    try {
	    	
	        sock = new Socket(HOST, PORT);
	        BufferedReader in  = new BufferedReader( 
	  		  		new InputStreamReader( sock.getInputStream() ) );
	        out = new PrintWriter( sock.getOutputStream(), true );

	       
	        // start watching for server message
	        new GameHandler(this, in).start();  
	      }
	      catch(Exception e)
	      {  
	         System.out.println("Cannot contact the Server");
	         System.exit(0);
	       }
	}
	
	
	// force client to disconnect if not disconnected when they quit
	synchronized public void disconnectClient(String message){
		
		  { if (!isDisabled) {    // the client can only be disabled once
		      try {
		        isDisabled = true;
		        out.println("disconnect");  // tell server
		        sock.close();
		        setStatus("Game Over: " + message);

		      }
		      catch(Exception e)
		      {  System.out.println( e );  }
		    }
		  }
		
	}
		  
	synchronized public void setStatus(String message){
		
		status = message;
		
	}
	
	synchronized public String getStatus(){
		
		return status;
	}

	public void showMsg(final String msg)

	  { 

	    Runnable updateMsgsText = new Runnable() {
	      public void run() 
	      { 
	    	  chatText.appendText(msg);  // append message to text area

	      }
	    };
	    SwingUtilities.invokeLater( updateMsgsText );
	  } // end of showMsg()

	
	private void sendMessage(String chatMessage){
		
	    if (chatMessage.equals(""))
	        JOptionPane.showMessageDialog( null, 
	             "No message entered", "Send Message Error", 
	  			JOptionPane.ERROR_MESSAGE);
	      else {
	        out.println(chatMessage);

	      }
		
		
	}
	//***********************************************************************************************



}
