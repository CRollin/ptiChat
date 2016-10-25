package main.java.fr.mrc.ptichat.connection;

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

    public ChatRunnable(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());
            this.requestThread = new Thread(new RequestRunnable(this.in, this.socket.getLocalAddress(), this.socket.getLocalPort()));
            this.requestThread.start();
            this.responseThread = new Thread(new ResponseRunnable(this.out, this.socket.getLocalAddress(), this.socket.getLocalPort()));
            this.responseThread.start();
        } catch(IOException e){
            System.err.println("Someone just disconnected from ptit Chat.");
        }
    }

    public Thread getRequestThread(){
        return this.requestThread;
    }
    public Thread getResponseThread(){
        return this.responseThread;
    }
}
