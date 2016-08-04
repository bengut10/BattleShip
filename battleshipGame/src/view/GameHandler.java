package view;

import java.io.BufferedReader;
import java.util.StringTokenizer;

public class GameHandler extends Thread {
	
	  private MultiPlayer multiPlayer;    
	  private BufferedReader in;
	
	GameHandler(MultiPlayer multiPlayer, BufferedReader in){
		
		this.multiPlayer = multiPlayer;
		this.in         = in;
		
	}
	
	/* read from the server's buffer and assign functions accordingly, server messages are passed in from the GameClient
	contactServer function */
	public void run(){
		
	    String line;
	    try {
	      while ((line = in.readLine()) != null) {
	  	 
      if (line.startsWith("OK")) // this is read in from the severHandler when a player is added
	          extractID(line.substring(3));
	        else if (line.startsWith("full"))
	          multiPlayer.disconnectClient("full game");    // disable client
	        else if (line.startsWith("start"))
	        	System.out.println("Game Started");
	        else if ((line.length() >= 6) &&     // "WHO$$ "
            (line.substring(0,5).equals("WHO$$")))
	            showWho( line.substring(5).trim() ); 

	        else  
	        	
	            multiPlayer.showMsg(line + "\n");

	      }
	    }
	    catch(Exception e)    
	    { 
	    	
	    	multiPlayer.disconnectClient("server link lost");   // end game as well
	    
		
	    }
 	  
  } // of run
	
	private void extractID(String line){
		
	}
	
	private void extractOther(){
		
	}
	
	//***********************Chat function******************************** 
	  private void showWho(String line)
	  /*  line has the format:
	             "cliAddr1 & port1 & ... cliAddrN & portN & "
	      Reformat it before calling showMsg() in the client.
	  */
	  { StringTokenizer st = new StringTokenizer(line, "&");
	    String addr;
	    int port;
	    int i = 1;
	    try {
	      while (st.hasMoreTokens()) {
	        addr = st.nextToken().trim();
	        port = Integer.parseInt( st.nextToken().trim() );
	        multiPlayer.showMsg("" + i + ". " + addr + " : " + port + "\n");
	        i++;
	      }
	      // client.showMsg("\n");
	    }
	    catch(Exception e)
	    { multiPlayer.showMsg("Problem parsing who info.\n");
	      System.out.println("Parsing error with who info: \n" + e);  
	    }
	  }  // end of showWho()
	

}