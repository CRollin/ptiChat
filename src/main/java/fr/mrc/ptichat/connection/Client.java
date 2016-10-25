package main.java.fr.mrc.ptichat.connection;

import java.io.IOException;
import java.net.*;

/**
 * Represent a socket between the client and a ClientServer.
 */
public class Client {

    private Socket socket = null;
    private Thread chatThread;

    /**
     * Initiate a socket between the client and the ServerSocket matching the host and port address.
     */
    public void initiateClientSocket(){
        try {
            // TODO: when linking this code to the UI, remove all the "System.out.println()" functions
            System.out.println("\n###### Client's socket Initialisation ######");
            String host = getHost();
            int port = getPort();
            this.socket = new Socket(host, port);
            // Create Chat Thread
            this.chatThread = new Thread(new ChatRunnable(this.socket));
            this.chatThread.start();
        } catch (UnknownHostException e){
            System.err.println("connection to " + this.socket.getLocalAddress() + " impossible");
        } catch (IOException e){
            System.err.println("The port " + this.socket.getLocalPort() + " is already used.");
        }
    }

    /**
     * Get the host address to connect to
     * @return host
     */
    public static String getHost(){
        Input input = new Input();
        System.out.println("Host IP address (ex: 127.0.0.1) : ");
        return input.getInput();
    }

    /**
     * Get the port to connect to
     * @return port
     */
    public static int getPort(){
        Input input = new Input();
        System.out.println("Port (ex: 9118): ");
        return input.getInt();
    }
}