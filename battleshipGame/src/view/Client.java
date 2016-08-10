package src.view;

import java.io.Serializable;
import java.util.function.Consumer;

/** This class provides the network methods to communicate between the client and server
 * 
 *@author thanvas
 */
public class Client extends Connection {

    
    private String ip;
    private int port;

    
    public Client(String ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    /* (non-Javadoc)
     * @see src.view.Connection#isServer()
     * @return false to indicate that the system is not set up as a server
     */
    @Override
    protected boolean isServer() {
        return false;
    }

    /* (non-Javadoc)
     * @see src.view.Connection#getIP()
     * This method returns the ip address of the client
     * @return ip returns the ip address of the client
     */
    @Override
    protected String getIP() {
        return ip;
    }

    /* (non-Javadoc)
     * This method returns the port number of the client
     * @see src.view.Connection#getPort()
     * @return port Returns the port number of the client's port
     */
    @Override
    protected int getPort() {
        return port;
    }
}