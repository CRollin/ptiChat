package main.java.fr.mrc.ptichat.connection;

import java.io.*;
import java.net.*;

/**
 * Represents the personal server created for each Client in order to receive
 * connection requests.
 */
public class ClientServer {

    private ServerSocket serverSocket = null;
    private Thread acceptConnectionsThread;

    /**
     * Initiates the ServerSocket.
     */
    public void initiateClientServerSocket(int port){
        try {
            // TODO: when linking this code to the UI, remove all the "System.out.println()" functions
            // Server initialisation
            System.out.println("\n###### Client's server socket Initialisation ######");
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server listening to the port " + serverSocket.getLocalPort());
            // Handle the connection requests
            this.acceptConnectionsThread = new Thread(new AcceptConnectionsRunnable(this.serverSocket));
            this.acceptConnectionsThread.start();
        } catch (IOException e){
            System.out.println("The port is already used.");
        }
    }

}
