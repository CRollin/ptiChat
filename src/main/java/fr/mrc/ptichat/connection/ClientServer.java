package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

import java.io.*;
import java.net.*;

/**
 * Represents the personal server created for each Client in order to receive
 * connection requests.
 */
public class ClientServer {

    private LanguagesController languagesController = LanguagesController.getInstance();
    private ServerSocket serverSocket = null;
    private Thread acceptConnectionsThread;

    /**
     * Initiates the ServerSocket.
     */
    public void initiateClientServerSocket(int port, ChatManager chatManager){
        try {
            // Server initialisation
            System.out.println("\n###### Client's server socket Initialisation ######");
            this.serverSocket = new ServerSocket(port);
            // Handle the connection requests
            this.acceptConnectionsThread = new Thread(new AcceptConnectionsRunnable(this.serverSocket, chatManager)); //, chatManager
            this.acceptConnectionsThread.start();
        } catch (IOException e){
            System.err.println(languagesController.getWord("PORT_USED_EXCEPTION"));
        }
    }

}
