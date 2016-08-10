package src.view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * The connection class will start a thread; the thread is used to make network connection between the server
 * and client. This class will also use a buffer as a means for the client and server to communicate
 *  
 * @author thanvas
 *
 */

public abstract class Connection {

	public static ArrayList<String> info = new ArrayList<String>();
    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;

    /**
     * The constructor receives a Serializable type and assigns it to this class's instance
     * variable onReceiveCallback and it set the thread as Daemon.
     * 
     * @param onReceiveCallback passes in Serializable and assign to this.onReceivedCallback
     * 
     *
     */
    public Connection(Consumer<Serializable> onReceiveCallback) {
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }

    /**
     * This function starts a thread and starts the run() in the ConnectionThread class
     *
     */
    public void startConnection() throws Exception {
        connThread.start();
    }

    /**
     * @param data Data that is received from the chat message and then is written to the buffer
     *
     */
    public void send(Serializable data) throws Exception 
    {    	
       connThread.out.writeObject(data);
    }
 
    /**
     * This function closes the socket for the client's connection
     * @exception Exception closes the socket
     *
     */
    public void closeConnection() throws Exception {
        connThread.socket.close();
    }

    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();

    private class ConnectionThread extends Thread {
    private Socket socket;
    private ObjectOutputStream out;

    /* (non-Javadoc)
     * 
     * This function make the necessary network connections. It is also used to read and write to and from the buffer.
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() 
    {
    	
        try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
        {
        		       		
            this.socket = socket;
            this.out = out;
            socket.setTcpNoDelay(true);
            while (true) 
            {
            	if(info.size() < 1 )
            	{
            		Connection.info = (ArrayList<String>)  in.readObject();
            
            	}
            	else
            	{
                	 Serializable data = (Serializable) in.readObject();
                     onReceiveCallback.accept(data);  
            	}
 
            }
         
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
            onReceiveCallback.accept("Connection closed");
        } 
    }
    }
}