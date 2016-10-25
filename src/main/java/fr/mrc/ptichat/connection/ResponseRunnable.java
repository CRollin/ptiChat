package main.java.fr.mrc.ptichat.connection;

import java.io.PrintWriter;
import java.net.InetAddress;

/**
 * Handles the messages to send
 */
public class ResponseRunnable implements Runnable {

    private boolean resume = false;
    private InetAddress address;
    private int port;
    private PrintWriter out;
    private String message = null;
    private Input input = new Input();

    public ResponseRunnable(PrintWriter out, InetAddress address, int port){
        this.address = address;
        this.out = out;
        this.port = port;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        while(!this.resume) {
            System.out.println("Type a message to " + this.address + ":" + this.port +  " : ");
            this.message = this.input.getInput();
            this.out.println(this.message);
            this.out.flush();
        }
        this.out.close();
    }

    public void stop(){
        this.resume = true;
    }
}
