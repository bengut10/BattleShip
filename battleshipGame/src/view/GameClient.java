package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

import controller.GameController;


 class GameClient extends JFrame{
	
	  
	  private static final int PORT = 1234;     
	  private static final String HOST = "localhost";
	
	  private static final int MAX_PLAYERS = 2;
	  private final static int PLAYER1    = 1;
	  private final static int PLAYER2    = 2; 
	  
	  private Socket sock;
	  private PrintWriter out; 
	  
	  private int playerID;
	  private String status;         // used to place info into the 3D canvas
	  private int numPlayers;
	  private int currPlayer;        // track current player so the game knows who's turn.
	  private boolean isDisabled;    // to indicate that the game has ended
	  
	  GameController gc;
	    
	GameClient(){
		

	    playerID = 0;     // no id as yet
	    status = null;    // no status to report 
	    numPlayers = 0;
	    currPlayer = 1;    // player 1 starts first
	    isDisabled = false;

	    contactServer();
	    	    
		
	}
	
	private void contactServer(){
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
	
	public static void main(String[] args){
		
		new GameClient();
		
	}

}
