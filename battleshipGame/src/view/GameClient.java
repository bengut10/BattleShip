package view;

import javax.swing.*;

import model.MultiGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

 class GameClient extends JFrame{
	
	  
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
	  private JTextArea jtaMesgs;  // output text area
	  private JTextField jtfMsg;   // for sending messages
	  private JButton jbWho;       // for sending a "who" message
	  
	  private MultiPlayer multiPlayer;
	  
    
	GameClient(){
	     super("Chat Client");

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
	     //   new GameHandler(this, in).start();  
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
