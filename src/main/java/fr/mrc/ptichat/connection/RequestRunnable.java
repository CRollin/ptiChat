package main.java.fr.mrc.ptichat.connection;

import java.io.*;
import java.net.InetAddress;

/**
 * Handles the messages to receive
 */
public class RequestRunnable implements Runnable {

    private boolean resume = false;
    private BufferedReader in;
    private InetAddress address;
    private int port;
    private String message = null;

    public RequestRunnable(BufferedReader in, InetAddress address, int port){
        this.in = in;
        this.address = address;
        this.port = port;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        while(!this.resume){
            try {
                this.message = this.in.readLine();
                System.out.println("Received (me: " + this.address + ":" + this.port + ") : " + this.message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void stop() { this.resume = true; }
}
