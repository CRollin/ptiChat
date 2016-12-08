package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;

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
    public void initiateClientSocket(String peerIp, int peerPort, ChatManager chatManager) throws NoServerException {
        try {
            System.out.println("\n###### Client's socket Initialisation ######");
            this.socket = new Socket(peerIp, peerPort);
            this.chatThread = new Thread(new ChatRunnable(this.socket, chatManager));
            this.chatThread.start();
        } catch (UnknownHostException e){
            throw new NoServerException();
        } catch (IOException e){
            throw new NoServerException();
        }
    }
}
