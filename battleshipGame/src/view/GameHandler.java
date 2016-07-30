package view;

import java.io.BufferedReader;

public class GameHandler extends Thread {
	  private GameClient gameClient;    
	  private BufferedReader in;
	
	GameHandler(GameClient gameClient, BufferedReader in){
		
		this.gameClient = gameClient;
		this.in         = in;	
	}
	/* read from the server's buffer and assign functions accordingly, server messages are passed in from the GameClient
	contactServer function */
	public void run(){
		
	//	System.out.println("Inside game handler");
		
	    String line;
	    try {
	      while ((line = in.readLine()) != null) {
	        if (line.startsWith("OK"))
	          extractID(line.substring(3));
	        else if (line.startsWith("full"))
	          gameClient.disconnectClient("full game");    // disable client

	        else  
	          System.out.println("ERR: " + line + "\n");
	      }
	    }
	    catch(Exception e)    
	    { 
	    	
	    	gameClient.disconnectClient("server link lost");   // end game as well
	    
		
	    }
 	  
  } // of run
	
	private void extractID(String line){
		
	}
	
	private void extractOther(){
		
	}
	

}