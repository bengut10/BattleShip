package model;

import java.net.*;
import java.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ServerHandler extends Thread {
	

	  private GameServer server;
	  private Socket clientSock;
	  private BufferedReader in;
	  private PrintWriter out;

	  private int playerID;     


	  public ServerHandler(Socket s, GameServer serv)
	  {
		  
		  // assign s and serv to instance variable
		  clientSock = s;
		  server = serv;
		  
		  System.out.println("Player has requested connection");
		  
		try{
			
			in  = new BufferedReader( new InputStreamReader(clientSock.getInputStream()));
			out = new PrintWriter( clientSock.getOutputStream(), true);

	    }
	    catch(Exception e){ 
	    	System.out.println(e);
	    }
	  }

	  public void run()
	  {
		  	  
		  // player being added and assigned an ID
		  playerID = server.addPlayer(this);
		  
		  // if -1 then game is full
		  if( playerID != -1)
		  {
			  
			  sendMessage("OK " + playerID); // send message to other player
			  System.out.println("ok " + playerID); // tell other player
			  server.notifyOthers(playerID, "Added " + playerID);
			  
			  processPlayerInput();
			  
		      server.removePlayer(playerID);   // remove player from server data
		      server.notifyOthers(playerID, "removed " + playerID);    // tell others
		    }
		    else    // game is full
		      sendMessage("full");

		    try {    
		    	// close socket from player
		      clientSock.close();
		      System.out.println("Player " + playerID + " connection closed\n");
		    }
		    catch(Exception e)
		    {  System.out.println(e);  }

		  }		  		  	

	  // uses buffer to read client's input
	   private void processPlayerInput()
	   {
		    String line;
		     boolean done = false;
		     try {
		       while (!done) {
		         if((line = in.readLine()) == null)
		           done = true;
		         else {

		           if (line.trim().equals("disconnect"))
		             done = true;
		           else 
		             doRequest(line);
		         }
		       }
		     }
		     
		     catch(IOException e)
		     { 
		    	 
		    	 System.out.println("Player " + playerID + " closed the connection");
		         
		     }
		     
		   }  
	
	  private void doRequest(String line)
	  {
	    if (line.startsWith("try")) {
	      try {
	    	  
	          int posn = Integer.parseInt( line.substring(4).trim() );
	         
	          if (server.maxPlayer())
	            server.notifyOthers(playerID, "otherTurn " + playerID + " " + posn);  // pass turn to others
	          else
	            sendMessage("tooFewPlayers");
	        }
	      
	      catch(NumberFormatException e)
	      { System.out.println(e); } 
	    }
	  } 


	  synchronized public void sendMessage(String msg)
	  { try {
		  
		  // send message to buffer
		  out.println(msg);

	    }
	    catch(Exception e){   
	    	
	    	System.out.println("Handler for player " + playerID + "\n" + e);	    	
	    }
	  } 



}
