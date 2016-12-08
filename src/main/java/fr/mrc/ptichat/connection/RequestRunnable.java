package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.processing.RequestHandler;

import java.io.*;
import java.net.InetAddress;

/**
 * Handles the messages to receive
 */
public class RequestRunnable implements Runnable {

    private BufferedReader in;
    private InetAddress address;
    private int port;
    private RequestHandler rh = new RequestHandler();
    private Flag stopFlag;

    public RequestRunnable(BufferedReader in, InetAddress address, int port, Flag stopFlag){
        this.in = in;
        this.address = address;
        this.port = port;
        this.stopFlag = stopFlag;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        String message;
        while(!this.stopFlag.getFlag()){
            try {
                if (this.in.ready()) {
                    message = this.in.readLine();
                    if (rh.isTerminationMessage(message)) {
                        message = "User left the chat, disconnecting...";
                        this.stop();
                    }
                    System.out.println("Received (me: " + this.address + ":" + this.port + ") : " + message);
                }
            } catch (IOException e){
                // System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
        try {
            // System.out.println("Closing reader");
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected");
    }

    public void stop() { this.stopFlag.setFlag(true); }
}
