package model;

import java.net.*;
public class GameServer {
	
	private static final int PORT = 1234;
	
	// game variables
	private static final int P1 = 1;
	private static final int P2 = 2;
	private static final int MAX_PLAYERS = 2;
	
	private int numPlayers;
	private ServerHandler[] handler;
	
	@SuppressWarnings("resource")
	public GameServer(){
		
		// initialize	
		handler = new ServerHandler[MAX_PLAYERS];
		numPlayers = 0;
		handler[0] = null;
		handler[1] = null;
		
		try{
			// create new ServerSocket object with port number "1234"
			// network variables
			ServerSocket serverSocket = new ServerSocket(PORT);
			Socket clientSocket;

			
			while(true){
				
				System.out.println("Waiting for client to connect");
				// give client privilege to connect
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
					
					handler[i] = myHandler;
					numPlayers++;
					return i + 1; // player ID is 1 or 2
				}
				
			}
			
			//if handler is full
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
		
	 // public static void main(String args[]) 
	 // {  new GameServer();  }


}
