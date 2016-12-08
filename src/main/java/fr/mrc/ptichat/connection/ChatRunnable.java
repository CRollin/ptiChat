package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;

import java.io.*;
import java.net.*;

/**
 * Creates two threads by socket : one to listen to incoming messages (RequestThread), the other to send messages
 * (ResponseRequest)
 */
public class ChatRunnable implements Runnable {

    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket socket = null;
    private Thread requestThread, responseThread;
    private Flag stopFlag = new Flag();
    private ChatManager chatManager;

    public ChatRunnable(Socket socket, ChatManager chatManager){
        this.chatManager = chatManager;
        this.socket = socket;
    }

    public void run(){
        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());
            // Instantiate both request and response threads with the same Flag instance
            this.requestThread = new Thread(new RequestRunnable(this.in, this.socket.getLocalAddress(), this.socket.getLocalPort(), this.stopFlag, this.chatManager)); //
            this.requestThread.start();
            this.responseThread = new Thread(new ResponseRunnable(this.out, this.socket.getLocalAddress(), this.socket.getLocalPort(), this.stopFlag, this.chatManager)); //
            this.responseThread.start();
            while(!this.stopFlag.getFlag()) {
                //Wait
            }
            this.responseThread.interrupt();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Thread getRequestThread(){
        return this.requestThread;
    }
    public Thread getResponseThread(){
        return this.responseThread;
    }

}
