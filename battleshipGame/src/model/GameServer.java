package model;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
public class GameServer {
	
	 private static final String GROUP_HOST = "228.5.6.7";
	 
	private static final int PORT = 1234;
	private static final int BUFSIZE = 1024;   // max size of a message
	
	private DatagramSocket serverSock;
	
	// holds names of members in multicast group
    private ArrayList groupMembers; 

	
	// game variables
	private static final int P1 = 1;
	private static final int P2 = 2;
	private static final int MAX_PLAYERS = 2;
	
	private int numPlayers;
	private ServerHandler[] handler;
	
	
	private Ship ship;
	
	private ArrayList<Ship> shipCoordPlayer1 = new ArrayList<Ship>();
	private ArrayList<Ship> shipCoordPlayer2 = new ArrayList<Ship>();
	
	@SuppressWarnings("resource")
	public GameServer(){
		
		// initialize	
		handler    = new ServerHandler[MAX_PLAYERS];
		numPlayers = 0;
		handler[0] = null;
		handler[1] = null;
		
	    try {  // try to create a socket for the server
	        serverSock = new DatagramSocket(PORT);
	      }
	      catch(SocketException se)
	      {  System.out.println(se);
	         System.exit(1);
	      }

		
		try{
			// create new ServerSocket object with port number "1234"
			// network variables
			ServerSocket serverSocket = new ServerSocket(PORT);
			Socket clientSocket;

			
			while(true){
				
				System.out.println("Waiting for client to connect");
				
				/* waits for a client to connect to the severSocket and accepts it, once connection is
				 made the system comes back into this loop and starts the ServerHandler thread */
			    clientSocket = serverSocket.accept();

				//start handler thread 
				new ServerHandler(clientSocket, this).start();			
				
			}
		}
		catch(Exception e){
			
			System.out.println(e);
			
		}
		

	}
	
	    // check if there is another player
		synchronized public boolean maxPlayer(){
			
			return (numPlayers == MAX_PLAYERS);
		}
		
		synchronized public int addPlayer(ServerHandler myHandler){
			
	
			
			for(int i = 0; i < MAX_PLAYERS; i++){
				
				if(handler[i] == null){
					
					// get ship coordinates
					if (i == 0){
					 //   shipCoordPlayer1 = ship.getLocationCells();
					//	System.out.println(shipCoordPlayer1.get(1));				
					}
					else if (i == 1){
					//	shipCoordPlayer2 = ship.getLocationCells();
					}
					handler[i] = myHandler;
					numPlayers++;
					return i + 1; // player ID is 1 or 2
				}
				
			}
			
			//if handler[] is full
			return -1;	
			
		}
		
		// used player id to remove players
		synchronized public void removePlayer(int playerID){
			
			handler[ playerID - 1] = null;
			numPlayers--;
						
		}
		
		// used by serverHandler.run() 
		synchronized public void notifyOthers(int playerID, String msg){
			
			int otherID = (playerID == P1) ? P2 : P1; // get other player's id
			
			// if there is a user then send message
			if(handler[otherID - 1] != null){
				
				handler[otherID - 1].sendMessage(msg);
				
			}
			
		}
		
		//**************Chat functions***************************
		private void waitForPackets()
		  // wait for a packet, process it, repeat
		  {
		    DatagramPacket receivePacket;
		    byte data[];

		    System.out.println("Ready for client messages");
		    try {
		      while (true) {
		        data = new byte[BUFSIZE];  // set up an empty packet
		        receivePacket = new DatagramPacket(data, data.length);
		        serverSock.receive( receivePacket );  // wait for a packet

		        // extract client address, port, message
		        InetAddress clientAddr = receivePacket.getAddress();
		        int clientPort = receivePacket.getPort();

		        String clientMsg = new String( receivePacket.getData(), 0,
		                           receivePacket.getLength() ).trim();
		        System.out.println("Received: " + clientMsg);

		        processClient(clientMsg, clientAddr, clientPort);
		      }
		    }
		    catch(IOException ioe)
		    {  System.out.println(ioe);  }
		  }  // end of waitForPackets()


		  private void processClient(String msg, InetAddress addr, int port)
		  /* There are three possible types of message:
		       hi <name>   // add the name to the group list (if name is unique)
		                      and tell them the multicast address

		       bye <name>  // delete this name from the group list

		       who         // send back a list of all the current members
		  */
		  {
		    if (msg.startsWith("hi")) {
		      String name = msg.substring(2).trim();
		      if (name != null && isUniqueName(name)) {
		          groupMembers.add(name);
		          sendMessage(GROUP_HOST, addr, port);  // send multicast addr
		      }
		      else
		        sendMessage("no", addr, port);
		    }
		    else if (msg.startsWith("bye")) {
		      String name = msg.substring(3).trim();
		      if (name != null)
		          removeName(name);  // removes name from list
		      // note: nothing is done to check the actual multicast group
		      // or to verify who sent this message   
		    }
		    else if (msg.equals("who"))
		      sendMessage( listNames(), addr, port);
		    else
		      System.out.println("Do not understand the message");

		  }  // end of processClient()


		  private void sendMessage(String msg, InetAddress addr, int port)
		  // send message back to the client
		  {
		    try {
		      DatagramPacket sendPacket =
		          new DatagramPacket( msg.getBytes(), msg.length(), 
		   						addr, port);
		      serverSock.send( sendPacket );
		    }
		    catch(IOException ioe)
		    {  System.out.println(ioe);  }
		  } // end of sendMessage()


		  // ------------------- names processing -------------------


		  private boolean isUniqueName(String nm)
		  // return true if nm is not already in the group members list
		  {
		    String name;
		    for(int i=0; i < groupMembers.size(); i++) {
		      name = (String) groupMembers.get(i);
		      if (name.equals(nm))
		        return false;
		    }
		    return true;
		  } // end of isUniqueName()


		  private void removeName(String nm)
		  // remove nm from the group members list
		  {
		    String name;
		    for(int i=0; i < groupMembers.size(); i++) {
		      name = (String) groupMembers.get(i);
		      if (name.equals(nm)) {
		        groupMembers.remove(i);
		        break;
		      }
		    }
		  }  // end of removeName()

		  
		  private String listNames()
		  /* Return the group members list as a string of the form
		        no. name \n no1. name1 \n ...
		  */
		  { String list = new String();
		    String name;
		    for(int i=0; i < groupMembers.size(); i++) {
		      name = (String) groupMembers.get(i);
		      list += "" + (i+1) + " " + name + "\n";
		    }
		    return list;
		  } // end of listNames()

		
	  public static void main(String args[]) 
	  {  new GameServer();  }


}
