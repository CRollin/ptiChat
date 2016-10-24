package fr.mrc.ptichat.connection;

import java.io.*;
import java.net.*;

/**
 * Represents the personal server created for each Client in order to receive
 * connection requests.
 */
public class ClientServer {

    private static ServerSocket serverSocket = null;
    private static Thread acceptConnectionsThread;

    /**
     * Initiates the ServerSocket.
     */
    public static void initiateClientServerSocket(){
        try {
            // TODO: when linking this code to the UI, remove all the "System.out.println()" functions
            // Server initialisation
            System.out.println("\n###### Client's server socket Initialisation ######");
            int port = getPort();
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening to the port " + serverSocket.getLocalPort());
            // Handle the connection requests
            acceptConnectionsThread = new Thread(new AcceptConnectionsRunnable(serverSocket));
            acceptConnectionsThread.start();
        } catch (IOException e){
            System.out.println("The port " + serverSocket.getLocalPort() + " is already used.");
        }
    }

    /**
     * Get the port to use for the ServerSocket
     * @return
     */
    public static int getPort(){
        Input input = new Input();
        System.out.println("What port do you want to use for the server? (ex: 9118) ");
        return input.getInt();
    }
}
