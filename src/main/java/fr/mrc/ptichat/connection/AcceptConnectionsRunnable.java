package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

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
    private ChatManager chatManager;
    private LanguagesController languagesController = LanguagesController.getInstance();

    public AcceptConnectionsRunnable(ServerSocket ss, ChatManager chatManager){ //,
        this.chatManager = chatManager;
        this.serverSocket = ss;
    }

    public void run() {
        try {
            while(!this.resume){
                this.socket = this.serverSocket.accept();
                // Create Chat Thread
                this.chatThread = new Thread(new ChatRunnable(this.socket, this.chatManager)); //, this.chatManager
                this.chatThread.start();
            }
        } catch (IOException e){
            System.err.println(languagesController.getWord("SERVER_EXCEPTION"));
        }
    }

    public void stop(){
        this.resume = true;
    }
}
