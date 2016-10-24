package fr.mrc.ptichat.connection;

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
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            requestThread = new Thread(new RequestRunnable(in, socket.getLocalAddress(), socket.getLocalPort()));
            requestThread.start();
            responseThread = new Thread(new ResponseRunnable(out, socket.getLocalAddress(), socket.getLocalPort()));
            responseThread.start();
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
