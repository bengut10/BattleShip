package src.view;


import java.io.Serializable;
import java.util.function.Consumer;

/**
 * This class is used to configure the system as a server
 * 
 * @author thanvas
 *
 */

public class Server extends Connection {

    private int port;

    /**
     * @param port passes in a port and assigns the port to the server's port variable
     * @param <Serializable> passes in an object <Serializable> and then is passed to the super class
     *
     */
    public Server(int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.port = port;
    }

    /**
    * This function set the system as a server
    * @return true The system is configure as a server
    *
    */
    @Override
    protected boolean isServer() {
        return true;
    }

    /**
    * @return null The server does not need an IP address
    *
    */
    @Override
    protected String getIP() {
        return null;
    }

    /**
    * @return port Returns the port number of the server
    *
    */
    @Override
    protected int getPort() {
        return port;
    }
    
    
    
    
}