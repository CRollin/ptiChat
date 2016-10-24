package fr.mrc.ptichat.connection;

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

    @Override
    public void run() {
        try {
            while(!resume){
                socket = serverSocket.accept();
                System.out.println("Someone wants to connect to you.");
                // Create Chat Thread
                chatThread = new Thread(new ChatRunnable(socket));
                chatThread.start();
            }
        } catch (IOException e){
            System.err.println("Server Error");
        }
    }

    public void stop(){
        resume = true;
    }
}
