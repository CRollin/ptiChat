package main.java.fr.mrc.ptichat.connection;

import java.io.*;
import java.net.*;

/**
 * Handles all the connections requests to the ClientServer.
 */
public class AcceptConnectionsRunnable implements Runnable {

    private boolean resume;
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private Thread chatThread;

    public AcceptConnectionsRunnable(ServerSocket ss){
        this.serverSocket = ss;
    }

    public void run() {
        try {
            while(!this.resume){
                this.socket = this.serverSocket.accept();
                System.out.println("Someone wants to connect to you.");
                // Create Chat Thread
                this.chatThread = new Thread(new ChatRunnable(this.socket));
                this.chatThread.start();
            }
        } catch (IOException e){
            System.err.println("Server Error");
        }
    }

    public void stop(){
        this.resume = true;
    }
}